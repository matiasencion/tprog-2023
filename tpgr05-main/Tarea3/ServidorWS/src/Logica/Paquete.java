package Logica;

import java.time.*;
import java.time.format.DateTimeFormatter;

import java.util.Map;

import types.DTPaquete;

import java.util.HashMap;

public class Paquete {
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private float costo;
	private LocalDate fechaAlta;
	private String foto;
	private Map<String, CantidadTOferta> tofertas;
	private Map<String, CompraPaquete> comprasPaquete;
	
	
	public Paquete(String nombre, String descrp, int val, float desc, LocalDate fecha, String foto) {
		this.nombre = nombre;
		this.descripcion = descrp;
		this.validez = val;
		this.descuento = desc;
		this.costo = 0;
		this.fechaAlta = fecha;
		this.foto = foto;
		this.tofertas = new HashMap<String, CantidadTOferta>();
		this.comprasPaquete = new HashMap<String, CompraPaquete>();
	}
	
	
	
	public void setCosto(float cost) {
		costo = cost;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getValidez() {
		return validez;
	}
	
	public float getDescuento() {
		return descuento/100;
	}
	
	public float setCosto() {
		return costo;
	}
	
	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public Map<String, CantidadTOferta> getCantTOfertas() {
		return tofertas;
	}
	public CantidadTOferta getCantTOferta(String nombre) {
		return tofertas.get(nombre);
	}

	public Map<String, CompraPaquete> getPaqueteComprado() {
		return comprasPaquete;
	}
	
	public void agregarCompraPaquete(CompraPaquete paquete) {
		comprasPaquete.put(paquete.getEmpresa().getNickname(), paquete);
	}
	
	public void agregarTOferta(CantidadTOferta cantOferta) {

		tofertas.put(cantOferta.getTOferta().getNombre(), cantOferta);
		System.out.println("TOferta agregada");
	}
	public float getCosto(){
		return costo;
	}

	public boolean Disponible() {
		
		return false;
	}

	public String getInfo() {
		String tiposOfertas = "";
		for (CantidadTOferta c : tofertas.values()) {
			tiposOfertas += c.getTOferta().getNombre() + " " + "Cantidad: " + c.getCant() + "\n";
		}

		String res = "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion + "\n" + "Validez: " + validez + "\n" + "Descuento: " + descuento + "\n" + "Costo: " + costo + "\n" + "Fecha de Alta: " + fechaAlta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + "\n" + "Tipos de Ofertas: " + "\n" + tiposOfertas; 
		return res;
	
		
	}

	public DTPaquete getInfoPaquete() {
		return new DTPaquete(this);
	}



	public String getFoto() {
		return foto;
	}
	
	

	

	
}
