
package Logica;


public abstract class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String foto;

    public Usuario(String nickname, String nombre, String apellido, String email, String contraseña, String foto) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contraseña;
        this.foto = foto;
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

    public void modificar(String nombre, String apellido, String contraseña, String foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contraseña;
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


}
