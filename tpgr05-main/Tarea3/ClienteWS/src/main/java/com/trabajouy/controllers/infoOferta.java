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




@WebServlet("/info-oferta")
public class infoOferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public infoOferta() {
        super();
       
    }

    private boolean esDispositivoMovil(HttpServletRequest request) {
      String agenteUsuario = request.getHeader("User-Agent");
      if (agenteUsuario == null) {
          return false;
      }
      String[] encabezadosMoviles = {
          "Mobile", "Android", "iPhone", "Windows Phone"
      };
      for (String encabezado : encabezadosMoviles) {
          if (agenteUsuario.contains(encabezado)) {
              return true;
          }
      }
      return false;
  }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		servidor.PublicadorService service = new servidor.PublicadorService();
		
		servidor.Publicador portPublicador = service.getPublicadorPort();
		
		
		
		String agregarfav = request.getParameter("agregarfav");
		String quitarfav = request.getParameter("quitarfav");
		if (quitarfav != null && ! quitarfav.isEmpty()) {
			try {
				portPublicador.quitarOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), quitarfav);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (agregarfav != null && ! agregarfav.isEmpty()) {
			try {
				portPublicador.agregarOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), agregarfav);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		HttpSession session = request.getSession();
		
		String finalizar = request.getParameter("oferta-finalizar");
		if (finalizar != null) {
			portPublicador.finalizarOferta(finalizar);
		}
		
    String postulante =  request.getParameter("postulante");

		if(postulante != null && !postulante.isEmpty()) {
	
			session.setAttribute("postulante_postulacion", postulante);
			
			response.sendRedirect(request.getContextPath()+"/postulacion");
		}
		
		if(request.getParameter("oferta")!=null) {
			String oferta =  request.getParameter("oferta");
			portPublicador.incCantVisitas(oferta);
			session.setAttribute("oferta_postulacion", oferta);

			try {
				portPublicador.cargarDatos();
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			servidor.DtOferta infoOferta;
		
			try {
				infoOferta = portPublicador.getDTOferta(oferta);
				request.setAttribute("info_oferta", infoOferta);
			} catch (OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(esDispositivoMovil(request) == true)
      {
          request.getRequestDispatcher("/WEB-INF/mobile/infoOferta.jsp").
          forward(request, response);
      }
      else
      {
      request.getRequestDispatcher("/WEB-INF/ofertas/infoOfertas.jsp").
      forward(request, response);
      }
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
