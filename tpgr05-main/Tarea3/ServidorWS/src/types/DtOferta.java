package types;




import java.util.ArrayList;

import Logica.Oferta;
import Logica.Postulacion;
import Logica.Publicar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;




import java.util.*;



@XmlAccessorType(XmlAccessType.FIELD)
public class DtOferta {

    private String nombre;
    private String empresa;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String departamento;
    private String ciudad;
    private String urlFoto;
    private String fechaAprobado;
    private float sueldo;
    private Set<DTPostulacion> postulaciones;
    private Set<String> keywords;
    private String nombrePaquete;
    private String urlFotoPaquete;
    private Publicar.ESTADO estado;
    private int favoritos;
    public int exposicion;

    public DtOferta(Oferta oferta) {
        this.nombre = oferta.getNombre();
        this.empresa = oferta.getNombreEmpresa();
        this.descripcion = oferta.getDescripcion();
        this.horaInicio = oferta.getHoraInicio();
        this.horaFin = oferta.getHoraFin();
        this.departamento = oferta.getDepartamento();
        this.ciudad = oferta.getCiudad();
        this.sueldo = oferta.getSueldo();
        this.urlFoto = oferta.getFoto();
        this.estado = oferta.getDatosPublicacion().getEstado();
        this.favoritos = oferta.getCantFavoritos();
        this.exposicion = oferta.getTOferta().getExposicion();

        if (oferta.getCompraPaquete() != null)
            this.nombrePaquete = oferta.getCompraPaquete().getPaquete().getNombre();
        if (oferta.getCompraPaquete() != null)
            this.urlFotoPaquete = oferta.getCompraPaquete().getPaquete().getFoto();
        if (oferta.getFechaAprobado() == null) {
            fechaAprobado = "No Aprobada.";
        } else {
            fechaAprobado = oferta.getFechaAprobado().toString();
        }
        this.postulaciones = new HashSet<DTPostulacion>();
        for (Postulacion pos : oferta.getPostulaciones().values()) {
            postulaciones.add(new DTPostulacion(pos));
        }
        keywords = oferta.getKeywords().keySet();

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getFechaAprovado() {
        return fechaAprobado;
    }

    public float getSueldo() {
        return sueldo;
    }

    public String getFoto() {
        return urlFoto;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public Set<DTPostulacion> getDTPostulaciones() {
        return postulaciones;
    }

    public String getInfoString() {
        String palabra = String.valueOf(sueldo);
        return "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion + "\n" + "Hora Inicio: " +
                horaInicio.toString() + "\n" + "Hora Fin: " + horaFin.toString() + "\n" + "Departamento: " +
                departamento + "\n" + "Ciudad: " + ciudad + "\n" + "Remuneracion: " + palabra + "\n";
    }

	public String getEmpresa() {
		return empresa;
	}
	
	public String getKeywordsString() {
        String palabra = "";
        for (String key : this.getKeywords()) {
        	palabra += key + ", ";
        }
        return palabra;
    }

    public String getNombrePaquete() {
        return nombrePaquete;
    }

    public String getUrlPaquete() {
        return urlFotoPaquete;
    }
    
    public Publicar.ESTADO getEstado() {
    	return estado;
    }
    
    public int getFavoritos() {
    	return favoritos;
    }
    
    public int getExposicion() {
    	return exposicion;
    }
}
