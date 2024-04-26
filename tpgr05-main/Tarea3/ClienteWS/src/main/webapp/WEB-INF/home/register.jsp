<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Objects" %>
    
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Página Web</title>

      <style>
            <%@include file="/media/styles/registerHtml.css" %>
        </style>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;300&display=swap" rel="stylesheet">
</head>

<body>
    <div class="container">
        <img src="media/images/logo2.png" alt="Logo" class="logo">
        <div class="register-form">
            <h2>Registro</h2>
            <form method="post" enctype="multipart/form-data" id="registroForm">
                <div class="input-row">
                    <input type="text" id="nickname" name ="nickname" placeholder="Nickname" required>
                    <label id="label-nickname" for="nickname"><p style="visibility: hidden;">hola</p></label>
                    <input type="email" id="email" name = "email" placeholder="Email" required>
                    <label id="label-email" for="email"><p style="visibility: hidden;">hola</p></label>
                    
                </div>
                <div class="input-row">
               		 <input type="text" id="name" name="name" placeholder="Nombre" required>
                    <input type="text" id="apellido" name="apellido" placeholder="Apellido" required>
                    
                    
                </div>
                <div class="input-row">
                    <input type="password" id="password" name = "password" placeholder="Contraseña" required>
					<input type="password" id="confirm_password" placeholder="Confirmar Contraseña" required>
                    
                </div>
               <div>
               <label for="fileInput">Selecciona una foto de perfil:</label>
				<input type="file" name="fileFieldName" id="fileInput" accept="image/*"/>
               </div>

                <div class="option-container">
                    <label for="type">Soy:</label>
                    <select id="type" name="user_type" required onchange="showFields(this.value)">
                        <option value="" hidden disabled selected>Selecciona una opción</option>
                        <option value="postulante">Postulante</option>
                        <option value="empresa">Empresa</option>
                    </select>
                </div>
                <div id="postulanteFields" class="extra-fields" style="display:none;">
                    <div class="input-row">
                        <input type="date" id = "fechaNacimiento" name = "fechaNacimiento" placeholder="Fecha de Nacimiento" name="birth_date">
                        <input type="text" id = "nacionalidad" name = "nacionalidad" placeholder="Nacionalidad" name="nationality">
              
                    </div>
                </div>

                <div id="empresaFields" class="extra-fields" style="display:none;">
                    <div class="input-row">
                        <input type="url" id = "website" name = "website" placeholder="Sitio Web" name="website">
                        <textarea placeholder="Descripción de la empresa"id="description" name="description"></textarea>
                        
                    </div>
                </div>

                <button id="botonregistrar" type="submit">Registrarse</button>
            </form>
        </div>
    </div>


    <script type="text/javascript">
        function showFields(value) {
            if (value === "postulante") {
                document.getElementById('postulanteFields').style.display = 'block';
                document.getElementById('empresaFields').style.display = 'none';
            } else if (value === "empresa") {
                document.getElementById('empresaFields').style.display = 'block';
                document.getElementById('postulanteFields').style.display = 'none';
            } else {
                document.getElementById('postulanteFields').style.display = 'none';
                document.getElementById('empresaFields').style.display = 'none';
            }
        }
        document.getElementById('registroForm').onsubmit = function() {
            var password = document.getElementById('password').value;
            var confirmPassword = document.getElementById('confirm_password').value;

            if (password !== confirmPassword) {
                alert('Las contraseñas no coinciden.');
                return false;  
            }

            return true;  
        }
        var errorMessage = "<%= Objects.toString(request.getAttribute("errorMessage"), "") %>";
        if (errorMessage) {
        	console.log("holaaa");
            alert(errorMessage);
        }
        
      

        
        function verificarEmail() {
            var email = document.getElementById("email").value;

            // Crear un objeto XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud
            xhr.open("POST", "/web/register", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la función que se ejecutará cuando la solicitud esté completa
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    // Manejar la respuesta del servidor
                	document.getElementById("label-email").innerHTML = xhr.responseText;
                }
            };

            // Enviar la solicitud con el valor del nickname
            xhr.send("email=" + encodeURIComponent(email));
        }
        document.getElementById("email").addEventListener("input", verificarEmail);
        
        function verificarNickname() {
            var nickname = document.getElementById("nickname").value;

            // Crear un objeto XMLHttpRequest
            var xhr = new XMLHttpRequest();

            // Configurar la solicitud
            xhr.open("POST", "/web/register", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            // Definir la función que se ejecutará cuando la solicitud esté completa
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    // Manejar la respuesta del servidor
                    document.getElementById("label-nickname").innerHTML = xhr.responseText;
                }
            };

            // Enviar la solicitud con el valor del nickname
            xhr.send("nickname=" + encodeURIComponent(nickname));
        }
        document.getElementById("nickname").addEventListener("input", verificarNickname);

        
    </script>
</body>