package Logica;


import java.util.Map;

import types.DTTipoOferta;

import java.time.LocalDate;
import java.util.HashMap;

public class TOferta {
    private String nombre;
    private String descripcion;
    private int exposicion;
    private int duracion;
    private float costo;
    private LocalDate fechaDeAlta;
    private Map<String, Oferta> ofertas;
    private Map<String, CantidadTOferta> cantidadesEnPaquete;

    public TOferta(String name, String descr, int expo, int duration, float cost, LocalDate fechaDeAlta) {
        this.nombre = name;
        this.descripcion = descr;
        this.exposicion = expo;
        this.duracion = duration;
        this.costo = cost;
        this.fechaDeAlta = fechaDeAlta;
        this.ofertas = new HashMap<String, Oferta>();
        this.cantidadesEnPaquete = new HashMap<String, CantidadTOferta>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getExposicion() {
        return exposicion;
    }

    public int getDuracion() {
        return duracion;
    }

    public float getCosto() {
        return costo;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

	public Map<String, Oferta> getOfertas() {
		return ofertas;
	}

	public Map<String, CantidadTOferta> getCantidadesEnPaquete() {
		return cantidadesEnPaquete;
	}
	
	public void agregarOfertas(Oferta oferta) {
		ofertas.put(oferta.getNombre(), oferta);
	}
	
	public void agregarCantidadAPaquete(CantidadTOferta cantToferta) {
		cantidadesEnPaquete.put(cantToferta.getPaquete().getNombre(), cantToferta);
	}
	
	 public DTTipoOferta getInfoTOferta() {
		 DTTipoOferta toferta = new DTTipoOferta(this);
   	if (this.getFechaDeAlta() != null) {
  		toferta.setFechaDeAlta(this.getFechaDeAlta().toString());
  	}
	        return toferta;
	  }
}
