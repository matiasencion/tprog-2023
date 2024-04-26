package Logica;

import java.util.HashMap;
import java.time.*;
import java.util.Map;
import java.util.Set;

import excepciones.OfertaRepetidaException;

public class Oferta {
    private Publicar datosPublicacion;
    private TOferta tOferta;
    private Map<String, Keyword> keywords;
    private Map<String, Postulacion> postulantes;
    private String nombre;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String departamento;
    private String ciudad;
    private float sueldo;

    public Oferta(Empresa e, TOferta to, String nombre, String descripcion,String horaInicio, String horaFin, float sueldo, 
    				String ciudad, String departamento, LocalDate fechaAlta, Set<Keyword> keys) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.sueldo = sueldo;
        this.tOferta = to;
        this.keywords = new HashMap<String, Keyword>();
        this.postulantes = new HashMap<String, Postulacion>();
        for (Keyword key : keys) {
        	agregarKeyword(key);
        }
        this.datosPublicacion = new Publicar(e, this ,fechaAlta);  
        e.addPublicacion(datosPublicacion);
        System.out.println("Oferta creada");
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

    public float getSueldo() {
        return sueldo;
    }

    public Map<String, Postulacion> getPostulaciones() {
        return postulantes;
    }

    public void addPostulacion(Postulacion postulacion) {
        postulantes.put(postulacion.getPostulante().getNickname(), postulacion);
    }

    public void agregarKeyword(Keyword k) {
    	keywords.put(k.getNombre(), k);
    }
    
    /*public Map<String, Keyword> consultarKeywords() {
    	return keywords;
    }*/
    
    public DTOferta getInfo() {
    	return new DTOferta(this);
    }
    
    public String getInfoString() {
    	String s = String.valueOf(sueldo);
    	return "Nombre: "+nombre+"\n"+"Descripcion: "+descripcion+"\n"+"Hora Inicio: "+
    			horaInicio.toString()+"\n"+"Hora Fin: "+horaFin.toString()+"\n"+"Departamento: "+
    			departamento+"\n"+"Ciudad: "+ciudad+"\n"+"Remuneracion: "+s+"\n";
    }
    
    /*public Publicar getDatosPublicacion() {
    	return datosPublicacion;
    }*/
    
    public TOferta getTOferta() {
        
    	return tOferta;
    }
    
    public String getInfoPostulantesString() {
    	String res = "";
    	for (Postulacion p : postulantes.values()) {
    		res= res + p.infoPostulanteString();
    	}
    	return res;
    }
    public String getNombreEmpresa() {
    	return datosPublicacion.getEmpresa().getNickname();
    }
    public String getInfoBasicaOferta(){
        return "Nombre: "+ nombre +"\n" + "Departamento: "+ departamento+ "\n" + "Ciudad: "+ ciudad + "\n";
    }
    
}
