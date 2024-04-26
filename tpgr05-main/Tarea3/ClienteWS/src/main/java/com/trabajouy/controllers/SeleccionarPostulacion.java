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

@WebServlet("/seleccionar-postulacion")
public class SeleccionarPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarPostulacion() {
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, OfertaNoExisteException {
			servidor.PublicadorService service = new servidor.PublicadorService();
			servidor.Publicador portPublicador = service.getPublicadorPort();
			HttpSession session = req.getSession();
			StringArray infoOfertasArray = portPublicador.getNombreOfertasConfirmadasVencidas((String) session.getAttribute("nickname_sesion"));
			List<String>infoOfertas = infoOfertasArray.getItem();
			Vector<DtOferta> dtOfertas = new Vector<DtOferta>();
			for (String oferta : infoOfertas) {
				DtOferta dtOferta = null;
				try {
					dtOferta = portPublicador.getDTOferta(oferta);
				} catch (OfertaNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dtOfertas.add(dtOferta);
			}
			req.setAttribute("ofertas", dtOfertas);
			req.getRequestDispatcher("/WEB-INF/ofertas/seleccionarPostulacion.jsp").
			forward(req, resp);
		}
}
