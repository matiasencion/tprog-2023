package Logica;

import java.util.Map;

public class DTCompraPaquete {
	private String fecha;
	private String vencimiento;
	private String paquete;
	private String empresa;
	private String urlFoto;
	private Map<String, Integer> disponibles;
	
	public DTCompraPaquete(CompraPaquete comprap) {
		this.paquete = comprap.getPaquete().getNombre();
		this.empresa = comprap.getEmpresa().getEmpresa();
		this.fecha = comprap.getFecha().toString();
		this.vencimiento = comprap.getVencimiento().toString();
		urlFoto = comprap.getPaquete().getFoto();
		disponibles = comprap.getDisponibles();

	}
	
	public String getEmpresa() {
		return empresa;
	}
	
	public String getPaquete() {
		return paquete;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getVencimiento() {
		return vencimiento;
	}
	
	public String getFoto() {
		return urlFoto;
	}
	
	public Map<String, Integer> getDisponibles() {
		return disponibles;
	}
	
}
