<%@page import="java.util.Vector"%>
<%@page import="java.util.List"%>
<%@page import="java.net.Authenticator.RequestorType"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import ="servidor.DtOferta" %>
<%@page import ="servidor.DtEmpresa" %>

    <!DOCTYPE html>
    <html lang="es">
	
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Busqueda</title>
			  <style>
            <%@include file="/media/styles/styleConsultaOfertas.css" %>
           
             </style>
			<jsp:include page="/WEB-INF/template/header.jsp" />
            <div class="main">  
       		 <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
              
                
		       <div class="center-panel">
		       <div style="display: flex; align-items: center; width: 100%;">
		       		<h1>Resultados</h1>
		       		<div style="margin-left: auto;">
			       		<label for="select-orden">Orden: </label>
			       		<select id="select-orden">
			       			<option id="orden-defecto" selected>Defecto</option>
			       			<option id="orden-alfabetico">Alfabetico A-Z</option>
			       		</select>
		       		</div>
		       </div>
		       
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
		    <div id="informacion">        


<% 
	    	List<DtOferta> info = (List<DtOferta>) request.getSession().getAttribute("ofertas_ordenadas_por_defecto");
	     	
 		   
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
	        				<a href="/web/barraDeBusqueda?quitarfav=<%= oferta.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
	        		<%
	        				} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?agregarfav=<%= oferta.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
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
	    	List<DtEmpresa> infoE = (List<DtEmpresa>) request.getSession().getAttribute("empresas_ordenadas_por_defecto");
	     	
	   
		       if (infoE != null && !infoE.isEmpty()) {
		           for (DtEmpresa empresa : infoE) {
	    %>
	    <div id="<%= empresa.getNombre() %>" class="list-item">
	        <div class="list-info">
	         
	          
	         <img src="/web/media/images/<%= empresa.getFoto() %>" alt="Imagen de la oferta" />

	            
	            <div class="list-block">
	                <div style="display:flex;">
	            		<h2><%= empresa.getNickname() %>
	            		<%
	                	servidor.PublicadorService service = new servidor.PublicadorService();
	            		servidor.Publicador portPublicador = service.getPublicadorPort();
	        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
	        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(empresa.getNickname())) {
	        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), empresa.getNickname())) {
	        		%>
	        				<a href="/web/barraDeBusqueda?unfollow=<%= empresa.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/unfollow.png"/></a>
	        		<%
	        					} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?follow=<%= empresa.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/follow.png"/></a>
	        		<%			}
	        				}
             		}
	        		%> 
	            		</h2>
	                
	           	 </div>
	                <ul>
	                    <li>
	                        <p><%= empresa.getDescripcion() %></p>
	                    </li>
	                </ul>
	            </div>
	        </div>
	
	        <div class="list-buy">
	            <a href="perfil?usuarioB=<%= empresa.getNickname() %>">
	                <button class="button-view" id="view-basico">Ver</button>
	            </a>
	        </div>
	    </div>
	    <% 
	        }
	    } 
	    %>



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
document.getElementById("select-orden").addEventListener("change", () => {
    if (document.getElementById("select-orden").value == "Defecto") {
    	document.getElementById("informacion").innerHTML = ` 		<% 
	    	List<DtOferta> infoD = (List<DtOferta>) request.getSession().getAttribute("ofertas_ordenadas_por_defecto");
	     	
 		   
		       if (infoD != null && !infoD.isEmpty()) {
		           for (DtOferta ofertaD : infoD) {
	    %>
	    <div id="<%= ofertaD.getNombre() %>" class="list-item">
	        <div class="list-info">
	         
	          
	         <img src="/web/media/images/<%= ofertaD.getUrlFoto() %>" alt="Imagen de la oferta" />

	            
	            <div class="list-block">
	            	<div style="display:flex;">
	            		<h2><%= ofertaD.getNombre() %></h2>
	                <%
	                	servidor.PublicadorService service = new servidor.PublicadorService();
	            		servidor.Publicador portPublicador = service.getPublicadorPort();
	        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
	        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), ofertaD.getNombre())) {
	        		%>
	        				<a href="/web/barraDeBusqueda?quitarfav=<%= ofertaD.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
	        		<%
	        				} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?agregarfav=<%= ofertaD.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
	        		<%		}
	        			}
	        		%> 
	           	 </div>
	                <ul>
	                    <li>
	                        <p><%= ofertaD.getDescripcion() %></p>
	                    </li>
	                </ul>
	            </div>
	        </div>
	
	        <div class="list-buy">
	        <%
	        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
	        %>
	        	<p>Favoritos: <%= ofertaD.getFavoritos() %></p>
	        <%
	        	} 
	        %>
	            <a href="info-oferta?oferta=<%= ofertaD.getNombre() %>">
	                <button class="button-view" id="view-basico">Ver</button>
	            </a>
	        </div>
	    </div>
	    <% 
	        }
	    } 
	    %>
	
	    <% 
	    	List<DtEmpresa> infoED = (List<DtEmpresa>) request.getSession().getAttribute("empresas_ordenadas_por_defecto");
	     	
	   
		       if (infoED != null && !infoED.isEmpty()) {
		           for (DtEmpresa empresaD : infoED) {
	    %>
	    <div id="<%= empresaD.getNombre() %>" class="list-item">
	        <div class="list-info">
	         
	          
	         <img src="/web/media/images/<%= empresaD.getFoto() %>" alt="Imagen de la oferta" />

	            
	            <div class="list-block">
	                <div style="display:flex;">
	            		<h2><%= empresaD.getNickname() %>
	            		<%
	                	servidor.PublicadorService service = new servidor.PublicadorService();
	            		servidor.Publicador portPublicador = service.getPublicadorPort();
	        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
	        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(empresaD.getNickname())) {
	        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), empresaD.getNickname())) {
	        		%>
	        				<a href="/web/barraDeBusqueda?unfollow=<%= empresaD.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/unfollow.png"/></a>
	        		<%
	        					} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?follow=<%= empresaD.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/follow.png"/></a>
	        		<%			}
	        				}
             		}
	        		%> 
	            		</h2>
	                
	           	 </div>
	                <ul>
	                    <li>
	                        <p><%= empresaD.getDescripcion() %></p>
	                    </li>
	                </ul>
	            </div>
	        </div>
	
	        <div class="list-buy">
	            <a href="perfil?usuarioB=<%= empresaD.getNickname() %>">
	                <button class="button-view" id="view-basico">Ver</button>
	            </a>
	        </div>
	    </div>
	    <% 
	        }
	    } 
	    %>`;
    } else {
        informacion.innerHTML = `		<% 
	    	List<DtOferta> infoAZ = (List<DtOferta>) request.getSession().getAttribute("ofertas_ordenadas_AZ");
	     	
 		   
		       if (infoAZ != null && !infoAZ.isEmpty()) {
		           for (DtOferta ofertaAZ : infoAZ) {
	    %>
	    <div id="<%= ofertaAZ.getNombre() %>" class="list-item">
	        <div class="list-info">
	         
	          
	         <img src="/web/media/images/<%= ofertaAZ.getUrlFoto() %>" alt="Imagen de la oferta" />

	            
	            <div class="list-block">
	            	<div style="display:flex;">
	            		<h2><%= ofertaAZ.getNombre() %></h2>
	                <%
	                	servidor.PublicadorService service = new servidor.PublicadorService();
	            		servidor.Publicador portPublicador = service.getPublicadorPort();
	        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
	        				if(portPublicador.esOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), ofertaAZ.getNombre())) {
	        		%>
	        				<a href="/web/barraDeBusqueda?quitarfav=<%= ofertaAZ.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-amarillo.png"/></a>
	        		<%
	        				} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?agregarfav=<%= ofertaAZ.getNombre()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="width:24px; height:24px;" src="/web/media/images/estrella-blanco.png"/></a>
	        		<%		}
	        			}
	        		%> 
	           	 </div>
	                <ul>
	                    <li>
	                        <p><%= ofertaAZ.getDescripcion() %></p>
	                    </li>
	                </ul>
	            </div>
	        </div>
	
	        <div class="list-buy">
	        <%
	        	if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
	        %>
	        	<p>Favoritos: <%= ofertaAZ.getFavoritos() %></p>
	        <%
	        	} 
	        %>
	            <a href="info-oferta?oferta=<%= ofertaAZ.getNombre() %>">
	                <button class="button-view" id="view-basico">Ver</button>
	            </a>
	        </div>
	    </div>
	    <% 
	        }
	    } 
	    %>
	
	    <% 
	    	List<DtEmpresa> infoEAZ = (List<DtEmpresa>) request.getSession().getAttribute("empresas_ordenadas_AZ");
	     	
	   
		       if (infoEAZ != null && !infoEAZ.isEmpty()) {
		           for (DtEmpresa empresaAZ : infoEAZ) {
	    %>
	    <div id="<%= empresaAZ.getNombre() %>" class="list-item">
	        <div class="list-info">
	         
	          
	         <img src="/web/media/images/<%= empresaAZ.getFoto() %>" alt="Imagen de la oferta" />

	            
	            <div class="list-block">
	                <div style="display:flex;">
	            		<h2><%= empresaAZ.getNickname() %>
	            		<%
	                	servidor.PublicadorService service = new servidor.PublicadorService();
	            		servidor.Publicador portPublicador = service.getPublicadorPort();
	        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
	        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(empresaAZ.getNickname())) {
	        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), empresaAZ.getNickname())) {
	        		%>
	        				<a href="/web/barraDeBusqueda?unfollow=<%= empresaAZ.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/unfollow.png"/></a>
	        		<%
	        					} else {
	        		%>
	        				<a href="/web/barraDeBusqueda?follow=<%= empresaAZ.getNickname()%>&busqueda=<%= request.getParameter("busqueda")%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/follow.png"/></a>
	        		<%			}
	        				}
             		}
	        		%> 
	            		</h2>
	                
	           	 </div>
	                <ul>
	                    <li>
	                        <p><%= empresaAZ.getDescripcion() %></p>
	                    </li>
	                </ul>
	            </div>
	        </div>
	
	        <div class="list-buy">
	            <a href="perfil?usuarioB=<%= empresaAZ.getNickname() %>">
	                <button class="button-view" id="view-basico">Ver</button>
	            </a>
	        </div>
	    </div>
	    <% 
	        }
	    } 
	    %>`;
    }
});


</script>
    </html>
