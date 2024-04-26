<%@page import="servidor.DtUsuarioSeguidores"%>
<%@page import="servidor.DtPostulacionArray"%>
<%@page import="java.util.List"%>
<%@page import="servidor.DtCompraPaqueteArray"%>
<%@page import="java.io.ObjectInputStream.GetField"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@ page import='java.util.Date' %>
<%@ page import='java.util.Vector' %>
<%@ page import='java.util.Map' %>
<%@ page import='servidor.DtUsuario' %>
<%@ page import='servidor.DtEmpresa' %>
<%@ page import='servidor.DtPostulante' %>
<%@ page import='servidor.DtPublicar' %>
<%@ page import='servidor.DtPostulacion' %>
<%@ page import='servidor.DtCompraPaquete' %>




<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <style>
        <%@include file="/media/styles/consultarPerfil.css" %>

        h1 img {
            vertical-align: middle; 
        }

    </style>
</head>
<body>


   <jsp:include page="/WEB-INF/template/header.jsp" />
   	
   <div class="main">
   <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
        <div class="center-panel">
            <div class="general-info">
                <img id="profile-photo-no-header" src="/web/media/images/<%= (String)((DtUsuario) request.getSession().getAttribute("info_perfil")).getFoto() %>"/>
                <div class="name-and-more">
                    <h1><%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getNombre() +" "+ ((DtUsuario) request.getSession().getAttribute("info_perfil")).getApellido() %>
                     <%
                     		DtUsuario user = (DtUsuario) request.getSession().getAttribute("info_perfil");
		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
		        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(((DtUsuario) request.getSession().getAttribute("info_perfil")).getNickname())) {
		        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), user.getNickname())) {
		        		%>
		        				<a href="/web/perfil?unfollow=<%= user.getNickname()%>&usuarioB=<%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getNickname() %>"><img style="width:30px; height:30px;" src="/web/media/images/unfollow.png"/></a>
		        		<%
		        					} else {
		        		%>
		        				<a href="/web/perfil?follow=<%= user.getNickname()%>&usuarioB=<%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getNickname() %>"><img style="width:30px; height:30px;" src="/web/media/images/follow.png"/></a>
		        		<%			}
		        				}
		        			}
		        		%> 
                    </h1>
                    <h2><%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getEmail() %></h2>
                    <h3>Último Ingreso: <span id="last-date"><%= new Date() %></span></h3>
                </div>
            </div>
            <div class="detailed-info">
            <%
            	if (request.getSession().getAttribute("info_perfil") instanceof DtEmpresa) {
            %>
            	<div class="menu-bar">
                    <button id="profile-button">Perfil</button>
                     <button id="seguidos-button" >Seguidos</button>
                    <button id="seguidores-button">Seguidores</button>
                    <button id="offers-button" style="visibility:hidden;">Ofertas Laborales</button>
                    <button id="packages-button" style="visibility:hidden;">Paquetes</button>
                    <a href="modificarUsuario" style="visibility:hidden;" id="edit-button"><button class="menu-button right-aligned">Editar</button></a>
                </div>
            
            <%
            	}else {
            		if (request.getSession().getAttribute("info_perfil") instanceof DtPostulante) {
            %>
                <div class="menu-bar">
                    <button class="menu-button active" id="profile-button">Perfil</button>
                    <button id="seguidos-button" >Seguidos</button>
                    <button id="seguidores-button">Seguidores</button>
                    <button class="menu-button" id="postulations-button" style="visibility:hidden;">Postulaciones</button>
                    <a href="modificarUsuario" style="visibility:hidden;" id="edit-button"><button class="menu-button right-aligned">Editar</button></a>
                </div>
            <%
            		}
            	}
            %>
                <div id="info-container" class="info-container">
                <%
            		if (portPublicador.esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))) {
            	%>
                    <ul>
                        <li><p><b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("consultar_perfil") %></span></p></li>
                        <li><p><b>Nombre: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                        <li><p><b>Apellido: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                        <li><p><b>Email: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                        <li><p><b>Sitio Web: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
                        <li><p><b>Descripción: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
	                </ul>
                <%
            		}else {
            		    
            			if (portPublicador.esPostulante((String) request.getSession().getAttribute("consultar_perfil"))) {
            	%>
	            	<ul>
                        <li><p><b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("consultar_perfil") %></span></p></li>
                        <li><p><b>Nombre: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                        <li><p><b>Apellido: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                        <li><p><b>Email: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                        <li><p><b>Fecha de Nacimiento: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getFechaB() %></p></li>
                        <li><p><b>Nacionalidad: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNacionalidad() %></p></li>
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
	     		if (request.getSession().getAttribute("info_perfil") instanceof DtPostulante) {
    	%>
		    const postulationsButton = document.getElementById("postulations-button");
		    const infoContainer = document.getElementById("info-container");
		    const profileButton = document.getElementById("profile-button");
		    const seguidoresButton = document.getElementById("seguidores-button");
	        const seguidosButton = document.getElementById("seguidos-button");
	        const editButton = document.getElementById("edit-button");
		    editButton.style.visibility = "visible";
		    postulationsButton.style.visibility = "visible";
		    seguidosButton.style.visibility="visible";
		    seguidoresButton.style.visibility="visible";
		    
		    profileButton.addEventListener("click", () => {
		    	editButton.disabled = false;
		    	editButton.style.visibility= "visible";
		    	infoContainer.innerHTML = `<ul>
                    <li><p><b>Nickname: </b><span id="profile-nickname"><%= request.getSession().getAttribute("nickname_sesion") %></span></p></li>
                    <li><p><b>Nombre: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                    <li><p><b>Apellido: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                    <li><p><b>Email: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                    <li><p><b>Fecha de Nacimiento: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getFechaB() %></p></li>
                </ul>`;
		    })
		     seguidoresButton.addEventListener("click", () => {
		     	editButton.style.visibility= "hidden";
		    	editButton.disabled = true;
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publiI = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publiI.getSeguidores()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi%></h2>
	                                                     
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
	                  
	            seguidosButton.addEventListener("click", () => {
	            	editButton.style.visibility= "hidden";
			    	editButton.disabled = true;
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publit = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publit.getSeguidos()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi%></h2>
	                                                     
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
		
		    postulationsButton.addEventListener("click", () => {
		    	editButton.style.visibility= "hidden";
		    	editButton.disabled = true;
		    	infoContainer.innerHTML = `
		        <% 
		        DtPostulacionArray postuArray = (DtPostulacionArray) request.getSession().getAttribute("postulaciones_perfil");     
		    	List<DtPostulacion> info =	postuArray.getItem();
		        for (DtPostulacion postu : info) {
		       
		        		%>
		        	<ul>
                         <li>
                         <a href="info-oferta?oferta=<%= postu.getOferta() %>">
	                       		<div class="list-item">
	                       			 <div class="list-info">
	                       			 <img src="/web/media/images/<%= postu.getUrlfoto() %>" alt="Imagen de la oferta" />
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
        		
        			
        		if (portPublicador.esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))) {
        %>
	        const offersButton = document.getElementById("offers-button");
	        const infoContainer = document.getElementById("info-container");
	        const profileButton = document.getElementById("profile-button");
	        const seguidoresButton = document.getElementById("seguidores-button");
	        const seguidosButton = document.getElementById("seguidos-button");
	        const packagesButton = document.getElementById("packages-button");
	        const editButton = document.getElementById("edit-button");
	        
	        seguidoresButton.style.visibility = "visible";
	        seguidosButton.style.visibility = "visible";
	        editButton.style.visibility = "visible";
	        offersButton.style.visibility = "visible";
	        packagesButton.style.visibility = "visible";
	
	        profileButton.addEventListener("click", () => {
	        	editButton.style.visibility = "visible";
	        	infoContainer.innerHTML = `<ul>
								        		<li><p><b>Nickname: </b><span id="profile-nickname"><%= request.getSession().getAttribute("nickname_sesion") %></span></p></li>
							                    <li><p><b>Nombre: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
							                    <li><p><b>Apellido: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
							                    <li><p><b>Email: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
							                    <li><p><b>Sitio Web: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
							                    <li><p><b>Descripción: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
	                                        </ul>`;
	        })
	
	        seguidoresButton.addEventListener("click", () => {
	            editButton.style.visibility = "hidden";
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publiI = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publiI.getSeguidores()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                    		<h2><%= publi%></h2>
	                                                     
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
	                  
	            seguidosButton.addEventListener("click", () => {
	            editButton.style.visibility = "hidden";
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publit = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publit.getSeguidos()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi%></h2>
	                                                     
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
	            
	            
	            
	             offersButton.addEventListener("click", () => {
	            editButton.style.visibility = "hidden";
	            infoContainer.innerHTML = `
	            <% 
		        	for (DtPublicar publi : (Vector<DtPublicar>)request.getSession().getAttribute("publicaciones_perfil")) {
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="info-oferta?oferta=<%=publi.getOferta() %>&estado=<%= publi.getEstado() %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%= publi.getUrlFoto() %>" alt="Imagen de la oferta" />
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
	            DtCompraPaqueteArray mapaP = (DtCompraPaqueteArray) request.getSession().getAttribute("paquetes_comprados_perfil");
	            List<DtCompraPaquete> info = mapaP.getItem();
		        	for (DtCompraPaquete paquete : info) {
		         
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="info-paquete?nombre=<%= paquete.getPaquete() %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=  paquete.getUrlFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= paquete.getPaquete()%></h2>
	                                                        		<ul>
	                                                            		<li><b>Fecha de Compra: </b><%= paquete.getFecha()%></li>
	                                                            		<li><b>Fecha de Vencimiento: </b><%= paquete.getVencimiento()%></li>
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
	     	} else if (portPublicador.esEmpresa((String) request.getSession().getAttribute("consultar_perfil"))){
	     		 %>
		           const offersButton = document.getElementById("offers-button");
			       const infoContainer = document.getElementById("info-container");
			       const profileButton = document.getElementById("profile-button");
			       const seguidoresButton = document.getElementById("seguidores-button");
			       const seguidosButton = document.getElementById("seguidos-button");
			       
			       offersButton.style.visibility = "visible";
			       profileButton.addEventListener("click", () => {
			        	infoContainer.innerHTML = `<ul>
										        		<li><p><b>Nickname: </b><span id="profile-nickname"><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getNickname() %></span></p></li>
									                    <li><p><b>Nombre: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
									                    <li><p><b>Apellido: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
									                    <li><p><b>Email: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
									                    <li><p><b>Sitio Web: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %></p></li>
									                    <li><p><b>Descripción: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></p></li>
			                                        </ul>`;
			        })
			        
			       offersButton.addEventListener("click", () => {
			            infoContainer.innerHTML = `
			            <% 
				        	for (DtPublicar publi : (Vector<DtPublicar>)request.getSession().getAttribute("publicacionesConfirmadas_perfil")) {
				       
				        		%>
			            							<ul>
			                                            <li>
			                                            <a href="info-oferta?oferta=<%= publi.getOferta()%>&estado=<%= publi.getEstado() %>">
			                                            		<div class="list-item">
			                                                		<div class="list-info">
			                                                		<img src="/web/media/images/<%= publi.getUrlFoto() %>" alt="Imagen de la oferta" />
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
			            
			   seguidoresButton.addEventListener("click", () => {
	           
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publiI = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publiI.getSeguidores()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi%></h2>
	                                                     
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
	                  
	            seguidosButton.addEventListener("click", () => {
	            
	            infoContainer.innerHTML = `
	            <% 
	           DtUsuario publit = (DtUsuario) request.getSession().getAttribute("info_perfil");
	            
	            
		        	for (String publi : publit.getSeguidos()) {
		        		
		            
		        		%>
	            							<ul>
	                                            <li>
	                                            <a href="/web/perfil?usuarioB=<%=publi %>">
	                                            		<div class="list-item">
	                                                		<div class="list-info">
	                                                		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
	                                                    		<div class="list-block">
	                                                        		<h2><%= publi%></h2>
	                                                     
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
	     	} else if (portPublicador.esPostulante((String) request.getSession().getAttribute("consultar_perfil"))){
	     		
        %>
         const seguidoresButton = document.getElementById("seguidores-button");
	     const seguidosButton = document.getElementById("seguidos-button");
	     const infoContainer = document.getElementById("info-container");
	     const profileButton = document.getElementById("profile-button");
        
 	   
	     
	     
	     
	     
	       profileButton.addEventListener("click", () => {
	        	infoContainer.innerHTML = `<ul>
                    <li><p><b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("consultar_perfil") %></span></p></li>
                    <li><p><b>Nombre: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %></p></li>
                    <li><p><b>Apellido: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %></p></li>
                    <li><p><b>Email: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p></li>
                    <li><p><b>Fecha de Nacimiento: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getFechaB() %></p></li>
                    <li><p><b>Nacionalidad: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNacionalidad() %></p></li>
                </ul>`;
	        })
	     
	     seguidoresButton.addEventListener("click", () => {
           
           infoContainer.innerHTML = `
           <% 
          DtUsuario publiI = (DtUsuario) request.getSession().getAttribute("info_perfil");
           
           
           for (String publi : publiI.getSeguidores()) {
	        		
	            
	        		%>
           							<ul>
                                           <li>
                                           <a href="/web/perfil?usuarioB=<%=publi %>">
                                           		<div class="list-item">
                                               		<div class="list-info">
                                               		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
                                                   		<div class="list-block">
                                                   		<h2><%= publi%></h2>
                                                    
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
                 
           seguidosButton.addEventListener("click", () => {
           
           infoContainer.innerHTML = `
           <% 
          DtUsuario publit = (DtUsuario) request.getSession().getAttribute("info_perfil");
           
           
	        	for (String publi : publit.getSeguidos()) {
	        		
	            
	        		%>
           							<ul>
                                           <li>
                                           <a href="/web/perfil?usuarioB=<%=publi %>">
                                           		<div class="list-item">
                                               		<div class="list-info">
                                               		<img src="/web/media/images/<%=portPublicador.getUsuario(publi).getFoto() %>" alt="Imagen de la oferta" />
                                                   		<div class="list-block">
                                                       		<h2><%= publi%></h2>
                                                    
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