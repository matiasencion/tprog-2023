<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.trabajouy.model.EstadoSesion"%>
    <%@page import="com.trabajouy.model.DTUsuario"%>
    <%@page import="com.trabajouy.model.DTOferta"%>
    <%@page import="com.trabajouy.model.DTPostulacion"%>
    <%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Postulacion</title>
    <style>
        <%@include file="/media/styles/infoOfertaFrontend.css" %>
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
                        <img src="/trabajouy/media/images/<%= (String)((DTOferta) request.getSession().getAttribute("info_oferta")).getFoto() %>" alt="foto trabajo">
                        <h2><%= ((DTOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></h2>
                        <ul>
                            <li class="dflex">
                                <h3>Postulante: </h3>
                                <p><%= ((DTPostulacion)request.getSession().getAttribute("postulacion")).getPostulante() %></p>
                            </li>
                            <li class="dflex">
                                <h3>CV reducido: </h3>
                                <textarea readonly><%= ((DTPostulacion)request.getSession().getAttribute("postulacion")).getCv() %></textarea>
                            </li>
                            <li class="dflex">
                                <h3>Motivacion: </h3>
                                    <textarea readonly><%= ((DTPostulacion)request.getSession().getAttribute("postulacion")).getDescripcion() %></textarea>
                            </li>

                            <li class="dflex">
                                <h3>Fecha postulacion:</h3>
                                <p><%= ((DTPostulacion)request.getSession().getAttribute("postulacion")).getFechaString() %></p>
                            </li>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </main>
</body>
</html>
