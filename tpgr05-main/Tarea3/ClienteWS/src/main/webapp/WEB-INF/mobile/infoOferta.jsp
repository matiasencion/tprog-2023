<%@page import="java.util.Vector"%>
<%@page import="com.trabajouy.model.EstadoSesion"%>
<%@page import="servidor.DtOferta"%>
<%@page import="servidor.DtPostulacion"%>
<%@page import="java.util.List"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="/media/styles/styleConsultaOfertas.css">
    <style>
                <%@include file="/media/styles/mobile/infoOferta.css" %>
    
        
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-custom">
        <a class="navbar-brand" href="home">
            <img id="logo" class="logo img-fluid" src="media/images/logo.png" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="consultaOferta">Ver ofertas laborales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Ver postulaciones</a>
                </li>
                <li class="nav-item">
                	<form action="/web/home" method = "post">
            <button class="nav-link">Salir</button>
            	</form>
    
                </li>
            </ul>
        </div>
    </nav>
    <main class="main-content">
                            <% DtOferta info = (DtOferta) request.getAttribute("info_oferta"); %>
    
    <%
                            List<DtPostulacion> postulantes = info.getPostulaciones();
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
                       <img class="imagen-oferta" src="/web/media/images/<%= info.getUrlFoto() %>" alt="Imagen de la oferta" />
                        <h2><%=info.getNombre() %></h2>

                        <ul>
                        <%if(esPostulante){ %>
            <a href="info-oferta?postulante=<%=post %>" class="btn ver-postulacion-btn">Ver Mi Postulacion</a>
        <%} %>
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
                            	</div>
                                                               
                                <p><%=info.getKeywords() %></p>
                             
                            </li>
                        </ul>
                    </div>
                   
                </div>
              
                 
            </div>
<div class="content-extra">
    <%  
    EstadoSesion estado = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
    String nick = (String) request.getSession().getAttribute("nickname_sesion");
    if(estado == EstadoSesion.EMPRESA && info.getEmpresa().equals(nick)){ %>

        <% if(info.getNombrePaquete() != null){ %>
            <div class="applicants-section mb-4">
                <h4>Paquete</h4>
                <a href="info-paquete?nombre=<%=info.getNombrePaquete()%>" class="card">
                    <div class="card-body">
                        <img src="/web/media/images/<%=info.getUrlFotoPaquete()%>" alt="paquete" class="img-fluid">
                        <p class="mt-2"><%=info.getNombrePaquete() %></p>
                    </div>
                </a>
            </div>
        <% } %>

        
        
    <% } %>
</div>

    </main>
	
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   
</body>

</html>
