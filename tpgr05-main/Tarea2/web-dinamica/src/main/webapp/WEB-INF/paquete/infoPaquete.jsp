<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <style>
        <%@include file="/media/styles/stylePaqueteBasico.css" %>
    </style>
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <%@ page import="com.trabajouy.model.EstadoSesion" %>
    <%@ page import="com.trabajouy.model.DTPaquete" %>
</head>

<body>

    <div id="frontend-visitante">
        <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
        <div class="content-section">
            <div class="card-paquete">
                <div class="relative-wrapper">
                <% DTPaquete info = (DTPaquete) request.getAttribute("info_paquete"); %>
                    <img src="/trabajouy/media/images/<%= info.getFoto() %>" alt="Imagen de la oferta" />
                    <h2><%= info.getNombre() %></h2>
                    <div class="general-info">
                        <div class="name-and-more">
                            <h3><%= info.getDescripcion() %></h3>
                            <p><b>Período: </b><%= info.getValidez() %> Días</p>
                                  <% 
                                 Float descuento = (info.getDescuento() * 100);
                                 String descuentoFormateado = String.format("%.2f", descuento);
                            %>
                         <p> <b>Descuento:</b> <%= descuentoFormateado %> %</p>
                            <p><b>Fecha: </b><%= info.getFechaAlta() %></p>
                            <p><b>Costo: </b><%= info.getCosto() %></p>
                        </div>
                    </div>
                    <div class="detailed-info">
                        <h3>Tipos de Ofertas Laborales</h3>
                        <ul>
                       
                      <%for (String tipo : info.getVectorTOfertas()) {
                    	  %>
                    	  <li><a href="tipo-oferta">
                    	  <%= tipo %>
                    	  </a>
                    	  </li>
                      <%}%>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="scripts/scriptPerfilEmpresa.js"></script>
</body>

</html>
