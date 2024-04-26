

<%@page import="java.net.Authenticator.RequestorType"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import ="servidor.DtOferta" %>
<%@page import ="servidor.DtOfertaArray" %>
<%@page import ="java.util.List" %>
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
            
          <div class="main">  
       		
       		 <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
              
                
		       <div class="center-panel">
		            <h1>Ofertas Laborales</h1>
		
		    <% 
		    DtOfertaArray infoA = (DtOfertaArray) request.getSession().getAttribute("infoOferta_sesion");
		    List<DtOferta> info = null;
		    if(infoA != null) {info =  infoA.getItem();}

			       if (info != null && !info.isEmpty()) {
			           for (DtOferta oferta : info) {
			        	 
		    %>
		    <div class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/web/media/images/<%= oferta.getUrlFoto() %>" alt="Imagen de la oferta" />

		            
		            <div class="list-block">
		                <div style="display:flex;">
		            		<h2><%= oferta.getNombre() %></h2>
		                <%
		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), oferta.getNombre())) {
		        		%>
		        				<a href="/web/home?quitarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
		        		<%
		        				} else {
		        		%>
		        				<a href="/web/home?agregarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
		        		<%		}
		        			}
		        		%> 
		           	 </div>
		                <ul>
		                    <li>
		                        <p><%= oferta.getDescripcion() %></p>
		                    </li>
		                </ul>
		            </div>
		        </div>
		
		        <div class="list-buy">
		        <%
		        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        %>
		        	<p>Favoritos: <%= oferta.getFavoritos() %></p>
		        <%
		        	} 
		        %>
		            <a href="info-oferta?oferta=<%= oferta.getNombre() %>">
		                <button class="button-view" id="view-basico">Ver</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		 
		  


    </html>