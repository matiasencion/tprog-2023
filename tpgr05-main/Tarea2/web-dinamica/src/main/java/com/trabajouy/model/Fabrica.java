package com.trabajouy.model;


public class Fabrica {

    private static Fabrica instancia = null;

    private Fabrica() {
    }

    public static Fabrica getInstancia() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IContUsuario getIContUsuario() {
        return new ContUsuario();
    }

    public IContOferta getIContOferta() {
        return new ContOferta();

    }

}
