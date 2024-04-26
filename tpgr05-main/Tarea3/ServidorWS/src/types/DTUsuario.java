package types;


import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import Logica.Usuario;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DTPostulante.class})
public class DTUsuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String foto;
    private Collection<String> seguidos;
    private Collection<String> seguidores;

    public DTUsuario(Usuario user) {
        this.nickname = user.getNickname();
        this.nombre = user.getNombre();
        this.apellido = user.getApellido();
        this.email = user.getEmail();
        this.foto = user.getFoto();
        this.seguidos = user.getSeguidos();
        this.seguidores = user.getSeguidores();
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
    
    public Collection<String> getSeguidos(){
    	return seguidos;
    }
    public Collection<String> getSeguidores(){
    	return seguidores;
    }
}

