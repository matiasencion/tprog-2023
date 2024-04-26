package com.trabajouy.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import java.util.Vector;



import com.trabajouy.exceptions.OfertaRepetidaException;
import com.trabajouy.model.EstadoSesion;

import servidor.DtCompraPaquete;
import servidor.DtCompraPaquete.Disponibles;
import servidor.DtCompraPaquete.Disponibles.Entry;
import servidor.DtCompraPaqueteArray;
import servidor.DtPaquete;
import servidor.DtPaqueteArray;
import servidor.OfertaRepetidaException_Exception;




import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.java.dev.jaxb.array.StringArray;
import jakarta.servlet.annotation.MultipartConfig;

@SuppressWarnings("serial")
@WebServlet("/oferta")
@MultipartConfig(
	    maxFileSize = 20848820L, 
	    maxRequestSize = 418018841L, 
	    fileSizeThreshold = 1048576
)
public class Oferta extends HttpServlet {
	
	servidor.PublicadorService service = new servidor.PublicadorService();
	servidor.Publicador portPublicador = service.getPublicadorPort();
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionNickname = (String) session.getAttribute("nickname_sesion");

        if (sessionNickname == null) {
            response.sendRedirect("/WEB-INF/home/home.jsp");
            return;
        }
        EstadoSesion sesion = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
        if(sesion == EstadoSesion.EMPRESA)
        {
        	DtPaqueteArray paquetesCompradosArray =  (DtPaqueteArray) portPublicador.getPaquetesComprados(sessionNickname); 
        	List<DtPaquete> paquetesComprados = paquetesCompradosArray.getItem();
        	
        	DtCompraPaqueteArray infoPaquetesArray = portPublicador.getCompraPaquetesEmpresa(sessionNickname);
        	List<DtCompraPaquete> infoPaquetes = infoPaquetesArray.getItem();

            List<String> nombresPaquetes = new ArrayList<>();
            for (DtPaquete paquete : paquetesComprados) {
        
            	nombresPaquetes.add(paquete.getNombre());
            }
            System.out.print(nombresPaquetes);

            request.setAttribute("nombresPaquetes", nombresPaquetes);
            request.setAttribute("infoPaquetes", infoPaquetes);
           

  
        
            
            
          
            
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/AltaOferta.jsp");
        dispatcher.forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    servidor.PublicadorService service = new servidor.PublicadorService();
  	servidor.Publicador portPublicador = service.getPublicadorPort();
   	String paqueteElegido = request.getParameter("paquete");
   	PrintWriter out = response.getWriter();
   	if (paqueteElegido != null && !paqueteElegido.equals("general")) {
   		String sessionNickname = (String) request.getSession().getAttribute("nickname_sesion");
     	DtCompraPaqueteArray infoPaquetesArray = portPublicador.getCompraPaquetesEmpresa(sessionNickname);
     	List<DtCompraPaquete> infoPaquetes = infoPaquetesArray.getItem();
     	String enviarTipos = "<option value='' disabled selected hidden></option>";
     		for (DtCompraPaquete clave : infoPaquetes) {
     			if (clave.getPaquete().equals(paqueteElegido)) {
	          Disponibles disponibles =  clave.getDisponibles();
	          for(Entry tipo :  disponibles.getEntry()) {
	          	if (tipo.getValue() > 0) {
	          		enviarTipos = enviarTipos+"<option value="+tipo.getKey()+">"+tipo.getKey()+"</option>";
	          	}
	          }
     			}
     		}
       out.print(enviarTipos);
       out.close();
   	 }
    	
    	
    	String relativePath = "/media/images/";
        String UPLOAD_DIRECTORY = this.getServletContext().getRealPath(relativePath);

        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        HttpSession session = request.getSession();
        String sessionNickname = (String) session.getAttribute("nickname_sesion");

        if (sessionNickname == null) {
            response.sendRedirect("/WEB-INF/home/home.jsp");
            return;
        }

        String nombreTOferta = request.getParameter("nombreTOferta");
        String nombreOferta = request.getParameter("nombreOferta");
        String descripcion = request.getParameter("descripcion");
        String horaI = request.getParameter("horaI");
        String horaF = request.getParameter("horaF");
        float sueldo = Float.parseFloat(request.getParameter("sueldo"));
        String ciudad = request.getParameter("ciudad");
        String depto = request.getParameter("depto");
        //LocalDate fecha = LocalDate.now();
        String[] keywords = request.getParameterValues("keywords[]"); 
        StringArray keywordsArray = new StringArray();
        int iter = 0;
        for(int i = 0; i < keywords.length; i++) {
        	keywordsArray.getItem().add(keywords[iter]);
        	iter++;
        }

        String paquete = request.getParameter("pago");
        String filePath = "";
        String uniqueFileName  = "";
        Part filePart = request.getPart("fileFieldName");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            uniqueFileName = System.currentTimeMillis() + "_" + fileName;
            filePath = Paths.get(UPLOAD_DIRECTORY, uniqueFileName).toString();
            filePart.write(filePath);
        }
        System.out.println(paquete +"aaa");
 
        try {
        	if(paquete.equals("general"))
        	{
        		portPublicador.altaOferta(sessionNickname, nombreTOferta, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad,
                        depto,  "aaaa", keywordsArray, "", uniqueFileName);
        	}
        	else
        	{
        		portPublicador.altaOferta(sessionNickname, nombreTOferta, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad,
                        depto, "aaaa", keywordsArray, paquete, uniqueFileName);
        	}
              response.sendRedirect(request.getContextPath()+"/info-oferta?oferta="+nombreOferta);
        } catch (OfertaRepetidaException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }
}