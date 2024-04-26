<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="com.trabajouy.model.DTOferta"%>
<%@page import="com.trabajouy.model.DTPostulacion"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Oferta Laboral</title>

 
    		<style>
            <%@include file="/media/styles/infoOfertaFrontend.css" %>
        	</style>
	   <jsp:include page="/WEB-INF/template/header.jsp" />
           
       		
<body>
    <main class="main-content">
     <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
        <div class="content-section">
            <div class="main-content">
                <div class="job-card">
                    <div class="info-oferta">
                    <% DTOferta info = (DTOferta) request.getAttribute("info_oferta"); %>
                       <img src="/trabajouy/media/images/<%= info.getFoto() %>" alt="Imagen de la oferta" />
                        <h2><%=info.getNombre() %></h2>

                        <ul>
                          <li class="dflex">
                                <h3>Empresa: </h3>
                                <p><%=info.getEmpresa() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Nombre: </h3>
                                <p><%=info.getNombre() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Descripcion: </h3>
                                <p><%=info.getDescripcion() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Horario </h3>
                                <p><%=info.getHoraInicio()+"-"+info.getHoraFin() %></p>
                            </li>

                            <li class="dflex">
                                <h3>Departamento: </h3>
                                <p><%=info.getDepartamento() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Ciudad: </h3>
                                <p><%=info.getCiudad() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Remuneracion: </h3>
                                <p><%=info.getSueldo()%></p>
                            </li>
                            <li class="dflex">
                                <h3>Fecha de alta: </h3>
                                <p><%=info.getFechaAprovado() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Keywords: </h3>
                                <p><%=info.getKeywordsString() %></p>
                             
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
                            <img src="/trabajouy/media/images/<%=info.getUrlPaquete()%>" alt="paquete">
                              <p><%=info.getNombrePaquete() %></p>
                        </div>
                    </a>
                    
                </div> 
                <%} %>
                <% Set<DTPostulacion> postulantes = info.getDTPostulaciones(); 
			      
                if(postulantes != null && !postulantes.isEmpty()){
			   
			        %>
		         <div class="applicants-section">
			       
					   <h4>Postulantes</h4>
					    <ul>
					      <% 
					    	  for (DTPostulacion postulante : postulantes) { %>


						<a href="info-oferta?postulante=<%=postulante.getPostulante() %>">
							<div class="applicant">
								<img
									src="/trabajouy/media/images/<%=postulante.getPostulanteFoto()%>"
									alt="<%=postulante.getPostulante()%>">
								<p><%=postulante.getPostulante()%></p>
							</div>
						</a>

						<%
						}
						}
						}
						%>
                            

                            </ul>   
                            <%
                            Set<DTPostulacion> postulantes = info.getDTPostulaciones();
                            boolean esPostulante=false;
                            String post ="";
                            String nickSesion = (String) request.getSession().getAttribute("nickname_sesion");
                            for (DTPostulacion postulante : postulantes) {
                            	if(postulante.getPostulante().equals(nickSesion) ){
                            		post = postulante.getPostulante();
                            		esPostulante = true;
                            	}
                            	
                            }
                            if(esPostulante){ %>
                 <a href="info-oferta?postulante=<%=post %>""><button class="login-button">Ver Mi Postulacion</button></a>
                 <%} %>
</div>

            </div>
        </div>
    </main>
</body>

</html>
