package ofertasFinalizadas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
@Entity
@Table(name = "empresas")
@PrimaryKeyJoinColumn(name = "id_usuario")

public class EmpresaOF extends Usuario {
    public EmpresaOF() {
		super();
		
	}

    @Column(nullable = false, length = 1000) // Cambia 1000 por el tamaño que necesites
    private String descripcion;

    @Column(nullable = false)
    private String sitioWeb;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

}

