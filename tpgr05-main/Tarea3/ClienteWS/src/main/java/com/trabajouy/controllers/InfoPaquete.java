package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DtPaquete;

import java.io.IOException;






@WebServlet("/info-paquete")
public class InfoPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InfoPaquete() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paquete = request.getParameter("nombre");
		 servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		
		DtPaquete infoPaquete = portPublicador.getDTPaquete(paquete);
		
		request.setAttribute("info_paquete", infoPaquete);
		
		
		
		request.getRequestDispatcher("/WEB-INF/paquete/infoPaquete.jsp").
		forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
