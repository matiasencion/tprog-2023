package com.trabajouy.controllers;

import jakarta.servlet.ServletException;






import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.java.dev.jaxb.array.StringArray;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;



import com.trabajouy.model.EstadoSesion;
import servidor.DtPublicar;
import servidor.OfertaNoExisteException_Exception;
import servidor.UsuarioNoExisteException_Exception;
import servidor.DtEmpresa;
import servidor.DtPostulante;



@WebServlet("/perfil")
/**
 * Servlet implementation class Perfil
 */
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Perfil() {
        super();
        // TODO Auto-generated constructor stub
    }


	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
    	
    servidor.PublicadorService service = new servidor.PublicadorService();
 		servidor.Publicador portPublicador = service.getPublicadorPort();
 		String follow = req.getParameter("follow");
		String unfollow = req.getParameter("unfollow");
		if (unfollow != null && ! unfollow.isEmpty()) {
			portPublicador.dejarSeguirUsuario((String) req.getSession().getAttribute("nickname_sesion"), unfollow);
		}
		
		if (follow != null && ! follow.isEmpty()) {
			portPublicador.seguirUsuario((String) req.getSession().getAttribute("nickname_sesion"), follow);
		}

    	String nickname = (String) req.getSession().getAttribute("nickname_sesion");
    	if(req.getParameter("usuarioB") != null) {
    		req.getSession().setAttribute("consultar_perfil", req.getParameter("usuarioB"));
    	}
    	if (req.getSession().getAttribute("consultar_perfil") == null) {
    		req.getSession().setAttribute("consultar_perfil", nickname);
    	}
    	String consultar = (String) req.getSession().getAttribute("consultar_perfil");/*NICKNAME DEL USUARIO A CONSULTAR*/
    	try {
			if (portPublicador.esEmpresa(consultar)) {
				DtEmpresa info = portPublicador.getInfoEmpresa(consultar);
				req.getSession().setAttribute("info_perfil", info);
				req.getSession().setAttribute("publicaciones_perfil", new Vector<DtPublicar>());
				req.getSession().setAttribute("publicacionesConfirmadas_perfil", new Vector<DtPublicar>());
				
				StringArray infoArray = portPublicador.getNombresOfertas(consultar);
				List<String> infoList = infoArray.getItem();
				for (String oferta : infoList) {
					try {
						((Vector<DtPublicar>) req.getSession().getAttribute("publicaciones_perfil")).add(portPublicador.getInfoPublicar(oferta));
					} catch (OfertaNoExisteException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				List<String> infoOfertas = portPublicador.getNombreOfertasConfirmadas(consultar).getItem();
				for (String ofertaC : infoOfertas) {
					try {
						((Vector<DtPublicar>) req.getSession().getAttribute("publicacionesConfirmadas_perfil")).add(portPublicador.getInfoPublicar(ofertaC));
					} catch (OfertaNoExisteException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				req.getSession().setAttribute("paquetes_comprados_perfil", portPublicador.getCompraPaquetesEmpresa(consultar));
				req.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").
				forward(req, resp);
			} else {
				
					if (portPublicador.esPostulante(consultar)) {
						DtPostulante info = portPublicador.getInfoPostulante(consultar);
						req.getSession().setAttribute("info_perfil", info);
						req.getSession().setAttribute("postulaciones_perfil", portPublicador.getPostulaciones(consultar));
						req.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").
						forward(req, resp);
					}
			}	
			req.getSession().setAttribute("consultar_perfil", null);/*lo pongo en null asi si le da a ver su perfil en el header le manda su propio perfil*/
   
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			doGet(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
