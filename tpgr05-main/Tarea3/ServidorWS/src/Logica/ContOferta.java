package Logica;

import java.awt.image.RescaleOp;
import java.time.LocalDate;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import Excepciones.*;
import Logica.Publicar.ESTADO;
import types.DTCompraPaquete;
import types.DTEmpresa;
import types.DTPaquete;
import types.DTPostulacion;
import types.DTPublicar;
import types.DTTipoOferta;
import types.DTUsuario;
import types.DtOferta;



public class ContOferta implements IContOferta {
	ContOferta() {
	}

	public void altaOferta(String nicknameEmpresa, String nombreTOferta, String nombreOferta, String descripcion,
			String horaI, String horaF, float sueldo, String ciudad, String depto, LocalDate fecha,
			Set<String> keywords, String paquete, String foto) throws OfertaRepetidaException {

		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
		Oferta offer = manejadorOfertas.getOferta(nombreOferta);
		Set<Keyword> vectorKeywords = new HashSet<Keyword>();
		if (sueldo <= 0) {
			throw new OfertaRepetidaException("El sueldo no es válido");
		}
		if (offer != null) {
			throw new OfertaRepetidaException("La oferta ya existe");
		} else {

			if (keywords != null) {
				for (String k : keywords) {
					Keyword key = manejadorOfertas.buscarKeyword(k);
					if (key != null) {
						vectorKeywords.add(key);
					}
				}
				TOferta toferta = manejadorOfertas.getTOferta(nombreTOferta);
				if (toferta == null)
					throw new OfertaRepetidaException("TOferta null");
				CompraPaquete compra = null;
				try {
					Empresa enterprise = (Empresa) manejadorUsuarios.getUsuario(nicknameEmpresa);
					if (paquete != "") {
						compra = enterprise.getCompraPaquete(paquete);
						if (compra==null || !compra.getDisponibles().containsKey(toferta.getNombre())) {
							throw new OfertaRepetidaException("Paquete no válido");
						}
					}
					offer = new Oferta(enterprise, toferta, nombreOferta.toLowerCase(), descripcion, horaI, horaF, sueldo, ciudad, depto, fecha,
							vectorKeywords, compra, foto);
					manejadorOfertas.agregarOferta(offer);
				} catch (Exception ex) {
					 ex.printStackTrace();
					throw new OfertaRepetidaException(ex.getMessage());

				}
				for (Keyword k : vectorKeywords) {
					k.agregarOferta(offer);
				}
				
				if (compra != null) {
					compra.gastar(offer);
				}
			} else {
				throw new OfertaRepetidaException("Falta pasar keywords");
			}
		}
	}

