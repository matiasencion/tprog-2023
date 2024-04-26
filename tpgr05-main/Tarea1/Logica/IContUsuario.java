package Logica;

import java.util.Collection;
import java.util.Set;
import java.util.Vector;

import excepciones.UsuarioEmailRepetido;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public interface IContUsuario {

        public abstract void altaEmpresa(String nickname, String nombre, String apellido, String correo, String empresa,
                        String descripcion, String link) throws UsuarioRepetidoException;

        public abstract void CrearPostulante(String nickname, String nombre, String apellido, String correo,
                        String FechaNac,
                        String nacionalidad) throws UsuarioRepetidoException;

        public abstract Usuario getUsuario(String nickname) throws UsuarioNoExisteException;

        public void modificarUsuario(String nickname, String nombre, String apellido) throws UsuarioEmailRepetido;
        
        public abstract Set<DTUsuario> getUsuarios();

        public abstract Vector<String> listarUsuarios();
        
        public abstract Vector<String> getNombresEmpresas();
        
        public abstract Vector<String> getNombresPostulantes();

        public abstract Vector<String> getNombresOfertas(String nickname);

        public abstract String infoUsuario(String nickname);

		public abstract DTEmpresa getInfoEmpresa(String string);

		public abstract DTPostulante getInfoPostulante(String string);
		public abstract String getNicknameUsuario(String nickname) throws UsuarioNoExisteException;
        
     
        


        

        

}
