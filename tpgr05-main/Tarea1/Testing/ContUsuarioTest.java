
package Testing;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.Vector;

import Logica.*;
import excepciones.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContUsuarioTest {

    private static IContUsuario ContU;

    @BeforeAll
    public static void setContU() {
        ContU = Fabrica.getInstancia().getIContUsuario();
    }

    @Test
    @Order(1)
    public void altaEmpresa() throws UsuarioRepetidoException {
        assertEmpresa("Microsoft", "Bill", "Gates", "b.gates@outlook.com", "Microsoft",
                "Microsoft es una Empresa de Software, creadora de Windows.", "www.microsoft.com");

        assertEmpresa("Amazon", "Jeff", "Bezos", "j.bezos@amazon.com", "Amazon", "Amazon es una Tienda Online.",
                "www.amazon.com");

        assertEmpresa("La Casta", "Javier", "Milei", "jmilei@argentina.com", "La Casta", "Mucho texto.",
                "www.bancocentral.com");

        assertEmpresa("Navidad", "Papa", "Noel", "noel.papa@polonorte.com", "Navidad",
                "Cada 25 de Diciembre trae carbon.", "www.elrenofeliz.com");
    }

    @Test
    @Order(2)
    public void crearPostulante() throws UsuarioRepetidoException {
        assertPostulante("emusk", "Elon", "Musk", "e.musk@outlook.com", "12/01/1930", "Uruguayo");
        assertPostulante("hpotter", "Harry", "Potter", "h.potter@hogwarts.com", "07/10/1990", "Ingles");
        assertPostulante("willyrex", "Guillermo", "Diaz", "willyrex@youtube.com","17/11/1990", "Espanol");
        assertPostulante("vegetta777", "Samuel", "De Luque", "vegetta777@youtube.com", "23/02/1992", "Espanol");
        assertPostulante("ironman", "Tony", "Stark", "iron.man@avengers.com", "17/05/1975", "Gringo");
    }

    @Test
    @Order(3)
    public void getUsuario() throws UsuarioNoExisteException, UsuarioRepetidoException {
        assertEquals("Amazon", ContU.getNicknameUsuario("Amazon"));
        assertEquals("hpotter", ContU.getNicknameUsuario("hpotter"));
    }

    @Test
    @Order(4)
    public void getUsuarios() {
        ContU.getUsuarios().forEach(d -> {
            if (d instanceof DTEmpresa) {
                checkEmpresaDetails((DTEmpresa) d);
            } else {
                checkPostulanteDetails((DTPostulante) d);
            }
        });
    }

    @Test
    @Order(5)
    public void listarUsuarios() {
        Vector<String> expectedUsers = new Vector<>(Set.of("Navidad", "Amazon", "La Casta", "Microsoft", "hpotter", "willyrex", "vegetta777", "emusk"));
        assertTrue(ContU.listarUsuarios().containsAll(expectedUsers));
    }

    @Test
    @Order(6)
    public void getNombresEmpresas() {
        Vector<String> expectedNames = new Vector<>(Set.of("Navidad", "Amazon", "La Casta", "Microsoft"));
        assertTrue(ContU.getNombresEmpresas().containsAll(expectedNames));
    }

    @Test
    @Order(7)
    public void getNombresPostulantes() {
        Vector<String> expectedNames = new Vector<>(Set.of("hpotter", "willyrex", "vegetta777", "emusk"));
        assertTrue(ContU.getNombresPostulantes().containsAll(expectedNames));
    }

    @Test
    @Order(8)
    public void infoUsuario() throws UsuarioRepetidoException {
		assertTrue(ContU.infoUsuario("La Casta").equals(("Nombre: " + "Javier" + "\n" + "Apellido: " + "Milei" + "\n"
				+ "Email: " + "jmilei@argentina.com" + "\n" + "Empresa: " + "La Casta" + "\n" + "Descripcion: "
				+ "Mucho texto." + "\n" + "Link: " + "www.bancocentral.com")));
		assertTrue(ContU.infoUsuario("ironman").equals("Nombre: " + "Tony" + "\n" + "Apellido: " + "Stark" + "\n"
				+ "Email: " + "iron.man@avengers.com" + "\n" + "Fecha de Nacimiento: " + "17/05/1975" + "\n"
				+ "Nacionalidad: " + "Gringo"));
	}


    @Test
    @Order(9)
    public void modificarUsuario() throws UsuarioEmailRepetido {
        ContU.modificarUsuario("hpotter", "Javier", "");
        DTPostulante dt = ContU.getInfoPostulante("hpotter");
        checkUserDetails(dt, "hpotter", "Javier", "Potter", "");

        ContU.modificarUsuario("hpotter", "", "ElSucio");
        dt = ContU.getInfoPostulante("hpotter");
        checkUserDetails(dt, "hpotter", "Javier", "ElSucio", "");

        ContU.modificarUsuario("hpotter", "", "");
        dt = ContU.getInfoPostulante("hpotter");
        checkUserDetails(dt, "hpotter", "Javier", "ElSucio", "");

        ContU.modificarUsuario("hpotter", "Harry", "Potter");
        dt = ContU.getInfoPostulante("hpotter");
        checkUserDetails(dt, "hpotter", "Harry", "Potter", "");
    }
    @Test
    @Order(10)
    public void testUsuarioRepetidoExceptionForAltaEmpresa() throws UsuarioRepetidoException {
        ContU.altaEmpresa("TestCompany", "Test", "User", "test@company.com", "TestCompany", "Test Description", "www.test.com");

        Exception exception = assertThrows(UsuarioRepetidoException.class, () -> {
            ContU.altaEmpresa("TestCompany", "Test", "User", "test@company.com", "TestCompany", "Test Description", "www.test.com");
        });

        assertTrue(exception.getMessage().contains("ya existe"));
    }

    @Test
    @Order(11)
    public void testUsuarioRepetidoExceptionForCrearPostulante() throws UsuarioRepetidoException {
        ContU.CrearPostulante("testPostulante", "Test", "User", "test@postulante.com", "12/01/1990", "TestNationality");

        Exception exception = assertThrows(UsuarioRepetidoException.class, () -> {
            ContU.CrearPostulante("testPostulante", "Test", "User", "test@postulante.com", "12/01/1990", "TestNationality");
        });

        assertTrue(exception.getMessage().contains("ya existe"));
    }

    @Test
    @Order(12)
    public void testUsuarioNoExisteExceptionForGetUsuario() {
        Exception exception = assertThrows(UsuarioNoExisteException.class, () -> {
            ContU.getUsuario("NonExistentUser");
        });

        assertTrue(exception.getMessage().contains("no existe"));
    }
    
    
    private void assertEmpresa(String nickname, String nombre, String apellido, String email, String empresa,
                               String descripcion, String link) throws UsuarioRepetidoException {
        ContU.altaEmpresa(nickname, nombre, apellido, email, empresa, descripcion, link);
        DTEmpresa dtE = ContU.getInfoEmpresa(nickname);
        checkUserDetails(dtE, nickname, nombre, apellido, email);
        assertEquals(empresa, dtE.getEmpresa());
        assertEquals(descripcion, dtE.getDescripcion());
        assertEquals(link, dtE.getLink());
    }

    private void assertPostulante(String nickname, String nombre, String apellido, String email, String fechaNac, String nacionalidad) throws UsuarioRepetidoException {
        ContU.CrearPostulante(nickname, nombre, apellido, email, fechaNac, nacionalidad);  
        Postulante u = null;
        try {
        	u =(Postulante) ContU.getUsuario(nickname);
        }
        catch(Exception error)
        {
        	System.out.println(error);
        }
        DTPostulante dtP = ContU.getInfoPostulante(nickname);
        checkUserDetails(dtP, nickname, nombre, apellido, email);
        assertEquals(u.getFechaNac(), dtP.getFechaNac());
        assertEquals(nacionalidad, dtP.getNacionalidad());
    }

    private void checkUserDetails(DTUsuario dt, String nickname, String nombre, String apellido, String email) {
        assertEquals(nickname, dt.getNickname());
        assertEquals(nombre, dt.getNombre());
        assertEquals(apellido, dt.getApellido());
        if(email != "")
        	assertEquals(email, dt.getEmail());
    }

    private void checkEmpresaDetails(DTEmpresa dtE) {
    	System.out.println(ContU.getNombresEmpresas());
        assertTrue(ContU.getNombresEmpresas().contains(dtE.getEmpresa()));
        assertTrue(dtE.getDescripcion() != null && !dtE.getDescripcion().isEmpty());
        assertTrue(dtE.getLink() != null && !dtE.getLink().isEmpty());
    }

    private void checkPostulanteDetails(DTPostulante dtP) {
        assertTrue(ContU.getNombresPostulantes().contains(dtP.getNickname()));
        assertTrue(dtP.getFechaNac() != null);
        assertTrue(dtP.getNacionalidad() != null && !dtP.getNacionalidad().isEmpty());
    }
    
    @Test
    public void getNombresOfertas() {
    	IContOferta ContO = Fabrica.getInstancia().getIContOferta();
    	CargaDatos c = new CargaDatos(ContU, ContO);
    	ManejadorUsuario ManU = ManejadorUsuario.getInstancia();
    	ManU.clear();
    	ManejadorOferta.getInstancia().clear();
    	c.cargarUsuarios();
    	c.cargarTipoPublicacion();
    	c.cargarKeywords();
    	c.cargarOfertas();
    	c.cargarPostulaciones();
    	Set<String> aux = new HashSet<String>();
    	aux.add("Desarrollador Frontend".toLowerCase());
    	aux.add("Estratega de Negocios".toLowerCase());
    	aux.add("Diseñador UX/UI".toLowerCase());
    	aux.add("Analista de Datos".toLowerCase());
    	aux.add("Content Manager".toLowerCase());
    	aux.add("Soporte Tecnico".toLowerCase());
    	aux.add("A. de Marketing Digital".toLowerCase());
    	aux.add("Contador Senior".toLowerCase());
    	for (Usuario i : ManU.getUsuariosPorEmail()) {
    		assertTrue(aux.containsAll(ContU.getNombresOfertas(i.getNickname()))); 		
    	}
    	
    }
}

