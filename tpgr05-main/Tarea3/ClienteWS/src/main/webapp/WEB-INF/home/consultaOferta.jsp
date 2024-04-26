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
        <title>Consulta Ofertas</title>
        	
			  <style>
            <%@include file="/media/styles/styleConsultaOfertas.css" %>
           
             </style>
			<jsp:include page="/WEB-INF/template/header.jsp" />
            <div class="main">  
       		 <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
              
                
		       <div class="center-panel">
		            <h1>Consultar Ofertas</h1>
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
		            <form action="consultaOferta" method="GET">
                <label class="label-consular" for="select-empresa">Empresa: </label>
                <select id="select-empresa" name="opcion">
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
                <input class="btn-primary" type="submit" value="Enviar">
                  </form>
                  <form action="consultaOferta" method="GET">
                <label class="label-consular" for="select-keyword">Keyword: </label>
                <select id="select-keyword" name="opcionK">
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
                <input class="btn-primary" type="submit" value="Enviar">
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
		            	<div style="display:flex;">
		            		<h2><%= oferta.getNombre() %></h2>
		                <%
		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), oferta.getNombre())) {
		        		%>
		        				<a href="/web/consultaOferta?quitarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
		        		<%
		        				} else {
		        		%>
		        				<a href="/web/consultaOferta?agregarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
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
		
		    <% 
		    	List<DtOferta> infoE = (List<DtOferta>) request.getSession().getAttribute("infoOferta_sesion");
		     	
		   
			       if (infoE != null && !infoE.isEmpty()) {
			           for (DtOferta oferta : infoE) {
		    %>
		    <div id="<%= oferta.getNombre() %>" class="list-item">
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
		        				<a href="/web/consultaOferta?quitarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
		        		<%
		        				} else {
		        		%>
		        				<a href="/web/consultaOferta?agregarfav=<%= oferta.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
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
		    
		    <% 
		    	List<DtOferta> infoK = (List<DtOferta>) request.getSession().getAttribute("infoOfertaK_sesion");
		     	
		   
			       if (infoK != null && !infoK.isEmpty()) {
			           for (DtOferta ofertaK : infoK) {
		    %>
		    <div id="<%= ofertaK.getNombre() %>" class="list-item">
		        <div class="list-info">
		         
		          
		         <img src="/web/media/images/<%= ofertaK.getUrlFoto() %>" alt="Imagen de la oferta" />

		            
		            <div class="list-block">
		            <div style="display:flex;">
		                <h2><%= ofertaK.getNombre() %></h2> 
		                <%
		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), ofertaK.getNombre())) {
		        		%>
		        				<a href="/web/consultaOferta?quitarfav=<%= ofertaK.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
		        		<%
		        				} else {
		        		%>
		        				<a href="/web/consultaOferta?agregarfav=<%= ofertaK.getNombre()%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
		        		<%		}
		        			}
		        		%> 
		        		</div>
		                <ul>
		                    <li>
		                        <p><%= ofertaK.getDescripcion() %></p>
		                    </li>
		                </ul>
		            </div>
		        </div>
		
		        <div class="list-buy">
		        <%
		        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
		        %>
		        	<p>Favoritos: <%= ofertaK.getFavoritos() %></p>
		        <%
		        	} 
		        %>
		            <a href="info-oferta?oferta=<%= ofertaK.getNombre() %>">
		                <button class="button-view" id="view-basico">Ver</button>
		            </a>
		        </div>
		    </div>
		    
		    <% 
		        }
		    } 
		    %>
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



    </html>