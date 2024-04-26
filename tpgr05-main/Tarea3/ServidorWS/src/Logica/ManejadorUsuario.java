package Logica;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Vector;

import types.DTUsuario;

public class ManejadorUsuario {

    private static ManejadorUsuario instancia = null;
    private Map<String, Usuario> usuarios;
    private Map<String, Empresa> empresas; // va por stributo Empresa
    private Map<String, Postulante> postulantes; // va por nickname
    private Map<String, Usuario> usuariosPorEmail;
 

    private ManejadorUsuario() {
        usuarios = new HashMap<String, Usuario>();
        usuariosPorEmail = new HashMap<String, Usuario>();
        postulantes = new HashMap<String, Postulante>();
        empresas = new HashMap<String, Empresa>();

    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario user) {
        usuarios.put(user.getNickname(), user); 
        usuariosPorEmail.put(user.getEmail(), user);
        if (user instanceof Postulante) {
            postulantes.put(user.getNickname(), (Postulante) user);
        } else {
            empresas.put(((Empresa) user).getNickname(), (Empresa) user);
        }
    }

    public Usuario getUsuario(String nickname) {
    	if (usuarios.get(nickname) != null)
    		return usuarios.get(nickname);
    	else if (usuariosPorEmail.get(nickname) != null)
    		return usuariosPorEmail.get(nickname);
    	else return null;
    }

    public Usuario getUsuarioPorEmail(String email) {
        return usuariosPorEmail.get(email);
    }

    public Set<DTUsuario> getUsuarios() {
        Set<DTUsuario> usuarios = new HashSet<DTUsuario>();
        for (Usuario usuario : this.usuarios.values()) {
            usuarios.add(usuario.getDT());
        }
        return usuarios;

    }

    public Collection<Usuario> getUsuariosPorEmail() {
        return usuariosPorEmail.values();
    }

    public boolean existeUsuario(String nickname) {
        return usuarios.containsKey(nickname) || usuariosPorEmail.containsKey(nickname);
    }

    public boolean existeUsuarioPorEmail(String email) {
        return usuariosPorEmail.containsKey(email);
    }

    /*public void eliminarUsuario(String nickname) {
    	Usuariouser= usuarios.get(nickname);
        String email = u.getEmail();
        if(u instanceof Empresa) {
        	empresas.remove(((Empresa)u).getEmpresa());
        }
        postulantes.remove(nickname);
        usuarios.remove(nickname);
        usuariosPorEmail.remove(email);
    }*/

    public boolean emailExist(String email) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

public Collection<Empresa> getEmpresas() {
	return empresas.values();
}


    public Collection<String> listarUsuarios() {
    	Collection<String> usuarios = new Vector<String>();
        for (Usuario usuario : this.usuarios.values()) {
            usuarios.add(usuario.getNickname());
        }
        return usuarios;
    }
    
    public Collection<String> listarEmpresas() {
    	Collection<String> empre = new Vector<String>();
        for (String s : empresas.keySet()) {
        	empre.add(s);
        }
        return empre;
    }

   
    public Collection<String> getOfertas(Usuario user) {
    
    	Collection<String> ofertas = new Vector<String>();
  


        
          if ( user instanceof Empresa) {
              Empresa empresa = (Empresa) user;
               ofertas = empresa.getOfertas();

              if (empresa.getOfertas().isEmpty()) {
                  System.out.println("No hay ofertas");
              }
             
              return ofertas;
          }else {
            if (user instanceof Postulante) {
                Postulante postu = (Postulante) user;
               ofertas = postu.listarPostulaciones(); 
              
                return ofertas;
              }

            }
       
    
    	return ofertas;
    }
    
    public Collection<String> getOfertasIngresadas(Usuario user) {
        
     
        Collection<String> ofertas = new Vector<String>();
         if (user instanceof Empresa) {
             Empresa empresa = (Empresa) user;
              ofertas = empresa.getOfertasIngresadas();

             if (empresa.getOfertas().isEmpty()) {
                 System.out.println("No hay ofertas");
             }          
             return ofertas;
         }else {
           if (user instanceof Postulante) {
               Postulante postu = (Postulante) user;
              ofertas = postu.listarPostulaciones();             
               return ofertas;
             }
           }   
   	return ofertas;
   }

    public Collection<String> listarPostulantes(){
    	Collection<String> postulantes = new Vector<String>();
        for (Postulante postulante : this.postulantes.values()) {
            postulantes.add(postulante.getNickname());
        }
        return postulantes;
	}
 
     /*public void getInfo(String nickname){
        Usuariouser= usuarios.get(nickname);
        if(u instanceof Empresa){
            Empresa e = (Empresa) u;
            e.getEmpresa();
            e.getDescripcion();
            e.getLink();
        }else{
            Postulante p = (Postulante) u;
            p.getNombre();
            p.getApellido();
            p.getEmail();
            p.getFechaNac();
            p.getNacionalidad();
        }
     }*/

	public DTUsuario getInfo(String nickname) {
		return getUsuario(nickname).getInfo();
	}
	
	public void modificarEmpresa(String nickname, String nombre, String Apellido, String email, String contrasena, String foto, String empresa, String descripcion, String link) {
		usuariosPorEmail.remove(getUsuario(nickname).getEmail());
		usuariosPorEmail.put(email, getUsuario(nickname));
		((Empresa) getUsuario(nickname)).modificarEmpresa(nombre, Apellido, contrasena, foto, empresa, descripcion, link);
	}

	public void clear() {
		usuarios.clear();
		empresas.clear();
		postulantes.clear();
		usuariosPorEmail.clear();
	}

	public void modificarPostulante(String nickname, String nombre, String apellido, String email, String contrasena,
			String foto, LocalDate date, String nacionalidad) {
		usuariosPorEmail.remove(getUsuario(nickname).getEmail());
		usuariosPorEmail.put(email, getUsuario(nickname));
		((Postulante) getUsuario(nickname)).modificarPostulante(nombre, apellido, contrasena, foto, date, nacionalidad);
	}
}

