<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.trabajouy.model.EstadoSesion"%>
    <%@page import="servidor.DtUsuario"%>
    <%@page import="servidor.DtOferta"%>
    <%@page import="servidor.DtPostulacion"%>
    <%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/media/styles/styleConsultaOfertas.css">
    <title>Postulacion</title>
    <style>
        <%@include file="/media/styles/mobile/infoOferta.css" %>
    </style>
</head>
<body><nav class="navbar navbar-expand-lg navbar-light bg-custom">
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
    <main id="frontend-visitante">
        <div class="content-section">
            <div class="main-content">
                <div class="job-card">
                    <div class="info-oferta">
                        <img class="imagen-oferta" src="/web/media/images/<%= (String)((DtOferta) request.getSession().getAttribute("info_oferta")).getUrlFoto() %>" alt="foto trabajo">
                        <h2><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></h2>
                        <ul>
                            <li class="dflex">
                                <h3>Postulante: </h3>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getPostulante() %></p>
                            </li>
                            <li class="dflex">
                                <h3>CV reducido: </h3>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getCurriculum() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Motivacion: </h3>
                                    <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getDescripcion() %></p>
                            </li>

                            <li class="dflex">
                                <h3>Fecha postulacion:</h3>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getFechaString() %></p>
                            </li>
                            <%  String nombrePostulante = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getPostulante(); 
                            if(!((DtPostulacion)request.getSession().getAttribute("postulacion")).getUrlVideo().isEmpty()){ 
                                 String url = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getUrlVideo();
                                 %>
                            <li class="dflex">
                               <iframe width="250" height="300" src="<%=url%>" title="<%=nombrePostulante%>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                            
                            </li>
                            <%} %>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   
</body>
</html>
