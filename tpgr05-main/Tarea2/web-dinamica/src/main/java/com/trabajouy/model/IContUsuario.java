package com.trabajouy.model;

import java.util.Collection;


import java.util.Set;

import com.trabajouy.exceptions.OfertaNoExisteException;
import com.trabajouy.exceptions.UsuarioEmailRepetido;
import com.trabajouy.exceptions.UsuarioNoExisteException;
import com.trabajouy.exceptions.UsuarioRepetidoException;

public interface IContUsuario {

        public abstract void altaEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String foto, String empresa,
                        String descripcion, String link) throws UsuarioRepetidoException;

        public abstract void CrearPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, String foto,
                        String FechaNac,
                        String nacionalidad) throws UsuarioRepetidoException;

        public abstract Usuario getUsuario(String nickname) throws UsuarioNoExisteException;

        public abstract void modificarEmpresa(String nickname,String nombre, String apellido, String contraseña, String foto, String empresa, String descripcion, String link) throws UsuarioEmailRepetido;
        public abstract void modificarPostulante(String nickname,String nombre, String apellido, String contraseña, String foto, String fecha, String nacionalidad) throws UsuarioEmailRepetido;
        
        public abstract Set<DTUsuario> getUsuarios();

        public abstract Collection<String> listarUsuarios();
        
        public abstract Collection<String> getNombresEmpresas();
        
        public abstract Collection<String> getNombresPostulantes();

        public abstract Collection<String> getNombresOfertas(String nickname);
        public abstract Collection<String> getNombresOfertasIngresadas(String nickname);
        public abstract Collection<String> getNombreOfertasConfirmadas(String nickname);

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
     
		public abstract Collection<DTOferta> getOfertasSinPostular (String nickname)throws UsuarioNoExisteException, OfertaNoExisteException;
        


        

        

}
