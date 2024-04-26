<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtUsuario"%>
<%@page import="servidor.DtOferta"%>
<%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Postularse</title>
<style>
<%@
include
 
file="/media/styles/infoOfertaFrontend.css"
 
%>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<main id="frontend-visitante">
		<jsp:include page="/WEB-INF/template/leftPanel.jsp" />
		<div class="content-section">
			<div class="main-content">
				<div class="job-card">
					<div class="info-oferta">
						<img class="oferta-image"
							src="/web/media/images/<%= (String)((DtOferta) request.getSession().getAttribute("info_oferta")).getUrlFoto() %>"
							alt="foto trabajo">
						<h2><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></h2>
						<ul>
						  <%
		        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        %>
		        		<li class="dflex">
                          <div class="icon-container">
                          	<img class="icon" src="/web/media/images/estrella-amarillo.png">
                            <h3>Favoritos </h3>
                          </div>
                          <p><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getFavoritos() %></p>
                        </li>
		        <%
		        	} 
		        %>
							<li class="dflex">
								<h3>Nombre:</h3>
								<p><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></p>
							</li>
							<li class="dflex">
								<h3>Descripcion:</h3>
								<p><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getDescripcion() %></p>
							</li>
							<li class="dflex">
								<h3>Horario</h3>
								<p><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getHoraInicio() +" - "+ ((DtOferta)request.getSession().getAttribute("info_oferta")).getHoraFin()%></p>
							</li>

							<li class="dflex">
								<h3>Departamento:</h3>
								<p><%=((DtOferta)request.getSession().getAttribute("info_oferta")).getDepartamento()%></p>
							</li>
							<li class="dflex">
								<h3>Ciudad:</h3>
								<p><%=((DtOferta)request.getSession().getAttribute("info_oferta")).getCiudad()%></p>
							</li>
							<li class="dflex">
								<h3>Remuneracion:</h3>
								<p><%=((DtOferta)request.getSession().getAttribute("info_oferta")).getSueldo()%></p>
							</li>
							<li class="dflex">
								<h3>Fecha de alta:</h3>
								<p><%=((DtOferta)request.getSession().getAttribute("info_oferta")).getFechaAprobado()%></p>
							</li>
							<li class="dflex">
								<h3>Keywords:</h3> <%
                                	for(String keyword : ((DtOferta)request.getSession().getAttribute("info_oferta")).getKeywords()) {
                                %>
								<p><%= keyword %></p> <%
                                	}
                                %>
							</li>
							<form action="postulacion" method="POST">
								
								<li class="dflex">
									<h3>CV:</h3> <textarea id="input-cv" name="cv" rows="5"
										cols="50" required></textarea>
								</li>
								<li class="dflex">
									<h3>Motivación:</h3> <textarea id="input-motivacion"
										name ="motivacion" rows="5" cols="50" required></textarea>

									
									<li class="dflex">
										
										<h3> Agregar Video:</h3>
									
										<div>	
											<button class="menu-button active" type="button"
												id="add-video-button" onclick="toggleVideo()">Sí</button>	
												</div>
										</li>
								</li>

								<li class="dflex" style="display: none;" id="video-container">
									<h3>Video:</h3> <textarea id="input-video" name="video"
										rows="5" cols="50"></textarea>
								</li>
					

					<div class="menu-bar">
						<input type="submit" class="menu-button active" id="accept-button"
							name="submit" value="Aceptar"> <a
							href="ofertas-postularse" class="menu-button" id="cancel-button">Cancelar</a>
					</div>
					</form>

					<script>
								function toggleVideo() {
									var videoContainer = document
											.getElementById("video-container");
									var addVideoButton = document
											.getElementById("add-video-button");
									if (videoContainer.style.display === "none") {
										videoContainer.style.display = "";
										addVideoButton.innerText = "No";
									} else {
										videoContainer.style.display = "none";
										addVideoButton.innerText = "Sí";
									}
								}
							</script>

					</ul>
				</div>
			</div>
		</div>
		</div>
	</main>
</body>
</html>
