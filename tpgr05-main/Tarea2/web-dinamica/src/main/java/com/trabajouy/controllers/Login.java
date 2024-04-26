package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.trabajouy.model.IContUsuario;
import com.trabajouy.model.EstadoSesion;
import com.trabajouy.model.Fabrica;



@WebServlet ("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	Fabrica fabrica = Fabrica.getInstancia();
		IContUsuario ContU = fabrica.getIContUsuario();
      String nickname = request.getParameter("nickname").toLowerCase();
      String password = request.getParameter("contraseña");
      HttpSession session = request.getSession();
      
      try {
    	  
		if(ContU.inicioSesion(nickname, password)) {
			if (ContU.esEmpresa(nickname)) {
				session.setAttribute("estado_sesion",EstadoSesion.EMPRESA);
				session.setAttribute("nickname_sesion",ContU.getUsuario(nickname).getNickname());
				session.setAttribute("foto_sesion",ContU.getUsuario(nickname).getFoto());
				response.sendRedirect(request.getContextPath()+"/perfil");
			} 
			
			if (ContU.esPostulante(nickname)) {
				session.setAttribute("estado_sesion",EstadoSesion.POSTULANTE);
				session.setAttribute("nickname_sesion",ContU.getUsuario(nickname).getNickname());
				session.setAttribute("foto_sesion",ContU.getUsuario(nickname).getFoto());
				response.sendRedirect(request.getContextPath()+"/perfil");
			} 
		} else {
			session.setAttribute("estado_sesion",EstadoSesion.BAD_LOGIN);
			request.getRequestDispatcher("/WEB-INF/home/login.jsp").
			forward(request, response);
			
		}
	} catch (Exception e) {
	
		String mensajeError = e.getMessage();
		session.setAttribute("estado_sesion", EstadoSesion.BAD_LOGIN);
		
		session.setAttribute("error_sesion", mensajeError);
		try {
			request.getRequestDispatcher("/WEB-INF/home/login.jsp").
			forward(request, response);
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
      
    }

     
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/home/login.jsp").
		forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
