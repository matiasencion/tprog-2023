<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.EstadoSesion" %>
<%@ page import="com.trabajouy.model.DTPaquete" %>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        	  <style>
            <%@include file="/media/styles/styleConsultaOfertas.css" %>
           
             </style>
        <jsp:include page="/WEB-INF/template/header.jsp" />
     
      </head> 
      <body>
    
      <div class="main">
        <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
           
          <div class="center-panel">
            <h1>Paquetes</h1>
            <%
              
                Vector<DTPaquete> paquetes = (Vector<DTPaquete>) request.getSession().getAttribute("comprar_paquetes");
                if (paquetes != null && !paquetes.isEmpty()) {
                    for (DTPaquete paquete : paquetes) {
            %>
            <div class="list-item">
                <div class="list-info">
                      <img src="/trabajouy/media/images/<%= paquete.getFoto() %>" alt="Imagen de la oferta" />
                    <div class="list-block">
                        <h2><%= paquete.getNombre() %></h2>
                        <ul>
                            <li><b>Período:</b> <%= paquete.getValidez() %> Días</li>
                            <% 
                                 Float descuento = (paquete.getDescuento() * 100);
                                 String descuentoFormateado = String.format("%.2f", descuento);
                            %>
                           <li><b>Descuento:</b> <%= descuentoFormateado %> %</li>
                            <li><b>Fecha:</b> <%= paquete.getFechaAlta() %></li>
                            <li><b>Costo:</b> <%= paquete.getCosto() %></li>
                        </ul>
                        
                         <h3>Cantidad de Tipos de Oferta:</h3>
                        <ul>
                          <li> <p><%= paquete.getTiposOfertras() %> </p></li>
                          
                        </ul>
                      
                    </div>
                </div>
                <form method="post" action="/trabajouy/compra-paquete" class="list-buy">
                    <button name="nombre-paquete-comprado" value="<%= paquete.getNombre() %>" class="button-view" id="view-basico">Comprar</button>
                </form>
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