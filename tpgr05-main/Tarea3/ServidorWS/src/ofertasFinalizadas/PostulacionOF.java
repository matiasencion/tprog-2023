package ofertasFinalizadas;

import java.time.LocalDate;

import Logica.Postulante;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.MapsId;


@Entity
@Table(name = "postulaciones")
public class PostulacionOF {
    @EmbeddedId
    private PostulacionId id;

    @MapsId("idPostulante") 
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_postulante", referencedColumnName = "id")
    private Usuario postulante;

    @MapsId("idOferta") 
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_oferta", referencedColumnName = "id")
    private OfertaFinalizada oferta;

    @Column(nullable = false)
    private String cv;

    @Column(nullable = false)
    private String motivacion;

    @Column(name = "fecha_postulacion", nullable = false)
    private LocalDate fechaPostulacion;

	public PostulanteOF getPostulante() {
		return (PostulanteOF) postulante;
	}

	public void setPostulante(PostulanteOF post) {
		this.postulante = post;
	}

	public OfertaFinalizada getOferta() {
		return oferta;
	}

	public void setOferta(OfertaFinalizada oferta) {
		this.oferta = oferta;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	public LocalDate getFechaPostulacion() {
		return fechaPostulacion;
	}

	public void setFechaPostulacion(LocalDate fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}

	public void setId(PostulacionId postulacionId) {
		id= postulacionId;
		
	}

}
