package Logica;


public class DTPublicar {
	private String empresa;
	private String oferta;
	private float costo;
	private String fecha;
	private String fechaAprobado;
	private String vencimiento;
	private String estado;
	private String urlFoto;

	public DTPublicar(Publicar publi) {
		empresa = publi.getEmpresa().getEmpresa();
		oferta = publi.getOferta().getNombre();
		costo = publi.getCosto();
		fecha = publi.getFecha().toString();
		if (publi.getFechaAprobado() == null) {
			fechaAprobado = "No Aprobada.";
		} else {
			fechaAprobado = publi.getFechaAprobado().toString();
		}
		if (publi.getFechaVencimiento() == null) {
			vencimiento = "No hay vencimiento.";
		} else {
			vencimiento = publi.getFechaVencimiento().toString();
		}
		estado = publi.getEstado().toString();
		urlFoto = publi.getOferta().getFoto();
	}

	public float getCosto() {
		return this.costo;
	}

	public String getFecha() {
		return this.fecha;
	}

	public String getFechaVencimiento() {
		return this.vencimiento;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public String getOferta() {
		return this.oferta;
	}

	public String getFechaAprobado() {
		return fechaAprobado;
	}

	public String getEstado() {
		return estado;
	}

	public String getFoto() {
		return urlFoto;
	}
}
