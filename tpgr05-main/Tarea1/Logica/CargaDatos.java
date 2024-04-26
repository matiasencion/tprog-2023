package Logica;

import java.time.LocalDate;
import excepciones.TOfertaRepetidaException;
import excepciones.UsuarioRepetidoException;
import excepciones.*;
import java.util.Set;
import java.time.format.DateTimeFormatter;


public class CargaDatos {
    private IContUsuario ControladorUsuario;
    private IContOferta ControladorOferta;

    public CargaDatos(IContUsuario ControladorUsuario,IContOferta ControladorOferta)
    {
        this.ControladorUsuario = ControladorUsuario;
        this.ControladorOferta = ControladorOferta;
    }

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void cargarUsuarios(){

        try {
        //   ---------
            ControladorUsuario.CrearPostulante("lgarcia", "Lucía", "García", "lgarcia85@gmail.com", "15/03/1985", "Uruguaya");
            ControladorUsuario.CrearPostulante("matilo", "Matías", "López", "matias.lopez90@hotmail.com", "21/08/1990", "Argentina");
            ControladorUsuario.CrearPostulante("maro", "María", "Rodríguez", "marrod@gmail.com", "10/11/1988", "Uruguaya");
            ControladorUsuario.CrearPostulante("javierf", "Javier", "Fernández", "javierf93@yahoo.com", "05/06/1993", "Mexicana");
            ControladorUsuario.CrearPostulante("valen25", "Valentina", "Martínez", "vale87@gmail.com", "25/02/1987", "Uruguaya");
            ControladorUsuario.CrearPostulante("andpe12", "Andrés", "Pérez", "anpe92@hotmail.com", "12/04/1992", "Chilena");
            ControladorUsuario.CrearPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com", "30/09/1989", "Uruguaya");
            ControladorUsuario.CrearPostulante("sebgon", "Sebastián", "González", "gonza95@yahoo.com", "18/01/1995", "Colombiana");
            ControladorUsuario.CrearPostulante("isabel", "Isabella", "López", "loisa@gmail.com", "07/07/1991", "Uruguaya");
            ControladorUsuario.CrearPostulante("marram02", "Martín", "Ramírez", "marram@hotmail.com", "02/12/1986", "Argentina");
        //--------------
         
            ControladorUsuario.altaEmpresa("EcoTech".toLowerCase(), "Sophia", "Johnson", "info@EcoTech.com".toLowerCase(),"EcoTech Innovations","EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles...","http://www.EcoTechInnovations.com");
            ControladorUsuario.altaEmpresa("FusionTech".toLowerCase(), "William", "Smith", "contacto@FusionTech.net".toLowerCase(),"FusionTech Dynamics","FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial...","http://www.FusionTechDynamics.net");
            ControladorUsuario.altaEmpresa("GlobalHealth".toLowerCase(), "Isabella", "Brown", "jobs@GlobalHealth.uy".toLowerCase(),"GlobalHealth Dynamics","GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica...","http://www.GlobalHealthDynamics.uy/info");
            ControladorUsuario.altaEmpresa("ANTEL".toLowerCase(), "Washington", "Rocha", "jarrington@ANTEL.com.uy".toLowerCase(),"Antel","En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos","ANTEL.com.uy");
            ControladorUsuario.altaEmpresa("MIEM".toLowerCase(), "Pablo", "Bengoechea", "eldiez@MIEM.org.uy".toLowerCase(),"Ministerio de Industria, Energía y Minería","La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.","MIEM.com.uy");
            ControladorUsuario.altaEmpresa("TechSolutions".toLowerCase(), "Mercedes", "Venn", "Mercedes@TechSolutions.com.uy".toLowerCase(),"TechSolutions Inc.","\"TechSolutions Inc.\" es una empresa líder en el sector de tecnología de la información y el software...","TechSolutions.com");


            }
                catch (UsuarioRepetidoException e) {
        System.out.println(e.getMessage());
    }


    }

