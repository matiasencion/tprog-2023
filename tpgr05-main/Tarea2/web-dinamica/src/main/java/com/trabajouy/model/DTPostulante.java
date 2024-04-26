package com.trabajouy.model;

import java.time.LocalDate;

public class DTPostulante extends DTUsuario {
    private String nacionalidad;
    private LocalDate FechaNac;
    
    public DTPostulante(Postulante user) {
        super(user); 
        this.nacionalidad = user.getNacionalidad();
        this.FechaNac = user.getFechaNac();
    }
    
    public String getNacionalidad() {
        return nacionalidad;
    }

    
    public LocalDate getFechaNac() {
        return FechaNac;
    }

}

