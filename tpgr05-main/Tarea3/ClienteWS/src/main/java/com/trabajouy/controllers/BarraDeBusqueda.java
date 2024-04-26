package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DtEmpresa;
import servidor.DtEmpresaArray;
import servidor.DtOferta;
import servidor.DtOfertaArray;
import servidor.OfertaNoExisteException_Exception;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class BarraDeBusqueda
 */
@WebServlet("/barraDeBusqueda")
public class BarraDeBusqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarraDeBusqueda() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, OfertaNoExisteException_Exception {
    	servidor.PublicadorService service = new servidor.PublicadorService();
   		servidor.Publicador portPublicador = service.getPublicadorPort();
   		String follow = request.getParameter("follow");
  		String unfollow = request.getParameter("unfollow");
  		if (unfollow != null && ! unfollow.isEmpty()) {
  			portPublicador.dejarSeguirUsuario((String) request.getSession().getAttribute("nickname_sesion"), unfollow);
  		}
  		
  		if (follow != null && ! follow.isEmpty()) {
  			portPublicador.seguirUsuario((String) request.getSession().getAttribute("nickname_sesion"), follow);
  		}
   		String agregarfav = request.getParameter("agregarfav");
  		String quitarfav = request.getParameter("quitarfav");
  		if (quitarfav != null && ! quitarfav.isEmpty()) {
  			portPublicador.quitarOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), quitarfav);
  		}
  		
  		if (agregarfav != null && ! agregarfav.isEmpty()) {
  			portPublicador.agregarOfertaFavorita((String) request.getSession().getAttribute("nickname_sesion"), agregarfav);
  		}
   		String busqueda = (String) request.getParameter("busqueda");
   		List<DtOferta> ofertas;
 			List<DtEmpresa> empresas;
   		if (busqueda != null ) {
   			ofertas = portPublicador.barraDeBusquedaOfertas(busqueda).getItem();
   			empresas = portPublicador.barraDeBusquedaEmpresa(busqueda).getItem();
   		} else {
   			/*CUANDO TE MANDAN BUSCAR SIN NINGUN PARAMETRO*/
   			ofertas = portPublicador.infoOfertaConfirmada().getItem();
   			empresas = portPublicador.getEmpresas().getItem();
   		}
   		List<DtOferta> ofertasOrdenadasPorDefecto = new ArrayList<DtOferta>();
   		for (int i = 1; i <= 4; i++) {
   			for (DtOferta dt : ofertas) {
   				if (dt.getExposicion() == i) {
   					ofertasOrdenadasPorDefecto.add(dt);
   				}
   			}
   		}
   		
   		for (int i = 0; i < empresas.size();i++) {
   			int agregar = i;
   			for(int j = i; j < empresas.size();j++) {
   				LocalDate fechaAgregar = LocalDate.parse(empresas.get(agregar).getFechaUltimaOferta());
   				LocalDate fechaComparar = LocalDate.parse(empresas.get(j).getFechaUltimaOferta());
   				if(fechaAgregar.isBefore(fechaComparar))
   					agregar = j;
   			}
   			DtEmpresa aux = empresas.get(i);
   			empresas.set(i, empresas.get(agregar));
   			empresas.set(agregar, aux);
   		}
   		List<DtEmpresa> empresasOrdenadasPorDefecto = new ArrayList<DtEmpresa>(empresas);
   		
   		request.getSession().setAttribute("ofertas_ordenadas_por_defecto", (List<DtOferta>)ofertasOrdenadasPorDefecto);
   		request.getSession().setAttribute("empresas_ordenadas_por_defecto", (List<DtEmpresa>)empresasOrdenadasPorDefecto);
   		

			
			for (int i = 0; i < ofertas.size();i++) {
   			int agregar = i;
   			for(int j = i; j < ofertas.size();j++) {
   				char letraAgregar = ofertas.get(agregar).getNombre().charAt(0);
   				char letraComparar = ofertas.get(j).getNombre().charAt(0);
   				if (letraComparar < letraAgregar) {
  					agregar = j;
   				}
   			}
   			DtOferta aux = ofertas.get(i);
   			ofertas.set(i, ofertas.get(agregar));
   			ofertas.set(agregar, aux);
   		}
			List<DtOferta> ofertasOrdenadasAZ = ofertas;
   		
			for (int i = 0; i < empresas.size();i++) {
   			int agregar = i;
   			for(int j = i; j < empresas.size();j++) {
   				char letraAgregar = empresas.get(agregar).getNickname().charAt(0);
   				char letraComparar = empresas.get(j).getNickname().charAt(0);
   				if (letraComparar < letraAgregar) {
  					agregar = j;
   				}
   			}
   			DtEmpresa aux = empresas.get(i);
   			empresas.set(i, empresas.get(agregar));
   			empresas.set(agregar, aux);
			}
   		List<DtEmpresa> empresasOrdenadasAZ = empresas;	
   			
   		request.getSession().setAttribute("ofertas_ordenadas_AZ", (List<DtOferta>)ofertasOrdenadasAZ);
   		request.getSession().setAttribute("empresas_ordenadas_AZ", (List<DtEmpresa>)empresasOrdenadasAZ);
   		
   		request.getRequestDispatcher("/WEB-INF/home/barraDeBusqueda.jsp").
			forward(request, response);
   		
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
				processRequest(request, response);
			} catch (ServletException | IOException | OfertaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
