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
 * Java class for t_perform_transition_response_result.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="t_perform_transition_response_result">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ok"/>
 *     &lt;enumeration value="inexecutable"/>
 *     &lt;enumeration value="invalid transition"/>
 *     &lt;enumeration value="invalid handle"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "t_perform_transition_response_result")
@XmlEnum
public enum TPerformTransitionResponseResult {

    @XmlEnumValue("ok")
    OK("ok"), @XmlEnumValue("inexecutable") //$NON-NLS-1$
    INEXECUTABLE("inexecutable"), @XmlEnumValue("invalid transition") //$NON-NLS-1$
    INVALID_TRANSITION("invalid transition"), @XmlEnumValue("invalid handle") //$NON-NLS-1$
    INVALID_HANDLE("invalid handle"); //$NON-NLS-1$

    private final String value;

    TPerformTransitionResponseResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TPerformTransitionResponseResult fromValue(String v) {
        for (TPerformTransitionResponseResult c : TPerformTransitionResponseResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
