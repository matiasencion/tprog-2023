package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.model.DTOferta;

import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;

@WebServlet("/info-oferta")
public class infoOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public infoOferta() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	
			HttpSession session = request.getSession();
			

         	String postulante =  request.getParameter("postulante");
         
    
	
		if(postulante != null && !postulante.isEmpty()) {
	
			session.setAttribute("postulante_postulacion", postulante);
			
			response.sendRedirect(request.getContextPath()+"/postulacion");
		}
		  if(request.getParameter("oferta")!=null) {
			
			
			
			
			String oferta =  request.getParameter("oferta");
			session.setAttribute("oferta_postulacion", oferta);
		
			
		
		Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
	
		

		
		DTOferta infoOferta;
		try {
			infoOferta = ContO.getDTOferta(oferta);
		   request.setAttribute("info_oferta", infoOferta);
		
		} catch (OfertaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/ofertas/infoOfertas.jsp").
		forward(request, response);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
