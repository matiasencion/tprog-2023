<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.EstadoSesion" %>
<%@ page import="com.trabajouy.model.DTTipoOferta" %>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tipos de Publicacion</title>
        <style>
            <%@include file="/media/styles/styleConsultaOfertas.css" %>
        </style>
      </head> 
<body>
    <jsp:include page="/WEB-INF/template/header.jsp" />
      <div class="main">
        <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
          <div class="center-panel">
            <h1>Tipos de Publicacion</h1>
            <%
                Vector<DTTipoOferta> tipos = (Vector<DTTipoOferta>) request.getSession().getAttribute("tipos_oferta");
                if (tipos != null && !tipos.isEmpty()) {
                    for (DTTipoOferta toferta : tipos) {
            %>
            <div class="list-item">
                <div class="list-info">
                      <!-- <img src="/trabajouy/media/images/" alt="Imagen de la oferta" /> -->
                    <div class="list-block">
                        <h2><%= toferta.getNombre() %></h2>
                        <ul>
                            <li><b>Descripción:</b> <%= toferta.getDescripcion() %></li>
                            <li><b>Exposición:</b> <%= toferta.getExposicion() %></li>
                            <li><b>Duración:</b> <%= toferta.getDuracion() %> Días</li>
                            <li><b>Costo:</b> <%= toferta.getCosto() %></li>
                            <li><b>Fecha de Alta:</b> <%= toferta.getFechaDeAlta() %></li>
                        </ul>
                    </div>
                </div>
            </div>
            <% 
                    } 
                } else {
            %>
            <p>No hay paquetes disponibles.</p>
            <% } %>
        </div>
    </div>
    <script src="scripts/scriptConsultaPaquetes.js"></script>
</body>
</html>