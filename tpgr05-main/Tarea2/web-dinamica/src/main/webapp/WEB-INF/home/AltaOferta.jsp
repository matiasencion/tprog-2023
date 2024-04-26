<%@page import="java.net.Authenticator.RequestorType"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="com.trabajouy.model.DTCompraPaquete"%>
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
        	<input type="checkbox" name="keywords[]" value="logica"> Lógica
        	<input type="checkbox" name="keywords[]" value="contabilidad"> Contabilidad
        </div>
        
    </div>
    </div>

<script>
function actualizarTiposOferta() {
    var metodoPago = document.getElementById("pago").value;
    var tiposOfertaSelect = document.getElementById("tipoPublicacion");

 <% 
 Map<String, DTCompraPaquete> info = (Map<String, DTCompraPaquete>) request.getAttribute("infoPaquetes");
     
  
 
     
   
 %>
 var infoJs = <%=info%>
 var infoDTCompra = infoJs[metodoPago]
 console.log(infoDTCompra);
     

       tiposOfertaSelect.innerHTML = "";

        for (var i = 0; i < infoDTCompra.length; i++) {
            var opcion = document.createElement("option");
            opcion.value = infoDTCompra[i].toLowerCase();
            opcion.text = infoDTCompra[i];
            tiposOfertaSelect.appendChild(opcion);
        }
}
    
}
</script>

	 <div class="input-row">
   
  
    <label for="pago">Método de pago</label>
    <select name="pago" id="pago" required onchange="actualizarTiposOferta()">
        <option selected value="general" >General</option>
        <% 
        List<String> nombresPaquetes = (List<String>) request.getAttribute("nombresPaquetes");
        if(nombresPaquetes != null && !nombresPaquetes.isEmpty()) {
            for(String nombre : nombresPaquetes) {
        %>
                <option value="<%= nombre.toLowerCase() %>"><%= nombre %></option>
        <% 
            }
        } else {
        %>
            
        <% 
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
            <option value="estandar">Estándar</option>
            <option value="basica">Básica</option>
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



</html>
