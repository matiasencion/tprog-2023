<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="servidor.DtUsuario"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de Usuarios</title>
    <style>
        <%@include file="/media/styles/styleConsultaUsuarios.css" %>
    </style>
    <jsp:include page="/WEB-INF/template/header.jsp" />
</head>
<body>
    <div class="main">
     
        <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
        
        <div class="center-panel"> 
         <h1>Usuarios</h1>
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
                List<DtUsuario> users = (List<DtUsuario>) request.getAttribute("usariosInfo_sesion");
                if (users != null && !users.isEmpty()) {
                    for (DtUsuario user : users) {
            %>
            <div class="list-item">
                <div class="list-info">
                                    
                    <img src="/web/media/images/<%= user.getFoto()%>" /> 
                
                    <div class="list-block">
                        <div style="display:flex;">
		            		<h2><%= user.getNickname() %>
		            		<%
		                	servidor.PublicadorService service = new servidor.PublicadorService();
		            		servidor.Publicador portPublicador = service.getPublicadorPort();
		        			if ((EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.POSTULANTE || (EstadoSesion) request.getSession().getAttribute("estado_sesion") == EstadoSesion.EMPRESA)  {
		        				if(!((String) request.getSession().getAttribute("nickname_sesion")).equals(user.getNickname())) {
		        					if(portPublicador.sigueAUsuario((String) request.getSession().getAttribute("nickname_sesion"), user.getNickname())) {
		        		%>
		        				<a href="/web/usuarios?unfollow=<%= user.getNickname()%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/unfollow.png"/></a>
		        		<%
		        					} else {
		        		%>
		        				<a href="/web/usuarios?follow=<%= user.getNickname()%>"><img style="vertical-align: middle; width:22px; height:22px;" src="/web/media/images/follow.png"/></a>
		        		<%			}
		        				}
                    		}
		        		%> 
		            		</h2>
		                
		           	 </div>
                        <ul>
                            <li><b>Nombre: </b><%= user.getNombre() %></li>
                            <li><b>Apellido: </b><%= user.getApellido() %></li>
                            <li><b>Email: </b><%= user.getEmail() %></li>
                        </ul>
                    </div>
                </div>
                <div class="list-buy">
                <form action="usuarios"	 method="post">
			<input type="hidden" name="buscarUsuario" value=<%=user.getNickname() %>>
			<button class="button-view" type="submit">Ver</button>
		</form>
             
            </div>
            </div>
            <%
                    }
                } else {
            %>
            <p>No se encontraron usuarios.</p>
            <%
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
