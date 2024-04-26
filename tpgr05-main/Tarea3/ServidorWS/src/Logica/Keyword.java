package Logica;

import java.util.Map;
import java.util.HashMap;

public class Keyword {
	private String nombre;
	private Map<String, Oferta> ofertas;
	
	public Keyword(String name) {
		this.nombre = name;
		this.ofertas = new HashMap<String, Oferta>();
	}
	
	public String getNombre() {
		return nombre;
	}

	public Map<String, Oferta> getOfertas() {
		return ofertas;
	}
	
	public void agregarOferta(Oferta oferta) {
		ofertas.put(oferta.getNombre(), oferta);
	}
}
