package Logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
*
* @author Igui
*/

@XmlAccessorType(XmlAccessType.FIELD)
public enum EstadoSesion {
    VISITANTE,           // nunca intentó iniciar sesión
    POSTULANTE,     // tiene la sesión iniciada
    EMPRESA,
    BAD_LOGIN
}
