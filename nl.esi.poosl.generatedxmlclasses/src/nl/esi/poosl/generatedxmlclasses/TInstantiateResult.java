//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.16 at 11:04:04 AM CET 
//


package nl.esi.poosl.generatedxmlclasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_instantiate_result.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="t_instantiate_result">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ok"/>
 *     &lt;enumeration value="invalid specification"/>
 *     &lt;enumeration value="unknown handle"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_instantiate_result")
@XmlEnum
public enum TInstantiateResult {

    @XmlEnumValue("ok")
    OK("ok"),
    @XmlEnumValue("invalid specification")
    INVALID_SPECIFICATION("invalid specification"),
    @XmlEnumValue("unknown handle")
    UNKNOWN_HANDLE("unknown handle");
    private final String value;

    TInstantiateResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TInstantiateResult fromValue(String v) {
        for (TInstantiateResult c: TInstantiateResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
