package com.trabajouy.controllers;

import java.io.IOException;

import java.nio.file.Paths;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import java.util.Vector;



import com.trabajouy.exceptions.OfertaRepetidaException;
import com.trabajouy.model.DTCompraPaquete;
import com.trabajouy.model.DTPaquete;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;

import com.trabajouy.model.EstadoSesion;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import jakarta.servlet.annotation.MultipartConfig;

@SuppressWarnings("serial")
@WebServlet("/oferta")
@MultipartConfig(
	    maxFileSize = 20848820L, 
	    maxRequestSize = 418018841L, 
	    fileSizeThreshold = 1048576
)
public class Oferta extends HttpServlet {
    Fabrica fabrica = Fabrica.getInstancia();
    IContOferta ContO = fabrica.getIContOferta();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionNickname = (String) session.getAttribute("nickname_sesion");

        if (sessionNickname == null) {
            response.sendRedirect("errorPage.jsp");
            return;
        }
        EstadoSesion sesion = (EstadoSesion) request.getSession().getAttribute("estado_sesion");
        if(sesion == EstadoSesion.EMPRESA)
        {
        	Vector<DTPaquete> paquetesComprados = (Vector<DTPaquete>) ContO.getPaquetesComprados(sessionNickname); 
        	
        	Map<String, DTCompraPaquete> infoPaquetes = ContO.getCompraPaquetesEmpresa(sessionNickname);
        	

            List<String> nombresPaquetes = new ArrayList<>();
            for (DTPaquete paquete : paquetesComprados) {
        
            	nombresPaquetes.add(paquete.getNombre());
            }
            System.out.print(nombresPaquetes);

            request.setAttribute("nombresPaquetes", nombresPaquetes);
            request.setAttribute("infoPaquetes", infoPaquetes);
           
            
            for (String clave : infoPaquetes.keySet()) {
               
                Set<String> disponibles = infoPaquetes.get(clave).getDisponibles().keySet();

              
                request.setAttribute(clave, disponibles);
            }

  
        
            
            
          
            
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/AltaOferta.jsp");
        dispatcher.forward(request, response);
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String relativePath = "/media/images/";
        String UPLOAD_DIRECTORY = this.getServletContext().getRealPath(relativePath);

        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        HttpSession session = request.getSession();
        String sessionNickname = (String) session.getAttribute("nickname_sesion");

        if (sessionNickname == null) {
            response.sendRedirect("errorPage.jsp");
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
        LocalDate fecha = LocalDate.now();
        String[] keywordArray = request.getParameterValues("keywords[]");
        Set<String> keywords = keywordArray != null ? new HashSet<>(Arrays.asList(keywordArray)) : new HashSet<>();        
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
        		ContO.altaOferta(sessionNickname, nombreTOferta, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad,
                        depto, fecha, keywords, "", uniqueFileName);
        	}
        	else
        	{
        		ContO.altaOferta(sessionNickname, nombreTOferta, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad,
                        depto, fecha, keywords, paquete, uniqueFileName);
        	}
              response.sendRedirect(request.getContextPath()+"/info-oferta?oferta="+nombreOferta);
        } catch (OfertaRepetidaException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/AltaOferta.jsp").forward(request, response);
        }
    }
}