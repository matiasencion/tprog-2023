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

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.model.DTOferta;
import com.trabajouy.model.Oferta;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;
import com.trabajouy.model.IContUsuario;


@WebServlet("/consultaOferta")
public class ConsultaOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ConsultaOferta() {
        super();
    }

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
		Fabrica fabrica = Fabrica.getInstancia();
		IContOferta ContO = fabrica.getIContOferta();
		IContUsuario ContU = fabrica.getIContUsuario();
		String opcionSeleccionada = req.getParameter("opcion");
		String opcionSeleccionadaK = req.getParameter("opcionK");
		Collection<String> infoOfertas = ContU.getNombreOfertasConfirmadas(opcionSeleccionada);
		Collection<String> infoOfertasK = ContO.getOfertaKeyword(opcionSeleccionadaK);
		Vector<DTOferta> dtOfertas = new Vector<DTOferta>();
		for (String oferta : infoOfertas) {
			Oferta aux = ContO.getOferta(oferta);
			DTOferta dtOferta = new DTOferta(aux);
			dtOfertas.add(dtOferta);
		}
		Vector<DTOferta> dtOfertasK = new Vector<DTOferta>();
		for (String ofertaK : infoOfertasK) {
			Oferta auxK = ContO.getOferta(ofertaK);
			DTOferta dtOfertaK = new DTOferta(auxK);
			dtOfertasK.add(dtOfertaK);
		}
		Collection<DTOferta> ofertas = ContO.infoOfertaConfirmada();
		if ((opcionSeleccionada != null && !opcionSeleccionada.equals("") || (opcionSeleccionadaK != null) && !opcionSeleccionadaK.equals(""))) {
			ofertas.clear();
		}
		Collection<String> nickEmpresas = ContO.listarEmpresas();
		HttpSession session = req.getSession();
		session.setAttribute("ofertas_sesion", ofertas);
		session.setAttribute("infoOferta_sesion", dtOfertas);
		session.setAttribute("infoOfertaK_sesion", dtOfertasK);
		session.setAttribute("nickEmpresas_sesion", nickEmpresas);
		req.getRequestDispatcher("/WEB-INF/home/consultaOferta.jsp").
		forward(req, resp);
	}
}

	