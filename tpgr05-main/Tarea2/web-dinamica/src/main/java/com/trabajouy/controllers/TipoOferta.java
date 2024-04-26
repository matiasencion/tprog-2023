package com.trabajouy.controllers;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collection;

import com.trabajouy.model.DTTipoOferta;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;

/**
 * Servlet implementation class TipoOferta
 */
@WebServlet("/tipo-oferta")
public class TipoOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TipoOferta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
		Collection<DTTipoOferta> infoTOferta = ContO.infoTOferta();
		HttpSession session = request.getSession();
		
		session.setAttribute("tipos_oferta", infoTOferta);
		
		 request.getRequestDispatcher("/WEB-INF/ofertas/consultaTiposOfertas.jsp").
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
