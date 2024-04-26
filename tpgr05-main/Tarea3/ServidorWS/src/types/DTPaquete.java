package types;

import java.util.List;

import Logica.CantidadTOferta;
import Logica.CompraPaquete;
import Logica.Paquete;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
	private String nombre;
	private String descripcion;
	private int validez;
	private float descuento;
	private float costo;
	private String fechaAlta;
	private Map<String, CantidadTOferta> tofertas;
	private Map<String, CompraPaquete> comprasPaquete;
	private String tiposOfertas;
	private String foto;

	public DTPaquete(Paquete paque) {
		nombre = paque.getNombre();
		descripcion = paque.getDescripcion();
		validez = paque.getValidez();
		descuento = paque.getDescuento();
		costo = paque.getCosto();
		fechaAlta = paque.getFechaAlta().toString();
		tofertas = paque.getCantTOfertas();
		comprasPaquete = paque.getPaqueteComprado();
		tiposOfertas = this.getTOfertas();
		foto = paque.getFoto();
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getValidez() {
		return validez;
	}

	public float getDescuento() {
		return descuento;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public float getCosto() {
		return costo;
	}

	public Map<String, CantidadTOferta> getCantTOfertas() {
		return tofertas;
	}

	public Map<String, CompraPaquete> getPaqueteComprado() {
		return comprasPaquete;
	}

	public String getTOfertas() {
		String ret = "";
		for (CantidadTOferta cto : tofertas.values()) {
			ret += cto.getTOferta().getNombre() + " - " + cto.getCant() + "\n";
		}
		return ret;
	}

	public String[] getVectorTOfertas() {
		List<String> res = null;
		for (CantidadTOferta cto : tofertas.values()) {
			String ret = cto.getTOferta().getNombre() + " - " + cto.getCant() + "\n";
			res.add(ret);
		}
		return res.toArray(new String[res.size()]);
	}

	public String getTiposOfertras() {
		return tiposOfertas;

	}

	public String getFoto() {
		return foto;
	}

}
