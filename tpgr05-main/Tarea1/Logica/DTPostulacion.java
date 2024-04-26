package Logica;

import java.time.LocalDate;

public class DTPostulacion {

	 private LocalDate fecha;
	 private String cv;
	 private String descripcion;
	 
	 public DTPostulacion(Postulacion pos) {
		 this.fecha = pos.getFecha();
		 this.cv = pos.getCv();
		 this.descripcion = pos.getDescripcion();
	 }
	 
	/* public LocalDate getFecha() {
		 return fecha;
	 }*/

	 public String getCv() {
		 return cv;
	 }

	 /*public String getDescripcion() {
	     return descripcion;
	 }*/
	 
	 public String getInfoString() {
			return "Fecha: "+ fecha +"\n"+
	        "CV: "+ cv+"\n"+
	        "Motivacion: "+ descripcion+"\n";
	}
}
