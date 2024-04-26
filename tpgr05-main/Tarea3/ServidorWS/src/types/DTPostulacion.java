package types;

import java.time.LocalDate;

import Logica.Postulacion;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulacion {

	 private LocalDate fecha;
	 private String curriculum;
	 private String descripcion;
	 private String oferta;
	 private String fechaString;
	 private String urlfoto;
	 private String postulante;
	 private String fotoPostulante;
	 private String urlVideo;
	 private int orden;
	 private String fechaOrdenString;
	 private String empresa;
	 
	 public DTPostulacion(Postulacion pos) {
		 this.fecha = pos.getFecha();
		 this.curriculum = pos.getCv();
		 this.descripcion = pos.getDescripcion();
		 oferta = pos.getOferta().getNombre();
		 fechaString = fecha.toString();
		 urlfoto = pos.getOferta().getFoto();
		 postulante = pos.getPostulante().getNickname();
		 fotoPostulante = pos.getPostulante().getFoto();
		 urlVideo = pos.getVideo();
		 orden = pos.getOrden();
		 if (pos.getFechaOrden()!= null) {
			 fechaOrdenString = pos.getFechaOrden().toString();
		 }else {
			 fechaOrdenString = "";
		 }
		 empresa = pos.getOferta().getNombreEmpresa();
		 
		 
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
	 
	 public String getVideo(){
		 return urlVideo;
	 }
	 
	 public int getOrden() {
		 return orden;
	 }
	 
	 public String getOrdenString() {
		 return Integer.toString(orden);
	 }
	 
	 public String getFechaOrdenString() {
		 return fechaOrdenString;
	 }

	public String getEmpresa() {
		return empresa;
	}
}
