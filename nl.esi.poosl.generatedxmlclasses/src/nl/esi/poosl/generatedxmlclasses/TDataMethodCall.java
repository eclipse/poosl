//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.11.16 at 11:04:04 AM CET 
//


package nl.esi.poosl.generatedxmlclasses;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for t_data_method_call complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="t_data_method_call">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="receiver" type="{uri:poosl}t_expression"/>
 *         &lt;element name="argument" type="{uri:poosl}t_expression" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="method_name" use="required" type="{uri:poosl}t_data_method_name" />
 *       &lt;attribute name="super" use="required" type="{uri:poosl}t_boolean" />
 *       &lt;attribute name="stmt_handle" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_data_method_call", propOrder = {
    "receiver",
    "argument"
})
public class TDataMethodCall {

    @XmlElement(required = true)
    protected TExpression receiver;
    protected List<TExpression> argument;
    @XmlAttribute(name = "method_name", required = true)
    protected String methodName;
    @XmlAttribute(name = "super", required = true)
    protected TBoolean _super;
    @XmlAttribute(name = "stmt_handle")
    protected Integer stmtHandle;

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link TExpression }
     *     
     */
    public TExpression getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link TExpression }
     *     
     */
    public void setReceiver(TExpression value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the argument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the argument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArgument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TExpression }
     * 
     * 
     */
    public List<TExpression> getArgument() {
        if (argument == null) {
            argument = new ArrayList<TExpression>();
        }
        return this.argument;
    }

    /**
     * Gets the value of the methodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Sets the value of the methodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMethodName(String value) {
        this.methodName = value;
    }

    /**
     * Gets the value of the super property.
     * 
     * @return
     *     possible object is
     *     {@link TBoolean }
     *     
     */
    public TBoolean getSuper() {
        return _super;
    }

    /**
     * Sets the value of the super property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBoolean }
     *     
     */
    public void setSuper(TBoolean value) {
        this._super = value;
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
