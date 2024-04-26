package Logica;


public class DTTipoOferta {
 	private String nombre;
    private String descripcion;
    private int exposicion;
    private int duracion;
    private float costo;
    private String fechaDeAlta;
    
    DTTipoOferta(TOferta toferta) {
    	nombre = toferta.getNombre();
    	descripcion = toferta.getDescripcion();
    	exposicion = toferta.getExposicion();
    	duracion = toferta.getDuracion();
    	costo = toferta.getCosto();
    	if (toferta.getFechaDeAlta() != null) {
    		fechaDeAlta = toferta.getFechaDeAlta().toString();
    	} else {
    		fechaDeAlta = "TipoOferta no aprobado aun.";
    	}
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

    public String getFechaDeAlta() {
        return fechaDeAlta;
    }
}
