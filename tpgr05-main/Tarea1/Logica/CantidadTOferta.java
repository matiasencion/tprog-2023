package Logica;

public class CantidadTOferta {
    private TOferta toferta;
    private int cant;
    private Paquete paquete; 

    public CantidadTOferta(TOferta t,Paquete p , int cant) {
        this.cant = cant;
        this.toferta = t;
        this.paquete = p;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getCant() {
        return cant;
    }
    
    public TOferta getTOferta() {
    	return toferta;
    }

	/*public Paquete getPaquete() {
		return paquete;
	}*/

    
}
