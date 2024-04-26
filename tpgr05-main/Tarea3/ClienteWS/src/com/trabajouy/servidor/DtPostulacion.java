
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fecha" type="{http://Servidor/}localDate" minOccurs="0"/>
 *         <element name="curriculum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="oferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="urlfoto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fotoPostulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="urlVideo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "fecha",
    "curriculum",
    "descripcion",
    "oferta",
    "fechaString",
    "urlfoto",
    "postulante",
    "fotoPostulante",
    "urlVideo"
})
public class DtPostulacion {

    protected LocalDate fecha;
    protected String curriculum;
    protected String descripcion;
    protected String oferta;
    protected String fechaString;
    protected String urlfoto;
    protected String postulante;
    protected String fotoPostulante;
    protected String urlVideo;

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFecha(LocalDate value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad curriculum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurriculum() {
        return curriculum;
    }

    /**
     * Define el valor de la propiedad curriculum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurriculum(String value) {
        this.curriculum = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad oferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOferta() {
        return oferta;
    }

    /**
     * Define el valor de la propiedad oferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOferta(String value) {
        this.oferta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaString() {
        return fechaString;
    }

    /**
     * Define el valor de la propiedad fechaString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaString(String value) {
        this.fechaString = value;
    }

    /**
     * Obtiene el valor de la propiedad urlfoto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlfoto() {
        return urlfoto;
    }

    /**
     * Define el valor de la propiedad urlfoto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlfoto(String value) {
        this.urlfoto = value;
    }

    /**
     * Obtiene el valor de la propiedad postulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostulante() {
        return postulante;
    }

    /**
     * Define el valor de la propiedad postulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostulante(String value) {
        this.postulante = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoPostulante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFotoPostulante() {
        return fotoPostulante;
    }

    /**
     * Define el valor de la propiedad fotoPostulante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFotoPostulante(String value) {
        this.fotoPostulante = value;
    }

    /**
     * Obtiene el valor de la propiedad urlVideo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlVideo() {
        return urlVideo;
    }

    /**
     * Define el valor de la propiedad urlVideo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlVideo(String value) {
        this.urlVideo = value;
    }

}
