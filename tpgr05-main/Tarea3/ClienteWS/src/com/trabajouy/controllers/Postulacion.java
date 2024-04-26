package com.trabajouy.controllers;

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DtOferta;
import servidor.DtPostulacion;
import servidor.OfertaNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;


/**
 * Servlet implementation class Postularse
 */
@WebServlet("/postulacion")
public class Postulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Postulacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unused")
    
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
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws UsuarioNoExisteException, IOException, OfertaNoExisteException, ServletException, OfertaNoExisteException_Exception, UsuarioNoExisteException_Exception {
    	String oferta = ((String) request.getSession().getAttribute("oferta_postulacion")).toLowerCase();/*OFERTA A POSTULARSE, VIENE DE ANTES*/
    	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
    	String postulante = (String) request.getSession().getAttribute("postulante_postulacion");/*info de quien es el postulante viene de antes*/
    	
   	    servidor.PublicadorService service = new servidor.PublicadorService();
   		servidor.Publicador portPublicador = service.getPublicadorPort();
    	request.getSession().setAttribute("info_oferta", portPublicador.getDTOferta(oferta));
    	DtOferta dtoferta = (DtOferta) request.getSession().getAttribute("info_oferta");
    	if (portPublicador.esPostulante(nickname)) {
    		if (!portPublicador.estaPostulado(nickname, oferta)) {
        		request.getRequestDispatcher("/WEB-INF/usuarios/postularse.jsp").
    			forward(request, response);
        		
        		
        	} else {
        		request.getSession().setAttribute("postulacion", (DtPostulacion) portPublicador.getInfoPostulacion(nickname, oferta));
if(esDispositivoMovil(request))
        		{
            		request.getRequestDispatcher("/WEB-INF/mobile/postulacion.jsp").forward(request, response);

        		}
        		else
        		{
            		request.getRequestDispatcher("/WEB-INF/usuarios/postulacion.jsp").forward(request, response);

        		}        	}
    	} else {
    		if (portPublicador.esEmpresa((String) nickname) && portPublicador.getEmpresaOferta(oferta).equals(nickname)) {
    			request.getSession().setAttribute("postulacion",(DtPostulacion) portPublicador.getInfoPostulacion(postulante, oferta));  /*info de quien es el postulante viene de antes*/
    			request.getRequestDispatcher("/WEB-INF/usuarios/postulacion.jsp").forward(request, response);
    		} else {
    			response.sendRedirect(request.getContextPath()+"/home");
    		}
    	}
    
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {
			try {
				processRequest(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UsuarioNoExisteException | IOException | OfertaNoExisteException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String oferta = ((String) request.getSession().getAttribute("oferta_postulacion")).toLowerCase();/*OFERTA A POSTULARSE, VIENE DE ANTES*/
    	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
		String submit = (String) request.getParameter("submit");
	    servidor.PublicadorService service = new servidor.PublicadorService();
   		servidor.Publicador portPublicador = service.getPublicadorPort();

		if(submit != null && submit.equals("Aceptar")) {
			String CV = (String) request.getParameter("cv");
	       
			LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String fechaFormateada = fechaActual.format(formato);
	         
        	String motivacion = (String) request.getParameter("motivacion");
    		try {
				portPublicador.postulacionOfertaLaboral(oferta, nickname, CV, motivacion, fechaFormateada);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				request.getSession().setAttribute("postulacion", (DtPostulacion) portPublicador.getInfoPostulacion(nickname, oferta));
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.getSession().setAttribute("postulante_postulacion", (String) nickname);
    		response.sendRedirect(request.getContextPath()+ "/postulacion");
    		
		}
			
	}

}
