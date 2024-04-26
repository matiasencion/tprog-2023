<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.trabajouy.model.EstadoSesion"%>
    <%@page import="com.trabajouy.model.DTUsuario"%>
    <%@page import="com.trabajouy.model.DTOferta"%>
    <%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Postularse</title>
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
                                <h3>Nombre: </h3>
                                <p><%= ((DTOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Descripcion: </h3>
                                <p><%= ((DTOferta)request.getSession().getAttribute("info_oferta")).getDescripcion() %></p>
                            </li>
                            <li class="dflex">
                                <h3>Horario </h3>
                                <p><%= ((DTOferta)request.getSession().getAttribute("info_oferta")).getHoraInicio() +" - "+ ((DTOferta)request.getSession().getAttribute("info_oferta")).getHoraFin()%></p>
                            </li>
                           
                            <li class="dflex">
                                <h3>Departamento: </h3>
                                <p><%=((DTOferta)request.getSession().getAttribute("info_oferta")).getDepartamento()%></p>
                            </li>
                            <li class="dflex">
                                <h3>Ciudad: </h3>
                                <p><%=((DTOferta)request.getSession().getAttribute("info_oferta")).getCiudad()%></p>
                            </li>
                            <li class="dflex">
                                <h3>Remuneracion: </h3>
                                <p><%=((DTOferta)request.getSession().getAttribute("info_oferta")).getSueldo()%></p>
                            </li>
                            <li class="dflex">
                                <h3>Fecha de alta: </h3>
                                <p><%=((DTOferta)request.getSession().getAttribute("info_oferta")).getFechaAprovado()%></p>
                            </li>
                            <li class="dflex">
                                <h3>Keywords: </h3>
                                <%
                                	for(String keyword : ((DTOferta)request.getSession().getAttribute("info_oferta")).getKeywords()) {
                                %>
                                <p><%= keyword %></p>
                                <%
                                	}
                                %>
                            </li>
                            <form action="postulacion" method="POST">
							    <li class="dflex">
							        <h3>CV: </h3>
							        <textarea id="input-cv" name="cv" rows="5" cols="50" required></textarea>
							    </li>
							    <li class="dflex">
							        <h3>Motivación: </h3>
							        <textarea id="input-motivacion" name="motivacion" rows="5" cols="50" required></textarea>
							    </li>
							    <div class="menu-bar">
							        <input type="submit" class="menu-button active" id="accept-button" name="submit" value="Aceptar">
							        <a href="ofertas-postularse" class="menu-button" id="cancel-button">Cancelar</a>
							    </div>
							</form>                      
              
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </main>    
</body>
</html>
