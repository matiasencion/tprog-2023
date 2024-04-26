package Logica;

import java.awt.dnd.DragGestureEvent;
import java.time.*;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import Excepciones.*;

import java.util.Set;
import java.util.Vector;

import Logica.Publicar.ESTADO;
import types.*;

public class ContUsuario implements IContUsuario {
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ContUsuario() {
    }

    public void altaEmpresa(String nickname, String nombre, String apellido, String email, String contrasena, String foto,String empresa,
            String descripcion, String link) throws UsuarioRepetidoException {

        if (ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
        }
        if (ManejadorUsuario.getInstancia().emailExist(email)) {
            throw new UsuarioRepetidoException("El email " + email + " ya existe");
        }

        Empresa empresaNueva = new Empresa(nickname, nombre, apellido, email, contrasena, foto, empresa, descripcion, link);

        ManejadorUsuario.getInstancia().agregarUsuario(empresaNueva);
    }

    public void CrearPostulante(String nickname, String nombre, String apellido, String email, String contrasena, String foto, String FechaNac,
            String nacionalidad) throws UsuarioRepetidoException {

        if (ManejadorUsuario.getInstancia().existeUsuario(nickname)) {
            throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
        }
        if (ManejadorUsuario.getInstancia().emailExist(email)) {
            throw new UsuarioRepetidoException("El email " + email + " ya existe");
        }
        

        LocalDate fecha = LocalDate.parse(FechaNac,dateFormatter);
        Postulante postulanteNuevo = new Postulante(nickname, nombre, apellido, email, contrasena, foto, fecha, nacionalidad);

        ManejadorUsuario.getInstancia().agregarUsuario(postulanteNuevo);

    }

