
package servidor;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estado.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="estado">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Ingresado"/>
 *     <enumeration value="Confirmado"/>
 *     <enumeration value="Rechazado"/>
 *     <enumeration value="Finalizado"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "estado")
@XmlEnum
public enum Estado {

    @XmlEnumValue("Ingresado")
    INGRESADO("Ingresado"),
    @XmlEnumValue("Confirmado")
    CONFIRMADO("Confirmado"),
    @XmlEnumValue("Rechazado")
    RECHAZADO("Rechazado"),
    @XmlEnumValue("Finalizado")
    FINALIZADO("Finalizado");
    private final String value;

    Estado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Estado fromValue(String v) {
        for (Estado c: Estado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
