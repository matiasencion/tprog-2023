package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.java.dev.jaxb.array.StringArray;
import servidor.DtOferta;
import servidor.OfertaNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;

import java.io.IOException;

import java.util.List;
import java.util.Vector;





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
			throws ServletException, IOException, OfertaNoExisteException_Exception, UsuarioNoExisteException_Exception {
    	servidor.PublicadorService service = new servidor.PublicadorService();
    	servidor.Publicador portPublicador = service.getPublicadorPort();
    	String nickname = (String) req.getSession().getAttribute("nickname_sesion");
    	 StringArray nickEmpresasA = portPublicador.listarEmpresas();
    	 List<String> nickEmpresas = null;
    	 if(nickEmpresasA!=null) {
    		nickEmpresas = nickEmpresasA.getItem(); 
    	 }
    	 
    	 
     

		
	servidor.DtOfertaArray ofertasA = portPublicador.getOfertasSinPostular(nickname);
		 
	  
     List<DtOferta> ofertas = ofertasA.getItem();
    	String opcionSeleccionada = req.getParameter("opcion");
		String opcionSeleccionadaK = req.getParameter("opcionK");
		Vector<DtOferta> ofertasEmpresa = new Vector<>();
		Vector<DtOferta> ofertasKeywords = new Vector<>();
		for (DtOferta oferta : ofertas) {
			if (oferta.getEmpresa().equals(opcionSeleccionada)) {
				ofertasEmpresa.add(oferta);
			}
		}
		for (DtOferta oferta : ofertas) {
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
		} catch (ServletException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfertaNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException_Exception e) {
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