    public Usuario getUsuario(String nickname) throws UsuarioNoExisteException{
    	String nick = nickname.toLowerCase();
             if (!ManejadorUsuario.getInstancia().existeUsuario(nick)) {
            throw new UsuarioNoExisteException("El usuario " + nick + " no existe");
        }
        return ManejadorUsuario.getInstancia().getUsuario(nick);
    
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
   
    public void modificarEmpresa(String nickname,String nombre, String apellido, String contrasena, String foto, String empresa, String descripcion, String link) {
        Empresa usuarioActual = (Empresa) ManejadorUsuario.getInstancia().getUsuario(nickname);
        if (nombre == "") {
            nombre = usuarioActual.getNombre();
        }
        
        if (apellido == "") {
            apellido = usuarioActual.getApellido();
        }
        if (contrasena == "") {
        	contrasena = usuarioActual.getCon();
        }
        if (foto == "") {
        	foto = usuarioActual.getFoto();
        }
        if (empresa == "") {
        	empresa = usuarioActual.getEmpresa();
        }
        if (descripcion == "") {
        	descripcion = usuarioActual.getDescripcion();
        }
        if (link == "") {
        	link = usuarioActual.getLink();
        }
        ManejadorUsuario.getInstancia().modificarEmpresa(nickname ,nombre, apellido, usuarioActual.getEmail(), contrasena, foto, empresa, descripcion, link);
    }
    
    public void modificarPostulante(String nickname,String nombre, String apellido, String contrasena, String foto, String fecha, String nacionalidad) {
        Postulante usuarioActual = (Postulante) ManejadorUsuario.getInstancia().getUsuario(nickname);
        if (nombre == "") {
            nombre = usuarioActual.getNombre();
        }
        
        if (apellido == "") {
            apellido = usuarioActual.getApellido();
        }
        if (contrasena == "") {
        	contrasena = usuarioActual.getCon();
        }
        if (foto == "") {
        	foto = usuarioActual.getFoto();
        }
        LocalDate date;
        if (fecha == "") {
        	date = usuarioActual.getFechaNac();
        } else {
        	date = LocalDate.parse(fecha,dateFormatter);
        }
        if (nacionalidad == "") {
        	nacionalidad = usuarioActual.getNacionalidad();
        }
        ManejadorUsuario.getInstancia().modificarPostulante(nickname ,nombre, apellido, usuarioActual.getEmail(), contrasena, foto, date, nacionalidad);
    }

    public Collection<String> listarUsuarios() {

        ManejadorUsuario manejador = ManejadorUsuario.getInstancia();

        return manejador.listarUsuarios();

    }

    public Collection<String> getNombresEmpresas() {
    	return ManejadorUsuario.getInstancia().listarEmpresas();
    }
    
    public String infoUsuario(String nickname) {

        Usuario usuario = ManejadorUsuario.getInstancia().getUsuario(nickname);

        if (usuario instanceof Empresa) {
            Empresa empresa = (Empresa) usuario;
            String info = "Nombre: " + empresa.getNombre() + "\n" + "Apellido: " + empresa.getApellido() + "\n" + "Email: "
                    + empresa.getEmail() + "\n" + "Contrasena: " + usuario.getCon() + "\n" + "Foto: " + usuario.getFoto() + "\n" + "Empresa: " + empresa.getEmpresa() + "\n" + "Descripcion: "
                    + empresa.getDescripcion() + "\n" + "Link: " + empresa.getLink();
            return info;
        } else {
            Postulante postulante = (Postulante) usuario;
            String info = "Nombre: " + usuario.getNombre() + "\n" + "Apellido: " + usuario.getApellido() + "\n"
                    + "Email: " + usuario.getEmail() + "\n" + "Contrasena: " + usuario.getCon() + "\n" + "Foto: " + usuario.getFoto() + "\n" + "Fecha de Nacimiento: "
                    + postulante.getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString()
 + "\n" + "Nacionalidad: " + postulante.getNacionalidad();
            return info;
        }

    }
    
    public Collection<String> getNombreOfertasConfirmadas(String nickname){
    	 ManejadorUsuario manejador = ManejadorUsuario.getInstancia();
    	 Collection<String> ofertas = new Vector<String>();
         Usuario user = manejador.getUsuario(nickname);
         if(user instanceof Empresa) {
             Empresa empre = (Empresa) user;
              ofertas = empre.getOfertasConfirmadas();
         }else {
           if(user instanceof Postulante) {
               Postulante postu = (Postulante) user;
              ofertas = postu.listarPostulaciones(); 
             }
           }
   	return ofertas;
   }
    
    public Collection<String> getNombreOfertasConfirmadasVencidas(String nickname){
   	 ManejadorUsuario manejador = ManejadorUsuario.getInstancia();
   	 Collection<String> ofertas = new Vector<String>();
        Usuario user = manejador.getUsuario(nickname);
        if(user instanceof Empresa) {
            Empresa empre = (Empresa) user;
             ofertas = empre.getOfertasConfirmadasVencidas();
        }else {
          if(user instanceof Postulante) {
              Postulante postu = (Postulante) user;
             ofertas = postu.listarPostulaciones(); 
            }
          }
  	return ofertas;
  }

    public Collection<String> getNombresOfertas(String nickname) {
        ManejadorUsuario manejador = ManejadorUsuario.getInstancia();
        Usuario usuario = manejador.getUsuario(nickname);

    	return manejador.getOfertas(usuario);
    }
    
    public Collection<String> getNombresOfertasIngresadas(String nickname) {
        ManejadorUsuario manejador = ManejadorUsuario.getInstancia();
        Usuario usuario = manejador.getUsuario(nickname);

    	return manejador.getOfertasIngresadas(usuario);
    }
    
    public Collection<String> getNombresPostulantes() {
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

	//Esto no se si va aca
	public boolean inicioSesion(String usuario, String clave) throws Exception {
		Usuario aux;
		
		if (ManejadorUsuario.getInstancia().getUsuario(usuario) != null) {
			aux = ManejadorUsuario.getInstancia().getUsuario(usuario);
		} else if (ManejadorUsuario.getInstancia().getUsuarioPorEmail(usuario) != null){
			aux  = ManejadorUsuario.getInstancia().getUsuarioPorEmail(usuario);
		} else {
			throw new Exception("El usuario no está registrado");
		}
		if (!aux.getCon().equals(clave)) {
			throw new Exception("La contrasena está mal");
		}
		return true;
	}
	
	public boolean esEmpresa(String nickname) throws UsuarioNoExisteException {
		return (getUsuario(nickname) instanceof Empresa);
	}
	
	public boolean esPostulante(String nickname) throws UsuarioNoExisteException {
		return (getUsuario(nickname) instanceof Postulante);
	}
	
	public DTPostulacion getInfoPostulacion(String nickname, String oferta) throws UsuarioNoExisteException {
		if (getUsuario(nickname) instanceof Postulante) {
			Map<String, Postulacion> mapa = ((Postulante) getUsuario(nickname)).getPostulaciones();
			if (mapa.containsKey(oferta))
				return mapa.get(oferta).getInfo();
			else
				return null;
		}
		return null;
	}
	
	public boolean estaPostulado(String nickname, String oferta) throws UsuarioNoExisteException {
		if (getUsuario(nickname) instanceof Postulante) {
			Map<String, Postulacion> mapa = ((Postulante) getUsuario(nickname)).getPostulaciones();
			return mapa.containsKey(oferta);
		}
		return false;
	}
	
	public Collection<DTPostulacion> getPostulaciones(String nickname) throws UsuarioNoExisteException {
		Collection<DTPostulacion> resu = new Vector<DTPostulacion>();
		if (getUsuario(nickname) instanceof Postulante) {
			Map<String, Postulacion> mapa = ((Postulante) getUsuario(nickname)).getPostulaciones();
			for(Postulacion pos : mapa.values()) {
				resu.add(pos.getInfo());
			}
		}
		return resu;
	}
	
	public Collection<DtOferta> getOfertasSinPostular (String nickname) throws UsuarioNoExisteException, OfertaNoExisteException {
		Collection<DtOferta> resu = new Vector<DtOferta>();
		if (getUsuario(nickname)instanceof Postulante) {
			IContOferta ContO = Fabrica.getInstancia().getIContOferta();
			Collection<String> aux = ContO.listarOfertas();
			Postulante postu = (Postulante) getUsuario(nickname);
			for(String oferta : postu.getPostulaciones().keySet()) {
				if(aux.contains(oferta)) {
					aux.remove(oferta);
				}
			}
			for(String dto : aux) {
				if(ContO.getOferta(dto).getDatosPublicacion().getEstado() == ESTADO.Confirmado && ContO.getOferta(dto).getDatosPublicacion().vencido())
				resu.add(ContO.getDTOferta(dto));
			}
		}
		return resu;
	}
	
	public  DTUsuario getDtUsuario(String nickname)throws UsuarioNoExisteException{
		
    	String nick = nickname.toLowerCase();
        if (!ManejadorUsuario.getInstancia().existeUsuario(nick)) {
       throw new UsuarioNoExisteException("El usuario " + nick + " no existe");
   }
   return ManejadorUsuario.getInstancia().getUsuario(nick).getDT();

   }
		
	public void agregarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException {
		IContOferta ContO = Fabrica.getInstancia().getIContOferta();
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(postulante);
		if (user  instanceof Postulante) {
			if(!esOfertaFavorita(postulante, oferta))
				((Postulante)user).agregarFavoritos(ContO.getOferta(oferta));
		}
	}
	
	public void quitarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException {
		IContOferta ContO = Fabrica.getInstancia().getIContOferta();
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(postulante);
		if (user  instanceof Postulante) {
			if(esOfertaFavorita(postulante, oferta))
				((Postulante)user).quitarFavoritos(ContO.getOferta(oferta));
		}
	}
	
	public Collection<DtOferta> getOfertasFavoritas (String postulante) {
		Collection<DtOferta> resu = new Vector<DtOferta>();
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(postulante);
		if (user  instanceof Postulante) {
			for (Oferta oferta : ((Postulante)user).getFavoritos()) {
				resu.add(oferta.getInfo());
			}
			return resu;
		} else 
				return resu;
	}
		
	//DEVUELVE TRUE SI LA OFERTA YA ESTA MARCADA COMO FAVORITA
	public boolean esOfertaFavorita(String postulante, String oferta) throws OfertaNoExisteException {
		IContOferta ContO = Fabrica.getInstancia().getIContOferta();
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(postulante);
		if (user  instanceof Postulante) {
			return ((Postulante)user).getFavoritos().contains(ContO.getOferta(oferta));
		}
		return true;
	}
	
	public  void seguirUsuario(String usuario, String usuarioAseguir) {
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(usuario);
		Usuario usuarioSeguir =  ManejadorUsuario.getInstancia().getUsuario(usuarioAseguir);
		
		user.agregarSeguido(usuarioAseguir);
		usuarioSeguir.agregarSeguidor(usuario);
		
	}
	
	public  void dejarSeguirUsuario(String usuario, String usuarioAseguir) {
		
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(usuario);
		Usuario usuarioSeguir =  ManejadorUsuario.getInstancia().getUsuario(usuarioAseguir);
		user.quitarSeguido(usuarioAseguir);
		usuarioSeguir.quitarSeguidor(usuario);
		
	}
	
	//DEVUELVE TRUE SI YA sigue al usuario
	public boolean sigueAUsuario(String usuario, String usuarioASeguir) throws UsuarioNoExisteException {
		IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
		Usuario user = ManejadorUsuario.getInstancia().getUsuario(usuario);
		return user.getSeguidos().contains(usuarioASeguir);
	}
	
	
	public boolean existeNickname(String nickname) {
			return ManejadorUsuario.getInstancia().existeUsuario(nickname);
	}
	
	public boolean existeEmail(String email) {
			return ManejadorUsuario.getInstancia().emailExist(email);
	}
	
	public Collection<DTEmpresa> barraDeBusquedaEmpresa(String busqueda) {
		List<DTEmpresa> resu = new ArrayList<DTEmpresa>();
		for (Empresa empresa : ManejadorUsuario.getInstancia().getEmpresas()) {
			DTEmpresa dtEmpresa = (DTEmpresa) empresa.getDT();
			if (dtEmpresa.getNickname().contains(busqueda) || dtEmpresa.getDescripcion().contains(busqueda)) {
				resu.add(dtEmpresa);
			}
		}
		return resu;
	}
	
}
