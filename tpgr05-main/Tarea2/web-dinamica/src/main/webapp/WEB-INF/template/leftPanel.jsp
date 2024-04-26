<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="java.util.Vector"%>
        <style>
            <%@include file="/media/styles/left-panel.css" %>
        </style>
       		 	<div class="left-panel">
        			 <div class="profile-panel">
                         <%
                         	if (request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA) {
                         %>
                         <a href="perfil">
                            <h2>Mi Perfil</h2>
                         </a>
                         <ul>
                            <a href="oferta"><li>Alta Oferta Laboral</li></a>
		                    <a href="compra-paquete"><li>Comprar Paquetes</li></a>
		                    <a href="consulta-paquete"><li>Consultar Paquetes</li></a>
		                    <a href="consultaOferta"><li>Consultar Ofertas</li></a>
		                    <a href="perfil"><li>Consultar Postulaciones</li></a>
		                    <a href="tipo-oferta"><li>Consultar Tipos Publicación</li></a>
		                    <a href="usuarios"><li>Consultar Usuarios</li></a>
                        </ul>
                        <%
	                       	} else {
	                       		if (request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE) {
                        %>
	                        <a href="perfil">
	                            <h2>Mi Perfil</h2>
	                        </a>
	                        <ul>
	                            <a href="ofertas-postularse"><li>Postularse a Oferta</li></a>
			                    <a href="consultaOferta"><li>Consultar Ofertas</li></a>
			                    <a href="consulta-paquete"><li>Consultar Paquetes</li></a>
			                    <a href="perfil"><li>Consultar Postulaciones</li></a>
			                    <a href="tipo-oferta"><li>Consultar Tipos Publicación</li></a>
			                    <a href="usuarios"><li>Consultar Usuarios</li></a>
	                        </ul>
	                     <%
	                       		} else {
	                     %>
		                     <a href="home">
	                            <h2>Inicio</h2>
	                         </a>
	                     	<ul>
	                            <a href="consultaOferta"><li>Consultar Ofertas</li></a>
			                    <a href="consulta-paquete"><li>Consultar Paquetes</li></a>
			                    <a href="tipo-oferta"><li>Consultar Tipos Publicación</li></a>
			                    <a href="usuarios"><li>Consultar Usuarios</li></a>
	                        </ul>
	                     <%
	                       		}
	                       	}
	                     %>
                    </div>
                    <div class="keywords-section">
                        <h2>Keywords</h2>
                        <ul>
                        <%
                        	if (request.getSession().getAttribute("keywords_leftpanel") != null) {
                        		for (String keyword : (Vector<String>) request.getSession().getAttribute("keywords_leftpanel")) {
                        %>
                            <a href="consultaOferta?opcionK=<%= keyword %>"><li><%= keyword %></li></a>
                        <%
                        	}
                        }
                        %>
                        </ul>
                    </div>
                </div>
