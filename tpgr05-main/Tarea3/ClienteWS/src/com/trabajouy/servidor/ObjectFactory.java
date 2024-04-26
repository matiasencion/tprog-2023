
package servidor;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://Servidor/", "Exception");
    private final static QName _KeywordRepetida_QNAME = new QName("http://Servidor/", "KeywordRepetida");
    private final static QName _OfertaNoExisteException_QNAME = new QName("http://Servidor/", "OfertaNoExisteException");
    private final static QName _OfertaRepetidaException_QNAME = new QName("http://Servidor/", "OfertaRepetidaException");
    private final static QName _TOfertaRepetidaException_QNAME = new QName("http://Servidor/", "TOfertaRepetidaException");
    private final static QName _UsuarioEmailRepetido_QNAME = new QName("http://Servidor/", "UsuarioEmailRepetido");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://Servidor/", "UsuarioNoExisteException");
    private final static QName _UsuarioRepetidoException_QNAME = new QName("http://Servidor/", "UsuarioRepetidoException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtCompraPaquete }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete }
     */
    public DtCompraPaquete createDtCompraPaquete() {
        return new DtCompraPaquete();
    }

    /**
     * Create an instance of {@link DtCompraPaquete.Disponibles }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete.Disponibles }
     */
    public DtCompraPaquete.Disponibles createDtCompraPaqueteDisponibles() {
        return new DtCompraPaquete.Disponibles();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPaquete.ComprasPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete.ComprasPaquete }
     */
    public DtPaquete.ComprasPaquete createDtPaqueteComprasPaquete() {
        return new DtPaquete.ComprasPaquete();
    }

    /**
     * Create an instance of {@link DtPaquete.Tofertas }
     * 
     * @return
     *     the new instance of {@link DtPaquete.Tofertas }
     */
    public DtPaquete.Tofertas createDtPaqueteTofertas() {
        return new DtPaquete.Tofertas();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     * @return
     *     the new instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link KeywordRepetida }
     * 
     * @return
     *     the new instance of {@link KeywordRepetida }
     */
    public KeywordRepetida createKeywordRepetida() {
        return new KeywordRepetida();
    }

    /**
     * Create an instance of {@link OfertaNoExisteException }
     * 
     * @return
     *     the new instance of {@link OfertaNoExisteException }
     */
    public OfertaNoExisteException createOfertaNoExisteException() {
        return new OfertaNoExisteException();
    }

    /**
     * Create an instance of {@link OfertaRepetidaException }
     * 
     * @return
     *     the new instance of {@link OfertaRepetidaException }
     */
    public OfertaRepetidaException createOfertaRepetidaException() {
        return new OfertaRepetidaException();
    }

    /**
     * Create an instance of {@link TOfertaRepetidaException }
     * 
     * @return
     *     the new instance of {@link TOfertaRepetidaException }
     */
    public TOfertaRepetidaException createTOfertaRepetidaException() {
        return new TOfertaRepetidaException();
    }

    /**
     * Create an instance of {@link UsuarioEmailRepetido }
     * 
     * @return
     *     the new instance of {@link UsuarioEmailRepetido }
     */
    public UsuarioEmailRepetido createUsuarioEmailRepetido() {
        return new UsuarioEmailRepetido();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     * @return
     *     the new instance of {@link UsuarioNoExisteException }
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoException }
     * 
     * @return
     *     the new instance of {@link UsuarioRepetidoException }
     */
    public UsuarioRepetidoException createUsuarioRepetidoException() {
        return new UsuarioRepetidoException();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link CantidadTOferta }
     * 
     * @return
     *     the new instance of {@link CantidadTOferta }
     */
    public CantidadTOferta createCantidadTOferta() {
        return new CantidadTOferta();
    }

    /**
     * Create an instance of {@link CompraPaquete }
     * 
     * @return
     *     the new instance of {@link CompraPaquete }
     */
    public CompraPaquete createCompraPaquete() {
        return new CompraPaquete();
    }

    /**
     * Create an instance of {@link TOferta }
     * 
     * @return
     *     the new instance of {@link TOferta }
     */
    public TOferta createTOferta() {
        return new TOferta();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtPublicar }
     * 
     * @return
     *     the new instance of {@link DtPublicar }
     */
    public DtPublicar createDtPublicar() {
        return new DtPublicar();
    }

    /**
     * Create an instance of {@link DtOferta }
     * 
     * @return
     *     the new instance of {@link DtOferta }
     */
    public DtOferta createDtOferta() {
        return new DtOferta();
    }

    /**
     * Create an instance of {@link Oferta }
     * 
     * @return
     *     the new instance of {@link Oferta }
     */
    public Oferta createOferta() {
        return new Oferta();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtTipoOferta }
     * 
     * @return
     *     the new instance of {@link DtTipoOferta }
     */
    public DtTipoOferta createDtTipoOferta() {
        return new DtTipoOferta();
    }

    /**
     * Create an instance of {@link DtPostulacionArray }
     * 
     * @return
     *     the new instance of {@link DtPostulacionArray }
     */
    public DtPostulacionArray createDtPostulacionArray() {
        return new DtPostulacionArray();
    }

    /**
     * Create an instance of {@link DtPaqueteArray }
     * 
     * @return
     *     the new instance of {@link DtPaqueteArray }
     */
    public DtPaqueteArray createDtPaqueteArray() {
        return new DtPaqueteArray();
    }

    /**
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

    /**
     * Create an instance of {@link DtOfertaArray }
     * 
     * @return
     *     the new instance of {@link DtOfertaArray }
     */
    public DtOfertaArray createDtOfertaArray() {
        return new DtOfertaArray();
    }

    /**
     * Create an instance of {@link DtCompraPaqueteArray }
     * 
     * @return
     *     the new instance of {@link DtCompraPaqueteArray }
     */
    public DtCompraPaqueteArray createDtCompraPaqueteArray() {
        return new DtCompraPaqueteArray();
    }

    /**
     * Create an instance of {@link DtTipoOfertaArray }
     * 
     * @return
     *     the new instance of {@link DtTipoOfertaArray }
     */
    public DtTipoOfertaArray createDtTipoOfertaArray() {
        return new DtTipoOfertaArray();
    }

    /**
     * Create an instance of {@link DtCompraPaquete.Disponibles.Entry }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete.Disponibles.Entry }
     */
    public DtCompraPaquete.Disponibles.Entry createDtCompraPaqueteDisponiblesEntry() {
        return new DtCompraPaquete.Disponibles.Entry();
    }

    /**
     * Create an instance of {@link DtPaquete.ComprasPaquete.Entry }
     * 
     * @return
     *     the new instance of {@link DtPaquete.ComprasPaquete.Entry }
     */
    public DtPaquete.ComprasPaquete.Entry createDtPaqueteComprasPaqueteEntry() {
        return new DtPaquete.ComprasPaquete.Entry();
    }

    /**
     * Create an instance of {@link DtPaquete.Tofertas.Entry }
     * 
     * @return
     *     the new instance of {@link DtPaquete.Tofertas.Entry }
     */
    public DtPaquete.Tofertas.Entry createDtPaqueteTofertasEntry() {
        return new DtPaquete.Tofertas.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeywordRepetida }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeywordRepetida }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "KeywordRepetida")
    public JAXBElement<KeywordRepetida> createKeywordRepetida(KeywordRepetida value) {
        return new JAXBElement<>(_KeywordRepetida_QNAME, KeywordRepetida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "OfertaNoExisteException")
    public JAXBElement<OfertaNoExisteException> createOfertaNoExisteException(OfertaNoExisteException value) {
        return new JAXBElement<>(_OfertaNoExisteException_QNAME, OfertaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfertaRepetidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link OfertaRepetidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "OfertaRepetidaException")
    public JAXBElement<OfertaRepetidaException> createOfertaRepetidaException(OfertaRepetidaException value) {
        return new JAXBElement<>(_OfertaRepetidaException_QNAME, OfertaRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOfertaRepetidaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TOfertaRepetidaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "TOfertaRepetidaException")
    public JAXBElement<TOfertaRepetidaException> createTOfertaRepetidaException(TOfertaRepetidaException value) {
        return new JAXBElement<>(_TOfertaRepetidaException_QNAME, TOfertaRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioEmailRepetido }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioEmailRepetido }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "UsuarioEmailRepetido")
    public JAXBElement<UsuarioEmailRepetido> createUsuarioEmailRepetido(UsuarioEmailRepetido value) {
        return new JAXBElement<>(_UsuarioEmailRepetido_QNAME, UsuarioEmailRepetido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://Servidor/", name = "UsuarioRepetidoException")
    public JAXBElement<UsuarioRepetidoException> createUsuarioRepetidoException(UsuarioRepetidoException value) {
        return new JAXBElement<>(_UsuarioRepetidoException_QNAME, UsuarioRepetidoException.class, null, value);
    }

}
