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
    <title>Postularse</title>
    <style>
        <%@include file="/media/styles/styleConsultaOfertas.css" %>
    </style>
</head>
<body>
   <jsp:include page="/WEB-INF/template/header.jsp" />
   <div class="main">
   <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
		<div class="center-panel">
		<h1>Postularse</h1>
		<div class="filters-panel">
		            <form action="ofertas-postularse" method="GET">
                <label for="select-empresa">Empresa: </label>
                <select id="select-empresa" name="opcion">
                    <option id="optionE-none" selected></option>
                    <% if (request.getSession().getAttribute("nickEmpresas_sesion") != null) {
                        		for (String nick : (Vector<String>) request.getSession().getAttribute("nickEmpresas_sesion")) {
                        %>
                            <option id="optionE-<%= nick %>"><%= nick %></option>
                        <%
                        	}
                        }
                        %>             
                </select>
                <input type="submit" value="Enviar">
                  </form>
                  <form action="ofertas-postularse" method="GET">
                <label for="select-keyword">Keyword: </label>
                <select id="select-keyword" name="opcionK">
                <option id="optionK-none" selected></option>
                <% if (request.getSession().getAttribute("keywords_leftpanel") != null) {
                        		for (String keyword : (Vector<String>) request.getSession().getAttribute("keywords_leftpanel")) {
                        %>
                            <option id="optionK-<%= keyword %>"><%= keyword %></option>
                        <%
                        	}
                        }
                        %>
                </select>
                <input type="submit" value="Enviar">
                </form>
            </div>
             <% 
	    	Vector<DTOferta> info = (Vector<DTOferta>) request.getSession().getAttribute("ofertas_sin_postularse");
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
	        <form action="/trabajouy/ofertas-postularse" method="post" class="list-buy">
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	    <% 
	        }
	    } 
	    %>
            
	   <% 
	    	Vector<DTOferta> infoE = (Vector<DTOferta>) request.getSession().getAttribute("ofertas_empresa");
		      if (infoE != null && !infoE.isEmpty()) {
		          for (DTOferta oferta : infoE) {
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
	        <form action="/trabajouy/ofertas-postularse" method="post" class="list-buy">
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	    <% 
	        }
	    } 
	    %>
	    
	    <% 
	    	Vector<DTOferta> infoK = (Vector<DTOferta>) request.getSession().getAttribute("ofertas_keywords");
		      if (infoK != null && !infoK.isEmpty()) {
		          for (DTOferta oferta : infoK) {
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
	        <form action="/trabajouy/ofertas-postularse" method="post" class="list-buy">
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	    <% 
	        }
	    } 
	    %>
		</div>
	   </div>
</body>
</html>