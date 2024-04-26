package Testing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import Logica.*;
import junit.framework.TestCase;
	
public class PublicarTest {
	private IContOferta ContO = Fabrica.getInstancia().getIContOferta();
	private IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
	private CargaDatos CD = new CargaDatos(ContU, ContO);
	
	@Order(1)
	@Test
	public void Publicar() {
		CD.cargarKeywords();
		CD.cargarTipoPublicacion();
		CD.cargarUsuarios();
		CD.cargarOfertas();
		CD.cargarPostulaciones();
		Set<Keyword> k = new HashSet<Keyword>();
		Empresa e = new Empresa("Fender", "Fender USA", "Worldwide", "fender@usa.com", "Fender", "Musica para todos", "www.fender.com");
		Oferta o = new Oferta(e, new TOferta("T1", "Tipo generico", 1, 20,300, LocalDate.of(2000, 2, 3)), 
				"Oferta Nueva", "DEscripcion", "08:00", "12:00", 3000, "California", "USA",LocalDate.of(2000, 2, 3), k);
		Publicar publi = new Publicar(e, o, LocalDate.of(2100, 2, 13));
	    Empresa e1 = new Empresa("Fander", "Fender USA", "Worldwide", "fender@usa.com", "Fender", "Musica para todos", "www.fender.com");
		Oferta o1 = new Oferta(e, new TOferta("T2", "Tipo generico", 1, 20,300, LocalDate.of(2000, 2, 3)), 
				"Oferta1 Nueva", "DEscripcion", "08:00", "12:00", 3000, "California", "USA",LocalDate.of(2000, 2, 3), k);
	    publi.getEmpresa();
	}

}
