<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.EstadoSesion" %>
<%@ page import="servidor.DtPaquete" %>
<%@page import="java.util.List"%>
<%@page import="servidor.DtPaqueteArray"%>

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
                     		       <div class="spinner-container">
		       
		    <div id="pixel-spinner" class="pixel-spinner">
  			<div class="pixel-spinner-inner">
  			</div>  
  			  			</div>  
  			</div>
          <main id="main"> 	
		            <script>
	document.getElementById('main').style.visibility = 'hidden';
		</script>	
            <%
              
            DtPaqueteArray paquetesA = (DtPaqueteArray) request.getSession().getAttribute("comprar_paquetes");
            
            List<DtPaquete> paquetes = paquetesA.getItem();
            
                if (paquetes != null && !paquetes.isEmpty()) {
                    for (DtPaquete paquete : paquetes) {
            %>
            <div class="list-item">
                <div class="list-info">
                      <img src="/web/media/images/<%= paquete.getFoto() %>" alt="Imagen de la oferta" />
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
                          <li> <p><%= paquete.getTiposOfertas() %> </p></li>
                          
                        </ul>
                      
                    </div>
                </div>
                <form method="post" action="/web/compra-paquete" class="list-buy">
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
     </main> 
    <script>
    document.getElementById('main').style.visibility = 'hidden';

    document.addEventListener("DOMContentLoaded", function() {
        setTimeout(function() {
            document.getElementById('pixel-spinner').style.visibility = 'hidden';
            document.getElementById('pixel-spinner').style.display = 'none';
            document.getElementById('main').style.visibility = 'visible';
        }, 1000); 
    }); 
    </script>
</body>
</html>