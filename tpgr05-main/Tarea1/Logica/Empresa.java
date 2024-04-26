package Logica;

import java.util.Set;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Empresa extends Usuario {

    private Map<String, Publicar> publicaciones;
    private Map<String, CompraPaquete> comprapaquetes;// nombre paquete de compra paquete y compra apquete
    private String empresa;
    private String descripcion;
    private String link;

    public Empresa(String nickname, String nombre, String apellido, String correo, String empresa, String descripcion,
            String link) {
        super(nickname, nombre, apellido, correo);
        this.empresa = empresa;
        this.descripcion = descripcion;
        if (link != "") {
        	this.link = link;
        } else {
        	this.link = null;
        }
        this.publicaciones = new HashMap<String, Publicar>();
        this.comprapaquetes = new HashMap<String, CompraPaquete>();
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }

    public void addPublicacion(Publicar publicacion) {
        this.publicaciones.put(publicacion.getOferta().getNombre(), publicacion);
    }

    /*public void removePublicacion(Publicar publicacion) {
        this.publicaciones.remove(publicacion.getOferta().getNombre(), publicacion);
    }*/

    public Map<String, Publicar> getPublicaciones() {
        return publicaciones;
    }

    /*public void agregarCompraPaquete(CompraPaquete x) {
        comprapaquetes.put(x.getPaquete().getNombre(), x);
    }*/

    public Vector<String> getOfertas() {
        Vector<String> ofertas = new Vector<String>();
        for (Publicar publicacion : this.publicaciones.values()) {
            ofertas.add(publicacion.getOferta().getNombre());
        }
        return ofertas;

    
    }

    @Override
    public DTUsuario getDT() {
        return new DTEmpresa(this);
    }
}
