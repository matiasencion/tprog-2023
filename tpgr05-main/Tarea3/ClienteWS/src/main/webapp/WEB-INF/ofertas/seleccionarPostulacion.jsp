<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="java.net.Authenticator.RequestorType"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import ="servidor.DtOferta" %>

    <!DOCTYPE html>
    <html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Seleccionar Postulación</title>
			  <style>
            <%@include file="/media/styles/styleConsultaOfertas.css" %>
           
             </style>
			<jsp:include page="/WEB-INF/template/header.jsp" />
            <div class="main">  
       		 <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
              
                
		       <div class="center-panel">
		            <h1>Seleccionar Oferta</h1>
		            
		
		    <% 
		    	List<DtOferta> infoE = (List<DtOferta>) request.getAttribute("ofertas");
		     	
		   
			       if (infoE != null && !infoE.isEmpty()) {
			           for (DtOferta oferta : infoE) {
		    %>
		    <div id="<%= oferta.getNombre() %>" class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/web/media/images/<%= oferta.getUrlFoto() %>" alt="Imagen de la oferta" />

		            
		            <div class="list-block">
		                <h2><%= oferta.getNombre() %></h2> 
		                <ul>
		                    <li>
		                        <p><%= oferta.getDescripcion() %></p>
		                    </li>
		                </ul>
		            </div>
		        </div>
		
		        <div class="list-buy">
		            <a href="rankear-postulacion?oferta=<%= oferta.getNombre() %>">
		                <button class="button-view" id="view-basico">Seleccionar</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		    

    </html>