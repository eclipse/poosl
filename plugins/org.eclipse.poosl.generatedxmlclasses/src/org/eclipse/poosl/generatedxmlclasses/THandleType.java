//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.16 at 11:04:04 AM CET 
//

package org.eclipse.poosl.generatedxmlclasses;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for t_handle_type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="t_handle_type">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="poosl_specification"/>
 *     &lt;enumeration value="cluster_class"/>
 *     &lt;enumeration value="process_class"/>
 *     &lt;enumeration value="data_class"/>
 *     &lt;enumeration value="initial_method_call"/>
 *     &lt;enumeration value="process_method"/>
 *     &lt;enumeration value="data_method"/>
 *     &lt;enumeration value="expression"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_handle_type")
@XmlEnum
public enum THandleType {

    @XmlEnumValue("poosl_specification")
    POOSL_SPECIFICATION("poosl_specification"), @XmlEnumValue("cluster_class") //$NON-NLS-1$
    CLUSTER_CLASS("cluster_class"), @XmlEnumValue("process_class") //$NON-NLS-1$
    PROCESS_CLASS("process_class"), @XmlEnumValue("data_class") //$NON-NLS-1$
    DATA_CLASS("data_class"), @XmlEnumValue("initial_method_call") //$NON-NLS-1$
    INITIAL_METHOD_CALL("initial_method_call"), @XmlEnumValue("process_method") //$NON-NLS-1$
    PROCESS_METHOD("process_method"), @XmlEnumValue("data_method") //$NON-NLS-1$
    DATA_METHOD("data_method"), @XmlEnumValue("expression") //$NON-NLS-1$
    EXPRESSION("expression"); //$NON-NLS-1$

    private final String value;

    THandleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static THandleType fromValue(String v) {
        for (THandleType c : THandleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
