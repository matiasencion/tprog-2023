package com.trabajouy.controllers;

import jakarta.servlet.ServletException;




import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servidor.DtOferta;
import servidor.OfertaNoExisteException_Exception;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.trabajouy.model.EstadoSesion;







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
    public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}
    
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, OfertaNoExisteException_Exception {
		
		
		
		servidor.PublicadorService service = new servidor.PublicadorService();
		
		servidor.Publicador portPublicador = service.getPublicadorPort();
		
		String agregarfav = req.getParameter("agregarfav");
		String quitarfav = req.getParameter("quitarfav");
		if (quitarfav != null && ! quitarfav.isEmpty()) {
			portPublicador.quitarOfertaFavorita((String) req.getSession().getAttribute("nickname_sesion"), quitarfav);
		}
		
		if (agregarfav != null && ! agregarfav.isEmpty()) {
			portPublicador.agregarOfertaFavorita((String) req.getSession().getAttribute("nickname_sesion"), agregarfav);
		}
		
		servidor.DtOfertaArray infoOfertas =  portPublicador.infoOfertaConfirmada();
		
		HttpSession session = req.getSession();
		session.setAttribute("infoOferta_sesion", infoOfertas);
		
		initSession(req);
		List<String> keywords_left = portPublicador.listarKeywords().getItem();
		req.getSession().setAttribute("keywords_leftpanel", keywords_left);
		switch (getEstado(req)) {
            case VISITANTE:
                if (esDispositivoMovil(req)) {
                	resp.sendRedirect("login");
                } else {
                    req.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(req, resp);
                }
                break;
            case BAD_LOGIN:
                session.setAttribute("estado_sesion", EstadoSesion.VISITANTE);
                if (esDispositivoMovil(req)) {
                	resp.sendRedirect("login");
                } else {
                    req.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(req, resp);
                }
                break;
            case POSTULANTE:
                if (esDispositivoMovil(req)) {
                    req.getRequestDispatcher("/WEB-INF/mobile/home.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(req, resp);
                }
                break;
            case EMPRESA:
                
                    req.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(req, resp);
        }
		
	}


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     
		HttpSession session = request.getSession();
	        
		String botonPresionado = request.getParameter("botonPresionado");

	    if (botonPresionado != null && botonPresionado.equals("true")) {

	    	servidor.PublicadorService service = new servidor.PublicadorService();
			
			servidor.Publicador portPublicador = service.getPublicadorPort();
			
			try {
				portPublicador.cargarDatos();
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	   
	    	
	 

			request.getSession().setAttribute("keywords_leftpanel", portPublicador.listarKeywords().getItem());
			
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
	    	 response.sendRedirect("home");
				
		}
	}	
}
	    
	       


