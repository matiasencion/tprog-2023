package com.trabajouy.controllers;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generarPDF")
public class GenerarPDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postulante = request.getParameter("postulante");
        String empresa = request.getParameter("empresa");
        String oferta = request.getParameter("oferta");
        String orden = request.getParameter("orden");
        String fecha = request.getParameter("fecha");
        String fechaOrden = request.getParameter("fechaOrden");

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + oferta + "_" + postulante.toUpperCase() + ".pdf");

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Postulante: " + postulante));
            document.add(new Paragraph("Empresa: " + empresa));
            document.add(new Paragraph("Oferta: " + oferta));
            document.add(new Paragraph("Orden: " + orden));
            document.add(new Paragraph("Fecha: " + fecha));
            document.add(new Paragraph("FechaOrden: " + fechaOrden));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document != null && document.isOpen()) {
                document.close();
            }
        }
    }
}