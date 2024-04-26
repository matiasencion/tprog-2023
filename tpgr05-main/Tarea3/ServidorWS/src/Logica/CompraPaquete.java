package Logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import types.DTCompraPaquete;

public class CompraPaquete {
	private LocalDate fecha;
	private LocalDate vencimiento;
	private Paquete paquete;
	private Empresa empresa;
	private Map<String, Oferta> gastadas;
	private Map<String, Integer> disponibles;

	public CompraPaquete(Paquete paque, Empresa empre, LocalDate date) {
		this.paquete = paque;
		this.empresa = empre;
		this.fecha = date;
		this.vencimiento = date.plusDays(paque.getValidez());
		this.disponibles = new HashMap<String, Integer>();
		for (Entry<String, CantidadTOferta> i : paque.getCantTOfertas().entrySet()) {
			this.disponibles.put(i.getKey(), i.getValue().getCant());
		}
		this.gastadas = new HashMap<String, Oferta>();
	}

	public Empresa getEmpresa() {
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
		return (LocalDate.now().isAfter(vencimiento));
	}

	public Map<String, Oferta> getGastadas() {
		return gastadas;
	}

	public Map<String, Integer> getDisponibles() {
		return disponibles;
	}

	public void gastar(Oferta gastada) {
		gastadas.put(gastada.getNombre(), gastada);
		if (disponibles.get(gastada.getTOferta().getNombre()) - 1 > 0)
			disponibles.put(gastada.getTOferta().getNombre(), disponibles.get(gastada.getTOferta().getNombre()) - 1);
		else
			disponibles.remove(gastada.getTOferta().getNombre());

	}

	public DTCompraPaquete getInfo() {
		return new DTCompraPaquete(this);
	}
}