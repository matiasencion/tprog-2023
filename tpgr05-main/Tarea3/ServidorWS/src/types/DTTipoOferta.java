package types;

import Logica.TOferta;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTTipoOferta {
 	private String nombre;
    private String descripcion;
    private int exposicion;
    private int duracion;
    private float costo;
    private String fechaDeAlta;
    
    public DTTipoOferta(TOferta toferta) {
    	nombre = toferta.getNombre();
    	descripcion = toferta.getDescripcion();
    	exposicion = toferta.getExposicion();
    	duracion = toferta.getDuracion();
    	costo = toferta.getCosto();
    	fechaDeAlta = "TipoOferta no aprobado aun.";
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

    public void setFechaDeAlta(String fecha) {
      fechaDeAlta = fecha;
    }
    
    public String getFechaDeAlta() {
        return fechaDeAlta;
    }
}
