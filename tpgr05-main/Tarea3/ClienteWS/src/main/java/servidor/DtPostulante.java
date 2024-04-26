
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPostulante complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPostulante">
 *   <complexContent>
 *     <extension base="{http://Servidor/}dtUsuario">
 *       <sequence>
 *         <element name="nacionalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="FechaNac" type="{http://Servidor/}localDate" minOccurs="0"/>
 *         <element name="fechaB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulante", propOrder = {
    "nacionalidad",
    "fechaNac",
    "fechaB",
    "fechaA"
})
public class DtPostulante
    extends DtUsuario
{

    protected String nacionalidad;
    @XmlElement(name = "FechaNac")
    protected LocalDate fechaNac;
    protected String fechaB;
    protected String fechaA;

    /**
     * Obtiene el valor de la propiedad nacionalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define el valor de la propiedad nacionalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidad(String value) {
        this.nacionalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaNac.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getFechaNac() {
        return fechaNac;
    }

    /**
     * Define el valor de la propiedad fechaNac.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setFechaNac(LocalDate value) {
        this.fechaNac = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaB.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaB() {
        return fechaB;
    }

    /**
     * Define el valor de la propiedad fechaB.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaB(String value) {
        this.fechaB = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaA() {
        return fechaA;
    }

    /**
     * Define el valor de la propiedad fechaA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaA(String value) {
        this.fechaA = value;
    }

}
