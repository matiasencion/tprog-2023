package Logica;

import java.time.*;
import java.time.format.DateTimeFormatter;

import types.DTPostulacion;

public class Postulacion {
    private Postulante postulante;
    private Oferta oferta;
    private LocalDate fecha;
    private String curriculum;
    private String motivacion;
    private String video;
    private int orden;
    private LocalDate fechaOrden;

    public Postulacion(LocalDate fecha, String cvitae, String motivacion, Postulante postu, Oferta oferta) {
        this.fecha = fecha;
        this.curriculum = cvitae;
        this.motivacion = motivacion;
        this.postulante = postu;
        this.oferta = oferta;
        this.video = "";
        this.orden = -1;
        this.fechaOrden = null;
        System.out.println("Postulacion creada");
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getCv() {
        return curriculum;
    }

    public String getDescripcion() {
        return motivacion;
    }

    public DTPostulacion getInfo() {
        return new DTPostulacion(this);
    }

    public String infoPostulanteString() {
        return "Postulante: " + postulante.getNickname() + "\n" + "Fecha: "
                + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + "\n" + "CV: " + curriculum + "\n"
                + "Motivación: " + motivacion + "\n";
    }

    public int getOrden() {
        return orden;
    }

    public LocalDate getFechaOrden() {
        return fechaOrden;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String urlYoutube) {
        video = urlYoutube;

    }

    public void seleccionar(int pos) {
        this.orden = pos;
        this.fechaOrden = LocalDate.now();
    }

    /*
     * public String getInfoString() {
     * return "Fecha: "+ fecha +"\n"+
     * "CV: "+ cv+"\n"+
     * "Motivacion: "+ motivacion+"\n";
     * }
     */
}
