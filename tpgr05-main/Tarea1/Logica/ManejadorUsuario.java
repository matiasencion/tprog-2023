package Logica;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Vector;

public class ManejadorUsuario {

    private static ManejadorUsuario instancia = null;
    private Map<String, Usuario> usuarios;
    private Map<String, Empresa> empresas;// va por stributo Empresa
    private Map<String, Postulante> postulantes;// va por nickname
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

    public void agregarUsuario(Usuario u) {
        usuarios.put(u.getNickname(), u);
        usuariosPorEmail.put(u.getEmail(), u);
        if (u instanceof Postulante) {
            postulantes.put(u.getNickname(), (Postulante) u);
        } else {
            empresas.put(((Empresa)u).getNickname(), (Empresa) u);
        }
    }

    public Usuario getUsuario(String nickname) {
        return usuarios.get(nickname);
    }

    public Usuario getUsuarioPorEmail(String email) {
        return usuarios.get(email);
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
        return usuarios.containsKey(nickname);
    }

    public boolean existeUsuarioPorEmail(String email) {
        return usuariosPorEmail.containsKey(email);
    }

    /*public void eliminarUsuario(String nickname) {
    	Usuario u = usuarios.get(nickname);
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



    public Vector<String> listarUsuarios() {
        Vector<String> usuarios = new Vector<String>();
        for (Usuario usuario : this.usuarios.values()) {
            usuarios.add(usuario.getNickname());
        }
        return usuarios;
    }
    
    public Vector<String> listarEmpresas() {
        Vector<String> e = new Vector<String>();
        for (String s : empresas.keySet()) {
            e.add(s);
        }
        return e;
    }

   
    public Vector<String> getOfertas(Usuario u) {
    
         System.out.println(u);
         Vector<String> ofertas = new Vector<String>();
  


        
          if( u instanceof Empresa) {
              Empresa e = (Empresa) u;
               ofertas = e.getOfertas();

              if(e.getOfertas().isEmpty()) {
                  System.out.println("No hay ofertas");
              }
             
              return ofertas;
          }else {
            if(u instanceof Postulante) {
                Postulante p = (Postulante) u;
               ofertas = p.listarPostulaciones(); 
              
                return ofertas;
              }

            }
       
    
    	return ofertas;
    }

    public Vector<String> listarPostulantes(){
        Vector<String> postulantes = new Vector<String>();
        for (Postulante postulante : this.postulantes.values()) {
            postulantes.add(postulante.getNickname());
        }
        return postulantes;
	}
 
     /*public void getInfo(String nickname){
        Usuario u = usuarios.get(nickname);
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
	
	public void modificarUsuario(String nickname, String nombre, String Apellido, String email) {
		usuariosPorEmail.remove(getUsuario(nickname).getEmail());
		usuariosPorEmail.put(email, getUsuario(nickname));
		getUsuario(nickname).modificar(nombre, Apellido);
	}

	public void clear() {
		usuarios.clear();
		empresas.clear();
		postulantes.clear();
		usuariosPorEmail.clear();
	}
}

