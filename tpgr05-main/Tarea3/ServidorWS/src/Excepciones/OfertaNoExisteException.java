package Excepciones;

@SuppressWarnings("serial")
public class OfertaNoExisteException extends Exception {

    public OfertaNoExisteException(String string) {
        super(string);
    }

}
