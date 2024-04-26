package com.trabajouy.model;

public class DTEmpresa extends DTUsuario {
	    
    private String empresa;
    private String descripcion;
    private String link;
		
    public DTEmpresa(Empresa user) {
        super(user);
        this.empresa = user.getEmpresa();
        this.descripcion = user.getDescripcion();
        this.link = user.getLink();
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
}


