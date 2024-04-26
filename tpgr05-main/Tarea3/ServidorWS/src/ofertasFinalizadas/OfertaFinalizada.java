package ofertasFinalizadas;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
@Table(name = "ofertas_finalizadas")
public class OfertaFinalizada {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String horario;

    @Column(nullable = false)
    private Double remuneracion;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String ciudad;

    @Column(name = "tipo_publicacion", nullable = false)
    private String tipoPublicacion;

    @Column(name = "fecha_alta", nullable = false)
    private String fechaAlta;

    @Column(name = "fecha_baja")
    private String fechaBaja;

    @Column(nullable = false)
    private Double costo;

    @Column(nullable = false)
    private String paquete;
    
    @OneToMany(mappedBy = "oferta", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PostulacionOF> postulaciones = new ArrayList<>();


    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_empresa", nullable = false)
    private EmpresaOF empresa;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Double getRemuneracion() {
		return remuneracion;
	}

	public void setRemuneracion(Double remuneracion) {
		this.remuneracion = remuneracion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaDatosPublicacion) {
		this.fechaAlta = fechaDatosPublicacion;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaActual) {
		this.fechaBaja = fechaActual;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public EmpresaOF getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaOF empresa) {
		this.empresa = empresa;
	}
	public List<PostulacionOF> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<PostulacionOF> postulaciones) {
        this.postulaciones = postulaciones;
    }
    
    
}
