package Logica;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

public class CompraPaquete {
	private LocalDate fecha;
	private LocalDate vencimiento;
	private Paquete paquete;
	private Empresa empresa;
	private Map<String, Oferta> gastadas;
	private int disponibles;
	
	/*public CompraPaquete(Paquete p, Empresa e, LocalDate f, int disponibles) {
		this.paquete = p;
		this.empresa = e;
		this.fecha = f;
		this.vencimiento = f.plusDays(p.getValidez());
		this.disponibles = disponibles;
		this.gastadas = new HashMap<String, Oferta>();
	}*/
	
/*	public Empresa getEmpresa() {
		return empresa;
	}
	
	public Paquete getPaquete() {
		return paquete;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public LocalDate getVencimiento() {
		return vencimiento;
	}
	
	public boolean vencido() {
		return (fecha.compareTo(vencimiento) >= 0);
	}

	public Map<String, Oferta> getGastadas() {
		return gastadas;
	}

	public int getDisponibles() {
		return disponibles;
	}
	
	public void gastar(Oferta gastada) {
		gastadas.put(gastada.getNombre(), gastada);
	}*/
	
}
