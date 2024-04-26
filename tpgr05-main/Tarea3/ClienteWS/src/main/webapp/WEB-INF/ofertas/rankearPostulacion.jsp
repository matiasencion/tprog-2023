<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtOferta"%>
<%@page import="servidor.DtPostulacion"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rankear Postulacion</title>
<jsp:include page="/WEB-INF/template/header.jsp" />
 
    		<style>
            <%@include file="/media/styles/infoOfertaFrontend.css" %>
        	</style>
	   
           
       		
<body>
    <main class="main-content">
                        <% 
                     	servidor.PublicadorService service = new servidor.PublicadorService();
                     	servidor.Publicador portPublicador = service.getPublicadorPort();
                        DtOferta info = (DtOferta) request.getAttribute("info_oferta");
                        %>
    
     <jsp:include page="/WEB-INF/template/leftPanel.jsp" />	
     <%
                            List<DtPostulacion> postulantes =  info.getPostulaciones();
                            boolean esPostulante=false;
                            String post ="";
                            String nickSesion = (String) request.getSession().getAttribute("nickname_sesion");
                            for (DtPostulacion postulante : postulantes) {
                            	if(postulante.getPostulante().equals(nickSesion) ){
                            		post = postulante.getPostulante();
                            		esPostulante = true;
                            	}
                            	
                            }%>
        <div class="content-section">
            <div class="main-content">
                <div class="job-card">
                    <div class="info-oferta">
    <div class="image-container">
        <img src="/web/media/images/<%= info.getUrlFoto() %>" alt="Imagen de la oferta" class="oferta-image"/>
    </div>
    <h2 class="nombre-oferta"><%=info.getNombre() %></h2>
                        <ul>
                          <li class="dflex">
                          <div class="icon-container">
                          <img class="icon" src="/web/media/images/enterprise.png">
                                <h3>Empresa </h3>
                          </div>
                          		
                                <p><%=info.getEmpresa() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            <img class="icon" src="/web/media/images/id-card.png">
                                                                                                                                                         		

                            
                                <h3>Nombre </h3>
                            </div >
                                 
                                <p><%=info.getNombre() %></p>
                            </li>
                            <li class="dflex">
                             <div class="icon-container">
                             	<img class="icon" src="/web/media/images/description.png">
                            
                                <h3>Descripcion </h3>
                             </div>
                                   
                                <p><%=info.getDescripcion() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/clock.png">
                            
                                <h3>Horario </h3>
                            </div>
                                                      		
                                <p><%=info.getHoraInicio()+"-"+info.getHoraFin() %></p>
                            </li>

                            <li class="dflex">
                            <div class="icon-container">
                            <img class="icon" src="/web/media/images/location.png">
                            
                                <h3>Departamento </h3>
                            </div>
                                                      		
                                <p><%=info.getDepartamento() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/cityscape.png">
                            
                                <h3>Ciudad </h3>
                            </div >
                                                      		
                                <p><%=info.getCiudad() %></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/hand.png">
                            
                                <h3>Remuneracion </h3>
                            </div >
                                                      		
                                <p><%=info.getSueldo()%></p>
                            </li>
                            <li class="dflex">
                            <div class="icon-container">
                            	<img class="icon" src="/web/media/images/calendar.png">
                            
                                <h3>Fecha de alta </h3>
                            </div >
                                                      		
                                <p><%=info.getFechaAprobado() %></p>
                            </li>
                            <li class="dflex">
                            	<div class="icon-container">
                            		<img class="icon" src="/web/media/images/keyword.png">
                            
                                <h3>Keywords </h3>
                                <%
                                String keywords = "";
                                for (String keyw : info.getKeywords()) {
                                	keywords = keywords + keyw +", ";
                                }%>
                                
                            	</div>
                                                               
                                <p><%=keywords %></p>
                            </li>
                        </ul>


                    </div>

                   <% 
      			       EstadoSesion estado = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
                   String nick =(String) request.getSession().getAttribute("nickname_sesion");
			       if(estado == EstadoSesion.EMPRESA && info.getEmpresa().equals(nick)){ %>
                
                <% 
			      
                if(postulantes != null && !postulantes.isEmpty()){
			   
			     %>
		         <div class="info-oferta">
					 <h3>Postulantes</h3>
					 <form class="rank-postulantes" action="rankear-postulacion?oferta=<%=info.getNombre()%>" method="POST" onsubmit="return validarFormulario()">
					  <% for (DtPostulacion postulante : postulantes) { %>
					    <div style="margin-left:5px;" class="applicant">
					      <a style="display: flex; justify-content: center; align-items: center;" href="info-oferta?postulante=<%=postulante.getPostulante() %>">
					        <img src="/web/media/images/<%=postulante.getFotoPostulante()%>" alt="<%=postulante.getPostulante()%>">
					        <p style="color: black;"><%=postulante.getPostulante()%></p>
					      </a>
					      <input type="number" min="1" max="<%=postulantes.size() %>" name="<%=postulante.getPostulante()%>" required>
					    </div>
					  <% } %>
					
					  <div style="margin-right: 5px;" class="menu-bar">
					    <input type="submit" style="width: 80px;" class="menu-button active" id="accept-button" value="Enviar">
					    <a href="/web/seleccionar-postulacion" style="color: black;" class="menu-button" id="cancel-button">Cancelar</a>
					  </div>
					</form>
				</div>	
				<%}
				}%>

				</div>
            </div>
        </div>
    </main>
</body>

<script>
  function validarFormulario() {
    var inputs = document.getElementsByTagName('input');
    var values = [];

    for (var i = 0; i < inputs.length; i++) {
      if (inputs[i].type === 'number') {
        values.push(inputs[i].value);
      }
    }

    // Verificar si hay valores duplicados
    if (new Set(values).size !== values.length) {
      alert('Los valores seleccionados deben ser distintos.');
      return false; // Evitar que se envíe el formulario
    }

    return true; // Permitir que se envíe el formulario
  }
</script>
</html>
