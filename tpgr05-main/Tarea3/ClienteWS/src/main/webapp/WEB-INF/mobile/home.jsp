<%@page import="java.util.Vector"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
                <%@include file="/media/styles/mobile/home.css" %>
    
        
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-custom">
        <a class="navbar-brand" href="home">
            <img id="logo" class="logo img-fluid" src="media/images/logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="consultaOferta">Ver ofertas laborales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="postulaciones">Ver postulaciones</a>
                </li>
                <li class="nav-item">
                	<form action="/web/home" method = "post">
            <button class="nav-link">Salir</button>
            	</form>
    
                </li>
            </ul>
        </div>
    </nav>
    <% String foto = (String) request.getSession().getAttribute("foto_sesion"); %>
	<div class="profile-photo-wrapper">
    <img id="profile-photo" src="/web/media/images/<%= foto %>" alt="Foto de perfil"/>
	</div>
	<% 
            	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
            	%>
    <div class="container mt-4">
                <div id="typed-output"></div>
    </div>
	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.12"></script>
    <script>
    <% String nombre = (String) request.getSession().getAttribute("Nombre_Bienvenida");
    if (nombre == null) {
        nombre = "Invitado"; 
    }
 %>
 var options = {
         strings: ["Bienvenido al Portal de Empleo, <%= nombre %>.", "Encuentra tu oportunidad laboral ideal hoy mismo."],
         typeSpeed: 60,
         backSpeed: 50,
         startDelay: 500,
         backDelay: 1000,
         loop: true,
         loopCount: Infinity,
         showCursor: true,
         cursorChar: '|',
         autoInsertCss: true,
     };
 	

        var typed = new Typed('#typed-output', options);
    </script>
</body>

</html>
