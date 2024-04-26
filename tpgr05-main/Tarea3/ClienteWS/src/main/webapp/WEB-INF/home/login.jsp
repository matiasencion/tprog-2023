<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.EstadoSesion" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Página Web</title>
 
    <style>
        <%@include file="/media/styles/inicioSesion.css" %>
    </style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <img src="media/images/logo2.png" alt="Logo" class="logo">
        <div class="login-form">
            <h2>Iniciar Sesión</h2>
            <% EstadoSesion sesion = (EstadoSesion) request.getSession().getAttribute("estado_sesion"); 
            if (sesion == EstadoSesion.BAD_LOGIN) { 
                String mensajeError = (String) request.getSession().getAttribute("error_sesion");
            %>
            <p style = "color: red;"><%= mensajeError %></p>
            <% } %>
            <form method="post">
                <input type="text" name="nickname" placeholder="Usuario" required>
                <input type="password" name="contrasena" placeholder="Contraseña" required>
                <button type="submit">Entrar</button>
            </form>
        </div>
    </div>
</body>
</html>
