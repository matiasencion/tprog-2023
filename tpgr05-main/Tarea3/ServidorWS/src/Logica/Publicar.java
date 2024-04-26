package Logica;

import java.time.LocalDate;

public class Publicar {
    private Empresa empresa;
    private Oferta oferta;
    private float costo;
    private LocalDate fecha;
    private LocalDate fechaAprobado;
    private LocalDate vencimiento;
    private ESTADO estado;
    private LocalDate fechaBaja;
    
    public enum ESTADO {
    	Ingresado,
    	Confirmado,
    	Rechazado,
    	Finalizado
    }

    public Publicar(Empresa empresa, Oferta oferta, LocalDate fecha, CompraPaquete paquete) {
    	if (paquete == null) {
    		this.costo = oferta.getTOferta().getCosto();	
    	} else {
    		this.costo = (1-paquete.getPaquete().getDescuento()) * oferta.getTOferta().getCosto();
    	}
        this.fecha = fecha;
        this.fechaAprobado = null;
        this.vencimiento = null;
        this.empresa = empresa;
        this.oferta = oferta;
        this.estado = ESTADO.Ingresado;
        this.fechaBaja = null;
        
    }
    
    public float getCosto() {
        return this.costo;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public LocalDate getFechaVencimiento() {
        return this.vencimiento;
    }

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public Oferta getOferta() {
        return this.oferta;
    }

	public LocalDate getFechaAprobado() {
		return fechaAprobado;
	}

	public ESTADO getEstado() {
		return estado;
	}
	
	public void setEstado(ESTADO estado) {
		this.estado = estado;
	}

    public boolean vencido() {
    	if (vencimiento != null)
		return LocalDate.now().isBefore(vencimiento);
    	else return false;
	}
    
    public void setFechaAprobado(LocalDate fecha) {
    	this.fechaAprobado = fecha;
    	this.vencimiento = fecha.plusDays(this.getOferta().getTOferta().getDuracion());
    }
    

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}


}

