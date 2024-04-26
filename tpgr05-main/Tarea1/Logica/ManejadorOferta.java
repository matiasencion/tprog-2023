package Logica;

import Logica.Oferta;
import excepciones.TOfertaNoExiste;

import java.util.Map;
import java.util.Vector;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class ManejadorOferta {

    private static ManejadorOferta instancia = null;
    private Map<String, Oferta> ofertas;
    private Map<String, TOferta> tofertas;
    private Map<String, Keyword> keywords;
    private Map<String, Paquete> paquetes;


    private ManejadorOferta() {
        this.ofertas = new HashMap<String, Oferta>();
        this.tofertas = new HashMap<String, TOferta>();
        this.keywords = new HashMap<String, Keyword>();
        this.paquetes = new HashMap<String, Paquete>();
    }

    public static ManejadorOferta getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorOferta();
        }
        return instancia;
    }

    public void agregarOferta(Oferta oferta) {
        this.ofertas.put(oferta.getNombre(), oferta);
    }

   /* public void eliminarOferta(Oferta oferta) {
        this.ofertas.remove(oferta.getNombre());
    }*/

    public void modificarOferta(Oferta oferta) {
        this.ofertas.put(oferta.getNombre(), oferta);
    }

    public Oferta getOferta(String nombre) {
        return this.ofertas.get(nombre);
    }

    public Collection<Oferta> getOfertas() {
        return this.ofertas.values();
    }


    public TOferta getTOferta(String nombre) {
        	return tofertas.get(nombre);
    }

    public Collection<TOferta> getTOfertas() {
        return tofertas.values();
    }
    
    public Vector<String> getNombreTOfertas() {
    	Vector<String> res = new Vector<String>();
    	for (String s: tofertas.keySet()) {
    		res.add(s);
    	}
        return res;
    }

    public Vector<String> listarOfertas() {
    	Vector<String> res = new Vector<String>();
    	for (String s: ofertas.keySet()) {
    		res.add(s);
    	}
        return res;
    }
  
    public void agregarTOferta(TOferta toferta) {
        tofertas.put(toferta.getNombre(), toferta);
    }

    public void agregarKeyword(String nombre) {
        Keyword k = new Keyword(nombre);
        keywords.put(nombre, k);
    }
    public Keyword buscarKeyword(String nombre) {
        return this.keywords.get(nombre);
    }
    public boolean existeKeyword(String nombre) {
        return keywords.containsKey(nombre);
    }

    public Collection<Keyword> getKeywords() {
        return keywords.values();
    }
    
    public Vector<String> getNombresPaquetesDisponibles() {
    	Vector<String> res = new Vector<String>();
    	for(String p : paquetes.keySet()) {
    		if(paquetes.get(p).Disponible()) {
    			res.add(p);
    		}
    	}
    	return res;
    }
    
    public Map<String, Paquete> getPaquetes(){
    	return paquetes;
    }

    public Paquete getPaquete(String nombre) {
        return paquetes.get(nombre);
    }


    public void agregarPaquete(Paquete paquete) {

        paquetes.put(paquete.getNombre(), paquete);

        System.out.println("Paquete agregado");
    }

    public Vector<String> listarPaquetes(){
        Vector<String> res = new Vector<String>();
        for (String s: paquetes.keySet()) {
            res.add(s);
        }
        return res;
        
    }
    
    public void clear() {
    	ofertas.clear();
    	tofertas.clear();
    	keywords.clear();
    	paquetes.clear();
    }
 


   public void agregarPTP(String nombrePaquete , String nombreTipo, int cantidad){
       
        Paquete p = paquetes.get(nombrePaquete);
        if(p != null) {
            TOferta t = tofertas.get(nombreTipo);
     
         if(p.getCantTOfertas().containsKey(t.getNombre())) {
                CantidadTOferta c = p.getCantTOfertas().get(t.getNombre());
                c.setCant(c.getCant() + cantidad);

                float  descuento = (1-p.getDescuento());
                float costoPaquete = p.getCosto() ;
                float costoTipo = t.getCosto() * cantidad;
                float costoTotal = (costoPaquete/descuento) + costoTipo; 
                float costoDescuetno = costoTotal * descuento;
                p.setCosto(costoDescuetno);
            }
            else{
                
                float  descuento = (1-p.getDescuento());
                float costoPaquete = p.getCosto() ;
                float costoTipo = t.getCosto() * cantidad;
                float costoTotal = (costoPaquete / descuento ) + costoTipo; 
                float costoDescuento = costoTotal * descuento;
                p.setCosto(costoDescuento);
                p.agregarTOferta(new CantidadTOferta(t,p,cantidad));
            }
           


        
        
          
        }

        

        
        
    } 
    
    public Vector<String> getNombresTOPaquetes(String nombrePaquete){
        Vector<String> res = new Vector<String>();
        Paquete p = paquetes.get(nombrePaquete);
        if(p != null) {
            for(CantidadTOferta c : p.getCantTOfertas().values()) {
                res.add(c.getTOferta().getNombre());
            }
        }
        return res;
    }

    public String getInfoTipo(String tipo){
        TOferta t = tofertas.get(tipo);


      String info = "Nombre: " + t.getNombre() + "\n" +
                    "Descripcion: " + t.getDescripcion() + "\n" +
                    "Exposicion: " + t.getExposicion() + "\n" +
                    "Duracion: " + t.getDuracion() + "\n" +
                    "Costo: " + t.getCosto() + "\n" +
                    "Fecha de Alta: " + t.getFechaDeAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + "\n";
        return info;
    }

    }
