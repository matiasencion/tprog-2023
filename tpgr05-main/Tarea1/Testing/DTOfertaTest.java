package Testing;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import Logica.*;
import junit.framework.TestCase;

public class DTOfertaTest {
	
	private IContOferta ContO = Fabrica.getInstancia().getIContOferta();
	private IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
	private CargaDatos CD = new CargaDatos(ContU, ContO);
	
	@Order(1)
	@Test
	public void DTOfeta() {
		CD.cargarKeywords();
		CD.cargarTipoPublicacion();
		CD.cargarUsuarios();
		CD.cargarOfertas();
		CD.cargarPostulaciones();
		DTOferta dto = ContO.getOferta("Desarrollador Frontend".toLowerCase()).getInfo();
		assertEquals(dto.getInfoString(),ContO.getInfoOfertaString("Desarrollador Frontend".toLowerCase()));
	}
	
	@Order(2)
	@Test
	public void DTPostulacionesenDTOferta( ) {
		DTOferta dto1 = ContO.getOferta("Estratega de Negocios".toLowerCase()).getInfo();
		Collection<Postulacion> post = ContO.getOferta("Estratega de Negocios".toLowerCase()).getPostulaciones().values();
		for(Postulacion p : post) {
			for (DTPostulacion d : dto1.getDTPostulaciones()) {
				if(p.getCv() == d.getCv()) {
					assertEquals(p.getInfo().getInfoString(), d.getInfoString());
				}
			}
		}
		
		
	}
}