    public void cargarTipoPublicacion(){
 try{
   
        
        ControladorOferta.agregarTOferta("Premium".toLowerCase(), "Obtén máxima visibilidad", 1, 30, 4000,LocalDate.parse("10/08/2023", dateFormatter));
        ControladorOferta.agregarTOferta("Destacada".toLowerCase(), "Destaca tu anuncio", 2, 15, 500,LocalDate.parse("05/08/2023", dateFormatter));
        ControladorOferta.agregarTOferta("Estandar".toLowerCase(), "Mejora la posición de tu anuncio", 3, 20, 150,LocalDate.parse("15/08/2023", dateFormatter));
        ControladorOferta.agregarTOferta("Basica".toLowerCase(), "Publica de forma sencilla en la lista de ofertas", 4, 7, 50,LocalDate.parse("07/08/2023", dateFormatter));



} catch (TOfertaRepetidaException e) {
        System.out.println(e.getMessage());
    }
    }


    public void cargarKeywords(){
        try{
             ControladorOferta.AltaDeKeyword("Tiempo completo".toLowerCase());
        ControladorOferta.AltaDeKeyword("Medio tiempo".toLowerCase());
        ControladorOferta.AltaDeKeyword("Remoto".toLowerCase());
        ControladorOferta.AltaDeKeyword("Freelance".toLowerCase());
        ControladorOferta.AltaDeKeyword("Temporal".toLowerCase());
        ControladorOferta.AltaDeKeyword("Permanente".toLowerCase());
        ControladorOferta.AltaDeKeyword("Computación".toLowerCase());
        ControladorOferta.AltaDeKeyword("Administración".toLowerCase());
        ControladorOferta.AltaDeKeyword("Logística".toLowerCase());
        ControladorOferta.AltaDeKeyword("Contabilidad".toLowerCase());
 
        }
       
        catch (KeywordRepetida e) {
        System.out.println(e.getMessage());
        }


       


    }


    public void cargarOfertas(){

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
     try{
         Set<String> keywords = new java.util.HashSet<String>();
        keywords.add("K1");
        keywords.add("K2");
        keywords.add("K3");
        keywords.add("K4");
        keywords.add("K5");
        keywords.add("K6");
        float precio = 90000;
      
          LocalDate.parse("14/08/23", dateFormatter);
        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Premium".toLowerCase(), "Desarrollador Frontend".toLowerCase(), "Únete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.", "09:00", "18:00", precio, "Montevideo", "Montevideo", LocalDate.parse("14/08/23", dateFormatter), keywords);

        keywords.clear();
        keywords.add("K5");
        precio = 80000;
        ControladorOferta.altaOferta("FusionTech".toLowerCase(), "Destacada".toLowerCase(), "Estratega de Negocios".toLowerCase(), "Únete a nuestro equipo de estrategia de negocios y ayuda a nuestros clientes a alcanzar sus objetivos.", "10:00", "19:00", precio, "Montevideo", "Montevideo", LocalDate.parse("13/08/23", dateFormatter), keywords);

        keywords.clear();
        keywords.add("K2");
        keywords.add("K3");
        keywords.add("K6");
        precio = 65000;
        ControladorOferta.altaOferta("GlobalHealth".toLowerCase(), "Premium".toLowerCase(), "Diseñador UX/UI".toLowerCase(), "Únete a nuestro equipo de desarrollo backend y crea soluciones escalables y de alto rendimiento.", "09:00", "18:00", precio, "Montevideo", "Montevideo", LocalDate.parse("12/08/23", dateFormatter), keywords);
        
        keywords.clear();
        keywords.add("K2");
        precio = 40000;
       
        ControladorOferta.altaOferta("ANTEL".toLowerCase(), "Estandar".toLowerCase(), "Analista de Datos".toLowerCase(), "Únete a nuestro equipo de mantenimiento y ayuda a mantener nuestras instalaciones en óptimas condiciones.", "08:00", "17:00", precio, "Montevideo", "Montevideo", LocalDate.parse("11/08/23", dateFormatter), keywords);
        
        keywords.clear();
        keywords.add("K4");
        precio = 10000;
        ControladorOferta.altaOferta("MIEM".toLowerCase(), "Estandar".toLowerCase(), "Content Manager".toLowerCase(), "Únete a nuestro equipo administrativo y ayuda a mantener nuestras operaciones en orden.", "08:00", "17:00", precio, "Montevideo", "Montevideo", LocalDate.parse("10/08/23", dateFormatter), keywords);

        keywords.clear();
        keywords.add("K1");
        precio = 30000;
        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Estandar".toLowerCase(), "Soporte Tecnico".toLowerCase(), "Únete a nuestro equipo de marketing y ayuda a promover nuestros productos y servicios.", "08:00", "17:00", precio, "Montevideo", "Montevideo", LocalDate.parse("09/08/23", dateFormatter), keywords);

        keywords.clear();

        precio = 80000;
       ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Destacada".toLowerCase(), "A. de Marketing Digital".toLowerCase(), "Únete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.", "08:30", "17:30", precio, "Colonia Suiza", "Colonia", LocalDate.parse("16/08/23", dateFormatter), keywords);
         
       precio = 10000;
        ControladorOferta.altaOferta("FusionTech".toLowerCase(), "Destacada".toLowerCase(), "Contador Senior".toLowerCase(), "Únete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.", "08:30", "17:30", precio, "Colonia Suiza", "Colonia", LocalDate.parse("16/08/23", dateFormatter), keywords);
} 
catch (OfertaRepetidaException e) {
        System.out.println(e.getMessage());

    }
    }

