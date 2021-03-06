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
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for t_source_position complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="t_source_position"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="line" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="offset" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "t_source_position", propOrder = { "file", "line", "column", "offset" })
public class TSourcePosition {

    @XmlElement(required = true)
    protected BigInteger file;

    @XmlElement(required = true)
    protected BigInteger line;

    @XmlElement(required = true)
    protected BigInteger column;

    @XmlElement(required = true)
    protected BigInteger offset;

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setFile(BigInteger value) {
        this.file = value;
    }

    /**
     * Gets the value of the line property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getLine() {
        return line;
    }

    /**
     * Sets the value of the line property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setLine(BigInteger value) {
        this.line = value;
    }

    /**
     * Gets the value of the column property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getColumn() {
        return column;
    }

    /**
     * Sets the value of the column property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setColumn(BigInteger value) {
        this.column = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     * 
     */
    public BigInteger getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     * 
     */
    public void setOffset(BigInteger value) {
        this.offset = value;
    }

}
