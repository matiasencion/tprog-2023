package Testing;

import Logica.*;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Set;
import java.util.Vector;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import excepciones.KeywordRepetida;
import excepciones.OfertaRepetidaException;
import excepciones.TOfertaRepetidaException;

import excepciones.UsuarioRepetidoException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContOfertaTest{

    private IContOferta ContO = Fabrica.getInstancia().getIContOferta();
    private IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
    private ManejadorOferta ManO = ManejadorOferta.getInstancia();
    private ManejadorUsuario ManU = ManejadorUsuario.getInstancia();
    private CargaDatos c = new CargaDatos(ContU, ContO);
    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Order(1)
    @Test
    public  void AltaDeKeyword() throws KeywordRepetida{
    	ContO.AltaDeKeyword("HomeOffice".toLowerCase());
    	ContO.AltaDeKeyword("Jornada Corta".toLowerCase());
    	
    	assertTrue(ContO.listarKeywords().contains("HomeOffice".toLowerCase()));
    	assertTrue(ContO.listarKeywords().contains("Jornada Corta".toLowerCase()));
    	
    	
    	Exception exceptionKeyRepited = assertThrows(KeywordRepetida.class, () -> {
    		ContO.AltaDeKeyword("Jornada Corta".toLowerCase());
    		});
    	assertTrue(exceptionKeyRepited.getMessage().contains("La keyword ya existe"));
    	
    	
    }
    @Order(2)
    @Test
    public  void agregarTOferta() throws TOfertaRepetidaException{
    	ContO.agregarTOferta("tipouno", "Descripcion TOfertauno.", 20, 20, 100, LocalDate.parse("27/08/2023", dateFormatter));
    	ContO.agregarTOferta("tipob", "Descripcion TOferta.", 2, 31, 300, LocalDate.parse("12/08/2023", dateFormatter));
    	assertTrue(ContO.getNombreTOfertas().contains("tipouno"));
    	assertTrue(ContO.getNombreTOfertas().contains("tipob"));
    }
    @Order(3)
    @Test
    public  void altaOferta() throws OfertaRepetidaException, TOfertaRepetidaException, UsuarioRepetidoException {
    	
    	c.cargarUsuarios();
    	c.cargarTipoPublicacion();
    	c.cargarOfertas();
    	c.cargarKeywords();
    	Set<String>keys = new HashSet<String>();
    	keys.add("HomeOffice".toLowerCase());
    	keys.add("Jornada Corta".toLowerCase());
    	keys.add("Contabilidad".toLowerCase());
    	
    
    	ContO.altaOferta("antel", "premium", "PlatitaGratis".toLowerCase(), "Queres trabajar? No?! Yo tampoco!.", "10:00", "10:35", 99995, "HomeOffice", "Canelones", LocalDate.parse("27/08/2023", dateFormatter), keys);
    	assertTrue(ContO.listarOfertas().contains("PlatitaGratis".toLowerCase()));
    	
    	Exception exceptionKeysEmpty = assertThrows(OfertaRepetidaException.class, () -> {
    		ContO.altaOferta("sjobs", "tipoc", "PlatitaGratis2".toLowerCase(), "Queres trabajar? No?! Yo tampoco!.", "10:00", 
    				"10:35", 99995, "HomeOffice", "Canelones", LocalDate.parse("28/08/2023", dateFormatter), null);});
    	assertTrue(exceptionKeysEmpty.getMessage().contains("Falta pasar keywords"));
    	
    	Exception exceptionOferta = assertThrows(OfertaRepetidaException.class, () -> {
    		ContO.altaOferta("sjobs", "tipock", "PlatitaGratis".toLowerCase(), "Queres trabajar? No?! Yo tampoco!.", "10:00", 
    				"10:35", 99995, "HomeOffice", "Canelones", LocalDate.parse("28/08/2023", dateFormatter), null);});
    	assertTrue(exceptionOferta.getMessage().contains("La oferta ya existe"));
    	
    	ManU.clear();
    	ManO.clear();
    }
    

    @Order(4)
    @Test
    public  void modificarOferta(){
    	c.cargarOfertas();
    	ContO.modificarOferta("Desarrollador Frontend".toLowerCase());
    	ContO.modificarOferta("PlatitaGratis".toLowerCase());
    	
    	ManO.clear();
    }
    @Order(5)
    @Test
    public  void getOferta() {
    	c.cargarOfertas();
    	ContO.getOferta("Desarrollador Frontend".toLowerCase());
    	ManO.clear();
    }
    
    @Test
    public void getTOferta() {
    	c.cargarTipoPublicacion();
    	ContO.getTOferta("Premium".toLowerCase());
    	ManO.clear();
    }
    @Order(6)
    @Test
    public  void getInfoOfertaString(){
    	ManU.clear();
    	ManO.clear();
    	c.cargarUsuarios();
    	c.cargarTipoPublicacion();
    	c.cargarKeywords();
    	c.cargarOfertas();
    	c.cargarPostulaciones();
    	c.cargarPaquetes();
    	Map<String, String> res = new HashMap<String, String>();
    	res.put("Desarrollador Frontend".toLowerCase(),"Nombre: "+"Desarrollador Frontend".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales."+"\n"+"Hora Inicio: "+
    			"09:00"+"\n"+"Hora Fin: "+"18:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"90000.0"+"\n");
    	res.put("Estratega de Negocios".toLowerCase(), "Nombre: "+"Estratega de Negocios".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo de estrategia de negocios y ayuda a nuestros clientes a alcanzar sus objetivos."+"\n"+"Hora Inicio: "+
    			"10:00"+"\n"+"Hora Fin: "+"19:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"80000.0"+"\n");
    	res.put("Diseñador UX/UI".toLowerCase(), "Nombre: "+"Diseñador UX/UI".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo de desarrollo backend y crea soluciones escalables y de alto rendimiento."+"\n"+"Hora Inicio: "+
    			"09:00"+"\n"+"Hora Fin: "+"18:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"65000.0"+"\n");
    	res.put("Analista de Datos".toLowerCase(), "Nombre: "+"Analista de Datos".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo de mantenimiento y ayuda a mantener nuestras instalaciones en óptimas condiciones."+"\n"+"Hora Inicio: "+
    			"08:00"+"\n"+"Hora Fin: "+"17:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"40000.0"+"\n");
    	res.put("Content Manager".toLowerCase(), "Nombre: "+"Content Manager".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo administrativo y ayuda a mantener nuestras operaciones en orden."+"\n"+"Hora Inicio: "+
    			"08:00"+"\n"+"Hora Fin: "+"17:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"10000.0"+"\n");
    	res.put("Soporte Tecnico".toLowerCase(), "Nombre: "+"Soporte Tecnico".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo de marketing y ayuda a promover nuestros productos y servicios."+"\n"+"Hora Inicio: "+
    			"08:00"+"\n"+"Hora Fin: "+"17:00"+"\n"+"Departamento: "+
    			"Montevideo"+"\n"+"Ciudad: "+"Montevideo"+"\n"+"Remuneracion: "+"30000.0"+"\n");
    	res.put("A. de Marketing Digital".toLowerCase(), "Nombre: "+"A. de Marketing Digital".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo contable y ayuda en la gestión financiera de la empresa."+"\n"+"Hora Inicio: "+
    			"08:30"+"\n"+"Hora Fin: "+"17:30"+"\n"+"Departamento: "+
    			"Colonia"+"\n"+"Ciudad: "+"Colonia Suiza"+"\n"+"Remuneracion: "+"80000.0"+"\n");
    	res.put("Contador Senior".toLowerCase(), "Nombre: "+"Contador Senior".toLowerCase()+"\n"+"Descripcion: "+"Únete a nuestro equipo contable y ayuda en la gestión financiera de la empresa."+"\n"+"Hora Inicio: "+
    			"08:30"+"\n"+"Hora Fin: "+"17:30"+"\n"+"Departamento: "+
    			"Colonia"+"\n"+"Ciudad: "+"Colonia Suiza"+"\n"+"Remuneracion: "+"10000.0"+"\n");
    	for (Oferta i : ManO.getOfertas()) {
    		assertEquals(res.get(i.getNombre()), ContO.getInfoOfertaString(i.getNombre()));
    		}
    	ManO.clear();
    	ManU.clear();
    }
    @Order(7)
    @Test    
    public  void getInfoPostulantesString(){
    	ManU.clear();
    	ManO.clear();
    	c.cargarUsuarios();
    	c.cargarTipoPublicacion();
    	c.cargarKeywords();
    	c.cargarOfertas();
    	c.cargarPostulaciones();
    	c.cargarPaquetes();
    	Map<String, String> res = new HashMap<String, String>();
    	res.put("lgarcia","Postulante: " + "lgarcia85@gmail.com"+ "\n" + "Fecha: "+"16/08/2023"+"\n"+"CV: "+"Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office."+"\n"+"Descripcion: "+"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo."+"\n");
    	res.put("matilo","Postulante: " + "matias.lopez90@hotmail.com"+ "\n" + "Fecha: "+"15/08/2023"+"\n"+"CV: "+"Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales. Experiencia en prácticas en medios locales."+"\n"+"Descripcion: "+"Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades en comunicación y marketing."+"\n");
    	res.put("maro","Postulante: " + "marrod@gmail.com"+ "\n" + "Fecha: "+"14/08/2023"+"\n"+"CV: "+"Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React."+"\n"+"Descripcion: "+"Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología."+"\n");
    	res.put("javierf","Postulante: " + "javierf93@yahoo.com"+ "\n" + "Fecha: "+"13/08/2023"+"\n"+"CV: "+"Técnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos eléctricos."+"\n"+"Descripcion: "+"Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades técnicas y contribuir al mantenimiento eficiente."+"\n");
    	res.put("valen25","Postulante: " + "vale87@gmail.com"+ "\n" + "Fecha: "+"12/08/2023"+"\n"+"CV: "+"Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra."+"\n"+"Descripcion: "+"Me gustaría combinar mi pasión por la música con una oportunidad laboral que me permita seguir creciendo como artista."+"\n");
    	res.put("lgarcia","Postulante: " + "lgarcia85@gmail.com"+ "\n" + "Fecha: "+"16/08/2023"+"\n"+"CV: "+"Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office."+"\n"+"Descripcion: "+"Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo."+"\n");

    	for (Oferta i : ManO.getOfertas()) {
    		String str = "";
    		for (String j : i.getPostulaciones().keySet()) {
    			str = str + res.get(j);
    		}
    		assertTrue(str.equals(ContO.getInfoPostulantesString(i.getNombre())));
    	}
    	ManO.clear();
    	ManU.clear();
    }
    @Order(8)
    @Test
    public  void listarEmpresas(){
    	ManU.clear();
    	ManO.clear();
    	c.cargarUsuarios();
    	Set<String> res = new HashSet<String>();
    	res.add("EcoTech".toLowerCase());
    	res.add("FusionTech".toLowerCase());
    	res.add("GlobalHealth".toLowerCase());
    	res.add("ANTEL".toLowerCase());
    	res.add("MIEM".toLowerCase());
    	res.add("TechSolutions".toLowerCase());
    	assertTrue(res.containsAll(ContO.listarEmpresas()));
    	ManO.clear();
    	ManU.clear();
    }
    @Order(9)
    @Test
    public  void listarTOfertas(){
    	ManO.clear();
    	c.cargarTipoPublicacion();
    	Set<String> res = new HashSet<String>();
    	res.add("Premium".toLowerCase());
    	res.add("Destacada".toLowerCase());
    	res.add("Estandar".toLowerCase());
    	res.add("Basica".toLowerCase());
    	assertTrue(res.containsAll(ContO.listarTOfertas()));
    	ManO.clear();
    	
    }

    @Order(10)
    @Test
    public void agregarNuevoPaqueteTest() throws TOfertaRepetidaException {
    	ContO.agregarPaquete("Paquete1", "Descripción", "10", "20", "27/08/23");
        assertTrue(ContO.listarPaquetes().contains("Paquete1"));
    }
    @Order(11)
    @Test
    public void agregarPaqueteRepetidoTest() {
        try {
            ContO.agregarPaquete("Paquete10", "Descripción", "10", "20", "27/08/23");
        } catch (TOfertaRepetidaException e) {
            fail("No se esperaba la excepción aquí.");
        }
        assertThrows(TOfertaRepetidaException.class, () -> 
        	ContO.agregarPaquete("Paquete10", "Descripción2", "15", "25", "28/08/23")
        );
    }
    @Order(12)
    @Test
    public void listarPaquetesDespuesDeAgregarTest() throws TOfertaRepetidaException {
    	ContO.agregarPaquete("Paquete2", "Descripción", "10", "20", "27/08/23");
        assertTrue(ContO.listarPaquetes().contains("Paquete2"));
    }
    @Order(13)
    @Test
    public void getInfoPaqueteTest() throws TOfertaRepetidaException {
        ContO.agregarPaquete("Paquete3", "Descripción Paquete 3", "10", "20", "27/08/20");
        String info = ContO.getInfoPaquete("Paquete3");
        System.out.println(info);
        assertEquals("Nombre: " + "Paquete3" + "\n" + "Descripcion: " + "Descripción Paquete 3" + "\n" + "Validez: " + 10 + "\n" + "Descuento: " + 20.0 + "\n" + "Costo: " + 0.0 + "\n" + "Fecha de Alta: " + "27/08/2020" + "\n" + "Tipos de Ofertas: " + "\n",info);
        
    }
    @Order(14)
    @Test
	public  void getNombresPaquetesDisponibles(){
    	Set<String> res = new HashSet<String>();
    	res.add("Premium".toLowerCase());
    	res.add("Destacado".toLowerCase());
    	res.add("Express".toLowerCase());
    	res.add("Basica".toLowerCase());
    	assertTrue(res.containsAll(ContO.getNombresPaquetesDisponibles()));
    }

    @Order(15)
    @Test
    public void getNombresTOPaquetesTest() throws TOfertaRepetidaException {
    	ContO.agregarTOferta("Tipo1", "Tipo1", 0, 0, 0, LocalDate.parse("27/08/2020", dateFormatter));
    	ContO.agregarPTP("Paquete3", "Tipo1", 5);
        Vector<String> tipos = ContO.getNombresTOPaquetes("Paquete3");
        assertTrue(tipos.contains("Tipo1"));
    }

    @Order(16)
    @Test
    public void getInfoTipoTest() {
        String info = ContO.getInfoTipo("Tipo1");
        assertEquals("Nombre: " + "Tipo1" + "\n" +
                "Descripcion: " + "Tipo1" + "\n" +
                "Exposicion: " + 0 + "\n" +
                "Duracion: " + 0 + "\n" +
                "Costo: " + 0.0 + "\n" +
                "Fecha de Alta: " + "27/08/2020" + "\n", info);
    }

}
