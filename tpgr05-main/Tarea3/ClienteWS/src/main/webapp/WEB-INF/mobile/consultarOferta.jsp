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
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        
        <title>Consulta Ofertas</title>
			  <style>
            <%@include file="/media/styles/mobile/consultarOferta.css" %>
           
             </style>
             <nav class="navbar navbar-expand-lg navbar-light bg-custom">
        <a class="navbar-brand" href="home">
            <img id="logo" class="logo img-fluid" src="media/images/logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="consultaOferta">Ver ofertas laborales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="postulaciones">Ver postulaciones</a>
                </li>
                <li class="nav-item">
                	<form action="/web/home" method = "post">
            <button class="nav-link">Salir</button>
            	</form>
    
                </li>
            </ul>
        </div>
    </nav>
            <div class="main">  
              
                
		       <div class="center-panel">
		            <<div class="row">
            <div class="container col-12">
                <!-- Empresa selection form -->
                <form action="consultaOferta" method="GET" class="mb-3">
                    <div class="form-group">
                        <label for="select-empresa">Empresa:</label>
                        <select id="select-empresa" name="opcion" class="form-control">
                            <option id="optionE-none" selected></option>
                            <% if (request.getSession().getAttribute("nickEmpresas_sesion") != null) {
                                for (String nick : (List<String>) request.getSession().getAttribute("nickEmpresas_sesion")) {
                            %>
                                <option id="optionE-<%= nick %>"><%= nick %></option>
                            <%
                                }
                            }
                            %>             
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>

                <!-- Keyword selection form -->
                <form action="consultaOferta" method="GET">
                    <div class="form-group">
                        <label for="select-keyword">Keyword:</label>
                        <select id="select-keyword" name="opcionK" class="form-control">
                            <option id="optionK-none" selected></option>
                            <% if (request.getSession().getAttribute("keywords_leftpanel") != null) {
                                for (String keyword : (List<String>) request.getSession().getAttribute("keywords_leftpanel")) {
                            %>
                                <option id="optionK-<%= keyword %>"><%= keyword %></option>
                            <%
                                }
                            }
                            %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
		<% 
		    	List<DtOferta> info = (List<DtOferta>) request.getSession().getAttribute("ofertas_sesion");
		     	
		   
			       if (info != null && !info.isEmpty()) {
			           for (DtOferta oferta : info) {
		    %>
		    <div id="<%= oferta.getNombre() %>" class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/web/media/images/<%= oferta.getUrlFoto() %>" alt="Imagen de la oferta" />

		            
		            <div class="list-block">
		                <h2><%= oferta.getNombre() %></h2> 
		                
		                        <p><%= oferta.getDescripcion() %></p>
		                    
		            </div>
		        </div>
		
		        <div class="list-buy">
		            <a href="info-oferta?oferta=<%= oferta.getNombre() %>">
		                <button class="button-view" id="view-basico">Ver</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		
		    <% 
		    	List<DtOferta> infoE = (List<DtOferta>) request.getSession().getAttribute("infoOferta_sesion");
		     	
		   
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
		            <a href="info-oferta?oferta=<%= oferta.getNombre() %>">
		                <button class="button-view" id="view-basico">Ver</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		    
		    <% 
		    	List<DtOferta> infoK = (List<DtOferta>) request.getSession().getAttribute("infoOfertaK_sesion");
		     	
		   
			       if (infoK != null && !infoK.isEmpty()) {
			           for (DtOferta ofertaK : infoK) {
		    %>
		    <div id="<%= ofertaK.getNombre() %>" class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/web/media/images/<%= ofertaK.getUrlFoto() %>" alt="Imagen de la oferta" />

		            
		            <div class="list-block">
		                <h2><%= ofertaK.getNombre() %></h2> 
		                <ul>
		                    <li>
		                        <p><%= ofertaK.getDescripcion() %></p>
		                    </li>
		                </ul>
		            </div>
		        </div>
		
		        <div class="list-buy">
		            <a href="info-oferta?oferta=<%= ofertaK.getNombre() %>">
		                <button class="button-view" id="view-basico">Ver</button>
		            </a>
		        </div>
		    </div>
		    <% 
		        }
		    } 
		    %>
		  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



    </html>
