<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtOferta"%>
<%@page import="servidor.DtPostulacion"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oferta Laboral</title>
<jsp:include page="/WEB-INF/template/header.jsp" />
 
    		<style>
            <%@include file="/media/styles/infoOfertaFrontend.css" %>
        	</style>
	   
           
       		
<body>
    <main class="main-content">
                        <% 
                     	servidor.PublicadorService service = new servidor.PublicadorService();
                     	servidor.Publicador portPublicador = service.getPublicadorPort();
                        DtOferta info = (DtOferta) request.getAttribute("info_oferta");
                        portPublicador.incCantVisitas(info.getNombre());
                        %>
    
     <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
     <%
                            List<DtPostulacion> postulantes =  info.getPostulaciones();
                            boolean esPostulante=false;
                            String post ="";
                            String nickSesion = (String) request.getSession().getAttribute("nickname_sesion");
                            for (DtPostulacion postulante : postulantes) {
                            	if(postulante.getPostulante().equals(nickSesion) ){
                            		post = postulante.getPostulante();
                            		esPostulante = true;
                            	}
                            	
                            }%>
        <div class="content-section">
            <div class="main-content">
                <div class="job-card">
                    <div class="info-oferta">
    <div class="image-container">
        <img src="/web/media/images/<%= info.getUrlFoto() %>" alt="Imagen de la oferta" class="oferta-image"/>
        <%if(esPostulante){ %>
            <a href="info-oferta?postulante=<%=post %>" class="btn ver-postulacion-btn">Ver Mi Postulacion</a>
        <%} %>
    </div>
    <div class="nombre-oferta">
    	<h2 ><%=info.getNombre() %>
    					<%

		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), info.getNombre())) {
		        		%>
		        				<a href="/web/infoOferta?quitarfav=<%= info.getNombre()%>&oferta=<%= info.getNombre()%>"><img style="width:35px; height:35px;" src="/web/media/images/estrella-amarillo.png"/></a>
		        		<%
		        				} else {
		        		%>
		        				<a href="/web/infoOferta?agregarfav=<%= info.getNombre()%>&oferta=<%= info.getNombre()%>"><img style="width:35px; height:35px;" src="/web/media/images/estrella-blanco.png"/></a>
		        		<%		}
		        			}
		        		%> 
		        		</h2>
    </div>
    
		
                        <ul>
                         <%
		        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        %>
		        		<li class="dflex">
                          <div class="icon-container">
                          	<img class="icon" src="/web/media/images/estrella-amarillo.png">
                            <h3>Favoritos </h3>
                          </div>
                          <p><%= info.getFavoritos() %></p>
                        </li>
		        <%
		        	} 
		        %>
                          <li class="dflex">
                          <div class="icon-container">
                          <img class="icon" src="/web/media/images/enterprise.png">
                                <h3>Empresa </h3>
                          </div>
                          		
                                <p><%=info.getEmpresa() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            <img class="icon" src="/web/media/images/id-card.png">
                                                                                                                                                         		

                            
                                <h3>Nombre </h3>
                            </div >
                                 
                                <p><%=info.getNombre() %></p>
                            </li>
                            <li class="dflex">
                             <div class="icon-container">
                             	<img class="icon" src="/web/media/images/description.png">
                            
                                <h3>Descripcion </h3>
                             </div>
                                   
                                <p><%=info.getDescripcion() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/clock.png">
                            
                                <h3>Horario </h3>
                            </div>
                                                      		
                                <p><%=info.getHoraInicio()+"-"+info.getHoraFin() %></p>
                            </li>

                            <li class="dflex">
                            <div class="icon-container">
                            <img class="icon" src="/web/media/images/location.png">
                            
                                <h3>Departamento </h3>
                            </div>
                                                      		
                                <p><%=info.getDepartamento() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/cityscape.png">
                            
                                <h3>Ciudad </h3>
                            </div >
                                                      		
                                <p><%=info.getCiudad() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/hand.png">
                            
                                <h3>Remuneracion </h3>
                            </div >
                                                      		
                                <p><%=info.getSueldo()%></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/calendar.png">
                            
                                <h3>Fecha de alta </h3>
                            </div >
                                                      		
                                <p><%=info.getFechaAprobado() %></p>
                            </li>
                            <li class="dflex">
                            	<div class="icon-container">
                            		<img class="icon" src="/web/media/images/keyword.png">
                            
                                <h3>Keywords </h3>
                                <%
                                String keywords = "";
                                for (String keyw : info.getKeywords()) {
                                	keywords = keywords + keyw +", ";
                                }%>
                                
                            	</div>
                                                               
                                <p><%=keywords %></p>
                            </li>
                        </ul>


                    </div>
                   
                </div>
              
                 
            </div>

            <div class="content-extra">
                   <% 
      			       EstadoSesion estado = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
                   String nick =(String) request.getSession().getAttribute("nickname_sesion");
			       if(estado == EstadoSesion.EMPRESA && info.getEmpresa().equals(nick)){ %>
                <% if(info.getNombrePaquete()!=null){ %>
                <div class="applicants-section">                      
                    <h4>Paquete</h4> 
                    <a href="info-paquete?nombre=<%=info.getNombrePaquete()%>">
                        <div class="applicant">
                            <img src="/web/media/images/<%=info.getUrlFotoPaquete()%>" alt="paquete">
                            <p><%=info.getNombrePaquete() %></p>
                        </div>
                    </a>  
                </div> 
                <%} %>
                <% 
			      
                if(postulantes != null && !postulantes.isEmpty()){
			   
			     %>
		         <div class="applicants-section">
					   <h4>Postulantes</h4>
					      <% 
					    	  for (DtPostulacion postulante : postulantes) { %>


						<a href="info-oferta?postulante=<%=postulante.getPostulante() %>">
							<div class="applicant">
								<img
									src="/web/media/images/<%=postulante.getFotoPostulante()%>"
									alt="<%=postulante.getPostulante()%>">
								<p><%=postulante.getPostulante()%></p>
							</div>
						</a>

						<%
						}%>
					      </div>
						<%}
						
						%>       

			    
				<% String estadoOferta = info.getEstado().value();

				boolean vencido = portPublicador.checkVencimiento(info.getNombre());
				if(estadoOferta != null &&  estadoOferta.equals("Confirmado") && !vencido) { %> 
				<a style="color: black;" href="infoOferta?oferta=<%= info.getNombre()%>&oferta-finalizar=<%= info.getNombre()%>">
			    <div class="finalizar-oferta applicants-section"> 
					
                        <h4>Finalizar Oferta</h4>
					
				</div>
				</a>
				<%
				}
				}%>
				
            </div>
        </div>
    </main>
</body>

</html>
