package types;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Logica.Postulante;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTPostulante extends DTUsuario {
    private String nacionalidad;
    private LocalDate FechaNac;
    private String fechaB;
    private String fechaA;
    
    public DTPostulante(Postulante user) {
        super(user); 
        this.nacionalidad = user.getNacionalidad();
        this.FechaNac = user.getFechaNac();
        this.fechaB = user.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        this.fechaA = user.getFechaNac().toString();
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }

    
    public LocalDate getFechaNac() {
        return FechaNac;
    }
    
    public String getFechab() {
    	return fechaB;
    }
   public String getFechaA() {
	   return fechaA;
   }
}

