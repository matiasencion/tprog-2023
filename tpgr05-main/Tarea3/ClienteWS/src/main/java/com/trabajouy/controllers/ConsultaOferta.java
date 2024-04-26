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


@WebServlet("/consultaOferta")
public class ConsultaOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ConsultaOferta() {
        super();
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | OfertaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException, OfertaNoExisteException, OfertaNoExisteException_Exception {
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
		String opcionSeleccionada = req.getParameter("opcion");
		String opcionSeleccionadaK = req.getParameter("opcionK");
		if (opcionSeleccionada == null) {
			opcionSeleccionada = "";
		}
		if (opcionSeleccionadaK == null) {
			opcionSeleccionadaK = "";
		}
		StringArray infoOfertasArray = portPublicador.getNombreOfertasConfirmadas(opcionSeleccionada);
		List<String>infoOfertas = infoOfertasArray.getItem();
		StringArray infoOfertasKArray = portPublicador.getOfertaKeyword(opcionSeleccionadaK);
		List<String>infoOfertasK = infoOfertasKArray.getItem();
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
		Vector<DtOferta> dtOfertasK = new Vector<DtOferta>();
		for (String ofertaK : infoOfertasK) {
			DtOferta dtOfertaK = null;
			try {
				dtOfertaK = portPublicador.getDTOferta(ofertaK);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dtOfertasK.add(dtOfertaK);
		}
		DtOfertaArray ofertasArray = portPublicador.infoOfertaConfirmada();
		List<DtOferta> ofertas = ofertasArray.getItem();
		if ((opcionSeleccionada != null && !opcionSeleccionada.equals("") || (opcionSeleccionadaK != null) && !opcionSeleccionadaK.equals(""))) {
			ofertas.clear();
		}
		StringArray nickEmpresasArray = portPublicador.listarEmpresas();
		List<String> nickEmpresas = nickEmpresasArray.getItem();
		HttpSession session = req.getSession();
		session.setAttribute("ofertas_sesion", ofertas);
		session.setAttribute("infoOferta_sesion", dtOfertas);
		session.setAttribute("infoOfertaK_sesion", dtOfertasK);
		session.setAttribute("nickEmpresas_sesion", nickEmpresas);
		if(esDispositivoMovil(req))
		{
			req.getRequestDispatcher("/WEB-INF/mobile/consultarOferta.jsp").
			forward(req, resp);		
		}
		else {
			req.getRequestDispatcher("/WEB-INF/home/consultaOferta.jsp").
			forward(req, resp);
		}


	}
}

	