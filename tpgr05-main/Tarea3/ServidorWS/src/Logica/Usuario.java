
package Logica;

import java.util.Collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import types.DTEmpresa;
import types.DTPostulante;
import types.DTUsuario;

import java.util.Vector;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String foto;
    private Vector<String> seguidos;
    private Vector<String> seguidores;


    public Usuario(String nickname, String nombre, String apellido, String email, String contrasena, String foto) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.foto = foto;
        this.seguidos = new Vector<String>();
        this.seguidores = new Vector<String>();

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

    public void modificar(String nombre, String apellido, String contrasena, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.foto = foto;
    }

    public abstract DTUsuario getDT();
    

	public DTUsuario getInfo() {
		if (this instanceof Empresa)
			return new DTEmpresa((Empresa) this);
		else
			return new DTPostulante((Postulante) this);
	}

	public String getCon() {
		return contrasena;
	}

	public String getFoto() {
		return foto;
	}
	
	public String getTipo(String nickname) {
		 
		String tipo = "EMPRESA";
	if (this instanceof Empresa) {
		return tipo;
	}else {
		tipo = "POSTULANTE";
		
		return tipo;
	}
		
		
		
	}

	public void agregarSeguidor(String seguidor) {
		if (!seguidores.contains(seguidor))
			seguidores.add(seguidor);
	}
	
	public void agregarSeguido(String seguido) {
		if (!seguidos.contains(seguido))
			seguidos.add(seguido);
	}
	
	public void quitarSeguidor(String seguidor) {
		if (seguidores.contains(seguidor))
			seguidores.remove(seguidor);
	}
	
	public void quitarSeguido(String seguido) {
		if (seguidos.contains(seguido))
			seguidos.remove(seguido);
	}
	
	public Collection<String> getSeguidos() {
		return seguidos;
	}

	public Collection<String> getSeguidores() {
		return seguidores;
	}
	
/*	public Collection<DTUsuario> getDTSeguidos() {
		Vector<DTUsuario> resu = new Vector<DTUsuario>();
		for (Usuario user : this.seguidos) {
			resu.add(new DTUsuario(user));
		}
		return resu;
	}

	public Collection<DTUsuario> getDTSeguidores() {
		Vector<DTUsuario> resu = new Vector<DTUsuario>();
		for (Usuario user : this.seguidores) {
			resu.add(new DTUsuario(user));
		}
		return resu;
	}
*/



}
