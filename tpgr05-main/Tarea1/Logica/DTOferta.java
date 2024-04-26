package Logica;



import java.util.*;

public class DTOferta {

    private String nombre;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String departamento;
    private String ciudad;
    private float sueldo;
    private Set<DTPostulacion> postulaciones;
    
    public DTOferta(Oferta oferta) {
    	this.nombre = oferta.getNombre();
        this.descripcion = oferta.getDescripcion();
        this.horaInicio = oferta.getHoraInicio();
        this.horaFin = oferta.getHoraFin();
        this.departamento = oferta.getDepartamento();
        this.ciudad = oferta.getCiudad();
        this.sueldo = oferta.getSueldo();
        this.postulaciones = new HashSet<DTPostulacion>();
        for(Postulacion pos : oferta.getPostulaciones().values()) {
        	postulaciones.add(new DTPostulacion(pos));
        }
        
    }
    
    /*public String getNombre() {
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

    public float getSueldo() {
        return sueldo;
    }*/
    
    public Set<DTPostulacion> getDTPostulaciones() {
        return postulaciones;
    }
    
    public String getInfoString() {
    	String s = String.valueOf(sueldo);
    	return "Nombre: "+nombre+"\n"+"Descripcion: "+descripcion+"\n"+"Hora Inicio: "+
    			horaInicio.toString()+"\n"+"Hora Fin: "+horaFin.toString()+"\n"+"Departamento: "+
    			departamento+"\n"+"Ciudad: "+ciudad+"\n"+"Remuneracion: "+s+"\n";
    }
}
