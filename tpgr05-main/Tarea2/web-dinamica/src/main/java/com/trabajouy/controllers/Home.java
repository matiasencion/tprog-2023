package com.trabajouy.controllers;

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;



import com.trabajouy.model.EstadoSesion;

import com.trabajouy.model.DTOferta;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;
import com.trabajouy.model.IContUsuario;
import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.model.CargaDatos;




/**
 * implementation class Home
 **/
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

   

	
	public static void initSession(HttpServletRequest request) {
		
		
		HttpSession session = request.getSession();
	
		if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion", EstadoSesion.VISITANTE);
		}
	}
    
    public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}
    
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
		
		Collection<DTOferta> infoOfertas = ContO.infoOfertaConfirmada();
		HttpSession session = req.getSession();
		session.setAttribute("infoOferta_sesion", infoOfertas);
		
		initSession(req);
		req.getSession().setAttribute("keywords_leftpanel", (Vector<String>) ContO.listarKeywords());
		switch(getEstado(req)){
			case VISITANTE:
				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
						forward(req, resp);
				break;
			case BAD_LOGIN:
				session.setAttribute("estado_sesion", EstadoSesion.VISITANTE);
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
						forward(req, resp);
				break;
			case POSTULANTE:
		
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
				forward(req, resp);
				break;
			case EMPRESA:
				
				req.getRequestDispatcher("/WEB-INF/home/home.jsp").
				forward(req, resp);
		}
		
	}


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		HttpSession session = request.getSession();
	        
		String botonPresionado = request.getParameter("botonPresionado");

	    if (botonPresionado != null && botonPresionado.equals("true")) {

	    	Fabrica fabrica = Fabrica.getInstancia();
	    	IContOferta ContO = fabrica.getIContOferta();
	    	IContUsuario contU = fabrica.getIContUsuario();
	    	
	    	CargaDatos cargaDatos = new CargaDatos(contU,ContO);
	    	
	    	cargaDatos.cargarUsuarios();
	    	cargaDatos.cargarTipoPublicacion();
	    	cargaDatos.cargarKeywords();
	    	cargaDatos.cargarPaquetes();
	    	cargaDatos.cargarOfertas();
	    	try {
				cargaDatos.cargarPostulaciones();
			} catch (OfertaNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};

			request.getSession().setAttribute("keywords_leftpanel", ContO.listarKeywords());
			
	        request.setAttribute("botonPresionado", "false");
	      
	        String referer = request.getHeader("Referer");
	        if (referer != null) {
	            response.sendRedirect(referer);
	        } else {
	       
	            response.sendRedirect(request.getContextPath() + "/home");
	        }
	    }
	    else {
	    	
	    	 session.setAttribute("estado_sesion", EstadoSesion.VISITANTE);
	    	 session.setAttribute("nickname_sesion", "");
	    	 request.getRequestDispatcher("/WEB-INF/home/home.jsp").
				forward(request, response);
		}
	}	
}
	    
	       


