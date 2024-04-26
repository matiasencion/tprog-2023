package Logica;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Postulacion {
    private Postulante postulante;
    private Oferta oferta;
    private LocalDate fecha;
    private String cv;
    private String motivacion;

    public Postulacion(LocalDate fecha, String cv, String motivacion, Postulante p, Oferta o) {
        this.fecha = fecha;
        this.cv = cv;
        this.motivacion = motivacion;
        this.postulante = p;
        this.oferta = o;
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
        return cv;
    }

    public String getDescripcion() {
        return motivacion;
    }

    public DTPostulacion getInfo() {
    	return new DTPostulacion(this);
    }
    
	public String infoPostulanteString() {
		return "Postulante: " + postulante.getEmail()+ "\n" + "Fecha: "+fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()+"\n"+"CV: "+cv+"\n"+"Descripcion: "+motivacion+"\n";
	}
	
	/*public String getInfoString() {
		return "Fecha: "+ fecha +"\n"+
        "CV: "+ cv+"\n"+
        "Motivacion: "+ motivacion+"\n";
	}*/
}
