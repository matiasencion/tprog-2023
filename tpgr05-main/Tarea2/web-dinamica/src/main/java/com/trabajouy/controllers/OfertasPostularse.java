package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Vector;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;
import com.trabajouy.model.DTOferta;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;
import com.trabajouy.model.IContUsuario;

/**
 * Servlet implementation class OfertasParaPostularse
 */
@WebServlet("/ofertas-postularse")
public class OfertasPostularse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfertasPostularse() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, UsuarioNoExisteException, OfertaNoExisteException {
    	Fabrica fabrica = Fabrica.getInstancia();
    	IContUsuario ContU = fabrica.getIContUsuario();
    	IContOferta ContO = fabrica.getIContOferta();
    	String nickname = (String) req.getSession().getAttribute("nickname_sesion");
    	Collection<String> nickEmpresas = ContO.listarEmpresas();
    	Collection<DTOferta> ofertas = ContU.getOfertasSinPostular(nickname);
    	String opcionSeleccionada = req.getParameter("opcion");
		String opcionSeleccionadaK = req.getParameter("opcionK");
		Vector<DTOferta> ofertasEmpresa = new Vector<>();
		Vector<DTOferta> ofertasKeywords = new Vector<>();
		for (DTOferta oferta : ofertas) {
			if (oferta.getEmpresa().equals(opcionSeleccionada)) {
				ofertasEmpresa.add(oferta);
			}
		}
		for (DTOferta oferta : ofertas) {
			if (oferta.getKeywords().contains(opcionSeleccionadaK) ) {
				ofertasKeywords.add(oferta);
			}
		}
		if ((opcionSeleccionada != null && !opcionSeleccionada.equals("") || (opcionSeleccionadaK != null) && !opcionSeleccionadaK.equals(""))) {
			ofertas.clear();
		}
    	req.getSession().setAttribute("nickEmpresas_sesion", nickEmpresas);
    	req.getSession().setAttribute("ofertas_sin_postularse", ofertas);
    	req.getSession().setAttribute("ofertas_empresa", ofertasEmpresa);
    	req.getSession().setAttribute("ofertas_keywords", ofertasKeywords);
		req.getRequestDispatcher("/WEB-INF/ofertas/ofertasParaPostularse.jsp").forward(req, resp);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | UsuarioNoExisteException | OfertaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String postularse = request.getParameter("postularse");
		if (postularse != null) {
			request.getSession().setAttribute("oferta_postulacion", (String) postularse);
			response.sendRedirect(request.getContextPath()+"/postulacion");
		}
	}

}
