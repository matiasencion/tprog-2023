package Logica;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import excepciones.*;
import Logica.DTUsuario;
import java.util.Set;
import java.util.Vector;

public class ContUsuario implements IContUsuario {
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ContUsuario() {
    }

    public void altaEmpresa(String nickname, String nombre, String apellido, String email, String empresa,
            String descripcion, String link) throws UsuarioRepetidoException {

        if (ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
        }
        if (ManejadorUsuario.getInstancia().emailExist(email)) {
            throw new UsuarioRepetidoException("El email " + email + " ya existe");
        }

        Empresa empresaNueva = new Empresa(nickname, nombre, apellido, email, empresa, descripcion, link);

        ManejadorUsuario.getInstancia().agregarUsuario(empresaNueva);
    }

    public void CrearPostulante(String nickname, String nombre, String apellido, String email, String FechaNac,
            String nacionalidad) throws UsuarioRepetidoException {

        if (ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
        }
        if (ManejadorUsuario.getInstancia().emailExist(email)) {
            throw new UsuarioRepetidoException("El email " + email + " ya existe");
        }
        

        LocalDate fecha = LocalDate.parse(FechaNac,dateFormatter);
        System.out.println(fecha);
        Postulante postulanteNuevo = new Postulante(nickname, nombre, apellido, email, fecha, nacionalidad);

        ManejadorUsuario.getInstancia().agregarUsuario(postulanteNuevo);

    }

    public Usuario getUsuario(String nickname) throws UsuarioNoExisteException{
    	

             if (!ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");
        }
        return ManejadorUsuario.getInstancia().getUsuario(nickname);
    
        }
    
    
    public String getNicknameUsuario(String nickname) throws UsuarioNoExisteException {

        if (!ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioNoExisteException("El usuario " + nickname + " no existe");
        }
        return ManejadorUsuario.getInstancia().getUsuario(nickname).getNickname();
    }

    public Set<DTUsuario> getUsuarios() {
        return ManejadorUsuario.getInstancia().getUsuarios();
    }

    public void modificarUsuario(String nickname,String nombre, String apellido) {
        Usuario usuarioActual = ManejadorUsuario.getInstancia().getUsuario(nickname);
        if (nombre == "") {
            nombre = usuarioActual.getNombre();
        }
        
        if (apellido == "") {
            apellido = usuarioActual.getApellido();
        }
        ManejadorUsuario.getInstancia().modificarUsuario(nickname ,nombre, apellido, usuarioActual.getEmail());
    }

    public Vector<String> listarUsuarios() {

        ManejadorUsuario manejador = ManejadorUsuario.getInstancia();

        return manejador.listarUsuarios();

    }

    public Vector<String> getNombresEmpresas() {
    	return ManejadorUsuario.getInstancia().listarEmpresas();
    }
    
    public String infoUsuario(String nickname) {

        Usuario usuario = ManejadorUsuario.getInstancia().getUsuario(nickname);

        if (usuario instanceof Empresa) {
            Empresa empresa = (Empresa) usuario;
            String info = "Nombre: " + empresa.getNombre() + "\n" + "Apellido: " + empresa.getApellido() + "\n" + "Email: "
                    + empresa.getEmail() + "\n" + "Empresa: " + empresa.getEmpresa() + "\n" + "Descripcion: "
                    + empresa.getDescripcion() + "\n" + "Link: " + empresa.getLink();
            return info;
        } else {
            Postulante postulante = (Postulante) usuario;
            String info = "Nombre: " + usuario.getNombre() + "\n" + "Apellido: " + usuario.getApellido() + "\n"
                    + "Email: " + usuario.getEmail() + "\n" + "Fecha de Nacimiento: "
                    + postulante.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
 + "\n" + "Nacionalidad: " + postulante.getNacionalidad();
            return info;
        }

    }

    public Vector<String> getNombresOfertas(String nickname) {
        ManejadorUsuario manejador = ManejadorUsuario.getInstancia();
        Usuario usuario = manejador.getUsuario(nickname);

    	return manejador.getOfertas(usuario);
    }
    
    public Vector<String> getNombresPostulantes() {
    	return ManejadorUsuario.getInstancia().listarPostulantes();
    }

	@Override
	public DTEmpresa getInfoEmpresa(String string) {
		return (DTEmpresa)ManejadorUsuario.getInstancia().getUsuario(string).getInfo();
	}

	@Override
	public DTPostulante getInfoPostulante(String string) {
		return (DTPostulante)ManejadorUsuario.getInstancia().getUsuario(string).getInfo();
	}



}
