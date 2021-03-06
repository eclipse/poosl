//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.1 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.12.20 at 03:57:25 PM CET 
//

package org.eclipse.poosl.generatedxmlclasses;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for t_set_variable_response complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="t_set_variable_response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="result" type="{uri:poosl}t_set_variable_result"/&gt;
 *         &lt;element name="var_handle" type="{uri:poosl}t_handle_id"/&gt;
 *         &lt;element name="list_handle" type="{uri:poosl}t_handle_id"/&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_set_variable_response",
        propOrder = { "result", "varHandle", "listHandle", "error" })
public class TSetVariableResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TSetVariableResult result;

    @XmlElement(name = "var_handle", required = true)
    protected BigInteger varHandle;

    @XmlElement(name = "list_handle", required = true)
    protected BigInteger listHandle;

    @XmlElement(required = true)
    protected String error;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link TSetVariableResult }
     * 
     */
    public TSetVariableResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSetVariableResult }
     * 
     */
    public void setResult(TSetVariableResult value) {
        this.result = value;
    }

    /**
     * Gets the value of the varHandle property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getVarHandle() {
        return varHandle;
    }

    /**
     * Sets the value of the varHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setVarHandle(BigInteger value) {
        this.varHandle = value;
    }

    /**
     * Gets the value of the listHandle property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getListHandle() {
        return listHandle;
    }

    /**
     * Sets the value of the listHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setListHandle(BigInteger value) {
        this.listHandle = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * 
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * 
     */
    public void setError(String value) {
        this.error = value;
    }

}