    public void cargarPostulaciones(){

        try{
        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "lgarcia", "Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.", "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.", "16/08/23");
        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "matilo", "Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales. Experiencia en prácticas en medios locales.", "Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades en comunicación y marketing.", "15/08/23");
        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "maro", "Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React.", "Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología.", "14/08/23");
        ControladorOferta.PostulacionOfertaLaboral("Diseñador UX/UI".toLowerCase(), "javierf", "Técnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos eléctricos.", "Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades técnicas y contribuir al mantenimiento eficiente.", "13/08/23");
        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "valen25", "Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra.", "Me gustaría combinar mi pasión por la música con una oportunidad laboral que me permita seguir creciendo como artista.", "12/08/23");
        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "lgarcia", "Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.", "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.", "16/08/23");

        }
        catch (UsuarioNoExisteException e) {
        System.out.println(e.getMessage());
        }


    }


    public void cargarPaquetes(){

        try{
        ControladorOferta.agregarPaquete("Basico".toLowerCase(), "Publica ofertas laborales en nuestra plataforma por un período de 30 días", "30", "20", "16/08/23");
        ControladorOferta.agregarPaquete("Destacado".toLowerCase(), "Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días", "45", "10", "15/08/23");
        ControladorOferta.agregarPaquete("Premium".toLowerCase(), "Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días", "60", "15", "14/08/23");
        ControladorOferta.agregarPaquete("Express".toLowerCase(), "Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.", "15", "5", "13/08/23");
        } catch (TOfertaRepetidaException  e) {
            
            System.out.println(e.getMessage());
    }
        
          //Agregar Tipos al paquete
        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Premium".toLowerCase(), 1);
        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Destacada".toLowerCase(), 1);
        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Estandar".toLowerCase(), 1);
        ControladorOferta.agregarPTP("Destacado".toLowerCase(), "Estandar".toLowerCase(), 2);
        ControladorOferta.agregarPTP("Destacado".toLowerCase(), "Basica".toLowerCase(), 1);
        ControladorOferta.agregarPTP("Premium".toLowerCase(), "Premium".toLowerCase(), 2);
        ControladorOferta.agregarPTP("Premium".toLowerCase(), "Estandar".toLowerCase(), 2);
        ControladorOferta.agregarPTP("Express".toLowerCase(), "Destacada".toLowerCase(), 2);
 


 }



          
}
