package Logica;

import java.time.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

public class Postulante extends Usuario {
    private String nacionalidad;
    private LocalDate FechaNac;
    private Map<String, Postulacion> postulaciones;


    public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate Fecha,
            String nacionalidad) {
        super(nickname, nombre, apellido, correo);
        this.nacionalidad = nacionalidad;
        this.FechaNac = Fecha;
        this.postulaciones = new HashMap<String, Postulacion>();
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

    public Vector<String> listarPostulaciones() {
        Vector<String> postulaciones = new Vector<String>();
        for (Postulacion postulacion : this.postulaciones.values()) {
               postulaciones.add(postulacion.getOferta().getNombre());
        }
        return postulaciones;
    }

     

}
