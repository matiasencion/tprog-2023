package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.DtCompraPaqueteArray;
import servidor.DtPaqueteArray;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;




/**
 * Servlet implementation class CompraPaquete
 */
@WebServlet("/compra-paquete")
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompraPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	;
	servidor.PublicadorService service = new servidor.PublicadorService();
	servidor.Publicador portPublicador = service.getPublicadorPort();
		DtPaqueteArray comprarPaquetes = portPublicador.infoPaquetesNoComprados((String) request.getSession().getAttribute("nickname_sesion"));
		HttpSession session = request.getSession();
		session.setAttribute("comprar_paquetes", comprarPaquetes);
		request.getRequestDispatcher("/WEB-INF/paquete/compraPaquete.jsp").
			forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String paquetecomprado = request.getParameter("nombre-paquete-comprado");
		System.out.println(paquetecomprado);
		String nickname = (String) request.getSession().getAttribute("nickname_sesion");
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		try {
			portPublicador.compraPaquete(nickname, paquetecomprado, LocalDate.now().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doGet( request,response);
	}

}
