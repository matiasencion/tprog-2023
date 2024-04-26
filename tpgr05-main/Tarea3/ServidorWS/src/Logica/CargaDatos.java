package Logica;

import java.time.LocalDate;


import Excepciones.*;

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

        
        public void cargarTodo() throws OfertaNoExisteException {
	         this.cargarUsuarios();
	 				 this.cargarTipoPublicacion();
	 				 this.cargarKeywords();
	 				 this.cargarPaquetes();
	 				 this.cargarOfertas();
	 				 try {
	 					this.cargarSociales();
	 				} catch (OfertaNoExisteException e1) {
	 					// TODO Auto-generated catch block
	 					e1.printStackTrace();
	 				}
	 				 try {
	 					this.cargarPostulaciones();
	 				} catch (OfertaNoExisteException e1) {
	 					// TODO Auto-generated catch block
	 					e1.printStackTrace();
	 				}
	 				this.cargarOrdenes();
	        }
        
        public void cargarUsuarios() {

                try {
                        // ---------
                        ControladorUsuario.CrearPostulante("lgarcia", "Lucia", "Garcia", "lgarcia85@gmail.com",
                                        "awdrg543",
                                        "lgarcia.webp", "15/03/1985", "Uruguaya");
                        ControladorUsuario.CrearPostulante("matilo", "Matias", "Lopez", "matias.lopez90@hotmail.com",
                                        "edrft543",
                                        "U2.jpg", "21/08/1990", "Argentina");
                        ControladorUsuario.CrearPostulante("maro", "Maria", "Rodriguez", "marrod@gmail.com", "r5t6y7u8",
                                        "U3.jpg", "10/11/1988", "Uruguaya");
                        ControladorUsuario.CrearPostulante("javierf", "Javier", "Fernandez", "javierf93@yahoo.com",
                                        "45idgaf67",
                                        "U4.jpg", "05/06/1993", "Mexicana");
                        ControladorUsuario.CrearPostulante("valen25", "Valentina", "Martinez", "vale87@gmail.com",
                                        "poiuy987",
                                        "U5.jpg", "25/02/1987", "Uruguaya");
                        ControladorUsuario.CrearPostulante("andpe12", "Andres", "Perez", "anpe92@hotmail.com",
                                        "xdrgb657",
                                        "U6.jpg", "12/04/1992", "Chilena");
                        ControladorUsuario.CrearPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com",
                                        "mnjkiu89",
                                        "U7.jpg", "30/09/1989", "Uruguaya");
                        ControladorUsuario.CrearPostulante("sebgon", "Sebastian", "Gonzalez", "gonza95@yahoo.com",
                                        "ytrewq10",
                                        "U8.jpg", "18/01/1995", "Colombiana");
                        ControladorUsuario.CrearPostulante("isabel", "Isabella", "Lopez", "loisa@gmail.com", "sbsplol1",
                                        "U9.jpg", "07/07/1991", "Uruguaya");
                        ControladorUsuario.CrearPostulante("marram02", "Martin", "Ramirez", "marram@hotmail.com",
                                        "okmnji98",
                                        "U10.jpg", "02/12/1986", "Argentina");
                        

                        // --------------
                        ControladorUsuario.altaEmpresa("EcoTech".toLowerCase(), "Sophia", "Johnson",
                                        "info@EcoTech.com".toLowerCase(), "qsxcdw43", "U11.jpg",
                                        "EcoTech Innovations",
                                        "EcoTech Innovations es una empresa lider en soluciones tecnologicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafios ambientales mas apremiantes de nuestro tiempo. Desde sistemas de energia renovable y dispositivos de monitorizacion ambiental hasta soluciones de gestion de residuos inteligentes, nuestra mision es proporcionar herramientas que permitan a las empresas y comunidades adoptar practicas mas ecologicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnologia y la naturaleza, y trabajamos incansablemente para impulsar un futuro mas limpio y sostenible.",
                                        "http://www.EcoTechInnovations.com");
                        ControladorUsuario.altaEmpresa("FusionTech".toLowerCase(), "William", "Smith",
                                        "contacto@FusionTech.net".toLowerCase(), "qpwoei586",
                                        "U12.jpg",
                                        "FusionTech Dynamics",
                                        "FusionTech Dynamics es una empresa pionera en el ambito de la inteligencia artificial y la automatizacion avanzada. Nuestro equipo multidisciplinario de ingenieros, cientificos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimizacion de procesos industriales hasta la creacion de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnologia mejore y amplie nuestras capacidades innatas.",
                                        "http://www.FusionTechDynamics.net");
                        ControladorUsuario.altaEmpresa("GlobalHealth".toLowerCase(), "Isabella", "Brown",
                                        "jobs@GlobalHealth.uy".toLowerCase(), "asdfg654",
                                        "U13.jpg",
                                        "GlobalHealth Dynamics",
                                        "GlobalHealth Dynamics es una empresa comprometida con el avance de la atencion medica a nivel mundial. Como lideres en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnosticos mas precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra vision es crear un ecosistema de salud conectado en el que los datos medicos se utilicen de manera etica y segura para mejorar la calidad de vida de las personas. A traves de la innovacion constante y la colaboracion con expertos medicos, estamos dando forma al futuro de la atencion medica, donde la tecnologia y la compasion se unen para salvar vidas y mejorar el bienestar en todo el mundo.",
                                        "http://www.GlobalHealthDynamics.uy/info");
                        ControladorUsuario.altaEmpresa("ANTEL".toLowerCase(), "Washington", "Rocha",
                                        "jarrington@ANTEL.com.uy".toLowerCase(), "2nru096",
                                        "U14.jpg", "Antel",
                                        "En Antel te brindamos servicios de vanguardia en tecnologia de comunicacion en Telefonia Movil, Fija, Banda Ancha y Datos",
                                        "ANTEL.com.uy");
                        ControladorUsuario.altaEmpresa("MIEM".toLowerCase(), "Pablo", "Bengoechea",
                                        "eldiez@MIEM.org.uy".toLowerCase(), "ibii4xo", "U15.jpg",
                                        "Ministerio de Industria, Energia y Mineria",
                                        "La Direccion Nacional de Energia (DNE) del Ministerio de Industria, Energia y Mineria (MIEM) presenta anualmente el BEN.",
                                        "MIEM.com.uy");
                        ControladorUsuario.altaEmpresa("TechSolutions".toLowerCase(), "Mercedes", "Venn",
                                        "Mercedes@TechSolutions.com.uy".toLowerCase(), "1ngs03p",
                                        "U16.jpg",
                                        "TechSolutions Inc.",
                                        "\"TechSolutions Inc.\" es una empresa lider en el sector de tecnologia de la informacion y el software. Se especializa en el desarrollo de soluciones de software personalizadas para empresas de diversos tamanios y sectores. Su enfoque se centra en la creacion de aplicaciones empresariales innovadoras que optimizan procesos, mejoran la eficiencia y brindan una ventaja competitiva a sus clientes.",
                                        "TechSolutions.com");

                } catch (UsuarioRepetidoException e) {
                        System.out.println(e.getMessage());
                }

        }

        public void cargarTipoPublicacion() {
                try {

                        ControladorOferta.agregarTOferta("Premium".toLowerCase(), "Obten maxima visibilidad", 1, 30,
                                        4000,
                                        LocalDate.parse("10/08/2023", dateFormatter));
                        ControladorOferta.agregarTOferta("Destacada".toLowerCase(), "Destaca tu anuncio", 2, 15, 500,
                                        LocalDate.parse("05/08/2023", dateFormatter));
                        ControladorOferta.agregarTOferta("Estandar".toLowerCase(), "Mejora la posicion de tu anuncio",
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
                        ControladorOferta.AltaDeKeyword("Computacion".toLowerCase());
                        ControladorOferta.AltaDeKeyword("Administracion".toLowerCase());
                        ControladorOferta.AltaDeKeyword("Logistica".toLowerCase());
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
                                        "Unete a nuestro equipo de desarrollo frontend y crea experiencias de usuario excepcionales.",
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
                ControladorOferta.setVisitas("Desarrollador Frontend".toLowerCase(),5);
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
                ControladorOferta.setVisitas("Estratega de Negocios".toLowerCase(),10);
                keywords.clear();
                keywords.add("medio tiempo");
                keywords.add("remoto");
                keywords.add("permanente");
                precio = 65000;
                try {
                        ControladorOferta.altaOferta("FusionTech".toLowerCase(), "Estandar".toLowerCase(),
                                        "Diseniador UX/UI".toLowerCase(),
                                        "Trabaja en colaboracion con nuestro talentoso equipo de disenio para crear soluciones impactantes.",
                                        "14:00", "18:00", precio, "Rosario", "Colonia",
                                        LocalDate.parse("29/10/2023", dateFormatter),
                                        keywords, "", "tres.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Diseniador UX/UI".toLowerCase(), true,
                                        LocalDate.parse("29/10/2023", dateFormatter));
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
                                        "Ayuda a nuestros clientes a tomar decisiones informadas basadas en analisis y visualizaciones de datos.",
                                        "09:00", "13:00", precio, "Maldonado", "Maldonado",
                                        LocalDate.parse("19/10/2023", dateFormatter),
                                        keywords, "", "cuatro.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Analista de Datos".toLowerCase(),15);
                keywords.clear();
                keywords.add("freelance");
                precio = 10000;
                try {
                        ControladorOferta.altaOferta("MIEM".toLowerCase(), "Destacada".toLowerCase(),
                                        "Content Manager".toLowerCase(),
                                        "Gestiona y crea contenido persuasivo y relevante para impulsar la presencia en linea de nuestros clientes.",
                                        "18:00", "22:00", precio, "Montevideo", "Montevideo",
                                        LocalDate.parse("20/10/2023", dateFormatter),
                                        keywords, "", "cinco.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.finalizarOferta("Content Manager".toLowerCase());
                ControladorOferta.setVisitas("Content Manager".toLowerCase(),20);
                keywords.clear();
                keywords.add("tiempo clompleto");
                precio = 30000;

                try {
                        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Basica".toLowerCase(),
                                        "Soporte Tecnico".toLowerCase(),
                                        "Ofrece un excelente servicio de soporte tecnico a nuestros clientes, resolviendo problemas y brindando soluciones.",
                                        "09:00", "18:00", precio, "Minas", "Lavalleja",
                                        LocalDate.parse("02/11/2023", dateFormatter),
                                        keywords, "Destacado".toLowerCase(), "seis.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Soporte Tecnico".toLowerCase(), true,
                                        LocalDate.parse("02/11/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Soporte Tecnico".toLowerCase(),25);
                keywords.clear();
                keywords.add("freelance");
                precio = 80000;
                try {
                        ControladorOferta.altaOferta("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        "A. de Marketing Digital".toLowerCase(),
                                        "Unete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
                                        "10:00", "19:00",
                                        precio, "Flores", "Flores",
                                        LocalDate.parse("02/11/2023", dateFormatter), keywords, "",
                                        "siete.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("A. de Marketing Digital".toLowerCase(), true,
                                        LocalDate.parse("02/11/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("A. de Marketing Digital".toLowerCase(),30);

                keywords.clear();
                keywords.add("tiempo completo");
                precio = 100000;
                try {
                        ControladorOferta.altaOferta("GlobalHealth".toLowerCase(), "Destacada".toLowerCase(),
                                        "Contador Senior".toLowerCase(),
                                        "Unete a nuestro equipo contable y ayuda en la gestion financiera de la empresa.",
                                        "08:30", "17:30",
                                        precio, "Colonia Suiza", "Colonia",
                                        LocalDate.parse("04/11/2023", dateFormatter), keywords, "",
                                        "ocho.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Contador Senior".toLowerCase(), false,
                                        LocalDate.parse("04/11/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Contador Senior".toLowerCase(),35);

                precio = 40000;
                keywords.clear();
                keywords.add("temporal");

                try {
                        ControladorOferta.altaOferta("ANTEL".toLowerCase(), "Premium".toLowerCase(),
                                        "Tecnico/a Basico Red".toLowerCase(),
                                        "REGIMEN DE CONTRATO EN FUNCION PUBLICA EN UN TODO DE ACUERDO A LA NORMATIVA VIGENTE (LEY 16.127, DE 7 DE AGOSTO DE 1990, ART. 1°, LITERAL A) Y B) CON LA MODIFICACION INTRODUCIDA POR EL ART. 11 DE LA LEY 17.930 DE 19 DE DICIEMBRE DE 2005).",
                                        "09:00", "17:00", precio, "Paysandu ", "Paysandu ",
                                        LocalDate.parse("29/10/2023", dateFormatter),
                                        keywords, "", "nueve.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                try {
                        ControladorOferta.verificarOferta("Tecnico/a Basico Red".toLowerCase(), true,
                                        LocalDate.parse("29/10/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Tecnico/a Basico Red".toLowerCase(),40);
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
                                        LocalDate.parse("04/11/2023", dateFormatter), keywords, "basico",
                                        "diez.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Desarrollador de Software Senior".toLowerCase(),45);

                precio = 135000;
                keywords.clear();

                keywords.add("remoto");

                try {
                        ControladorOferta.altaOferta("TechSolutions".toLowerCase(), "Premium".toLowerCase(),
                                        "Desarrollador de Software Full Stack".toLowerCase(),
                                        "Unete a nuestro equipo para crear soluciones de software personalizadas de extremo a extremo. Colabora en proyectos emocionantes y desafiantes.",
                                        "04:00",
                                        "13:00", precio, "Fray Bentos", "Rio Negro",
                                        LocalDate.parse("25/10/2023", dateFormatter),
                                        keywords, "", "once.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Desarrollador de Software Full Stack".toLowerCase(),50);

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
                                        LocalDate.parse("05/11/2023", dateFormatter),
                                        keywords, "", "doce.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }

                try {
                        ControladorOferta.verificarOferta("Gerente de Proyecto".toLowerCase(), true,
                                        LocalDate.parse("05/11/2023", dateFormatter));
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Gerente de Proyecto".toLowerCase(),55);

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
                                        LocalDate.parse("01/11/2023", dateFormatter),
                                        keywords, "", "trece.jpg");
                } catch (OfertaRepetidaException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                ControladorOferta.setVisitas("Ingeniero de Calidad de Software".toLowerCase(),7);


                

        }
       
        	

        public void cargarPostulaciones() throws OfertaNoExisteException {

                try {
                        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "lgarcia",
                                        "Licenciada en Administracion, experiencia en gestion de equipos y proyectos. Conocimientos en Microsoft Office.",
                                        "Estoy emocionada por la oportunidad de formar parte de un equipo dinamico y contribuir con mis habilidades de liderazgo.",
                                        "01/10/2023","https://www.youtube.com/embed/sqh77QZS0G4");


                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "matilo",
                        "Estudiante de Comunicacion, habilidades en redaccion y manejo de redes sociales. Experiencia en practicas en medios locales.",
                        "Me encantaria formar parte de un equipo que me permita desarrollar mis habilidades en comunicacion y marketing.",
                        "30/09/2023","https://www.youtube.com/embed/ekm1D3sKoVA");
                } catch (UsuarioNoExisteException e) {
                    System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Desarrollador Frontend".toLowerCase(), "maro",
                                        "Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones moviles. Conocimientos en JavaScript y React.",
                                        "Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir creciendo como profesional en el campo de la tecnologia.",
                                        "02/10/2023","");

                        ControladorOferta.PostulacionOfertaLaboral("Diseniador UX/UI".toLowerCase(), "javierf",
                                        "Tecnico en Electricidad, experiencia en mantenimiento industrial. Conocimientos en lectura de planos electricos.",
                                        "Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades tecnicas y contribuir al mantenimiento eficiente.",
                                        "30/10/2023","https://www.youtube.com/embed/uNCzhfQCqAs");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "valen25",
                                        "Musico profesional, experiencia en espectaculos en vivo. Habilidades en canto y guitarra.",
                                        "Me gustaria combinar mi pasion por la musica con una oportunidad laboral que me permita seguir creciendo como artista.",
                                        "30/09/2023","https://www.youtube.com/embed/jwiV9gbjEi8");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                try {
                        ControladorOferta.PostulacionOfertaLaboral("Estratega de Negocios".toLowerCase(), "lgarcia",
                                        "Licenciada en Administracion, me considero genia, experiencia en gestion de equipos y proyectos. Conocimientos en Microsoft Office.",
                                        "Estoy emocionada por la oportunidad de formar parte de un equipo dinamico y contribuir con mis habilidades de liderazgo.",
                                        "02/10/2023","");
                } catch (UsuarioNoExisteException e) {
                        System.out.println(e.getMessage());
                }
                
                try {
                  ControladorOferta.PostulacionOfertaLaboral("Content Manager".toLowerCase(), "lgarcia",
                                  "Licenciada en Administracion, me considero la mejor menejadora de contenidos del mundo, tengo experiencia en gestion de equipos y proyectos. Conocimientos en Microsoft Office.",
                                  "Estoy emocionada por la oportunidad de formar parte de un equipo tan bonito y contribuir con mis habilidades de liderazgo.",
                                  "21/10/2023","");
			          } catch (UsuarioNoExisteException e) {
			                  System.out.println(e.getMessage());
			          }
                
                try {
                  ControladorOferta.PostulacionOfertaLaboral("Content Manager".toLowerCase(), "valen25",
                                  "Me manejo las redes, tengo 20M de seguidores.",
                                  "Me gustaria combinar mi pasion por la musica con una oportunidad laboral que me permita seguir creciendo como artista.",
                                  "22/10/2023","https://www.youtube.com/embed/jwiV9gbjEi8");
			          } catch (UsuarioNoExisteException e) {
			                  System.out.println(e.getMessage());
			          }

        }

        public void cargarPaquetes() {

                try {
                        ControladorOferta.agregarPaquete("Basico".toLowerCase(),
                                        "Publica ofertas laborales en nuestra plataforma por un periodo de 30 dias",
                                        "30", "20",
                                        "16/08/2023", "paq1.jpg");
                        ControladorOferta.agregarPaquete("Destacado".toLowerCase(),
                                        "Publica ofertas laborales destacadas que se mostrara en la parte superior de los resultados de busqueda por 45 dias",
                                        "45", "10", "15/08/2023", "paq2.jpg");
                        ControladorOferta.agregarPaquete("Premium".toLowerCase(),
                                        "Publica ofertas laborales premium que incluye promocion en nuestras redes sociales y listado en la seccion destacada por 60 dias",
                                        "60", "15", "14/08/2023", "paq3.jpg");
                        ControladorOferta.agregarPaquete("Express".toLowerCase(),
                                        "Publica ofertas laborales urgentes resaltada en color y se mostrara en la seccion de urgente por 15 dias.",
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
                                        LocalDate.parse("30/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("TechSolutions".toLowerCase(), "Destacado".toLowerCase(),
                                        LocalDate.parse("08/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("EcoTech".toLowerCase(), "Premium".toLowerCase(),
                                        LocalDate.parse("31/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("FusionTech".toLowerCase(), "Destacado".toLowerCase(),
                                        LocalDate.parse("13/10/2023", dateFormatter));
                        ControladorOferta.compraPaquete("EcoTech".toLowerCase(), "Express".toLowerCase(),
                                        LocalDate.parse("01/10/2023", dateFormatter));
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                
        }
        public void cargarSociales() throws OfertaNoExisteException {
        	// U1
        	ControladorUsuario.seguirUsuario("lgarcia".toLowerCase(), "EcoTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("lgarcia".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("lgarcia".toLowerCase(), "GlobalHealth".toLowerCase());
        	ControladorUsuario.seguirUsuario("lgarcia".toLowerCase(), "ANTEL".toLowerCase());
        	ControladorUsuario.seguirUsuario("lgarcia".toLowerCase(), "MIEM".toLowerCase());
        	
        	//U2
        	ControladorUsuario.seguirUsuario("matilo".toLowerCase(), "EcoTech".toLowerCase());
        	
        	//U3
        	ControladorUsuario.seguirUsuario("maro".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("maro".toLowerCase(), "GlobalHealth".toLowerCase());
        	ControladorUsuario.seguirUsuario("maro".toLowerCase(), "MIEM".toLowerCase());
        	ControladorUsuario.seguirUsuario("maro".toLowerCase(), "TechSolutions".toLowerCase());
        	
        	//U4
          	ControladorUsuario.seguirUsuario("javierf".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("javierf".toLowerCase(), "ANTEL".toLowerCase());
        	
        	//U5
        	ControladorUsuario.seguirUsuario("valen25".toLowerCase(), "GlobalHealth".toLowerCase());
        	ControladorUsuario.seguirUsuario("valen25".toLowerCase(), "MIEM".toLowerCase());
        	ControladorUsuario.seguirUsuario("valen25".toLowerCase(), "TechSolutions".toLowerCase());
        	
        	//U6
        	ControladorUsuario.seguirUsuario("andpe12".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("andpe12".toLowerCase(), "ANTEL".toLowerCase());
        	ControladorUsuario.seguirUsuario("andpe12".toLowerCase(), "MIEM".toLowerCase());
        	
        	//U7
        	ControladorUsuario.seguirUsuario("sicam".toLowerCase(), "EcoTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("sicam".toLowerCase(), "ANTEL".toLowerCase());
        	
        	//U8
        	ControladorUsuario.seguirUsuario("sebgon".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("sebgon".toLowerCase(), "GlobalHealth".toLowerCase());
        	
        	//U9
        	ControladorUsuario.seguirUsuario("isabel".toLowerCase(), "lgarcia".toLowerCase());
        	ControladorUsuario.seguirUsuario("isabel".toLowerCase(), "EcoTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("isabel".toLowerCase(), "FusionTech".toLowerCase());
        	ControladorUsuario.seguirUsuario("isabel".toLowerCase(), "MIEM".toLowerCase());
        	
        	//U11
         	ControladorUsuario.seguirUsuario("EcoTech".toLowerCase(), "lgarcia".toLowerCase());
         	ControladorUsuario.seguirUsuario("EcoTech".toLowerCase(), "FusionTech".toLowerCase());
        
         	//U12
         	ControladorUsuario.seguirUsuario("FusionTech".toLowerCase(), "GlobalHealth".toLowerCase());
        	
         	//U13
         	ControladorUsuario.seguirUsuario("GlobalHealth".toLowerCase(), "lgarcia".toLowerCase());
         	ControladorUsuario.seguirUsuario("GlobalHealth".toLowerCase(), "ANTEL".toLowerCase());
         	ControladorUsuario.seguirUsuario("GlobalHealth".toLowerCase(), "MIEM".toLowerCase());
         	ControladorUsuario.seguirUsuario("GlobalHealth".toLowerCase(), "TechSolutions".toLowerCase());
         	
         	//U14
         	ControladorUsuario.seguirUsuario("ANTEL".toLowerCase(), "MIEM".toLowerCase());
         	
         	//U15
         	ControladorUsuario.seguirUsuario("MIEM".toLowerCase(), "ANTEL".toLowerCase());
         	
         	//U16
         	ControladorUsuario.seguirUsuario("TechSolutions".toLowerCase(), "MIEM".toLowerCase());

         	
         	ControladorUsuario.agregarOfertaFavorita("lgarcia".toLowerCase(), "Desarrollador Frontend".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("lgarcia".toLowerCase(), "A. de Marketing Digital".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("lgarcia".toLowerCase(), "Gerente de Proyecto".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("matilo".toLowerCase(), "A. de Marketing Digital".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("maro".toLowerCase(), "Desarrollador Frontend".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("maro".toLowerCase(), "Gerente de Proyecto".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("javierf".toLowerCase(), "A. de Marketing Digital".toLowerCase());
         	ControladorUsuario.agregarOfertaFavorita("valen25".toLowerCase(), "Tecnico/a Basico Red".toLowerCase());
        	ControladorUsuario.agregarOfertaFavorita("valen25".toLowerCase(), "A. de Marketing Digital".toLowerCase());
         	
         	System.out.println("Seguidores y favoritos Cargados");
         	
        }
        
        public void cargarOrdenes() {
        	String[] orden = new String[2];
        	String[] nombres = new String[2];
        	orden[0] = "1";
        	orden[1] = "2";
        	nombres[0] = "maro";
        	nombres[1] = "lgarcia";
        	ControladorOferta.seleccionarPostulacion("Desarrollador Frontend".toLowerCase(), orden, nombres);
        	String[] orden2 = new String[3];
        	String[] nombres2 = new String[3];
        	orden2[0] = "3";
        	orden2[1] = "2";
        	orden2[2] = "1";
        	nombres2[0] = "matilo";
        	nombres2[1] = "valen25";
        	nombres2[2] = "lgarcia";
        	ControladorOferta.seleccionarPostulacion("Estratega de Negocios".toLowerCase(), orden2, nombres2);
        }
}