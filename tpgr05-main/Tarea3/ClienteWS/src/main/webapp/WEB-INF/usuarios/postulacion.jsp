<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.trabajouy.model.EstadoSesion"%>
    <%@page import="servidor.DtUsuario"%>
    <%@page import="servidor.DtOferta"%>
    <%@page import="servidor.DtPostulacion"%>
    <%@page import="java.util.Set"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Postulacion</title>
    <style>
        <%@include file="/media/styles/infoOfertaFrontend.css" %>
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/template/header.jsp" />
    <main id="frontend-visitante">
    <jsp:include page="/WEB-INF/template/leftPanel.jsp" />
        <div class="content-section">
            <div class="main-content">
                <div class="job-card">
                    <div class="info-oferta">
                        <img class="oferta-image" src="/web/media/images/<%= (String)((DtOferta) request.getSession().getAttribute("info_oferta")).getUrlFoto() %>" alt="foto trabajo">
                        <h2><%= ((DtOferta)request.getSession().getAttribute("info_oferta")).getNombre() %></h2>
                        <ul>
                            <li class="dflex postulacionicons">
                                <div class="icon-container">
                             	<img class="icon" src="/web/media/images/id-card.png">
                            
                                <h3>Postulante</h3>
                             </div>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getPostulante() %></p>
                            </li>
                            <li class="dflex">
                                <div class="icon-container">
                             	<img class="icon" src="/web/media/images/cv.png">
                            
                                <h3>CV reducido</h3>
                             </div>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getCurriculum() %></p>
                            </li>
                            <li class="dflex">
                               <div class="icon-container">
                             	<img class="icon" src="/web/media/images/achivement.png">
                            
                                <h3>Motivacion</h3>
                             </div>
                                    <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getDescripcion() %></p>
                            </li>

                            <li class="dflex">
                            <div class="icon-container">
                             	<img class="icon" src="/web/media/images/calendar.png">
                            
                                <h3>Fecha postulacion</h3>
                             </div>
                                
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getFechaString() %></p>
                            </li>
                             <li class="dflex">
                            <div class="icon-container">
                             	<img class="icon" src="/web/media/images/orden.png">
                            
                                <h3>Orden seleccionado</h3>
                             </div>
                                <% if (((DtPostulacion)request.getSession().getAttribute("postulacion")).getOrden() != -1) {%>
                               <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getOrden() %></p>
                                <p><%= ((DtPostulacion)request.getSession().getAttribute("postulacion")).getFechaOrdenString() %></p>
                                
                                
                                
                              <%	String postulante = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getPostulante();
                                	String empresa = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getEmpresa();
                                	String oferta = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getOferta();
                                	int orden = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getOrden();
                                	String fecha = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getFechaString();
                                	String fechaOrden = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getFechaOrdenString();
                                	%>
                                <form action="generarPDF" method="get">
                                	<input type="hidden" name="postulante" value="<%= postulante %>">
							        <input type="hidden" name="empresa" value="<%= empresa %>">
							        <input type="hidden" name="oferta" value="<%= oferta %>">
							        <input type="hidden" name="orden" value="<%= orden %>">
							        <input type="hidden" name="fecha" value="<%= fecha %>">
							        <input type="hidden" name="fechaOrden" value="<%= fechaOrden %>">
							        <button type="submit">Descargar PDF</button>
							    </form>
                           <%} else { %>
                           		<p>Pendiente</p>
                           <%} %>
                            </li>
                            <%  String nombrePostulante = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getPostulante(); 
                            if(!((DtPostulacion)request.getSession().getAttribute("postulacion")).getUrlVideo().isEmpty()){ 
                                 String url = ((DtPostulacion)request.getSession().getAttribute("postulacion")).getUrlVideo();
                                 %>
                            <li>
                               <iframe width="800" height="600" src="<%=url%>" title="<%=nombrePostulante%>" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                            
                            </li>
                            <%} %>
                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </main>
</body>
</html>
