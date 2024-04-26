<%@page import="java.io.ObjectInputStream.GetField"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="com.trabajouy.model.DTUsuario"%>
<%@page import="com.trabajouy.model.DTEmpresa"%>
<%@page import="com.trabajouy.model.DTPostulante"%>
<%@page import="com.trabajouy.model.Fabrica"%>
<%@page import="com.trabajouy.model.IContUsuario"%>
<%@page import="com.trabajouy.model.DTPostulacion"%>
<%@page import="com.trabajouy.model.DTPublicar"%>
<%@page import="com.trabajouy.model.DTCompraPaquete"%>
<%@page import="com.trabajouy.model.DTPaquete"%>
<%@ page import='java.util.Date' %>
<%@ page import='java.util.Vector' %>
<%@ page import='java.util.Map' %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <style>
        <%@include file="/media/styles/consultarPerfil.css" %>
    </style>
</head>
<body>
   <jsp:include page="/WEB-INF/template/header.jsp" />
   <div class="main">
   <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
        <div class="center-panel">
            <div class="general-info">
                <img id="profile-photo" src="/trabajouy/media/images/<%= (String)((DTUsuario) request.getSession().getAttribute("info_perfil")).getFoto() %>"/>
                <div class="name-and-more">
                    <h1><%= ((DTUsuario) request.getSession().getAttribute("info_perfil")).getNombre() +" "+ ((DTUsuario) request.getSession().getAttribute("info_perfil")).getApellido() %></h1>
                    <h2><%= ((DTUsuario) request.getSession().getAttribute("info_perfil")).getEmail() %></h2>
                    <h3>Último Ingreso: <span id="last-date"><%= new Date() %></span></h3>
                </div>
            </div>
            <div class="detailed-info">
            <%
            	if (request.getSession().getAttribute("info_perfil") instanceof DTEmpresa) {
            %>
            	<div class="menu-bar">
                    <button id="profile-button">Perfil</button>
                    <button id="offers-button" style="visibility:hidden;">Ofertas Laborales</button>
                    <button id="packages-button" style="visibility:hidden;">Paquetes</button>
                    <a href="modificarUsuario" style="visibility:hidden;" id="edit-button"><button class="menu-button right-aligned">Editar</button></a>
                </div>
            
            <%
            	}else {
            		if (request.getSession().getAttribute("info_perfil") instanceof DTPostulante) {
            %>
                <div class="menu-bar">
                    <button class="menu-button active" id="profile-button">Perfil</button>
                    <button class="menu-button" id="postulations-button" style="visibility:hidden;">Postulaciones</button>
                    <a href="modificarUsuario" style="visibility:hidden;" id="edit-button"><button class="menu-button right-aligned">Editar</button></a>
                </div>
            <%
            		}
            	}
            %>
                <div id="info-container" class="info-container">
                <%
            		if (Fabrica.getInstancia().getIContUsuario().esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))) {
            	%>
                    <ul>
                        <li><p><b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("consultar_perfil") %></span></p></li>
                        <li><p><b>Nombre: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                        <li><p><b>Apellido: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                        <li><p><b>Email: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                        <li><p><b>Sitio Web: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
                        <li><p><b>Descripción: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
	                </ul>
                <%
            		}else {
            			if (Fabrica.getInstancia().getIContUsuario().esPostulante((String) request.getSession().getAttribute("consultar_perfil"))) {
            	%>
	            	<ul>
                        <li><p><b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("consultar_perfil") %></span></p></li>
                        <li><p><b>Nombre: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                        <li><p><b>Apellido: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                        <li><p><b>Email: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                        <li><p><b>Fecha de Nacimiento: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getFechaNac() %></p></li>
                        <li><p><b>Nacionalidad: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getNacionalidad() %></p></li>
	                </ul>
	            <%
            			}
            		}
            	%>
                </div>
            </div>
        </div>
    </div>
	<script>
	    <%
               String consultarPerfil = (String) request.getSession().getAttribute("consultar_perfil");
	           String nickSesion = (String) request.getSession().getAttribute("nickname_sesion");
	    
	       
	     	if (nickSesion !=null && nickSesion.equals(consultarPerfil)) {
	     		if (request.getSession().getAttribute("info_perfil") instanceof DTPostulante) {
    	%>
		    const postulationsButton = document.getElementById("postulations-button");
		    const infoContainer = document.getElementById("info-container");
		    const profileButton = document.getElementById("profile-button");
		    const editButton = document.getElementById("edit-button");
		    
		    editButton.style.visibility = "visible";
		    postulationsButton.style.visibility = "visible";
		    
		    profileButton.addEventListener("click", () => {
		    	editButton.disabled = false;
		    	editButton.style.visibility= "visible";
		    	infoContainer.innerHTML = `<ul>
                    <li><p><b>Nickname: </b><span id="profile-nickname"><%= request.getSession().getAttribute("nickname_sesion") %></span></p></li>
                    <li><p><b>Nombre: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                    <li><p><b>Apellido: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                    <li><p><b>Email: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                    <li><p><b>Fecha de Nacimiento: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getFechaNac() %></p></li>
                    <li><p><b>Nacionalidad: </b><%= ((DTPostulante) request.getSession().getAttribute("info_perfil")).getNacionalidad() %></p></li>
                </ul>`;
		    })
		
		    postulationsButton.addEventListener("click", () => {
		    	editButton.style.visibility= "hidden";
		    	editButton.disabled = true;
		    	infoContainer.innerHTML = `
		        <% 
		        	for (DTPostulacion postu : (Vector<DTPostulacion>)request.getSession().getAttribute("postulaciones_perfil")) {
		        %>
		        	<ul>
                         <li>
                         <a href="info-oferta?oferta=<%= postu.getOferta() %>">
	                       		<div class="list-item">
	                       			 <div class="list-info">
	                       			 <img src="/trabajouy/media/images/<%= postu.getFoto() %>" alt="Imagen de la oferta" />
	                           			<div class="list-block">
	                               			<h2><%= postu.getOferta()%></h2>
	                               			<ul>
	                                   			<li><b>Fecha de Postulación: </b><%=postu.getFechaString()%></li>
	                               			</ul>
	                           			</div>
	                       			</div>
	                   			</div>
                   			</a>
                     	</li>
                     </ul>
                <%
     				}
                %>
		    `;}) 
	    <%
        	} else {
        		if (Fabrica.getInstancia().getIContUsuario().esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))) {
        %>
	        const offersButton = document.getElementById("offers-button");
	        const infoContainer = document.getElementById("info-container");
	        const profileButton = document.getElementById("profile-button");
	        const packagesButton = document.getElementById("packages-button");
	        const editButton = document.getElementById("edit-button");
	
	        editButton.style.visibility = "visible";
	        offersButton.style.visibility = "visible";
	        packagesButton.style.visibility = "visible";
	
	        profileButton.addEventListener("click", () => {
	        	editButton.style.visibility = "visible";
	        	infoContainer.innerHTML = `<ul>
								        		<li><p><b>Nickname: </b><span id="profile-nickname"><%= request.getSession().getAttribute("nickname_sesion") %></span></p></li>
							                    <li><p><b>Nombre: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
							                    <li><p><b>Apellido: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
							                    <li><p><b>Email: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
							                    <li><p><b>Sitio Web: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
							                    <li><p><b>Descripción: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
	                                        </ul>`;
	        })
	
	        offersButton.addEventListener("click", () => {
	            editButton.style.visibility = "hidden";
	            infoContainer.innerHTML = `
	            <% 
		        	for (DTPublicar publi : (Vector<DTPublicar>)request.getSession().getAttribute("publicaciones_perfil")) {
		        %>
	            							<ul>
	                                            <li>
	                                            <a href="info-oferta?oferta=<%= publi.getOferta() %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/trabajouy/media/images/<%= publi.getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi.getOferta()%></h2>
	                                                        		<ul>
	                                                            		<li><b>Estado: </b><%= publi.getEstado()%></li>
	                                                        		</ul>
	                                                    		</div>
	                                                		</div>
	                                            		</div>
	                                            	</a>
	                                        	</li>
	                                        </ul>
	            <%
     				}
                %>                              
	            `;})
	
	        packagesButton.addEventListener("click", () => {
	            editButton.style.visibility = "hidden";
	            infoContainer.innerHTML = `
	            <% 
	            	Map<String, DTCompraPaquete> mapaP = (Map<String, DTCompraPaquete>)request.getSession().getAttribute("paquetes_comprados_perfil");
		        	for (String paquete : mapaP.keySet()) {
		        %>
	            							<ul>
	                                            <li>
	                                            <a href="info-paquete?nombre=<%= mapaP.get(paquete).getPaquete() %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/trabajouy/media/images/<%= mapaP.get(paquete).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= mapaP.get(paquete).getPaquete()%></h2>
	                                                        		<ul>
	                                                            		<li><b>Fecha de Compra: </b><%= mapaP.get(paquete).getFecha()%></li>
	                                                            		<li><b>Fecha de Vencimiento: </b><%= mapaP.get(paquete).getVencimiento()%></li>
	                                                        		</ul>
	                                                    		</div>
	                                                		</div>
	                                            		</div>
	                                            	</a>
	                                        	</li>
	                                        </ul>
	          <%
		        	}
	          %>                              
	          `;})
        <%
        			}
        		}
	     	} else if (Fabrica.getInstancia().getIContUsuario().esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))){
	     		 %>
		           const offersButton = document.getElementById("offers-button");
			       const infoContainer = document.getElementById("info-container");
			       const profileButton = document.getElementById("profile-button");
			       offersButton.style.visibility = "visible";
			       profileButton.addEventListener("click", () => {
			        	infoContainer.innerHTML = `<ul>
										        		<li><p><b>Nickname: </b><span id="profile-nickname"><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getNickname() %></span></p></li>
									                    <li><p><b>Nombre: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
									                    <li><p><b>Apellido: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
									                    <li><p><b>Email: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
									                    <li><p><b>Sitio Web: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
									                    <li><p><b>Descripción: </b><%= ((DTEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
			                                        </ul>`;
			        })
			       offersButton.addEventListener("click", () => {
			            infoContainer.innerHTML = `
			            <% 
				        	for (DTPublicar publi : (Vector<DTPublicar>)request.getSession().getAttribute("publicacionesConfirmadas_perfil")) {
				        %>
			            							<ul>
			                                            <li>
			                                            <a href="info-oferta?oferta=<%= publi.getOferta() %>">
			                                            		<div class="list-item">
			                                                		<div class="list-info">
			                                                		<img src="/trabajouy/media/images/<%= publi.getFoto() %>" alt="Imagen de la oferta" />
			                                                    		<div class="list-block">
			                                                        		<h2><%= publi.getOferta()%></h2>
			                                                        		<ul>
			                                                            		<li><b>Estado: </b><%= publi.getEstado()%></li>
			                                                        		</ul>
			                                                    		</div>
			                                                		</div>
			                                            		</div>
			                                            	</a>
			                                        	</li>
			                                        </ul>
			            <%
		     				}
		                %>                              
			            `;})
			       
			       <% 
	     	}
        %>
    </script>
</body>
</html>
