package Logica;


import java.util.Map;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import types.DTPostulante;
import types.DTUsuario;

public class Postulante extends Usuario {
    private String nacionalidad;
    private LocalDate FechaNac;
    private Map<String, Postulacion> postulaciones;
    private Collection<Oferta> favoritos;


    public Postulante(String nickname, String nombre, String apellido, String correo, String contrasena, String foto, LocalDate Fecha,
            String nacionalidad) {
        super(nickname, nombre, apellido, correo, contrasena, foto);
        this.nacionalidad = nacionalidad;
        this.FechaNac = Fecha;
        this.postulaciones = new HashMap<String, Postulacion>();
        this.favoritos = new Vector<Oferta>();
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getFechaNac() {
        return FechaNac;
    }

    public void addPostulacion(Postulacion postulacion) {
        this.postulaciones.put(postulacion.getOferta().getNombre(), postulacion);
    }

    /*public void removePostulacion(Postulacion postulacion) {
        this.postulaciones.remove(postulacion.getOferta().getNombre());
    }*/

    @Override
    public DTUsuario getDT() {
        return new DTPostulante(this);
    }

    public Collection<String> listarPostulaciones() {
    	Collection<String> postulaciones = new Vector<String>();
        for (Postulacion postulacion : this.postulaciones.values()) {
               postulaciones.add(postulacion.getOferta().getNombre());
        }
        return postulaciones;
    }

	public void modificarPostulante(String nombre, String apellido, String contrasena, String foto, LocalDate date,
			String nacionalidad2) {
		modificar(nombre, apellido, contrasena, foto);
    	this.FechaNac = date;
    	this.nacionalidad = nacionalidad2;
	}

	public Map<String, Postulacion> getPostulaciones() {
        return postulaciones;
    }

	public Collection<Oferta> getFavoritos() {
		return favoritos;
	}
	
	public void agregarFavoritos(Oferta fav) {
		this.favoritos.add(fav);
		fav.incCantFavoritos();
	}
	
	public void quitarFavoritos(Oferta fav) {
		this.favoritos.remove(fav);
		fav.decCantFavoritos();
	}


}
