package Testing;
import Logica.*;



import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import excepciones.KeywordRepetida;
import excepciones.OfertaNoExisteException;
import excepciones.OfertaRepetidaException;
import excepciones.TOfertaRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import junit.framework.TestCase;


public class CargarDatosTest{
	private IContOferta ContO = Fabrica.getInstancia().getIContOferta();
    private IContUsuario ContU = Fabrica.getInstancia().getIContUsuario();
    private ManejadorUsuario mu = ManejadorUsuario.getInstancia();
    private ManejadorOferta mo = ManejadorOferta.getInstancia();
    
    @Test
    public void CargarDatosU() {
    	CargaDatos c = new CargaDatos(ContU, ContO);
    	
    	c.cargarUsuarios();
    	c.cargarUsuarios();
    	
    	Set<String> res = new HashSet<String>();
    	res.add("lgarcia");
    	res.add("matilo");
    	res.add("maro");
    	res.add("javierf");
    	res.add("valen25");
    	res.add("andpe12");
    	res.add("sicam");
    	res.add("sebgon");
    	res.add("isabel");
    	res.add("marram02");
    	res.add("ecotech");
    	res.add("fusiontech");
    	res.add("globalhealth");
    	res.add("antel");
    	res.add("miem");
    	res.add("techsolutions");
    	for (String i : res) {
    		assertTrue(mu.existeUsuario(i));
    	}
    }
    	
    	private void assertTrue(boolean existeUsuario) {
		// TODO Auto-generated method stub
		
	}

		@Test
        public void CargarDatosTO() {
        	CargaDatos c = new CargaDatos(ContU, ContO);
        	
        	c.cargarTipoPublicacion();
        	c.cargarTipoPublicacion();
        	
        	Set<String> res = new HashSet<String>();
        	res.add("Premium".toLowerCase());
        	res.add("Destacada".toLowerCase());
        	res.add("Estandar".toLowerCase());
        	res.add("Basica".toLowerCase());
        	for (String i : res) {
        		assertTrue(mo.getTOfertas().contains(mo.getTOferta(i)));
        	}
    	
    }
    
    	@Test
        public void CargarDatosK() {
        	CargaDatos c = new CargaDatos(ContU, ContO);
        	
        	c.cargarKeywords();
        	c.cargarKeywords();
        	
        	Set<String> res = new HashSet<String>();
        	res.add("Tiempo completo".toLowerCase());
        	res.add("Medio tiempo".toLowerCase());
        	res.add("Remoto".toLowerCase());
        	res.add("Freelance".toLowerCase());
        	res.add("Temporal".toLowerCase());
        	res.add("Permanente".toLowerCase());
        	res.add("Computación".toLowerCase());
        	res.add("Administración".toLowerCase());
        	res.add("Logística".toLowerCase());
        	res.add("Contabilidad".toLowerCase());
        	
        	for (String i : res) {
        		assertTrue(mo.existeKeyword(i));
        	}
    	
    }
    	
    	@Test
        public void CargarDatosO() {
    		mu.clear();
    		mo.clear();
        	CargaDatos c = new CargaDatos(ContU, ContO);
        	
        	c.cargarUsuarios();
        	c.cargarTipoPublicacion();
        	c.cargarKeywords();
        	c.cargarPaquetes();
        	c.cargarOfertas();
        	
        	Set<String> res = new HashSet<String>();
        	res.add("Desarrollador Frontend".toLowerCase());
        	res.add("Estratega de Negocios".toLowerCase());
        	res.add("Diseñador UX/UI".toLowerCase());
        	res.add("Analista de Datos".toLowerCase());
        	res.add("Content Manager".toLowerCase());
        	res.add("Soporte Tecnico".toLowerCase());
        	res.add("A. de Marketing Digital".toLowerCase());
        	res.add("Contador Senior".toLowerCase());        	
        	for (String i : res) {
        		assertTrue(mo.getOfertas().contains(mo.getOferta(i)));
        	}
    	
    }
    	
    	@Test
        public void CargarDatosP() throws OfertaNoExisteException {
        	CargaDatos c = new CargaDatos(ContU, ContO);
        	
        	c.cargarPostulaciones();
        	c.cargarPostulaciones();
        	
        	Map<String, String> res = new HashMap<String, String>();
        	res.put("lgarcia".toLowerCase(), "Desarrollador Frontend".toLowerCase());
        	res.put("matilo".toLowerCase(), "Estratega de Negocios".toLowerCase());
        	res.put("maro".toLowerCase(), "Desarrollador Frontend".toLowerCase());
        	res.put("javierf".toLowerCase(), "Diseñador UX/UI".toLowerCase());
        	res.put("valen25".toLowerCase(), "Estratega de Negocios".toLowerCase());
        	res.put("lgarcia".toLowerCase(), "Estratega de Negocios".toLowerCase());
        	for (String i : res.keySet()) {
        		Postulante p = (Postulante) mu.getUsuario(i);
        		assertTrue(p.listarPostulaciones().contains(res.get(i)));
        	}
    	}
    	
    	
    	@Test
        public void CargarDatosPaq() throws TOfertaRepetidaException{
        	CargaDatos c = new CargaDatos(ContU, ContO);
        	c.cargarTipoPublicacion();
        	c.cargarTipoPublicacion();
        	c.cargarPaquetes();
        	c.cargarPaquetes();
        	Set<String> res = new HashSet<String>();
        	res.add("Basico".toLowerCase());
        	res.add("Destacado".toLowerCase());
        	res.add("Premium".toLowerCase());
        	res.add("Express".toLowerCase());
        	for (String i : res) {
        		assertTrue(mo.getPaquetes().keySet().contains(i));
        	}
        	
   
    	}
  
    
    
}