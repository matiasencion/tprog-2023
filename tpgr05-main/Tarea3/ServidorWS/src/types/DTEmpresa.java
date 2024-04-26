package types;

import java.time.LocalDate;

import Logica.Empresa;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTEmpresa extends DTUsuario {
	    
    private String empresa;
    private String descripcion;
    private String link;
    private String fechaUltimaOferta;
		
    public DTEmpresa(Empresa user) {
        super(user);
        this.empresa = user.getEmpresa();
        this.descripcion = user.getDescripcion();
        this.link = user.getLink();
        if(user.getFechaUltimaOferta() != null) {
        	this.fechaUltimaOferta = user.getFechaUltimaOferta().toString();
        } else {
        	this.fechaUltimaOferta = LocalDate.now().minusDays(30).toString();
        }
    }
	    
    public String getEmpresa() {
        return empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getLink() {
        return link;
    }
    
    public String getFechaUltimaOferta() {
    	return fechaUltimaOferta;
    }
}


