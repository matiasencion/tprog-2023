package Logica;

public class CantidadTOferta {
  private TOferta toferta;
  private int cant;
  private Paquete paquete;

  public CantidadTOferta(TOferta toferta, Paquete paque, int cant) {
    this.cant = cant;
    this.toferta = toferta;
    this.paquete = paque;
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

  public Paquete getPaquete() {
    return paquete;
  }

}
