package Logica;

import java.time.*;

public class Publicar {
    private Empresa empresa;
    private Oferta oferta;
    private float costo;
    private LocalDate fecha;
    private LocalDate vencimiento;

    public Publicar(Empresa e, Oferta o, LocalDate fecha) {
        this.costo = o.getTOferta().getCosto();
        this.fecha = fecha;
        this.vencimiento = fecha.plusDays(o.getTOferta().getDuracion());
        this.empresa = e;
        this.oferta = o;
    }

    /*public float getCosto() {
        return this.costo;
    }*/

    /*public LocalDate getFecha() {
        return this.fecha;
    }*/

    /*public LocalDate getFechaVencimiento() {
        return this.vencimiento;
    }*/

    public Empresa getEmpresa() {
        return this.empresa;
    }

    public Oferta getOferta() {
        return this.oferta;
    }

   /* public boolean vencido() {
		return (fecha.compareTo(vencimiento) >= 0);
	}*/
}
