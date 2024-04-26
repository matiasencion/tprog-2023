<%@page import="java.util.Vector"%>
<%@page import="java.net.Authenticator.RequestorType"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import ="com.trabajouy.model.DTOferta" %>

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
		    	Vector<DTOferta> info = (Vector<DTOferta>) request.getSession().getAttribute("infoOferta_sesion");
		     	
		   
			       if (info != null && !info.isEmpty()) {
			           for (DTOferta oferta : info) {
			        	 
		    %>
		    <div class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/trabajouy/media/images/<%= oferta.getFoto() %>" alt="Imagen de la oferta" />

		            
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
		            <a href="info-oferta?oferta=<%= oferta.getNombre() %>">
		                <button class="button-view" id="view-basico">Leer mas</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		 
		  


    </html>