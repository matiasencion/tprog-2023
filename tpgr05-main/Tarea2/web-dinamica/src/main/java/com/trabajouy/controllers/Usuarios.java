package com.trabajouy.controllers;


import com.trabajouy.model.IContUsuario;

import com.trabajouy.model.DTUsuario;
import com.trabajouy.model.Fabrica;


import java.io.IOException;

import java.util.Set;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 * Servlet implementation class Usuarios
 */
@WebServlet ("/usuarios")
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Fabrica fabrica = Fabrica.getInstancia();
		IContUsuario ContU = fabrica.getIContUsuario();
		
	
		Set<DTUsuario> infoPaquetes = ContU.getUsuarios();
		
		
		request.setAttribute("usariosInfo_sesion", infoPaquetes);
		
		 request.getRequestDispatcher("/WEB-INF/usuarios/consultarUsuario.jsp").
			forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String botonPresionado = request.getParameter("buscarUsuario");
		
		HttpSession session = request.getSession();
		
		System.out.println(botonPresionado);
		
		session.setAttribute("consultar_perfil", botonPresionado);
		
		
		System.out.println(session.getAttribute("nickname_sesion"));
	
		response.sendRedirect(request.getContextPath()+"/perfil");
		
		
		
	}

}
