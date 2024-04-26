package Logica;

import java.time.LocalDate;


public class DTPostulacion {

	 private LocalDate fecha;
	 private String curriculum;
	 private String descripcion;
	 private String oferta;
	 private String fechaString;
	 private String urlfoto;
	 private String postulante;
	 private String fotoPostulante;
	 
	 public DTPostulacion(Postulacion pos) {
		 this.fecha = pos.getFecha();
		 this.curriculum = pos.getCv();
		 this.descripcion = pos.getDescripcion();
		 oferta = pos.getOferta().getNombre();
		 fechaString = fecha.toString();
		 urlfoto = pos.getOferta().getFoto();
		 postulante = pos.getPostulante().getNickname();
		 fotoPostulante = pos.getPostulante().getFoto();
		 
		 
	 }
	 
	 public String getOferta() {
		 return oferta;
	 }
	 
	 public String getFechaString() {
		 return fechaString;
	 }
	 
	public LocalDate getFecha() {
		 return fecha;
	}

	 public String getCv() {
		 return curriculum;
	 }

	 public String getDescripcion() {
	     return descripcion;
	 }
	 
	 public String getPostulante() {
	     return postulante;
	 }
	 
	 public String getInfoString() {
			return "Fecha: "+ fecha +"\n"+
	        "CV: "+ curriculum+"\n"+
	        "Motivacion: "+ descripcion+"\n";
	}
	 
	 public String getFoto() {
		 return urlfoto;
	 }
	 
	 public String getPostulanteFoto() {
		 return fotoPostulante;
	 }
}
