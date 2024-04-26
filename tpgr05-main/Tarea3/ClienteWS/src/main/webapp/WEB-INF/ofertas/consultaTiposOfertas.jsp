<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.EstadoSesion" %>
<%@ page import="servidor.DtTipoOfertaArray"%>
<%@ page import="servidor.DtTipoOferta"%>
<%@page import="java.util.List"%>
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
            DtTipoOfertaArray tiposA = (DtTipoOfertaArray) request.getSession().getAttribute("tipos_oferta");
               List<DtTipoOferta> tipos  = tiposA.getItem();
            if (tipos != null && !tipos.isEmpty()) {
                    for (DtTipoOferta toferta : tipos) {
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