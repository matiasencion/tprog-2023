package com.trabajouy.model;

/**
*
* @author Igui
*/
public enum EstadoSesion {
    VISITANTE,           // nunca intentó iniciar sesión
    POSTULANTE,     // tiene la sesión iniciada
    EMPRESA,
    BAD_LOGIN
}
