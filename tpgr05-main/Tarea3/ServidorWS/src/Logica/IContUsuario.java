package Logica;

import java.util.Collection;



import java.util.Set;

import Excepciones.*;
import types.DTEmpresa;
import types.DTPostulacion;
import types.DTPostulante;
import types.DTUsuario;
import types.DtOferta;

public interface IContUsuario {

        public abstract void altaEmpresa(String nickname, String nombre, String apellido, String correo, String contrasena, String foto, String empresa,
                        String descripcion, String link) throws UsuarioRepetidoException;

        public abstract void CrearPostulante(String nickname, String nombre, String apellido, String correo, String contrasena, String foto,
                        String FechaNac,
                        String nacionalidad) throws UsuarioRepetidoException;

        public abstract Usuario getUsuario(String nickname) throws UsuarioNoExisteException;

        public abstract void modificarEmpresa(String nickname,String nombre, String apellido, String contrasena, String foto, String empresa, String descripcion, String link) throws UsuarioEmailRepetido;
        public abstract void modificarPostulante(String nickname,String nombre, String apellido, String contrasena, String foto, String fecha, String nacionalidad) throws UsuarioEmailRepetido;
        
        public abstract Set<DTUsuario> getUsuarios();

        public abstract Collection<String> listarUsuarios();
        
        public abstract Collection<String> getNombresEmpresas();
        
        public abstract Collection<String> getNombresPostulantes();

        public abstract Collection<String> getNombresOfertas(String nickname);
        public abstract Collection<String> getNombresOfertasIngresadas(String nickname);
        public abstract Collection<String> getNombreOfertasConfirmadas(String nickname);
        public abstract Collection<String> getNombreOfertasConfirmadasVencidas(String nickname);

        public abstract String infoUsuario(String nickname);

		public abstract DTEmpresa getInfoEmpresa(String string);

		public abstract DTPostulante getInfoPostulante(String string);
		public abstract String getNicknameUsuario(String nickname) throws UsuarioNoExisteException;
		
		public abstract boolean inicioSesion(String usuario, String clave) throws Exception;
		
		public abstract boolean esEmpresa(String nickname) throws UsuarioNoExisteException;
		public abstract boolean esPostulante(String nickname) throws UsuarioNoExisteException;
		public abstract DTPostulacion getInfoPostulacion(String nickname, String oferta) throws UsuarioNoExisteException;
		public abstract Collection<DTPostulacion> getPostulaciones(String nickname) throws UsuarioNoExisteException;
		public abstract boolean estaPostulado(String nickname, String oferta) throws UsuarioNoExisteException;
     
		public abstract Collection<DtOferta> getOfertasSinPostular (String nickname)throws UsuarioNoExisteException, OfertaNoExisteException;
		
		public abstract DTUsuario getDtUsuario(String nickname)throws UsuarioNoExisteException;
		
		public abstract void agregarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException;
		
		public abstract void quitarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException;
		
		public abstract Collection<DtOferta> getOfertasFavoritas (String postulante);
        
		public abstract boolean esOfertaFavorita(String postulante, String oferta) throws OfertaNoExisteException;
		
		public abstract void seguirUsuario(String usuario, String usuarioAseguir);
		
		public abstract void dejarSeguirUsuario(String usuario, String usuarioAseguir);

		public abstract boolean sigueAUsuario(String usuario, String usuarioASeguir) throws UsuarioNoExisteException; 

		public abstract boolean existeEmail(String email);
		
		public abstract boolean existeNickname(String nickname);
		public abstract Collection<DTEmpresa> barraDeBusquedaEmpresa(String busqueda);
        

        

}
