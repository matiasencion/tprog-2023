package com.trabajouy.model;

public class DTUsuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String foto;

    public DTUsuario(Usuario user) {
        this.nickname = user.getNickname();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
        this.foto = user.getFoto();
    }
    
    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }
    
    public String getFoto() {
        return foto;
    }

}

