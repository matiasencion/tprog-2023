package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.java.dev.jaxb.array.StringArray;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import com.trabajouy.exceptions.OfertaNoExisteException;
import servidor.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.trabajouy.exceptions.OfertaNoExisteException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rankear-postulacion")
public class RankearPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankearPostulacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | OfertaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
		HttpSession session = req.getSession();
		String oferta = (String)req.getParameter("oferta");
		servidor.DtOferta infoOferta = null;
		try {
			infoOferta = portPublicador.getDTOferta(oferta);
		} catch (OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<DtPostulacion> postulantes = infoOferta.getPostulaciones();
		int cant = portPublicador.cantPostulantes(oferta);
		StringArray orden = new StringArray();
		StringArray nombres = new StringArray();
		for (DtPostulacion postulante : postulantes) {
			String num = req.getParameter(postulante.getPostulante());
			if (num != null && !num.isEmpty()) {
                orden.getItem().add(num) ;
                nombres.getItem().add(postulante.getPostulante());
            }
		}
		
		portPublicador.seleccionarPostulacion((String) oferta, orden, nombres);
		req.getRequestDispatcher("/WEB-INF/home/home.jsp").
		forward(req, resp);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, OfertaNoExisteException {
			servidor.PublicadorService service = new servidor.PublicadorService();
			servidor.Publicador portPublicador = service.getPublicadorPort();
			HttpSession session = req.getSession();
			
			String oferta =  (String)req.getParameter("oferta");
			session.setAttribute("oferta_postulacion", oferta);
			servidor.DtOferta infoOferta;
			try {
				infoOferta = portPublicador.getDTOferta(oferta);
				req.setAttribute("info_oferta", infoOferta);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int cantPostulantes = portPublicador.cantPostulantes(oferta);
			req.setAttribute("cant_postulantes", cantPostulantes);
			req.getRequestDispatcher("/WEB-INF/ofertas/rankearPostulacion.jsp").
			forward(req, resp);
		}

}
