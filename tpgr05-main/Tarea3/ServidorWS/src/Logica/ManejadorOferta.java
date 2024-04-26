package Logica;

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
    
    public Collection<String> getNombreTOfertas() {
    	Collection<String> res = new Vector<String>();
    	for (String s: tofertas.keySet()) {
    		res.add(s);
    	}
        return res;
    }

    public Collection<String> listarOfertas() {
    	Collection<String> res = new Vector<String>();
    	for (String s: ofertas.keySet()) {
    		res.add(s);
    	}
        return res;
    }
  
    public void agregarTOferta(TOferta toferta) {
        tofertas.put(toferta.getNombre(), toferta);
    }

    public void agregarKeyword(String nombre) {
        Keyword keyw= new Keyword(nombre);
        keywords.put(nombre, keyw);
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
    
    public Collection<String> getNombresPaquetesDisponibles() {
    	Collection<String> res = new Vector<String>();
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

    public Collection<String> listarPaquetes(){
        Collection<String> res = new Vector<String>();
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
       
        Paquete paquete = paquetes.get(nombrePaquete);
        if(paquete != null) {
            TOferta tipoOferta = tofertas.get(nombreTipo);
     
         if(paquete.getCantTOfertas().containsKey(tipoOferta.getNombre())) {
                CantidadTOferta cantiTo = paquete.getCantTOfertas().get(tipoOferta.getNombre());
                cantiTo.setCant(cantiTo.getCant() + cantidad);

                float  descuento = (1-paquete.getDescuento());
                float costoPaquete = paquete.getCosto() ;
                float costoTipo = tipoOferta.getCosto() * cantidad;
                float costoTotal = (costoPaquete/descuento) + costoTipo; 
                float costoDescuetno = costoTotal * descuento;
                paquete.setCosto(costoDescuetno);
            }
            else{
                
                float  descuento = (1-paquete.getDescuento());
                float costoPaquete = paquete.getCosto() ;
                float costoTipo = tipoOferta.getCosto() * cantidad;
                float costoTotal = (costoPaquete / descuento ) + costoTipo; 
                float costoDescuento = costoTotal * descuento;
                paquete.setCosto(costoDescuento);
                paquete.agregarTOferta(new CantidadTOferta(tipoOferta,paquete,cantidad));
            }
           


        
        
          
        }

        

        
        
    } 
    
    public Collection<String> getNombresTOPaquetes(String nombrePaquete){
        Collection<String> res = new Vector<String>();
        Paquete paque = paquetes.get(nombrePaquete);
        if(paque != null) {
            for(CantidadTOferta c : paque.getCantTOfertas().values()) {
                res.add(c.getTOferta().getNombre());
            }
        }
        return res;
    }

    public String getInfoTipo(String tipo){
        TOferta tipoOferta = tofertas.get(tipo);


      String info = "Nombre: " + tipoOferta.getNombre() + "\n" +
                    "Descripcion: " + tipoOferta.getDescripcion() + "\n" +
                    "Exposicion: " + tipoOferta.getExposicion() + "\n" +
                    "Duracion: " + tipoOferta.getDuracion() + "\n" +
                    "Costo: " + tipoOferta.getCosto() + "\n" +
                    "Fecha de Alta: " + tipoOferta.getFechaDeAlta().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString() + "\n";
        return info;
    }

    }
