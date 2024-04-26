<%@page import="java.util.Set"%>
<%@page import="com.trabajouy.model.DTUsuario"%>
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
            <%
                Set<DTUsuario> users = (Set<DTUsuario>) request.getAttribute("usariosInfo_sesion");
                if (users != null && !users.isEmpty()) {
                    for (DTUsuario user : users) {
            %>
            <div class="list-item">
                <div class="list-info">
                                    
                    <img src="/trabajouy/media/images/<%= user.getFoto()%>" /> 
                
                    <div class="list-block">
                        <h2><%= user.getNickname() %></h2>
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
    <script src="scripts/scriptConsultaUsuarios.js"></script>
</body>
</html>
