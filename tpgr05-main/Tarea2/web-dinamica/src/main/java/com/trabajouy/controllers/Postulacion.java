package com.trabajouy.controllers;

import jakarta.servlet.ServletException;



import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;

import com.trabajouy.model.DTOferta;
import com.trabajouy.model.DTPostulacion;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;
import com.trabajouy.model.IContUsuario;

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
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws UsuarioNoExisteException, IOException, OfertaNoExisteException, ServletException {
    	String oferta = ((String) request.getSession().getAttribute("oferta_postulacion")).toLowerCase();/*OFERTA A POSTULARSE, VIENE DE ANTES*/
    	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
    	String postulante = (String) request.getSession().getAttribute("postulante_postulacion");/*info de quien es el postulante viene de antes*/
    	IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
    	IContOferta ContO = Fabrica.getInstancia().getIContOferta();
    	request.getSession().setAttribute("info_oferta", ContO.getDTOferta(oferta));
    	DTOferta dtoferta = (DTOferta) request.getSession().getAttribute("info_oferta");
    	if (ContU.esPostulante(nickname)) {
    		if (!ContU.estaPostulado(nickname, oferta)) {
        		request.getRequestDispatcher("/WEB-INF/usuarios/postularse.jsp").
    			forward(request, response);
        		
        		
        	} else {
        		request.getSession().setAttribute("postulacion", (DTPostulacion) ContU.getInfoPostulacion(nickname, oferta));
        		request.getRequestDispatcher("/WEB-INF/usuarios/postulacion.jsp").forward(request, response);
        	}
    	} else {
    		if (ContU.esEmpresa((String) nickname) && ContO.getEmpresaOferta(oferta).equals(nickname)) {
    			request.getSession().setAttribute("postulacion",(DTPostulacion) ContU.getInfoPostulacion(postulante, oferta));/*info de quien es el postulante viene de antes*/
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
			processRequest(request, response);
		} catch (UsuarioNoExisteException | IOException | OfertaNoExisteException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
    	String oferta = ((String) request.getSession().getAttribute("oferta_postulacion")).toLowerCase();/*OFERTA A POSTULARSE, VIENE DE ANTES*/
    	String nickname = (String) request.getSession().getAttribute("nickname_sesion");
		String submit = (String) request.getParameter("submit");
    	IContOferta ContO = Fabrica.getInstancia().getIContOferta();

		if(submit != null && submit.equals("Aceptar")) {
			String CV = (String) request.getParameter("cv");
	       
			LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        String fechaFormateada = fechaActual.format(formato);
	         
        	String motivacion = (String) request.getParameter("motivacion");
    		try {
				ContO.PostulacionOfertaLaboral(oferta, nickname, CV, motivacion, fechaFormateada);
			} catch (UsuarioNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OfertaNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		try {
				request.getSession().setAttribute("postulacion", (DTPostulacion) ContU.getInfoPostulacion(nickname, oferta));
			} catch (UsuarioNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		request.getSession().setAttribute("postulante_postulacion", (String) nickname);
    		response.sendRedirect(request.getContextPath()+ "/postulacion");
    		
		}
			
	}

}
