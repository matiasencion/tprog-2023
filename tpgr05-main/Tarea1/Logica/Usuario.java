
package Logica;
import Logica.*;
public abstract class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;

    public Usuario(String nickname, String nombre, String apellido, String email) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
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

    public void modificar(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public abstract DTUsuario getDT();

	public DTUsuario getInfo() {
		if (this instanceof Empresa)
			return new DTEmpresa((Empresa)this);
		else
			return new DTPostulante((Postulante)this);
	}

}
