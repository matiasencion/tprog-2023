
package com.trabajouy.controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


import com.trabajouy.model.EstadoSesion;

import com.trabajouy.exceptions.UsuarioRepetidoException;

@WebServlet("/register")
@MultipartConfig(maxFileSize = 20848820L, maxRequestSize = 418018841L, fileSizeThreshold = 1048576)
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Register() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UsuarioRepetidoException {
      
	    servidor.PublicadorService service = new servidor.PublicadorService();
		servidor.Publicador portPublicador = service.getPublicadorPort();
        String relativePath = "/media/images/";
        String UPLOAD_DIRECTORY = this.getServletContext().getRealPath(relativePath);
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String nickname = request.getParameter("nickname").toLowerCase();
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email").toLowerCase();
        String usrType = request.getParameter("user_type");
        HttpSession session = request.getSession();
      
        
        String uniqueFileName = "";
        String filePath = "";
        Part filePart = request.getPart("fileFieldName");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = fileName.substring(fileName.lastIndexOf('.'));
            uniqueFileName = nickname + extension;
            filePath = Paths.get(UPLOAD_DIRECTORY, uniqueFileName).toString();
            System.out.println(filePath);
        }

        if (usrType.equals("empresa")) {
            String website = request.getParameter("website");
            String descripcion = request.getParameter("description");

            try {
                portPublicador.altaEmpresa(nickname, name, apellido, email, password, uniqueFileName, name, descripcion,
                        website);
                filePart.write(filePath);
                session.setAttribute("estado_sesion", EstadoSesion.EMPRESA);
                session.setAttribute("nickname_sesion", nickname);
                session.setAttribute("foto_sesion",uniqueFileName);
                response.sendRedirect("/trabajouy/perfil");  // mover aquí

            }catch (Exception e) {
                request.setAttribute("errorMessage", "El nickname o correo ya está en uso.");
                request.getRequestDispatcher("/WEB-INF/home/register.jsp").forward(request, response);
                System.out.println(e);
            }

        } else {
            if (usrType.equals("postulante")) {

                String fechaN = request.getParameter("fechaNacimiento");
                String nacionalidad = request.getParameter("nacionalidad");
                String[] fechaNacimiento = fechaN.split("-");
                fechaN = fechaNacimiento[2] + "/" + fechaNacimiento[1] + "/" + fechaNacimiento[0];

                try {
                	portPublicador.crearPostulante(nickname, name, apellido, email, password, uniqueFileName, fechaN,
                            nacionalidad);
                    filePart.write(filePath);
                    session.setAttribute("estado_sesion", EstadoSesion.POSTULANTE);
                    session.setAttribute("nickname_sesion", nickname);
                    session.setAttribute("foto_sesion",uniqueFileName);
                    response.sendRedirect("/trabajouy/perfil");  // mover aquí

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/home/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



