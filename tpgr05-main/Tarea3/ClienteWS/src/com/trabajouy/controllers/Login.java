package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import com.trabajouy.model.EstadoSesion;



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
    private boolean esDispositivoMovil(HttpServletRequest request) {
      String agenteUsuario = request.getHeader("User-Agent");
      if (agenteUsuario == null) {
          return false;
      }
      String[] encabezadosMoviles = { "Mobile", "Android", "iPhone", "Windows Phone" };
      for (String encabezado : encabezadosMoviles) {
          if (agenteUsuario.contains(encabezado)) {
              return true;
          }
      }
      return false;
  }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	
	    servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		

      String nickname = request.getParameter("nickname").toLowerCase();
      String password = request.getParameter("contraseña");
      HttpSession session = request.getSession();
      
      try {
    	  
		if(portPublicador.inicioSesion(nickname, password)) {
			if (portPublicador.esEmpresa(nickname)) {
                    if (esDispositivoMovil(request)) {
                        session.setAttribute("error_sesion", "Lo siento, no puedes iniciar sesión como empresa desde un dispositivo móvil.");
                        session.setAttribute("estado_sesion", EstadoSesion.BAD_LOGIN);

                        request.getRequestDispatcher("/WEB-INF/home/login.jsp").forward(request, response);
                        return; 
                    }
                    session.setAttribute("estado_sesion", EstadoSesion.EMPRESA);
                    session.setAttribute("nickname_sesion", portPublicador.getUsuario(nickname).getNickname());
                    session.setAttribute("foto_sesion", portPublicador.getUsuario(nickname).getFoto());
                    response.sendRedirect(request.getContextPath() + "/perfil");
                }
			
			if (portPublicador.esPostulante(nickname)) {
				session.setAttribute("estado_sesion",EstadoSesion.POSTULANTE);
				session.setAttribute("nickname_sesion",portPublicador.getUsuario(nickname).getNickname());
				session.setAttribute("foto_sesion",portPublicador.getUsuario(nickname).getFoto());
				session.setAttribute("Nombre_Bienvenida", portPublicador.getUsuario(nickname).getNombre());
				if (esDispositivoMovil(request))
                    {
                        response.sendRedirect(request.getContextPath() + "/home");


                    }
                    else {
                        response.sendRedirect(request.getContextPath() + "/perfil");

                    }
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
