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
import servidor.DtEmpresa;
import servidor.DtUsuario;
import servidor.DtUsuarioArray;
import servidor.OfertaNoExisteException_Exception;


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
		
		String follow = request.getParameter("follow");
		String unfollow = request.getParameter("unfollow");
		if (unfollow != null && ! unfollow.isEmpty()) {
			portPublicador.dejarSeguirUsuario((String) request.getSession().getAttribute("nickname_sesion"), unfollow);
		}
		
		if (follow != null && ! follow.isEmpty()) {
			portPublicador.seguirUsuario((String) request.getSession().getAttribute("nickname_sesion"), follow);
		}
		
		DtUsuarioArray  infoUsuariosArray = portPublicador.getUsuarios();
		List<DtUsuario> infoUsuarios = infoUsuariosArray.getItem();
		
		/*ORDENAR USUARIOS*/
		
		for (int i = 0; i < infoUsuarios.size();i++) {
 			int agregar = i;
 			for(int j = i; j < infoUsuarios.size();j++) {
 				char letraAgregar = infoUsuarios.get(agregar).getNickname().charAt(0);
 				char letraComparar = infoUsuarios.get(j).getNickname().charAt(0);
 				if (letraComparar < letraAgregar) {
					agregar = j;
 				}
 			}
 			DtUsuario aux = infoUsuarios.get(i);
 			infoUsuarios.set(i, infoUsuarios.get(agregar));
 			infoUsuarios.set(agregar, aux);
		}
		
		request.setAttribute("usariosInfo_sesion", infoUsuarios);
		
		 request.getRequestDispatcher("/WEB-INF/usuarios/consultarUsuario.jsp").
			forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
