package Logica;

import excepciones.KeywordRepetida;
import excepciones.OfertaRepetidaException;
import excepciones.TOfertaRepetidaException;
import excepciones.UsuarioNoExisteException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


public class ContOferta implements IContOferta {
	ContOferta() {
	}

	public void altaOferta(String nicknameEmpresa, String nombreTOferta, String nombreOferta, String descripcion, 
			String horaI, String horaF, float sueldo, String ciudad, String depto, LocalDate fecha, Set<String> keywords) throws OfertaRepetidaException {

		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
		Oferta o = manejadorOfertas.getOferta(nombreOferta);
		Set<Keyword> vectorKeywords = new HashSet<Keyword>();
		if (o != null) {
			throw new OfertaRepetidaException("La oferta ya existe");
		} else {            


			if(keywords != null) {
				for (String k : keywords) {
					Keyword key = manejadorOfertas.buscarKeyword(k);
					if (key != null) {
						vectorKeywords.add(key);
					}
				}
				TOferta to = manejadorOfertas.getTOferta(nombreTOferta);
				if (to == null)
					System.out.println("TOferta null");

				try {
					Empresa e = (Empresa) manejadorUsuarios.getUsuario(nicknameEmpresa);
					o = new Oferta(e, to, nombreOferta, descripcion, horaI, horaF, sueldo, ciudad, depto, fecha,vectorKeywords);
					manejadorOfertas.agregarOferta(o);
				} catch (Exception ex) {
					throw new OfertaRepetidaException(ex.getMessage());

				}
				for (Keyword k : vectorKeywords) {
					k.agregarOferta(o);
				}

			} else {
				throw new OfertaRepetidaException("Falta pasar keywords");
			}
		}
	}

	public void modificarOferta(String nombre) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Oferta o = manejadorOfertas.getOferta(nombre);
		if (o != null) {
			manejadorOfertas.modificarOferta(o);
		}
	}

	public Oferta getOferta(String nombre) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Oferta o = manejadorOfertas.getOferta(nombre);
		return o;
	}

	public TOferta getTOferta(String nombre) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		TOferta to = manejadorOfertas.getTOferta(nombre);
		return to;
	}

	public Collection<Oferta> getOfertas() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<Oferta> ofertas = manejadorOfertas.getOfertas();
		return ofertas;
	}


	public void AltaDeKeyword(String nombre) throws KeywordRepetida {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();



		if (manejadorOfertas.existeKeyword(nombre)) {
			System.out.println(nombre + "============");
			throw new KeywordRepetida("La keyword ya existe");
		} else {

			manejadorOfertas.agregarKeyword(nombre);
		}
	}

	public DTOferta getInfoOferta(String nombre) {
		return getOferta(nombre).getInfo();
	}

	public String getInfoOfertaString(String nombre) {
		return getOferta(nombre.toLowerCase()).getInfoString();
	}

	public String getInfoOfertaBasico(String nombre) {
		return getOferta(nombre).getInfoBasicaOferta();
	}

	public String getInfoPostulantesString(String nombre) {
		return getOferta(nombre).getInfoPostulantesString();
	}

	public void PostulacionOfertaLaboral(String nombreOferta, String nickPostulante, String CVreducido, String motivacion, String fecha) throws UsuarioNoExisteException {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		if (!(CVreducido.equals("") || motivacion.equals("") || fecha.equals(""))) {
		try { 
			Oferta o = manejadorOfertas.getOferta(nombreOferta);
			for (Postulacion p : o.getPostulaciones().values()) {
				if (p.getPostulante().getNickname() == nickPostulante) {
					throw new UsuarioNoExisteException("El usuario ya esta postulado");
				}
			}
			ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
			Postulante pos = (Postulante) manejadorUsuarios.getUsuario(nickPostulante);



			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
			LocalDate date = LocalDate.parse(fecha, formatter);

			Postulacion res = new Postulacion(date, CVreducido, motivacion, pos, o);
			o.addPostulacion(res);
			pos.addPostulacion(res);


		} catch (Exception ex) {
			throw new UsuarioNoExisteException(ex.getMessage());
		}}else {
			throw new UsuarioNoExisteException("Faltan datos");
		}



	}



	public Vector<String> listarEmpresas() {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
		Vector<String> empresas = new Vector<String>();
		for (DTUsuario u : manejadorUsuarios.getUsuarios()) {
			if (u instanceof DTEmpresa) {
				empresas.add(u.getNickname());
			}
		}
		return empresas;
	}

	public void agregarTOferta(String name, String descr, int expo, int duration, float cost, LocalDate date)
			throws TOfertaRepetidaException {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();

		TOferta t = manejadorOfertas.getTOferta(name);
		if (t != null) {
			throw new TOfertaRepetidaException("El nombre " + name + " ya esta registrado.\n");
		} else {
			TOferta res = new TOferta(name, descr, expo, duration, cost, date);
			manejadorOfertas.agregarTOferta(res);
		}
	}




	public Vector<String> listarTOfertas() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Vector<String> tofertas = new Vector<String>();
		for (TOferta to : manejadorOfertas.getTOfertas()) {
			tofertas.add(to.getNombre());
		}
		return tofertas;
	}

	public Vector<String> listarKeywords() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Vector<String> keywords = new Vector<String>();
		for (Keyword k : manejadorOfertas.getKeywords()) {
			keywords.add(k.getNombre());
		}
		return keywords;
	}

	@Override
	public Vector<String> getNombresPaquetesDisponibles() {
		return ManejadorOferta.getInstancia().getNombresPaquetesDisponibles();
	}

	@Override
	public void agregarPaquete(String nombre,String descripcion,String Duracion,String Descuento,String fechaDeAlta) throws TOfertaRepetidaException {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Paquete t = manejadorOfertas.getPaquete(nombre);


		if( t != null){
			throw new TOfertaRepetidaException("El nombre " + nombre + " ya esta registrado.\n");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate date = LocalDate.parse(fechaDeAlta, formatter);
		int duracion = Integer.parseInt(Duracion);
		float descuento = Float.parseFloat(Descuento);
		Paquete p = new Paquete(nombre,descripcion,duracion,descuento,date);
		ManejadorOferta.getInstancia().agregarPaquete(p);
	}

	public  Vector<String> getNombreTOfertas() {
		return ManejadorOferta.getInstancia().getNombreTOfertas();
	}

	public  Vector<String> listarOfertas() {
		return ManejadorOferta.getInstancia().listarOfertas();
	}

	public String getEmpresaOferta(String nombreOferta) {
		return getOferta(nombreOferta).getNombreEmpresa();
	}

	@Override
	public Vector<String> listarPaquetes(){

		return ManejadorOferta.getInstancia().listarPaquetes();

	}

	public String getInfoPaquete(String paquete) {
		return ManejadorOferta.getInstancia().getPaquetes().get(paquete).getInfo();
	}

	@Override
	public void agregarPTP(String nombrePaquete , String nombreTipo, int cantidad){
		ManejadorOferta.getInstancia().agregarPTP(nombrePaquete,nombreTipo,cantidad);

	}

	public Vector<String> getNombresTOPaquetes( String nombrePaquete){
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		return manejadorOfertas.getNombresTOPaquetes( nombrePaquete);
	}

	public String getInfoTipo(String tipo){
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		return manejadorOfertas.getInfoTipo(tipo);
	}

}



