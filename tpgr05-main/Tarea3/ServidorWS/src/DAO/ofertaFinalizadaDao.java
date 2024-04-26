package DAO;

import java.util.Collection;
import java.util.List;

import Logica.Empresa;
import Logica.Postulacion;
import ofertasFinalizadas.OfertaFinalizada;

public interface ofertaFinalizadaDao {


    List<OfertaFinalizada> obtenerOfertasFinalizadas();

	void agregar(String nombre, String descripcion, String horario, String sueldo, String departamento, String ciudad,
			String nombreOferta, String fechaDatosPublicacion, String fechaActual, String costo, String paquete,
			Collection<Postulacion> postulantes, Empresa Empresa);

}



