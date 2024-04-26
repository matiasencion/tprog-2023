package com.trabajouy.model;

import java.util.*;



public class DTOferta {

    private String nombre;
    private String empresa;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String departamento;
    private String ciudad;
    private String urlFoto;
    private String fechaAprobado;
    private float sueldo;
    private Set<DTPostulacion> postulaciones;
    private Set<String> keywords;
    private String nombrePaquete;
    private String urlFotoPaquete;

    public DTOferta(Oferta oferta) {
        this.nombre = oferta.getNombre();
        this.empresa = oferta.getNombreEmpresa();
        this.descripcion = oferta.getDescripcion();
        this.horaInicio = oferta.getHoraInicio();
        this.horaFin = oferta.getHoraFin();
        this.departamento = oferta.getDepartamento();
        this.ciudad = oferta.getCiudad();
        this.sueldo = oferta.getSueldo();
        this.urlFoto = oferta.getFoto();

        if (oferta.getCompraPaquete() != null)
            this.nombrePaquete = oferta.getCompraPaquete().getPaquete().getNombre();
        if (oferta.getCompraPaquete() != null)
            this.urlFotoPaquete = oferta.getCompraPaquete().getPaquete().getFoto();
        if (oferta.getFechaAprobado() == null) {
            fechaAprobado = "No Aprobada.";
        } else {
            fechaAprobado = oferta.getFechaAprobado().toString();
        }
        this.postulaciones = new HashSet<DTPostulacion>();
        for (Postulacion pos : oferta.getPostulaciones().values()) {
            postulaciones.add(new DTPostulacion(pos));
        }
        keywords = oferta.getKeywords().keySet();

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFechaAprovado() {
        return fechaAprobado;
    }

    public float getSueldo() {
        return sueldo;
    }

    public String getFoto() {
        return urlFoto;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Set<DTPostulacion> getDTPostulaciones() {
        return postulaciones;
    }

    public String getInfoString() {
        String palabra = String.valueOf(sueldo);
        return "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion + "\n" + "Hora Inicio: " +
                horaInicio.toString() + "\n" + "Hora Fin: " + horaFin.toString() + "\n" + "Departamento: " +
                departamento + "\n" + "Ciudad: " + ciudad + "\n" + "Remuneracion: " + palabra + "\n";
    }

	public String getEmpresa() {
		return empresa;
	}
	
	public String getKeywordsString() {
        String palabra = "";
        for (String key : this.getKeywords()) {
        	palabra += key + ", ";
        }
        return palabra;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public String getUrlPaquete() {
        return urlFotoPaquete;
    }
}
