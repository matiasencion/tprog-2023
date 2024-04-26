package ofertasFinalizadas;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PostulacionId implements Serializable {
    private static final long serialVersionUID = 1L;
	private Long idPostulante;
    private Long idOferta;

    public PostulacionId() {
    }

    // Constructor con parámetros
    public PostulacionId(Long idPostulante, Long idOferta) {
        this.idPostulante = idPostulante;
        this.idOferta = idOferta;
    }

    // Getters y setters
    public Long getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(Long idPostulante) {
        this.idPostulante = idPostulante;
    }

    public Long getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
    }

    // Implementación de equals basada en idPostulante e idOferta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostulacionId)) return false;
        PostulacionId that = (PostulacionId) o;
        return Objects.equals(getIdPostulante(), that.getIdPostulante()) &&
               Objects.equals(getIdOferta(), that.getIdOferta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPostulante(), getIdOferta());
    }

    @Override
    public String toString() {
        return "PostulacionId{" +
                "idPostulante=" + idPostulante +
                ", idOferta=" + idOferta +
                '}';
    }
}
