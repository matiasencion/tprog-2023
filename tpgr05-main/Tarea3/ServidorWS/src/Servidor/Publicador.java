package Servidor;

import jakarta.jws.WebMethod;

import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collection;

import Excepciones.KeywordRepetida;
import Excepciones.OfertaNoExisteException;
import Excepciones.OfertaRepetidaException;
import Excepciones.TOfertaRepetidaException;
import Excepciones.UsuarioEmailRepetido;
import Excepciones.UsuarioNoExisteException;
import Excepciones.UsuarioRepetidoException;
import Logica.*;
import types.DTCompraPaquete;
import types.DTEmpresa;
import types.DTPaquete;
import types.DTPostulacion;
import types.DTPostulante;
import types.DTPublicar;
import types.DTTipoOferta;
import types.DTUsuario;
import types.DtOferta;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

    private Endpoint endpoint = null;
    private String path;


    // Constructor
    public Publicador() throws IOException {
    	CargarPath();
    }


    public void CargarPath() throws IOException {
      File file = new File("../../settings.properties");
      FileInputStream input = new FileInputStream(file);
      BufferedReader buff = new BufferedReader(new InputStreamReader(input));
      this.path = buff.readLine();
      System.out.println(path);
  }

    
    @WebMethod(exclude = true)
    public void publicar() {
    	if (this.path != null)
        endpoint = Endpoint.publish(this.path, this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public void altaOferta(String nicknameEmpresa, String nombreTOferta, String nombreOferta,
            String descripcion, String horaI, String horaF, float sueldo, String ciudad, String depto,
            String fecha, String[] keywords, String paquete, String foto) throws OfertaRepetidaException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        Set<String> keywordsSet = new HashSet<String>();
        for (int i = 0; i < keywords.length; i++) {
            keywordsSet.add(keywords[i]);
        }
        
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        LocalDate fechaServidor = LocalDate.now();
       // LocalDate fechaLocalDate = LocalDate.parse(fechaServidor, formatter);
        ContO.altaOferta(nicknameEmpresa, nombreTOferta, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad, depto,
        		fechaServidor, keywordsSet, paquete, foto);
    }

    @WebMethod
    public void modificarOferta(String nombre) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.modificarOferta(nombre);
    }

    @WebMethod
    public Oferta getOferta(String nombre) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getOferta(nombre);
    }

  

    @WebMethod
    public void agregarTOferta(String name, String descr, int expo, int duration, float cost,
            LocalDate date) throws TOfertaRepetidaException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.agregarTOferta(name, descr, expo, duration, cost, date);
    }

    @WebMethod
    public void AltaDeKeyword(String nombre) throws KeywordRepetida {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.AltaDeKeyword(nombre);
    }

    @WebMethod
    public String getInfoOfertaString(String nombre) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoOfertaString(nombre);
    }

    @WebMethod
    public String getInfoPostulantesString(String nombre) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoPostulantesString(nombre);
    }

    @WebMethod
    public void PostulacionOfertaLaboral(String nombreOferta, String nickPostulante, String CVreducido,
            String motivacion, String fecha,String urlVideo) throws UsuarioNoExisteException, OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.PostulacionOfertaLaboral(nombreOferta, nickPostulante, CVreducido, motivacion, fecha,urlVideo);
    }
    //

    @WebMethod
    public String[] listarEmpresas() {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();

        Collection<String> empresaStrings = ContO.listarEmpresas();

        String[] infoArray = empresaStrings.toArray(new String[0]);

        return infoArray;

    }

    //
    // @WebMethod
    // public Collection<String> listarTOfertas()
    // {
    // Fabrica fabrica = Fabrica.getInstancia();
    // IContOferta ContO = fabrica.getIContOferta();
    // return ContO.listarTOfertas();
    // }
    //
    @WebMethod
    public String[] listarKeywords() {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();

        List<String> list = (List<String>) ContO.listarKeywords();
        String[] infoArray = list.toArray(new String[0]);

        return infoArray;

    }


    @WebMethod
    public void agregarPaquete(String nombre, String descripcion, String Duracion, String Descuento,
            String fechaDeAlta, String foto) throws TOfertaRepetidaException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.agregarPaquete(nombre, descripcion, Duracion, Descuento, fechaDeAlta, foto);
    }


    @WebMethod
    public TOferta getTOferta(String nombre) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getTOferta(nombre);
    }

    @WebMethod
    public String[] getOfertaKeyword(String oferta) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        Collection<String> aux = ContO.getOfertaKeyword(oferta);
        String[] keywords = new String[aux.size()];
        int iter = 0;
        for (String key : ContO.getOfertaKeyword(oferta)) {
            keywords[iter] = key;
            iter++;
        }
        return keywords;
    }

 

    @WebMethod
    public String getEmpresaOferta(String nombreOferta) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getEmpresaOferta(nombreOferta);
    }

    @WebMethod
    public String getInfoOfertaBasico(String nombreOferta) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoOfertaBasico(nombreOferta);
    }



    @WebMethod
    public String getInfoPaquete(String nombrePaquete) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoPaquete(nombrePaquete);
    }



    @WebMethod
    public void agregarPTP(String nombrePaquete, String nombreTipo, int cantidad)
            throws TOfertaRepetidaException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.agregarPTP(nombrePaquete, nombreTipo, cantidad);
    }

    @WebMethod
    public String getInfoTipo(String tipo) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoTipo(tipo);
    }

    @WebMethod
    public void verificarOferta(String oferta, boolean valor, LocalDate fecha) throws Exception {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        ContO.verificarOferta(oferta, valor, fecha);
    }

    @WebMethod
    public DTPublicar getInfoPublicar(String oferta) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getInfoPublicar(oferta);
    }

    @WebMethod
    public DTPaquete getDTPaquete(String paquete) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getDTPaquete(paquete);
    }

    //
    @WebMethod
    public DTPaquete[] getPaquetesComprados(String empresa) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        List<DTPaquete> list = (List<DTPaquete>) ContO.getPaquetesComprados(empresa);
        DTPaquete[] array = (DTPaquete[]) list.toArray(new DTPaquete[0]);
        return array;
    }




    @WebMethod
    public DtOferta[] infoOfertaConfirmada() {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        List<DtOferta> list = (List<DtOferta>) ContO.infoOfertaConfirmada();

        DtOferta[] array = list.toArray(new DtOferta[0]);

        return array;
    }

    @WebMethod
    public DtOferta getDTOferta(String oferta) throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        return ContO.getDTOferta(oferta);
    }

    @WebMethod
    public DTTipoOferta[] infoTOferta()
    {
    Fabrica fabrica = Fabrica.getInstancia();
    IContOferta ContO = fabrica.getIContOferta();
    List<DTTipoOferta> list = (List<DTTipoOferta>) ContO.infoTOferta();

    DTTipoOferta[] array = list.toArray(new DTTipoOferta[0]);

    return array;
    }

 

    @WebMethod
    public void CargarDatos() throws OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta ContO = fabrica.getIContOferta();
        IContUsuario contUsuario = fabrica.getIContUsuario();
        CargaDatos cargaDatos = new CargaDatos(contUsuario, ContO);

        cargaDatos.cargarUsuarios();
        cargaDatos.cargarTipoPublicacion();
        cargaDatos.cargarKeywords();
        cargaDatos.cargarPaquetes();
        cargaDatos.cargarOfertas();
        try {
            cargaDatos.cargarPostulaciones();
        } catch (OfertaNoExisteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ;

    }

    // ----------------------------------- Controlador Usuario
    // ---------------------------------
    @WebMethod
    public boolean inicioSesion(String usuario, String clave) throws Exception {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.inicioSesion(usuario, clave);
    }

    @WebMethod
    public DTUsuario getUsuario(String nickname) throws UsuarioNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.getDtUsuario(nickname);

    }

    @WebMethod
    public boolean esEmpresa(String nickname) throws UsuarioNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.esEmpresa(nickname);

    }

    @WebMethod
    public boolean esPostulante(String nickname) throws UsuarioNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.esPostulante(nickname);
    }

    @WebMethod
    public void altaEmpresa(String nickname, String nombre, String apellido, String correo, String contrasena,
            String foto, String empresa,
            String descripcion, String link) throws UsuarioRepetidoException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        contUsuario.altaEmpresa(nickname, nombre, apellido, correo, contrasena, foto, empresa, descripcion, link);
    }

    @WebMethod
    public void crearPostulante(String nickname, String nombre, String apellido, String correo, String contrasena,
            String foto,
            String FechaNac, String nacionalidad) throws UsuarioRepetidoException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        contUsuario.CrearPostulante(nickname, nombre, apellido, correo, contrasena, foto, FechaNac, nacionalidad);
    }

    @WebMethod
    public DTEmpresa getInfoEmpresa(String string) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.getInfoEmpresa(string);
    }

    @WebMethod
    public String[] getNombresOfertas(String nickname) {

        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        List<String> list = (List<String>) contUsuario.getNombresOfertas(nickname);
        String[] infoArray = list.toArray(new String[0]);

        return infoArray;
    }

    @WebMethod
    public String[] getNombreOfertasConfirmadas(String nickname) {

        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        List<String> list = (List<String>) contUsuario.getNombreOfertasConfirmadas(nickname);
        String[] infoArray = list.toArray(new String[0]);

        return infoArray;

    }
    
    @WebMethod
    public String[] getNombreOfertasConfirmadasVencidas(String nickname) {

        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        List<String> list = (List<String>) contUsuario.getNombreOfertasConfirmadasVencidas(nickname);
        String[] infoArray = list.toArray(new String[0]);

        return infoArray;
        
    }
    

    @WebMethod
    public DTCompraPaquete[] getCompraPaquetesEmpresa(String empresa) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta contOferta = fabrica.getIContOferta();

        Map<String, DTCompraPaquete> compraPaquetes = contOferta.getCompraPaquetesEmpresa(empresa);

        Collection<DTCompraPaquete> valores = compraPaquetes.values();

        DTCompraPaquete[] info = valores.toArray(new DTCompraPaquete[valores.size()]);

        return info;
    }

    public DTPaquete[] infoPaquetesNoComprados(String nickname) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta contOferta = fabrica.getIContOferta();

        List<DTPaquete> list = (List<DTPaquete>) contOferta.infoPaquetesNoComprados(nickname);

        DTPaquete[] infoArray = list.toArray(new DTPaquete[0]);

        return infoArray;
    }

    @WebMethod
    public DTPostulante getInfoPostulante(String string) {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.getInfoPostulante(string);

    }

    @WebMethod
    public DTPostulacion[] getPostulaciones(String nickname) throws UsuarioNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        Collection<DTPostulacion> postulaciones = contUsuario.getPostulaciones(nickname);

        DTPostulacion[] infoDtPostulacions = postulaciones.toArray(new DTPostulacion[postulaciones.size()]);

        return infoDtPostulacions;

    }

    @WebMethod
    public DTUsuario[] getUsuarios() {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();
        Set<DTUsuario> setUsuarios = contUsuario.getUsuarios();
        DTUsuario[] infoDtUsuario = setUsuarios.toArray(new DTUsuario[setUsuarios.size()]);
        return infoDtUsuario;
    }

    @WebMethod
    public DTPaquete[] infoPaquetes() {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta contOferta = fabrica.getIContOferta();

        Collection<DTPaquete> paquetes = contOferta.infoPaquetes();

        DTPaquete[] infoDtPaquete = paquetes.toArray(new DTPaquete[paquetes.size()]);

        return infoDtPaquete;

    }

    @WebMethod
    public void compraPaquete(String empresa, String paquete, String fecha) throws Exception {
        Fabrica fabrica = Fabrica.getInstancia();
        IContOferta contOferta = fabrica.getIContOferta();
        LocalDate fechaLocal = LocalDate.parse(fecha);

        contOferta.compraPaquete(empresa, paquete, fechaLocal);
    }

    @WebMethod
    public DtOferta[] getOfertasSinPostular(String nickname) throws UsuarioNoExisteException, OfertaNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();
        Collection<DtOferta> ofertas = contUsuario.getOfertasSinPostular(nickname);

        DtOferta[] infoDtofertas = ofertas.toArray(new DtOferta[ofertas.size()]);

        return infoDtofertas;
    }

    @WebMethod
    public boolean estaPostulado(String nickname, String oferta) throws UsuarioNoExisteException {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.estaPostulado(nickname, oferta);
    }

    @WebMethod
    public DTPostulacion getInfoPostulacion(String nickname, String oferta) throws UsuarioNoExisteException {

        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();

        return contUsuario.getInfoPostulacion(nickname, oferta);

    }

    @WebMethod
    public void modificarEmpresa(String nickname, String nombre, String apellido, String contrasena, String foto,
            String empresa, String descripcion, String link) throws UsuarioEmailRepetido {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();
        contUsuario.modificarEmpresa(nickname, nombre, apellido, contrasena, foto, empresa, descripcion, link);
    }

    @WebMethod
    public void modificarPostulante(String nickname, String nombre, String apellido, String contrasena, String foto,
            String fecha, String nacionalidad) throws UsuarioEmailRepetido {
        Fabrica fabrica = Fabrica.getInstancia();
        IContUsuario contUsuario = fabrica.getIContUsuario();
        contUsuario.modificarPostulante(nickname, nombre, apellido, contrasena, foto, fecha, nacionalidad);
    }

    @WebMethod
    public void finalizarOferta(String oferta) {
    	 Fabrica fabrica = Fabrica.getInstancia();
       IContOferta contOferta = fabrica.getIContOferta();
       contOferta.finalizarOferta(oferta);
    }
    
    @WebMethod
    public boolean checkVencimiento(String nombre) {
    	 Fabrica fabrica = Fabrica.getInstancia();
       IContOferta contOferta = fabrica.getIContOferta();
       return contOferta.checkVencimiento(nombre);
    }
    @WebMethod
    public void incCantVisitas(String nombre) {
    	Fabrica fabrica = Fabrica.getInstancia();
      IContOferta contOferta = fabrica.getIContOferta();
      contOferta.incCantVisitas(nombre);
    }
    
    @WebMethod
    public int cantPostulantes(String nombre) {
    	Fabrica fabrica = Fabrica.getInstancia();
    	IContOferta contOferta = fabrica.getIContOferta();
    	return contOferta.cantPostulantes(nombre);
    	
    }
    
    @WebMethod
    public void seleccionarPostulacion(String nombreOfertra, String[] ordenPostulantes, String[] nombres) {
    	Fabrica fabrica = Fabrica.getInstancia();
    	IContOferta contOferta = fabrica.getIContOferta();
    	contOferta.seleccionarPostulacion(nombreOfertra, ordenPostulantes, nombres);
    	
    }
    @WebMethod
    public  boolean esOfertaFavorita(String postulante, String oferta) throws OfertaNoExisteException {
    	Fabrica fabrica = Fabrica.getInstancia();
      IContUsuario contUsuario = fabrica.getIContUsuario();
      return contUsuario.esOfertaFavorita(postulante, oferta);
    }
    
    @WebMethod
    public void agregarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException {
    	Fabrica fabrica = Fabrica.getInstancia();
      IContUsuario contUsuario = fabrica.getIContUsuario();
      contUsuario.agregarOfertaFavorita(postulante, oferta);
    }
		
    @WebMethod
		public void quitarOfertaFavorita (String postulante, String oferta) throws OfertaNoExisteException {
			Fabrica fabrica = Fabrica.getInstancia();
      IContUsuario contUsuario = fabrica.getIContUsuario();
      contUsuario.quitarOfertaFavorita(postulante, oferta);
		}
    @WebMethod
	public  void seguirUsuario(String usuario, String usuarioAseguir) {
    	Fabrica fabrica = Fabrica.getInstancia();
    	IContUsuario contUsuario = fabrica.getIContUsuario();
      contUsuario.seguirUsuario(usuario, usuarioAseguir);
    }
	
	@WebMethod
	public void dejarSeguirUsuario(String usuario, String usuarioAseguir) {
		Fabrica fabrica = Fabrica.getInstancia();
    	IContUsuario contUsuario = fabrica.getIContUsuario();
    	contUsuario.dejarSeguirUsuario(usuario, usuarioAseguir);
	}
	
	@WebMethod
	public DtOferta[] getOfertasFavoritas (String postulante) {
		Fabrica fabrica = Fabrica.getInstancia();
  	IContUsuario contUsuario = fabrica.getIContUsuario();
  	DtOferta[] infoDtofertas = contUsuario.getOfertasFavoritas(postulante).toArray(new DtOferta[contUsuario.getOfertasFavoritas(postulante).size()]);
    return infoDtofertas;
	}
	
	@WebMethod
	public boolean sigueAUsuario(String usuario, String usuarioASeguir) throws UsuarioNoExisteException {
		Fabrica fabrica = Fabrica.getInstancia();
  	IContUsuario contUsuario = fabrica.getIContUsuario();
  	return contUsuario.sigueAUsuario(usuario, usuarioASeguir);
		
	}
	
	@WebMethod
	public boolean existeEmail(String email) {
		return Fabrica.getInstancia().getIContUsuario().existeEmail(email);
	}
	
	@WebMethod
	public boolean existeNickname(String nickname) {
		return Fabrica.getInstancia().getIContUsuario().existeNickname(nickname);
	}
	
	@WebMethod
	public DtOferta[] barraDeBusquedaOfertas(String busqueda) {
		Collection<DtOferta> aux = Fabrica.getInstancia().getIContOferta().barraDeBusquedaOfertas(busqueda);
		DtOferta[] resu = aux.toArray(new DtOferta[aux.size()]);
		return resu;
	}
	
	@WebMethod
	public DTEmpresa[] barraDeBusquedaEmpresa(String busqueda) {
		Collection<DTEmpresa> aux = Fabrica.getInstancia().getIContUsuario().barraDeBusquedaEmpresa(busqueda);
		DTEmpresa[] resu = aux.toArray(new DTEmpresa[aux.size()]);
		return resu;
	}
	
	@WebMethod
	public DtOferta[] getOfertas() {
		List<DtOferta> resu = new ArrayList<DtOferta>();
		for (Oferta oferta : Fabrica.getInstancia().getIContOferta().getOfertas()) {
			resu.add(oferta.getInfo());
		}
		return (DtOferta[]) resu.toArray();
	}
	
	@WebMethod
	public DTEmpresa[] getEmpresas() {
		List<DTEmpresa> resu = new ArrayList<DTEmpresa>();
		for (DTUsuario empresa : Fabrica.getInstancia().getIContUsuario().getUsuarios()) {
			if (empresa instanceof DTEmpresa)
				resu.add((DTEmpresa)empresa);
		}
		return (DTEmpresa[]) resu.toArray();
	}
	
}
