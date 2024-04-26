package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.trabajouy.model.DTPaquete;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;




@WebServlet("/info-paquete")
public class InfoPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InfoPaquete() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
		
		String paquete = request.getParameter("nombre");
		
		
		DTPaquete infoPaquete = ContO.getDTPaquete(paquete);
		
		request.setAttribute("info_paquete", infoPaquete);
		
		
		
		request.getRequestDispatcher("/WEB-INF/paquete/infoPaquete.jsp").
		forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
