package com.trabajouy.model;

import java.time.LocalDate;

import com.trabajouy.exceptions.*;

import java.util.Set;
import java.time.format.DateTimeFormatter;

public class CargaDatos {
        private IContUsuario ControladorUsuario;
        private IContOferta ControladorOferta;

        public CargaDatos(IContUsuario ControladorUsuario, IContOferta ControladorOferta) {
                this.ControladorUsuario = ControladorUsuario;
                this.ControladorOferta = ControladorOferta;
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public void cargarUsuarios() {

                try {
                        // ---------
                        ControladorUsuario.CrearPostulante("lgarcia", "Lucía", "García", "lgarcia85@gmail.com",
                                        "awdrg543",
                                        "lgarcia.webp", "15/03/1985", "Uruguaya");
                        ControladorUsuario.CrearPostulante("matilo", "Matías", "López", "matias.lopez90@hotmail.com",
                                        "edrft543",
                                        "U2.jpg", "21/08/1990", "Argentina");
                        ControladorUsuario.CrearPostulante("maro", "María", "Rodríguez", "marrod@gmail.com", "r5t6y7u8",
                                        "U3.jpg", "10/11/1988", "Uruguaya");
                        ControladorUsuario.CrearPostulante("javierf", "Javier", "Fernández", "javierf93@yahoo.com",
                                        "45idgaf67",
                                        "U4.jpg", "05/06/1993", "Mexicana");
                        ControladorUsuario.CrearPostulante("valen25", "Valentina", "Martínez", "vale87@gmail.com",
                                        "poiuy987",
                                        "U5.jpg", "25/02/1987", "Uruguaya");
                        ControladorUsuario.CrearPostulante("andpe12", "Andrés", "Pérez", "anpe92@hotmail.com",
                                        "xdrgb657",
                                        "U6.jpg", "12/04/1992", "Chilena");
                        ControladorUsuario.CrearPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com",
                                        "mnjkiu89",
                                        "U7.jpg", "30/09/1989", "Uruguaya");
                        ControladorUsuario.CrearPostulante("sebgon", "Sebastián", "González", "gonza95@yahoo.com",
                                        "ytrewq10",
                                        "U8.jpg", "18/01/1995", "Colombiana");
                        ControladorUsuario.CrearPostulante("isabel", "Isabella", "López", "loisa@gmail.com", "sbsplol1",
                                        "U9.jpg", "07/07/1991", "Uruguaya");
                        ControladorUsuario.CrearPostulante("marram02", "Martín", "Ramírez", "marram@hotmail.com",
                                        "okmnji98",
                                        "U10.jpg", "02/12/1986", "Argentina");

                        // --------------
                        ControladorUsuario.altaEmpresa("EcoTech".toLowerCase(), "Sophia", "Johnson",
                                        "info@EcoTech.com".toLowerCase(), "qsxcdw43", "U11.jpg",
                                        "EcoTech Innovations",
                                        "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología y la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.",
                                        "http://www.EcoTechInnovations.com");
                        ControladorUsuario.altaEmpresa("FusionTech".toLowerCase(), "William", "Smith",
                                        "contacto@FusionTech.net".toLowerCase(), "qpwoei586",
                                        "U12.jpg",
                                        "FusionTech Dynamics",
                                        "FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros, científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimización de procesos industriales hasta la creación de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnología mejore y amplíe nuestras capacidades innatas.",
                                        "http://www.FusionTechDynamics.net");
                        ControladorUsuario.altaEmpresa("GlobalHealth".toLowerCase(), "Isabella", "Brown",
                                        "jobs@GlobalHealth.uy".toLowerCase(), "asdfg654",
                                        "U13.jpg",
                                        "GlobalHealth Dynamics",
                                        "GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnósticos más precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema de salud conectado en el que los datos médicos se utilicen de manera ética y segura para mejorar la calidad de vida de las personas. A través de la innovación constante y la colaboración con expertos médicos, estamos dando forma al futuro de la atención médica, donde la tecnología y la compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo.",
                                        "http://www.GlobalHealthDynamics.uy/info");
                        ControladorUsuario.altaEmpresa("ANTEL".toLowerCase(), "Washington", "Rocha",
                                        "jarrington@ANTEL.com.uy".toLowerCase(), "2nru096",
                                        "U14.jpg", "Antel",
                                        "En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos",
                                        "ANTEL.com.uy");
                        ControladorUsuario.altaEmpresa("MIEM".toLowerCase(), "Pablo", "Bengoechea",
                                        "eldiez@MIEM.org.uy".toLowerCase(), "ibii4xo", "U15.jpg",
                                        "Ministerio de Industria, Energía y Minería",
                                        "La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.",
                                        "MIEM.com.uy");
                        ControladorUsuario.altaEmpresa("TechSolutions".toLowerCase(), "Mercedes", "Venn",
                                        "Mercedes@TechSolutions.com.uy".toLowerCase(), "1ngs03p",
                                        "U16.jpg",
                                        "TechSolutions Inc.",
                                        "\"TechSolutions Inc.\" es una empresa líder en el sector de tecnología de la información y el software. Se especializa en el desarrollo de soluciones de software personalizadas para empresas de diversos tamaños y sectores. Su enfoque se centra en la creación de aplicaciones empresariales innovadoras que optimizan procesos, mejoran la eficiencia y brindan una ventaja competitiva a sus clientes.",
                                        "TechSolutions.com");

                } catch (UsuarioRepetidoException e) {
                        System.out.println(e.getMessage());
                }

        }

