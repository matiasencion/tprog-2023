<%@page import="java.io.ObjectInputStream.GetField"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtUsuario"%>
<%@page import="servidor.DtEmpresa"%>
<%@page import="servidor.DtPostulante"%>
<%@page import="servidor.PublicadorService"%>
<%@page import="servidor.Publicador"%>
<%@page import="servidor.DtPostulacion"%>
<%@page import="servidor.DtPublicar"%>
<%@page import="servidor.DtCompraPaquete"%>
<%@page import="servidor.DtPaquete"%>
<%@ page import='java.util.Date'%>
<%@ page import='java.util.Vector'%>
<%@ page import='java.util.Map'%>

<%
	servidor.PublicadorService service = new servidor.PublicadorService();
	servidor.Publicador portPublicador = service.getPublicadorPort();
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Modificar perfil</title>
<style>
        <%@include file="/media/styles/ModificarPerfil.css" %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div class="main">
		<jsp:include page="/WEB-INF/template/leftPanel.jsp" />
		<div class="center-panel">
			<div class="general-info">
			
                <div class="edit-photo">
				<img id="profile-photo"
					src="/web/media/images/<%= (String)((DtUsuario) request.getSession().getAttribute("info_perfil")).getFoto() %>" />
					<button>Editar</button>
					</div>
				<div class="name-and-more">
					<h1><%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getNombre() +" "+ ((DtUsuario) request.getSession().getAttribute("info_perfil")).getApellido() %></h1>
					<h2><%= ((DtUsuario) request.getSession().getAttribute("info_perfil")).getEmail() %></h2>
					<h3>
						Último Ingreso: <span id="last-date"><%= new Date() %></span>
					</h3>
				</div>
			</div>
			<div class="detailed-info">

				<div id="info-container" class="info-container">
					<%
            		if (portPublicador.esEmpresa((String) request.getSession().getAttribute("nickname_sesion"))) {
            	%>


					<form id="form-edit-profile" class="info-container"  method="POST" onsubmit="return validarFormulario()">
						<!--DONDE SE MOSTRARA LOS ATRIBUTOS PARA CAMBIAR-->
						<ul>
							<li class="flex-line">
								<p>
									<b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("nickname_sesion") %></span>
								</p>
							</li>
							<li class="flex-line">
								<p>
									<b>Nombre: </b>
								</p> <input type="text" id="input-name-e" name="input-name-e" value=<%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getNombre() %>>
							</li>
							<li class="flex-line">
								<p for="input-lastname">
									<b>Apellido: </b>
								</p> <input type="text" id="input-lastname-e" name="input-lastname-e" value=<%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getApellido() %>>
							</li>
							<li class="flex-line">
								<p>
									<b>Email: </b><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getEmail() %>
								</p>
							</li>
							<li class="flex-line">
								<p for="input-website">
									<b>SitioWeb: </b>
								</p> <input type="text" id="input-website" name="input-webSite"
								value=<%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getLink() %>><br>
							</li>
							<li class="flex-line">
								<p > 
									<b>Descripción: </b>
								</p> <textarea name="input-description" rows=12 cols=56 id="input-description"><%= ((DtEmpresa) request.getSession().getAttribute("info_perfil")).getDescripcion() %></textarea><br>
							</li>
						
							<li class="flex-line">
								<p>
									<b>Nueva contraseña: </b>
								</p> <input type="password" id="input-new-pass" name="input-new-pass-e"
								value=""><br>
							</li>
							<li class="flex-line">
								<p>
									<b>Repetir contraseña: </b>
								</p> <input type="password" id="input-rep-pass" name="input-rep-pass-e"
								value=""><br>
							</li>
						</ul>
						
						<div class="menu-bar">
							<input type="submit" id="submit" value="Guardar" name="submit">
							<a href="perfil" class="menu-button" id="cancel-button">Cancelar</a>
						</div>
						
					</form>
					
					

					
					<%
            		}else {
            			if (portPublicador.esPostulante((String) request.getSession().getAttribute("nickname_sesion"))) {
            	%>

					<form id="form-edit-profile" class="info-container"  method="POST" onsubmit="return validarFormulario()">
						<ul>
							<li class="flex-line">
								<p>
									<b>Nickname: </b><span id="profile-nickname"><%= (String) request.getSession().getAttribute("nickname_sesion") %></span>
								</p>
							</li>
							<li class="flex-line">
								<p>
									<b>Nombre: </b>
								</p> <input type="text" id="input-name-p" name="input-name-p"
								value=<%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNombre() %>><br>
							</li>
							<li class="flex-line">
								<p for="input-lastname">
									<b>Apellido: </b>
								</p> <input type="text" id="input-lastname-p" name="input-lastname-p"
								value=<%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getApellido() %>><br>
							</li>
							<li class="flex-line">
								<p>
									<b>Email: </b><%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getEmail() %></p>
							</li>
							<li class="flex-line">
								<p for="input-website">
									<b>Fecha de Nacimiento: </b>
								</p> <input type="date" id="input-date" name="input-date"
								value=<%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getFechaA() %>><br>
							</li>
							<li class="flex-line">
								<p for="input-description">
									<b>Nacionalidad: </b>
								</p> <input type="text" id="input-nationality" name="input-nacionalidad"
								value=<%= ((DtPostulante) request.getSession().getAttribute("info_perfil")).getNacionalidad() %>><br>
							</li>
							<li class="flex-line">
								<p>
									<b>Nueva contraseña: </b>
								</p> <input type="password" id="input-new-pass" name="input-new-pass-p"
								value=""><br>
							</li>
							<li class="flex-line">
								<p>
									<b>Repetir contraseña: </b>
								</p> <input type="password" id="input-rep-pass"  name="input-rep-pass-p"
								value=""><br>
							</li>
						</ul>
						<div class="menu-bar">
							<input type="submit" id="submit" value="Guardar" name="submit">
							<a href="perfil" class="menu-button" id="cancel-button">Cancelar</a>
						</div>
					</form>
					



					<%
					}
					}
					%>
				</div>
			</div>
		</div>
	</div>
	<script>
	   
	 
		function validarFormulario() {
			
				var contrasena1 = document.getElementById("input-new-pass").value;
				var contrasena2 = document.getElementById("input-rep-pass").value;
				
			
			
			if (contrasena1 !== contrasena2) {
				alert("Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
				return false;
			}

			return true;
		}
	</script>
</body>
</html>