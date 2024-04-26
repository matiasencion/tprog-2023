package Logica;

import java.util.HashMap;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;

import Logica.Publicar.ESTADO;
import types.*;
import ofertasFinalizadas.*;
import DAO.*;
import Logica.Postulacion;

public class Oferta {
    private Publicar datosPublicacion;
    private TOferta tOferta;
    private Map<String, Keyword> keywords;
    private Map<String, Postulacion> postulantes;
    private CompraPaquete paquete;
    private String nombre;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String departamento;
    private String ciudad;
    private String foto;
    private float sueldo;
    private int cantFavoritos;
    private int cantVisitas;

    public Oferta(Empresa empresa, TOferta toferta, String nombre, String descripcion, String horaInicio, String horaFin,
            float sueldo,
            String ciudad, String departamento, LocalDate fechaAlta, Set<Keyword> keys, CompraPaquete paquete,
            String foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        if (paquete != null)
        	this.paquete = paquete;
        else {
        	this.paquete = null;
        }
        this.cantFavoritos = 0;
        this.cantVisitas = 0;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.sueldo = sueldo;
        this.tOferta = toferta;
        this.foto = foto;
        this.keywords = new HashMap<String, Keyword>();
        this.postulantes = new HashMap<String, Postulacion>();
        for (Keyword key : keys) {
            agregarKeyword(key);
        }
        this.datosPublicacion = new Publicar(empresa, this, fechaAlta, paquete);
        empresa.addPublicacion(datosPublicacion);
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

    public void agregarKeyword(Keyword keyword) {
        keywords.put(keyword.getNombre(), keyword);
    }

    public Map<String, Keyword> getKeywords() {
        return keywords;
    }

    public DtOferta getInfo() {
        return new DtOferta(this);
    }

    public CompraPaquete getCompraPaquete() {
        return paquete;
    }

    public String getInfoString() {
        String sueld = String.valueOf(sueldo);
        String fefe;
        if (datosPublicacion.getFechaAprobado() == null) {
        	fefe = "No Aprobado";
        } else {
        	fefe = datosPublicacion.getFechaAprobado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        }
        return "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion + "\n" + "Hora Inicio: " +
                horaInicio.toString() + "\n" + "Hora Fin: " + horaFin.toString() + "\n" + "Departamento: " +
                departamento + "\n" + "Ciudad: " + ciudad + "\n" + "Remuneracion: " + sueld + "\n" + "Fecha de Alta: "
                + datosPublicacion.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + "\n" +
                "Estado: " + datosPublicacion.getEstado().toString() + "\n" + "Fecha de Aprobacion: " + fefe + "\n"
                + "Tipo de oferta: " + tOferta.getNombre().toString() + "\n";
    }

    public Publicar getDatosPublicacion() {
        return datosPublicacion;
    }

    public TOferta getTOferta() {

        return tOferta;
    }

    public String getInfoPostulantesString() {
        String res = "";
        for (Postulacion p : postulantes.values()) {
            res = res + p.infoPostulanteString();
        }
        return res;
    }

    public String getNombreEmpresa() {
        return datosPublicacion.getEmpresa().getNickname();
    }

    public String getInfoBasicaOferta() {
        String suel = String.valueOf(sueldo);
        return "Nombre: " + nombre + "\n" + "Descripcion: " + descripcion + "\n" + "Hora Inicio: " +
                horaInicio.toString() + "\n" + "Hora Fin: " + horaFin.toString() + "\n" + "Departamento: "
                + departamento + "\n" + "Ciudad: " + ciudad + "\n" + "Remuneracion: " + suel + "\n";
    }

    public String getFoto() {
        return foto;
    }

    public DTPublicar getInfoPublicar() {
        return new DTPublicar(datosPublicacion);
    }

    public LocalDate getFechaAprobado() {
        return datosPublicacion.getFechaAprobado();
    }

	public CompraPaquete getPaquete() {
		return paquete;
	}
	
	public void finalizarOferta() {
		OfertaFinalizadaDaoImpl ofertasfinalizadas = new OfertaFinalizadaDaoImpl();
		String horario = horaInicio +"-" + horaFin;
		String paquete2 = "null";
		if(paquete != null && paquete.getPaquete() != null)
		{
			paquete2 = paquete.getPaquete().getNombre();
		}
		ofertasfinalizadas.agregar(
				nombre,
				descripcion,
				horario,
				Float.toString(sueldo),
				departamento,
				ciudad,
				tOferta.getNombre(),
				datosPublicacion.getFecha().toString(),
				LocalDate.now().toString(),
				Float.toString(tOferta.getCosto()),
				paquete2
				,postulantes.values(),
				datosPublicacion.getEmpresa()
				);
		datosPublicacion.setEstado(ESTADO.Finalizado);
	}

	
	public boolean checkVencimiento() {
		return datosPublicacion.vencido();
	}
	
	public int getCantFavoritos() {
		return cantFavoritos;
	}

	public int getCantVisitas() {
		return cantVisitas;
	}
	
	public void incCantVisitas() {
		this.cantVisitas++;
	}
	
	public void setVisitas(int cant) {
		this.cantVisitas = cant;
	}
	
	public void incCantFavoritos() {
		this.cantFavoritos++;
	}
	
	public void decCantFavoritos() {
		this.cantFavoritos--;
	}
}
