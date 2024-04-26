<%@page import="net.java.dev.jaxb.array.StringArray"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
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
		<div class="filters-panel">
		            <form action="ofertas-postularse" method="GET">
                <label class="label-consular" for="select-empresa">Empresa: </label>
                <select id="select-empresa" name="opcion">
                    <option id="optionE-none" selected></option>
                    <% if (request.getSession().getAttribute("nickEmpresas_sesion") != null) {
                    	List<String> info = (List<String>) request.getSession().getAttribute("nickEmpresas_sesion");
              
                        		for (String nick : info ) {
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
                <label class="label-consular" for="select-keyword">Keyword: </label>
                <select id="select-keyword" name="opcionK">
                <option id="optionK-none" selected></option>
                <% if (request.getSession().getAttribute("keywords_leftpanel") != null) {
                	List<String> info = (List<String>) request.getSession().getAttribute("keywords_leftpanel");
                	 
                	
                	for (String keyword : info ) {
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
             List<DtOferta> info = (List<DtOferta>) request.getSession().getAttribute("ofertas_sin_postularse");
		      if (info != null && !info.isEmpty()) {
		          for (DtOferta oferta : info) {
	   %>
	    <div class="list-item">
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
	        <form action="/web/ofertas-postularse" method="post" class="list-buy">
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	    <% 
	        }
	    } 
	    %>
            
	   <% 
	    	Vector<DtOferta> infoE = (Vector<DtOferta>) request.getSession().getAttribute("ofertas_empresa");
		      if (infoE != null && !infoE.isEmpty()) {
		          for (DtOferta oferta : infoE) {
	   %>
	    <div class="list-item">
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
	        <form action="/ofertas-postularse" method="post" class="list-buy">
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	    <% 
	        }
	    } 
	    %>
	    
	    <% 
	    	Vector<DtOferta> infoK = (Vector<DtOferta>) request.getSession().getAttribute("ofertas_keywords");
		      if (infoK != null && !infoK.isEmpty()) {
		          for (DtOferta oferta : infoK) {
	   %>
	    <div class="list-item">
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
	        <%
		        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        %>
		        	<p>Favoritos: <%= oferta.getFavoritos() %></p>
		        <%
		        	} 
		        %>
		        <form action="/ofertas-postularse" method="post">
	        
	        	<button class="button-view" name="postularse" value="<%= oferta.getNombre()%>" id="view-basico">Postularse</button>
	        </form>
	        </div>
	        
	        </div>
	    <% 
	        }
	    } 
	    %>
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