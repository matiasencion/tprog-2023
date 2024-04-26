package com.trabajouy.controllers;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import java.io.IOException;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;
import com.trabajouy.exceptions.UsuarioRepetidoException;


@WebServlet("/postulaciones")
public class ConsultarPostulaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ConsultarPostulaciones() {
        super();
       
    }	
    private boolean esDispositivoMovil(HttpServletRequest request) {
        String agenteUsuario = request.getHeader("User-Agent");
        if (agenteUsuario == null) {
            return false;
        }
        String[] encabezadosMoviles = {
            "Mobile", "Android", "iPhone", "Windows Phone"
        };
        for (String encabezado : encabezadosMoviles) {
            if (agenteUsuario.contains(encabezado)) {
                return true;
            }
        }
        return false;
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		if (esDispositivoMovil(request) == false) {
	        response.sendRedirect("home");
	    }
    	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
    	if(nickname == null)
    	{
	        response.sendRedirect("home");

    	}
    	else {
    		try {
        	    request.getSession().setAttribute("postulaciones_perfil", portPublicador.getPostulaciones(nickname));
        		request.getRequestDispatcher("/WEB-INF/mobile/consultarPostulaciones.jsp").forward(request, response);

    		}
    		catch (Exception  e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    	}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
	

}
