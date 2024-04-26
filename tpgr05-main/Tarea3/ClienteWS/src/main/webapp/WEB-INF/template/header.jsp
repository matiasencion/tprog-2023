<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>


		<style>
            <%@include file="/media/styles/headerandreset.css" %>

        </style>
        <header>

            <a href="home"><img id="logo" class="logo" src="media/images/logo.png"
                    alt="Logo de la empresa"></a>
                   <div class="buttons-section">
		
		<!--<form action="/trabajouy/home"	 method="post">
			<input type="hidden" name="botonPresionado" value="true">
			<button class="login-button" type="submit">Cargar Datos</button>
		</form>-->


	</div>
            <form method="POST" action="/web/barraDeBusqueda" class="search-box">
                <input type="text" name="busqueda" placeholder="Buscar...">
                <button type="submit" class="search-button">Buscar</button>
            </form>
    
            
            <%
           EstadoSesion sesion = (EstadoSesion) request.getSession().getAttribute("estado_sesion"); 
            if(sesion == null || sesion == EstadoSesion.VISITANTE){
            %>
            <div class="buttons-section">
                <a href="login"><button class="login-button">Iniciar sesión</button></a>
                <a href="register"><button class="register-button">Registrarse</button></a>
            </div>
            <% } else if (sesion == EstadoSesion.EMPRESA || sesion == EstadoSesion.POSTULANTE){
            	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
            	%>
           <div class="profile-section">
            <a href="perfil">
                <div id="header-to-profile">
                <% String foto = (String) request.getSession().getAttribute("foto_sesion");
                System.out.println(foto);
                %>
                    <img id="profile-photo" src="/web/media/images/<%=foto%>"/>
                    <p id="header-nickname"><b><%= nickname%></b></p>
                </div>
            </a>
            <form action="/web/home" method = "post">
            <button>Cerrar Sesión</button>
            </form>
            
        </div>
        <%}%>
        </header>
