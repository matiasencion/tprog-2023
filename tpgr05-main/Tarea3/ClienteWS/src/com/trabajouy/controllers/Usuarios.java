package com.trabajouy.controllers;




import java.io.IOException;
import java.util.List;
import java.util.Set;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.DtUsuario;
import servidor.DtUsuarioArray;


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
		
	
		
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		DtUsuarioArray  infoPaquetesArray = portPublicador.getUsuarios();
		List<DtUsuario> infoPaquetes = infoPaquetesArray.getItem();
		
		request.setAttribute("usariosInfo_sesion", infoPaquetes);
		
		 request.getRequestDispatcher("/WEB-INF/usuarios/consultarUsuario.jsp").
			forward(request, response);
			
	
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuarioB");
		
		if(usuario != null){
			
			HttpSession session = request.getSession();
			session.setAttribute("consultar_perfil", usuario);
			response.sendRedirect(request.getContextPath()+"/perfil");
		}else {
		
		processRequest(request, response);
		}
		
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
