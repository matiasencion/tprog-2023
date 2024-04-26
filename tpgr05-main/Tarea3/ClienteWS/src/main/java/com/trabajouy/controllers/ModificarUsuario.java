package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DtEmpresa;
import servidor.DtPostulante;
import servidor.UsuarioNoExisteException_Exception;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioNoExisteException;
import com.trabajouy.exceptions.UsuarioRepetidoException;

@WebServlet("/modificarUsuario")

public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarUsuario() {
		super();
	}

	@SuppressWarnings({ "unchecked" })
	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, UsuarioNoExisteException, OfertaNoExisteException,
			UsuarioRepetidoException, UsuarioNoExisteException_Exception {

		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();

		String nickname = (String) req.getSession().getAttribute("nickname_sesion");

		if (portPublicador.esEmpresa(nickname)) {
			DtEmpresa info = portPublicador.getInfoEmpresa(nickname);
			req.getSession().setAttribute("info_perfil", info);

		}
		if (portPublicador.esPostulante(nickname)) {
			DtPostulante info = portPublicador.getInfoPostulante(nickname);
			req.getSession().setAttribute("info_perfil", info);
		}

		req.getRequestDispatcher("/WEB-INF/usuarios/ModificarUsuario.jsp").forward(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			try {
				processRequest(request, response);
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException | IOException | UsuarioNoExisteException | OfertaNoExisteException
				| UsuarioRepetidoException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();

		String nickname = (String) request.getSession().getAttribute("nickname_sesion");
		String submit = (String) request.getParameter("submit");

		if (submit != null && submit.equals("Guardar")) {
			// String fecha = (String) request.getParameter("input-date");
			// DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			// String fechaFormateada = fechaActual.format(formato);

			// String newPass = (String) request.getParameter("input-pass");

			try {
				if (portPublicador.esEmpresa(nickname)) {

					String nombre = (String) request.getParameter("input-name-e");
					String apellido = (String) request.getParameter("input-lastname-e");
					String pass1 = (String) request.getParameter("input-new-pass-e");
					String pass2 = (String) request.getParameter("input-rep-pass-e");
					String urlNewPhoto = (String) request.getParameter("");

					if (apellido == null) {
						apellido = "";
					}
					if (nombre == null) {
						nombre = "";
					}

					if (pass1 == null || pass2 == null) {
						pass1 = "";
					} else if (!pass1.equals(pass2)) {
						pass1 = "";
					}
					if (urlNewPhoto == null) {
						urlNewPhoto = "";
					}
					String webSite = (String) request.getParameter("input-webSite");
					String desc = (String) request.getParameter("input-description");

					portPublicador.modificarEmpresa(nickname, nombre, apellido, pass1, urlNewPhoto, nickname, desc,
							webSite);
					// String nickname,String nombre, String apellido, String contraseña, String
					// foto, String empresa, String descripcion, String link

				} else if (portPublicador.esPostulante(nickname)) {

					String nombre = (String) request.getParameter("input-name-p");
					String apellido = (String) request.getParameter("input-lastname-p");
					String pass1 = (String) request.getParameter("input-new-pass-p");
					String pass2 = (String) request.getParameter("input-rep-pass-p");
					String urlNewPhoto = (String) request.getParameter("");

					System.out.println(pass1);
					System.out.println(pass2);

					if (apellido == null) {
						apellido = "";
					}
					if (nombre == null) {
						nombre = "";
					}

					if (pass1 == null || pass2 == null) {
						pass1 = "";
					} else if (!pass1.equals(pass2)) {
						pass1 = "";
					}
					if (urlNewPhoto == null) {
						urlNewPhoto = "";
					}
					String fecha = (String) request.getParameter("input-date");
					String[] fechaNacimiento = fecha.split("-");
					fecha = fechaNacimiento[2] + "/" + fechaNacimiento[1] + "/" + fechaNacimiento[0];
					String nacionalidad = (String) request.getParameter("input-nacionalidad");
					portPublicador.modificarPostulante(nickname, nombre, apellido, pass1, urlNewPhoto, fecha,
							nacionalidad);

				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		response.sendRedirect(request.getContextPath() + "/perfil");

	}
}
