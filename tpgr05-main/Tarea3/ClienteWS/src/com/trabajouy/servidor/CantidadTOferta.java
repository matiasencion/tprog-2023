
package servidor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cantidadTOferta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="cantidadTOferta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cant" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cantidadTOferta", propOrder = {
    "cant"
})
public class CantidadTOferta {

    protected int cant;

    /**
     * Obtiene el valor de la propiedad cant.
     * 
     */
    public int getCant() {
        return cant;
    }

    /**
     * Define el valor de la propiedad cant.
     * 
     */
    public void setCant(int value) {
        this.cant = value;
    }

}
