<%@ page import='java.util.Date' %>
<%@ page import='java.util.Vector' %>
<%@ page import='java.util.Map' %>
<%@ page import='java.util.List' %>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtPostulacionArray"%>
<%@page import="servidor.DtPostulacion"%>
<%@page import="java.io.ObjectInputStream.GetField"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <link rel="stylesheet" href="/media/styles/styleConsultaOfertas.css">
    <style>
                <%@include file="/media/styles/mobile/consultarPostulaciones.css" %>
    
        
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
                    <a class="nav-link" href="#">Ver postulaciones</a>
                </li>
                <li class="nav-item">
                	<form action="/web/home" method = "post">
            <button class="nav-link">Salir</button>
            	</form>
    
                </li>
            </ul>
        </div>
    </nav>
    <main>
   <% 
   DtPostulacionArray postuArray = (DtPostulacionArray) request.getSession().getAttribute("postulaciones_perfil");     
 	List<DtPostulacion> infoList =	postuArray.getItem();
    for (DtPostulacion postu : infoList) {
%>
    <div class="card mb-4 shadow-sm"> <!-- Tarjeta con sombra y margen -->
        <div class="row no-gutters">
            <div class="col-4">
                <img src="/web/media/images/<%= postu.getUrlfoto() %>" class="img-fluid card-img" alt="Imagen de la oferta" style="object-fit: cover; height: 200px;"> <!-- Imagen con tamaño fijo y object-fit -->
            </div>
            <div class="col-8">
                <div class="card-body">
                    <h5 class="card-title"><%= postu.getOferta()%></h5>
                    <p class="card-text"><small class="">Fecha de Postulación: <%= postu.getFechaString() %></small></p>
                    <a href="info-oferta?oferta=<%= postu.getOferta() %>" class="btn btn-primary stretched-link">Más información</a>
                </div>
            </div>
        </div>
    </div>
<%
    }
%>
</main>


	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.12"></script>
   
</body>

</html>
