<%@page import="java.net.Authenticator.RequestorType"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtCompraPaquete"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
    <%@ page import="java.util.Objects" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta oferta</title>
    <style>
        <%@include file="/media/styles/styleAltaOferta.css" %>|
    </style>
</head>

<body>
       <jsp:include page="/WEB-INF/template/header.jsp" />

    <div class="main">
        
   <jsp:include page="/WEB-INF/template/leftPanel.jsp" />

       <% 
       EstadoSesion sesion = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
       if(sesion == EstadoSesion.EMPRESA) { %>
        <div class="center-panel">
			<form class="container3" method="post" enctype="multipart/form-data" action="oferta">
    <h1>Alta de Oferta Laboral</h1>

    <div class="input-row">
        <input name="nombreOferta" id="nombre" type="text" placeholder="Nombre" required>
        <input name="descripcion" id="descripcion" type="text" placeholder="Descripción" required>
    </div>

    <div class="input-row">
        <input name="horaI" id="horarioI" type="text" placeholder="Horario Inicio" required>
        <input name="horaF" id="horarioF" type="text" placeholder="Horario Fin" required>
    </div>

    <div class="input-row">
        <input name="sueldo" id="remuneracion" type="number" placeholder="Remuneración" required>
        <input name="fileFieldName" id="imagen" type="file" required>
    </div>

    <div class="input-row">
        <input name="depto" id="departamento" type="text" placeholder="Departamento" required>
        <input name="ciudad" id="ciudad" type="text" placeholder="Ciudad" required>
    </div>

    <div class="input-row">
        <div class="input-row">
        <label>Keywords:</label>
        <div>
        	<input type="checkbox" name="keywords[]" value="tiempo completo"> Tiempo Completo
        	<input type="checkbox" name="keywords[]" value="medio tiempo"> Medio Tiempo
        	<input type="checkbox" name="keywords[]" value="remoto"> Remoto
        	<input type="checkbox" name="keywords[]" value="permanente"> Permanente
        	<input type="checkbox" name="keywords[]" value="computacion"> Computación
        	<input type="checkbox" name="keywords[]" value="administracion"> Administración
        	<input type="checkbox" name="keywords[]" value="logistica"> Logistica
        	<input type="checkbox" name="keywords[]" value="contabilidad"> Contabilidad
        	<input type="checkbox" name="keywords[]" value="temporal"> Temporal
        	<input type="checkbox" name="keywords[]" value="freelance"> Freelance
        </div>
        
    </div>
    </div>

	 <div class="input-row">
   
  
    <label for="pago">Metodo de pago</label>
    <select name="pago" id="pago" required>
        <option selected value="general" >General</option>
        <% 
        List<String> nombresPaquetes = (List<String>) request.getAttribute("nombresPaquetes");
        if(nombresPaquetes != null && !nombresPaquetes.isEmpty()) {
            for(String nombre : nombresPaquetes) {
        %>
                <option value="<%= nombre.toLowerCase() %>"><%= nombre %></option>
        <% 
            }
        }
        %>
    </select>  
   
</div>

    <div class="input-row">
        <label for="tipoPublicacion">Tipo de Publicación</label>
        <select name="nombreTOferta" id="tipoPublicacion" required>
            <option value="" disabled selected hidden></option>
            <option value="premium">Premium</option>
            <option value="destacada">Destacada</option>
            <option value="estandar">Estandar</option>
            <option value="basica">Basica</option>
        </select>
    </div>
    <div class="option-container">
        <input class="botonForm" type="submit" value="Aceptar">
        <a class="botonForm" href="homeEmpresa.html">Cancelar</a>
    </div>
</form>

		</div>
    <% } else if(sesion == EstadoSesion.POSTULANTE) { %>
        <div class="center-panel">
            <h2>No está autorizado</h2>
            <p>Lo sentimos, como postulante no tiene permisos para acceder a esta sección.</p>
        </div>
    <% } else if(sesion == EstadoSesion.VISITANTE || sesion == null) { %>
        <div class="center-panel">
            <h2>Por favor inicie sesión</h2>
            <p>Para acceder a esta sección, necesita iniciar sesión.</p>
        </div>
    <% } %>
    </div>

</body>

<script>

function pedirTipos() {
    var paquete = document.getElementById("pago").value;

    if (paquete != "general") {
    console.log("entraste");
	    // Crear un objeto XMLHttpRequest
	    var xhr = new XMLHttpRequest();
	
	    // Configurar la solicitud
	    xhr.open("POST", "/web/oferta", true);
	    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	    // Definir la función que se ejecutará cuando la solicitud esté completa
	    xhr.onload = function () {
	        if (xhr.status >= 200 && xhr.status < 300) {
	            // Manejar la respuesta del servidor
	            document.getElementById("tipoPublicacion").innerHTML = xhr.responseText;
	            console.log(xhr.responseText);
	        }
	    };
	
	    // Enviar la solicitud con el valor del paquete
	    xhr.send("paquete=" + encodeURIComponent(paquete));
    } else {
    	document.getElementById("tipoPublicacion").innerHTML =`
	    	<option value="" disabled selected hidden></option>
	        <option value="premium">Premium</option>
	        <option value="destacada">Destacada</option>
	        <option value="estandar">Estandar</option>
	        <option value="basica">Basica</option>`;
    }
}

document.getElementById("pago").addEventListener("change", pedirTipos);


</script>

</html>
