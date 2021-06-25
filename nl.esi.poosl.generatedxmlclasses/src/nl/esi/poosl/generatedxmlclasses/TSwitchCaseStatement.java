//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.16 at 11:04:04 AM CET 
//


package nl.esi.poosl.generatedxmlclasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_switch_case_statement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_switch_case_statement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value_expression" type="{uri:poosl}t_expression"/>
 *         &lt;element name="body" type="{uri:poosl}t_statement"/>
 *       &lt;/sequence>
 *       &lt;attribute name="stmt_handle" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_switch_case_statement", propOrder = {
    "valueExpression",
    "body"
})
public class TSwitchCaseStatement {

    @XmlElement(name = "value_expression", required = true)
    protected TExpression valueExpression;
    @XmlElement(required = true)
    protected TStatement body;
    @XmlAttribute(name = "stmt_handle")
    protected Integer stmtHandle;

    /**
     * Gets the value of the valueExpression property.
     * 
     * @return
     *     possible object is
     *     {@link TExpression }
     *     
     */
    public TExpression getValueExpression() {
        return valueExpression;
    }

    /**
     * Sets the value of the valueExpression property.
     * 
     * @param value
     *     allowed object is
     *     {@link TExpression }
     *     
     */
    public void setValueExpression(TExpression value) {
        this.valueExpression = value;
    }

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link TStatement }
     *     
     */
    public TStatement getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStatement }
     *     
     */
    public void setBody(TStatement value) {
        this.body = value;
    }

    /**
     * Gets the value of the stmtHandle property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStmtHandle() {
        return stmtHandle;
    }

    /**
     * Sets the value of the stmtHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStmtHandle(Integer value) {
        this.stmtHandle = value;
    }

}