        public void cargarTipoPublicacion() {
                try {

                        ControladorOferta.agregarTOferta("Premium".toLowerCase(), "Obtén máxima visibilidad", 1, 30,
                                        4000,
                                        LocalDate.parse("10/08/2023", dateFormatter));
                        ControladorOferta.agregarTOferta("Destacada".toLowerCase(), "Destaca tu anuncio", 2, 15, 500,
                                        LocalDate.parse("05/08/2023", dateFormatter));
                        ControladorOferta.agregarTOferta("Estandar".toLowerCase(), "Mejora la posición de tu anuncio",
                                        3, 20, 150,
                                        LocalDate.parse("15/08/2023", dateFormatter));
                        ControladorOferta.agregarTOferta("Basica".toLowerCase(),
                                        "Publica de forma sencilla en la lista de ofertas",
                                        4, 7, 50, LocalDate.parse("07/08/2023", dateFormatter));

                } catch (TOfertaRepetidaException e) {
                        System.out.println(e.getMessage());
                }
        }

        public void cargarKeywords() {
                try {
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

        public void cargarOfertas() {

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
                Set<String> keywords = new java.util.HashSet<String>();    
                keywords.add("tiempo completo");
                keywords.add("medio tiempo");
                keywords.add("remoto");
                keywords.add("freelance");
                keywords.add("temporal");
                keywords.add("permanente");
                float precio = 90000;

                LocalDate.parse("14/08/2023", dateFormatter);
                try {
                        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        "Desarrollador Frontend".toLowerCase(),
                                        "Unete a nuestro equipo de desarrollo frontend y creá experiencias de usuario excepcionales.",
                                        "09:00", "18:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("30/09/2023", dateFormatter),
                                        keywords, "Basico".toLowerCase(), "uno.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                try {
                        ControladorOferta.verificarOferta("Desarrollador Frontend".toLowerCase(), true,
                                        LocalDate.parse("30/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("temporal");
                precio = 80000;
                try {
                        ControladorOferta.altaOferta("GlobalHealth".toLowerCase(), "Estandar".toLowerCase(),
                                        "Estratega de Negocios".toLowerCase(),
                                        "Forma parte de nuestro equipo de estrategia y contribuye al crecimiento de las empresas clientes.",
                                        "08:00", "17:00", precio, "Punta del Este", "Maldonado",
                                        LocalDate.parse("29/09/2023", dateFormatter),
                                        keywords, "",
                                        "dos.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Estratega de Negocios".toLowerCase(), true,
                                        LocalDate.parse("29/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("medio tiempo");
                keywords.add("remoto");
                keywords.add("permanente");
                precio = 65000;
                try {
                        ControladorOferta.altaOferta("FusionTech".toLowerCase(), "Estandar".toLowerCase(),
                                        "Diseñador UX/UI".toLowerCase(),
                                        "Trabaja en colaboración con nuestro talentoso equipo de diseño para crear soluciones impactantes.",
                                        "14:00", "18:00", precio, "Rosario", "Colonia",
                                        LocalDate.parse("29/09/2023", dateFormatter),
                                        keywords, "", "tres.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Diseñador UX/UI".toLowerCase(), true,
                                        LocalDate.parse("29/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("medio tiempo");
                precio = 40000;

                try {
                        ControladorOferta.altaOferta("ANTEL".toLowerCase(), "Premium".toLowerCase(),
                                        "Analista de Datos".toLowerCase(),
                                        "Ayuda a nuestros clientes a tomar decisiones informadas basadas en análisis y visualizaciones de datos.",
                                        "09:00", "13:00", precio, "Maldonado", "Maldonado",
                                        LocalDate.parse("19/09/2023", dateFormatter),
                                        keywords, "", "cuatro.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("freelance");
                precio = 10000;
                try {
                        ControladorOferta.altaOferta("MIEM".toLowerCase(), "Destacada".toLowerCase(),
                                        "Content Manager".toLowerCase(),
                                        "Gestiona y crea contenido persuasivo y relevante para impulsar la presencia en línea de nuestros clientes.",
                                        "18:00", "22:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("02/10/2023", dateFormatter),
                                        keywords, "", "cinco.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("tiempo clompleto");
                precio = 30000;

                try {
                        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Basica".toLowerCase(),
                                        "Soporte Técnico".toLowerCase(),
                                        "Ofrece un excelente servicio de soporte técnico a nuestros clientes, resolviendo problemas y brindando soluciones.",
                                        "09:00", "18:00", precio, "Minas", "Lavalleja",
                                        LocalDate.parse("10/09/2023", dateFormatter),
                                        keywords, "Destacado".toLowerCase(), "seis.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Soporte Técnico".toLowerCase(), true,
                                        LocalDate.parse("10/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("freelance");
                precio = 80000;
                try {
                        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        "A. de Marketing Digital".toLowerCase(),
                                        "Unete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
                                        "10:00", "19:00",
                                        precio, "Flores", "Flores",
                                        LocalDate.parse("21/09/2023", dateFormatter), keywords, "",
                                        "siete.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("A. de Marketing Digital".toLowerCase(), true,
                                        LocalDate.parse("21/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                keywords.clear();
                keywords.add("tiempo completo");
                precio = 100000;
                try {
                        ControladorOferta.altaOferta("GlobalHealth".toLowerCase(), "Destacada".toLowerCase(),
                                        "Contador Senior".toLowerCase(),
                                        "Unete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.",
                                        "08:30", "17:30",
                                        precio, "Colonia Suiza", "Colonia",
                                        LocalDate.parse("02/10/2023", dateFormatter), keywords, "",
                                        "ocho.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Contador Senior".toLowerCase(), false,
                                        LocalDate.parse("02/10/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                precio = 40000;
                keywords.clear();
                keywords.add("temporal");

                try {
                        ControladorOferta.altaOferta("ANTEL".toLowerCase(), "Premium".toLowerCase(),
                                        "Técnico/a Básico Red".toLowerCase(),
                                        "REGIMEN DE CONTRATO EN FUNCION PUBLICA EN UN TODO DE ACUERDO A LA NORMATIVA VIGENTE (LEY 16.127, DE 7 DE AGOSTO DE 1990, ART. 1°, LITERAL A) Y B) CON LA MODIFICACION INTRODUCIDA POR EL ART. 11 DE LA LEY 17.930 DE 19 DE DICIEMBRE DE 2005).",
                                        "09:00", "17:00", precio, "Paysandu ", "Paysandu ",
                                        LocalDate.parse("29/09/2023", dateFormatter),
                                        keywords, "", "nueve.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                try {
                        ControladorOferta.verificarOferta("Técnico/a Básico Red".toLowerCase(), true,
                                        LocalDate.parse("29/09/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                precio = 123000;
                keywords.clear();
                keywords.add("tiempo completo");
                keywords.add("permanente");
                keywords.add("logistica");

                try {
                        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Destacada".toLowerCase(),
                                        "Desarrollador de Software Senior".toLowerCase(),
                                        "Unete a nuestro equipo y lidera proyectos de desarrollo de software sostenible y ecologico. Impulsa la innovacion y contribuye a un futuro mas verde.",
                                        "09:00", "16:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("02/10/2023", dateFormatter), keywords, "basico",
                                        "diez.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                precio = 135000;
                keywords.clear();

                keywords.add("remoto");

                try {
                        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Premium".toLowerCase(),
                                        "Desarrollador de Software Full Stack".toLowerCase(),
                                        "Unete a nuestro equipo para crear soluciones de software personalizadas de extremo a extremo. Colabora en proyectos emocionantes y desafiantes.",
                                        "04:00",
                                        "13:00", precio, "Fray Bentos", "Río Negro",
                                        LocalDate.parse("25/09/2023", dateFormatter),
                                        keywords, "", "once.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();

                keywords.add("remoto");
                keywords.add("permanente");
                precio = 230000;
                try {
                        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Destacada".toLowerCase(),
                                        "Gerente de Proyecto".toLowerCase(),
                                        "Unete a nuestro equipo de gestion de proyectos y lidera la entrega exitosa de soluciones de software personalizadas. Colabora con equipos multidisciplinarios y clientes exigentes.",
                                        "04:00",
                                        "12:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("02/10/2023", dateFormatter),
                                        keywords, "", "doce.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Gerente de Proyecto".toLowerCase(), true,
                                        LocalDate.parse("02/10/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                keywords.clear();
                keywords.add("tiempo completo");
                keywords.add("contabilidad");
                precio = 60000;

                try {
                        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        "Ingeniero de Calidad de Software".toLowerCase(),
                                        "Asegura la calidad de nuestros productos de software sostenibles. Unete a nosotros para garantizar un impacto positivo en el medio ambiente.",
                                        "14:00",
                                        "18:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("01/10/2023", dateFormatter),
                                        keywords, "", "trece.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

        }

        public void cargarPostulaciones() throws OfertaNoExisteException {

                try {
                        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "lgarcia",
                                        "Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.",
                                        "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
                                        "01/10/2023");

                        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "matilo",
                                        "Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales. Experiencia en prácticas en medios locales.",
                                        "Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades en comunicación y marketing.",
                                        "30/09/2023");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "maro",
                                        "Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles. Conocimientos en JavaScript y React.",
                                        "Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnología.",
                                        "02/10/2023");

                        ControladorOferta.PostulacionOfertaLaboral("Diseñador UX/UI".toLowerCase(), "javierf",
                                        "Técnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos eléctricos.",
                                        "Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades técnicas y contribuir al mantenimiento eficiente.",
                                        "30/09/2023");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "valen25",
                                        "Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra.",
                                        "Me gustaría combinar mi pasión por la música con una oportunidad laboral que me permita seguir creciendo como artista.",
                                        "30/09/2023");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "lgarcia",
                                        "Licenciada en Administración, experiencia en gestión de equipos y proyectos. Conocimientos en Microsoft Office.",
                                        "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y contribuir con mis habilidades de liderazgo.",
                                        "02/10/2023");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }

        }

        public void cargarPaquetes() {

                try {
                        ControladorOferta.agregarPaquete("Basico".toLowerCase(),
                                        "Publica ofertas laborales en nuestra plataforma por un período de 30 días",
                                        "30", "20",
                                        "16/08/2023", "paq1.jpg");
                        ControladorOferta.agregarPaquete("Destacado".toLowerCase(),
                                        "Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días",
                                        "45", "10", "15/08/2023", "paq2.jpg");
                        ControladorOferta.agregarPaquete("Premium".toLowerCase(),
                                        "Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días",
                                        "60", "15", "14/08/2023", "paq3.jpg");
                        ControladorOferta.agregarPaquete("Express".toLowerCase(),
                                        "Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.",
                                        "15", "5", "13/08/2023", "paq4.jpg");
                } catch (TOfertaRepetidaException e) {

                        System.out.println(e.getMessage());
                }

                // Agregar Tipos al paquete
                try {
                        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Premium".toLowerCase(), 1);
                        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Destacada".toLowerCase(), 1);
                        ControladorOferta.agregarPTP("Basico".toLowerCase(), "Estandar".toLowerCase(), 1);
                        ControladorOferta.agregarPTP("Destacado".toLowerCase(), "Estandar".toLowerCase(), 2);
                        ControladorOferta.agregarPTP("Destacado".toLowerCase(), "Basica".toLowerCase(), 1);
                        ControladorOferta.agregarPTP("Premium".toLowerCase(), "Premium".toLowerCase(), 2);
                        ControladorOferta.agregarPTP("Premium".toLowerCase(), "Estandar".toLowerCase(), 2);
                        ControladorOferta.agregarPTP("Express".toLowerCase(), "Destacada".toLowerCase(), 2);
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }

                // Comprar Paquetes
                try {
                        ControladorOferta.compraPaquete("EcoTech".toLowerCase(), "Basico".toLowerCase(),
                                        LocalDate.parse("29/09/2023", dateFormatter));
                        ControladorOferta.compraPaquete("TechSolutions".toLowerCase(), "Destacado".toLowerCase(),
                                        LocalDate.parse("08/09/2023", dateFormatter));
                        ControladorOferta.compraPaquete("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        LocalDate.parse("01/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("FusionTech".toLowerCase(), "Destacado".toLowerCase(),
                                        LocalDate.parse("23/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("EcoTech".toLowerCase(), "Express".toLowerCase(),
                                        LocalDate.parse("01/09/2023", dateFormatter));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
        }

}