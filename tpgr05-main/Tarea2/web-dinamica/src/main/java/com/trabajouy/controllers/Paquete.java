package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;


import com.trabajouy.model.DTPaquete;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;

/**
 * Servlet implementation class Paquete
 */
@WebServlet("/consulta-paquete")
public class Paquete extends HttpServlet {
  private static final long serialVersionUID = 1L;
       
  public Paquete() {
        super();

    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
		
	
		Collection<DTPaquete> infoPaquetes = ContO.infoPaquetes();
	
		HttpSession session = request.getSession();
		
		session.setAttribute("paquetesInfo_sesion", infoPaquetes);
		
		 request.getRequestDispatcher("/WEB-INF/paquete/consultaPaquete.jsp").
			forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
