package Logica;

import java.util.Collection;
import java.util.Set;


import java.time.LocalDate;
import excepciones.OfertaRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.TOfertaRepetidaException;
import excepciones.KeywordRepetida;
import excepciones.TOfertaRepetidaException;
import java.util.Vector;
import java.time.LocalDate;

public interface IContOferta {

    public abstract void altaOferta(String nicknameEmpresa, String nombreTOferta, String nombreOferta, String descripcion, 
			String horaI, String horaF, float sueldo, String ciudad, String depto, LocalDate fecha, Set<String> keywords) throws OfertaRepetidaException;


    public abstract void modificarOferta(String nombre);

    public abstract Oferta getOferta(String nombre); // ---> Pasar a DTypes

    public abstract Collection<Oferta> getOfertas(); // ---> Pasar a DTypes

    public abstract void agregarTOferta(String name, String descr, int expo, int duration, float cost, LocalDate date) throws TOfertaRepetidaException;

    public abstract void AltaDeKeyword(String nombre) throws KeywordRepetida;
    
    public abstract String getInfoOfertaString(String nombre);
    
    public abstract String getInfoPostulantesString(String nombre);
    
    public abstract void PostulacionOfertaLaboral(String nombreOferta, String nickPostulante, String CVreducido, String motivacion, String fecha) throws UsuarioNoExisteException;

    public abstract Vector<String> listarEmpresas();
  
    public abstract Vector<String> listarTOfertas();

    public abstract Vector<String> listarKeywords();

	public abstract Vector<String> getNombresPaquetesDisponibles();

    public abstract void agregarPaquete(String nombre,String descripcion,String Duracion,String Descuento,String fechaDeAlta) throws TOfertaRepetidaException;

    public abstract Vector<String> getNombreTOfertas();
    
    public abstract TOferta getTOferta(String nombre);
    
    public abstract Vector<String> listarOfertas();

    public abstract String getEmpresaOferta(String nombreOferta);

    public abstract String getInfoOfertaBasico(String nombreOferta);
    
    public abstract Vector<String> listarPaquetes();

    public abstract String getInfoPaquete(String nombrePaquete);
  
    public Vector<String> getNombresTOPaquetes( String nombrePaquete);

    public abstract void agregarPTP(String nombrePaquete , String nombreTipo, int cantidad);

    public abstract String getInfoTipo(String tipo);
}