	public void modificarOferta(String nombre) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Oferta offer = manejadorOfertas.getOferta(nombre);
		if (offer != null) {
			manejadorOfertas.modificarOferta(offer);
		}
	}

	public Oferta getOferta(String nombre) throws OfertaNoExisteException{
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Oferta oferta = manejadorOfertas.getOferta(nombre);
		if (oferta == null) {
			throw new OfertaNoExisteException("La oferta "+ nombre+" no existe.");
		}else {
			return oferta;
		}	
	}
	
	public Collection<String> getOfertaKeyword(String keyword){
		Collection<String>ofertasK = new Vector<String>();
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Keyword key = manejadorOfertas.buscarKeyword(keyword);
		if (key != null) {
		Map<String,Oferta> ofertas = key.getOfertas();
		
		for (Map.Entry<String, Oferta> entry : ofertas.entrySet()) {
			if (entry.getValue().getDatosPublicacion().getEstado() == ESTADO.Confirmado && entry.getValue().getDatosPublicacion().vencido())
			 ofertasK.add(entry.getKey());	
		}	
		}
		
  	return ofertasK;
  }

	public TOferta getTOferta(String nombre) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		TOferta toferta = manejadorOfertas.getTOferta(nombre);
		return toferta;
	}

	public Collection<Oferta> getOfertas() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<Oferta> ofertas = manejadorOfertas.getOfertas();
		return ofertas;
	}

	public void AltaDeKeyword(String nombre) throws KeywordRepetida {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();

		if (manejadorOfertas.existeKeyword(nombre)) {
			throw new KeywordRepetida("La keyword ya existe");
		} else {

			manejadorOfertas.agregarKeyword(nombre);
		}
	}

	public DtOferta getInfoOferta(String nombre) throws OfertaNoExisteException {
		return getOferta(nombre).getInfo();
	}

	public String getInfoOfertaString(String nombre) throws OfertaNoExisteException {
		return getOferta(nombre.toLowerCase()).getInfoString();
	}

	public String getInfoOfertaBasico(String nombre) throws OfertaNoExisteException {
		return getOferta(nombre).getInfoBasicaOferta();
	}

	public String getInfoPostulantesString(String nombre) throws OfertaNoExisteException {
		return getOferta(nombre).getInfoPostulantesString();
	}

	public void PostulacionOfertaLaboral(String nombreOferta, String nickPostulante, String CVreducido,
			String motivacion, String fecha,String urlVideo) throws UsuarioNoExisteException {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		if (!(CVreducido.equals("") || motivacion.equals("") || fecha.equals(""))) {
			try {
				Oferta oferta = manejadorOfertas.getOferta(nombreOferta);
				for (Postulacion postu : oferta.getPostulaciones().values()) {
					if (postu.getPostulante().getNickname() == nickPostulante) {
						throw new UsuarioNoExisteException("El usuario ya esta postulado");
					}
				}
				ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
				Postulante pos = (Postulante) manejadorUsuarios.getUsuario(nickPostulante);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate date = LocalDate.parse(fecha, formatter);
				if (oferta.getDatosPublicacion().getEstado() != ESTADO.Confirmado) {
					throw new UsuarioNoExisteException("La publicacion no está Aprobada");
				}
				if (date.isBefore(oferta.getDatosPublicacion().getFechaAprobado())
						|| date.isAfter(oferta.getDatosPublicacion().getFechaVencimiento())) {
					throw new UsuarioNoExisteException(
							"La fecha debe estar entre la fecha de aprobacion y la fecha de vencimiento de la oferta");
				}
				Postulacion res = new Postulacion(date, CVreducido, motivacion, pos, oferta);
				if(!urlVideo.isEmpty()){
					res.setVideo(urlVideo);
				}
				
				oferta.addPostulacion(res);
				pos.addPostulacion(res);

			} catch (Exception ex) {
				throw new UsuarioNoExisteException(ex.getMessage());
			}
		} else {
			throw new UsuarioNoExisteException("Faltan datos");
		}

	}

	public Collection<String> listarEmpresas() {
		ManejadorUsuario manejadorUsuarios = ManejadorUsuario.getInstancia();
		Collection<String> empresas = new Vector<String>();
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
		if (cost <= 0) {
			throw new TOfertaRepetidaException("Costo inválido");
		}
		if (duration <= 0) {
			throw new TOfertaRepetidaException("Duracion inválida");
		}
		TOferta toferta = manejadorOfertas.getTOferta(name);
		if (toferta != null) {
			throw new TOfertaRepetidaException("El nombre " + name + " ya esta registrado.\n");
		} else {
			TOferta res = new TOferta(name, descr, expo, duration, cost, date);
			manejadorOfertas.agregarTOferta(res);
		}
	}

	public Collection<String> listarTOfertas() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<String> tofertas = new Vector<String>();
		for (TOferta toferta : manejadorOfertas.getTOfertas()) {
			tofertas.add(toferta.getNombre());
		}
		return tofertas;
	}

	public Collection<String> listarKeywords() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<String> keywords = new Vector<String>();
		for (Keyword k : manejadorOfertas.getKeywords()) {
			keywords.add(k.getNombre());
		}
		return keywords;
	}

	@Override
	public Collection<String> getNombresPaquetesDisponibles() {
		return ManejadorOferta.getInstancia().getNombresPaquetesDisponibles();
	}

	@Override

	public void agregarPaquete(String nombre, String descripcion, String Duracion, String Descuento, String fechaDeAlta,
			String foto) throws TOfertaRepetidaException {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Paquete paque = manejadorOfertas.getPaquete(nombre);

		if (paque != null) {
			throw new TOfertaRepetidaException("El nombre " + nombre + " ya esta registrado.\n");
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(fechaDeAlta, formatter);
		int duracion = Integer.parseInt(Duracion);
		float descuento = Float.parseFloat(Descuento);
		if (descuento <= 0) {
			throw new TOfertaRepetidaException("Descuento inválido");
		}
		if (duracion <= 0) {
			throw new TOfertaRepetidaException("Duracion inválida");
		}

		Paquete paqueton = new Paquete(nombre, descripcion, duracion, descuento, date, foto);

		ManejadorOferta.getInstancia().agregarPaquete(paqueton);
	}

	public Collection<String> getNombreTOfertas() {
		return ManejadorOferta.getInstancia().getNombreTOfertas();
	}

	public Collection<String> listarOfertas() {
		return ManejadorOferta.getInstancia().listarOfertas();
	}

	public String getEmpresaOferta(String nombreOferta) throws OfertaNoExisteException {
		return getOferta(nombreOferta).getNombreEmpresa();
	}

	@Override
	public Collection<String> listarPaquetes() {

		return ManejadorOferta.getInstancia().listarPaquetes();

	}

	public String getInfoPaquete(String paquete) {
		return ManejadorOferta.getInstancia().getPaquetes().get(paquete).getInfo();
	}

	@Override
	public void agregarPTP(String nombrePaquete, String nombreTipo, int cantidad) throws TOfertaRepetidaException {
		if (cantidad <= 0) {
			throw new TOfertaRepetidaException("Cantidad inválida");
		}
		if (!ManejadorOferta.getInstancia().getPaquete(nombrePaquete).getPaqueteComprado().isEmpty()) {
			throw new TOfertaRepetidaException("El paquete ya fue comprado");
		}
		ManejadorOferta.getInstancia().agregarPTP(nombrePaquete, nombreTipo, cantidad);

	}

	public Collection<String> getNombresTOPaquetes(String nombrePaquete) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		return manejadorOfertas.getNombresTOPaquetes(nombrePaquete);
	}

	public String getInfoTipo(String tipo) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		return manejadorOfertas.getInfoTipo(tipo);
	}

	public void verificarOferta(String oferta, boolean valor, LocalDate fecha) throws Exception {
		Oferta ofer = ManejadorOferta.getInstancia().getOferta(oferta);
		if (ofer == null)
			throw new Exception("Oferta no válida");
		if (ofer.getDatosPublicacion().getEstado() != ESTADO.Ingresado) {
			throw new Exception("Oferta no válida");
		}
		if (valor == true) {
			ofer.getDatosPublicacion().setEstado(ESTADO.Confirmado);
			ofer.getDatosPublicacion().setFechaAprobado(fecha);
		} else {
			ofer.getDatosPublicacion().setEstado(ESTADO.Rechazado);
			ofer.getDatosPublicacion().setFechaAprobado(fecha);
		}
	}

	public DTPublicar getInfoPublicar(String oferta) throws OfertaNoExisteException {
		return getOferta(oferta).getInfoPublicar();
	}

	public DTPaquete getDTPaquete(String paquete) {
		return ManejadorOferta.getInstancia().getPaquete(paquete).getInfoPaquete();
	}

	public Collection<DTPaquete> getPaquetesComprados(String empresa) {
		Collection<DTPaquete> resu = new Vector<DTPaquete>();
		Empresa empre = (Empresa) ManejadorUsuario.getInstancia().getUsuario(empresa);
		for (String nombre : empre.getCompraPaquetes().keySet()) {
			if (!empre.getCompraPaquetes().get(nombre).vencido()) {
			resu.add(ManejadorOferta.getInstancia().getPaquete(nombre).getInfoPaquete());
			}
		}
		return resu;
	}

	/* RETORNA UN MAP CON EL <PAQUETE, DTCOMPRAPAQUETE> */
	public Map<String, DTCompraPaquete> getCompraPaquetesEmpresa(String empresa) {
		Map<String, DTCompraPaquete> resu = new HashMap<String, DTCompraPaquete>();
		Empresa empre = (Empresa) ManejadorUsuario.getInstancia().getUsuario(empresa);
		for (CompraPaquete compra : empre.getCompraPaquetes().values()) {
			resu.put(compra.getPaquete().getNombre(), compra.getInfo());
		}
		return resu;
	}

	public void compraPaquete(String empresa, String paquete, LocalDate fecha) throws Exception {
		ManejadorUsuario manU = ManejadorUsuario.getInstancia();
		ManejadorOferta manO = ManejadorOferta.getInstancia();
		if (manU.getUsuario(empresa) == null)
			throw new Exception("Usuario no válido");
		if (manO.getPaquete(paquete) == null)
			throw new Exception("Paquete no válido");
		Empresa empre = (Empresa) manU.getUsuario(empresa);
		Paquete paque = manO.getPaquete(paquete);
		if (empre.getCompraPaquete(paquete) != null)
			throw new Exception("Paquete ya comprado");
		CompraPaquete comprapaque = new CompraPaquete(paque, empre, fecha);
		empre.agregarCompraPaquete(comprapaque);
		paque.agregarCompraPaquete(comprapaque);
	}

	@Override
	public Collection<DtOferta> infoOferta() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<DtOferta> infoOfertas = new Vector<DtOferta>();

		for (Oferta ofer : manejadorOfertas.getOfertas()) {
			infoOfertas.add(ofer.getInfo());
		}

		return infoOfertas;

	}
	
	public Collection<DtOferta> infoOfertaConfirmada() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<DtOferta> infoOfertas = new Vector<DtOferta>();

		for (Oferta ofer : manejadorOfertas.getOfertas()) {
			if (ofer.getDatosPublicacion().getEstado() == ESTADO.Confirmado && ofer.getDatosPublicacion().vencido())
			infoOfertas.add(ofer.getInfo());
		}

		return infoOfertas;

	}

	public DtOferta getDTOferta(String oferta) throws OfertaNoExisteException{
		return getOferta(oferta).getInfo();
	}

	@Override
	public Collection<DTPaquete> infoPaquetes() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<DTPaquete> infoPaquetes = new Vector<DTPaquete>();

		for (Paquete paquet : manejadorOfertas.getPaquetes().values()) {
			infoPaquetes.add(paquet.getInfoPaquete());
		}

		return infoPaquetes;
	}
	
	public Collection<DTTipoOferta> infoTOferta() {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<DTTipoOferta> resu = new Vector<DTTipoOferta>();
		for (TOferta to : manejadorOfertas.getTOfertas()) {
			resu.add(to.getInfoTOferta());
		}
		return resu;
	}
	
	public Collection<DTPaquete> infoPaquetesNoComprados(String nickname) {
		ManejadorOferta manejadorOfertas = ManejadorOferta.getInstancia();
		Collection<DTPaquete> paquetes = new Vector<DTPaquete>();
		Collection<String> comprados = getCompraPaquetesEmpresa(nickname).keySet();
		for (String paquet : manejadorOfertas.getPaquetes().keySet()) {
			if (!comprados.contains(paquet)) {
				paquetes.add(getDTPaquete(paquet));
			}
		}
		return paquetes;
	}
	
	public void finalizarOferta(String nombre) {
		ManejadorOferta.getInstancia().getOferta(nombre).finalizarOferta();
	}
	
	public boolean checkVencimiento(String nombre) {
		return ManejadorOferta.getInstancia().getOferta(nombre).checkVencimiento();
	}
	
	public void seleccionarPostulacion(String nombreOferta, String[] ordenPostulantes, String[] nombres) {
		Oferta oferta = ManejadorOferta.getInstancia().getOferta(nombreOferta);
		if (oferta.getDatosPublicacion().getEstado() == ESTADO.Confirmado && !oferta.getDatosPublicacion().vencido()) {
			int i = 0;
			for (String entry : nombres) {
				int num = Integer.parseInt(ordenPostulantes[i]);
				oferta.getPostulaciones().get(entry).seleccionar(num);
				i++;
			}
		}
		
	}
	
	public void incCantVisitas(String nombre) {
		ManejadorOferta.getInstancia().getOferta(nombre).incCantVisitas();
	}
	
	public int cantPostulantes(String nombreOferta) {
		Map<String,Postulacion> aux= ManejadorOferta.getInstancia().getOferta(nombreOferta).getPostulaciones();
		int res = aux.size();	
		return res;
		
	}
	
	public Collection<DtOferta> barraDeBusquedaOfertas(String busqueda) {
		List<DtOferta> resu = new ArrayList<DtOferta>();
		for (DtOferta dtOferta : this.infoOfertaConfirmada()) {
			if (dtOferta.getNombre().contains(busqueda) || dtOferta.getDescripcion().contains(busqueda)) {
				resu.add(dtOferta);
			}
		}
		return resu;
	}
	
	public Map<Oferta, Integer> ofertasMasVistas() {
		Map<Oferta, Integer> res = new HashMap<Oferta, Integer>(); 
		for (Oferta oferta : ManejadorOferta.getInstancia().getOfertas()) {
			res.put(oferta,oferta.getCantVisitas());
		}
		return res;
	}
	
	public void setVisitas(String nombreOferta, int cant) {
		Oferta oferta = ManejadorOferta.getInstancia().getOferta(nombreOferta);
		oferta.setVisitas(cant);
	}
}
