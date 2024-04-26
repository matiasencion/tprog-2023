package com.trabajouy.controllers;

import jakarta.servlet.ServletException;





import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;
import com.trabajouy.exceptions.UsuarioRepetidoException;
import com.trabajouy.model.CargaDatos;
import com.trabajouy.model.DTCompraPaquete;
import com.trabajouy.model.DTEmpresa;
import com.trabajouy.model.DTPaquete;
import com.trabajouy.model.DTPostulacion;
import com.trabajouy.model.DTPostulante;
import com.trabajouy.model.DTPublicar;
import com.trabajouy.model.EstadoSesion;
import com.trabajouy.model.Fabrica;
import com.trabajouy.model.IContOferta;
import com.trabajouy.model.IContUsuario;


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

    @SuppressWarnings({ "unchecked"})
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, UsuarioNoExisteException, OfertaNoExisteException, UsuarioRepetidoException {
    	Fabrica fabrica = Fabrica.getInstancia();
    	IContUsuario ContU = fabrica.getIContUsuario();
    	IContOferta ContO = fabrica.getIContOferta();
    	String nickname = (String) req.getSession().getAttribute("nickname_sesion");
    	if (req.getSession().getAttribute("consultar_perfil") == null) {
    		req.getSession().setAttribute("consultar_perfil", nickname);
    	}
    	String consultar = (String) req.getSession().getAttribute("consultar_perfil");/*NICKNAME DEL USUARIO A CONSULTAR*/
    	if (ContU.esEmpresa(consultar)) {
    		DTEmpresa info = ContU.getInfoEmpresa(consultar);
    		req.getSession().setAttribute("info_perfil", info);
    		req.getSession().setAttribute("publicaciones_perfil", new Vector<DTPublicar>());
    		req.getSession().setAttribute("publicacionesConfirmadas_perfil", new Vector<DTPublicar>());
    		for (String oferta : ContU.getNombresOfertas(consultar)) {
    			((Vector<DTPublicar>) req.getSession().getAttribute("publicaciones_perfil")).add(ContO.getInfoPublicar(oferta));
    		}
    		for (String ofertaC : ContU.getNombreOfertasConfirmadas(consultar)) {
    			((Vector<DTPublicar>) req.getSession().getAttribute("publicacionesConfirmadas_perfil")).add(ContO.getInfoPublicar(ofertaC));
    		}
    		req.getSession().setAttribute("paquetes_comprados_perfil", ContO.getCompraPaquetesEmpresa(consultar));
    		req.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").
			forward(req, resp);
    	} else {
    		if (ContU.esPostulante(consultar)) {
    			DTPostulante info = ContU.getInfoPostulante(consultar);
        		req.getSession().setAttribute("info_perfil", info);
        		req.getSession().setAttribute("postulaciones_perfil", ContU.getPostulaciones(consultar));
        		req.getRequestDispatcher("/WEB-INF/usuarios/perfil.jsp").
    			forward(req, resp);
    		}
    	}
    	req.getSession().setAttribute("consultar_perfil", null);/*lo pongo en null asi si le da a ver su perfil en el header le manda su propio perfil*/
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | UsuarioNoExisteException | OfertaNoExisteException
				| UsuarioRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
