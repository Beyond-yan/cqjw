
package com.centit.gk.msasoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackRedbackRedblackBack complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackRedbackRedblackBack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redblackType" type="{http://msasoa.gk.centit.com/}blackRedbackRedblackType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackRedbackRedblackBack", propOrder = {
    "name",
    "code",
    "redblackType"
})
public class BlackRedbackRedblackBack {

    protected String name;
    protected String code;
    protected List<BlackRedbackRedblackType> redblackType;

    /**
     * ��ȡname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * ����name���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * ��ȡcode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * ����code���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the redblackType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the redblackType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRedblackType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlackRedbackRedblackType }
     * 
     * 
     */
    public List<BlackRedbackRedblackType> getRedblackType() {
        if (redblackType == null) {
            redblackType = new ArrayList<BlackRedbackRedblackType>();
        }
        return this.redblackType;
    }

	public void setRedblackType(List<BlackRedbackRedblackType> redblackType) {
		this.redblackType = redblackType;
	}

}
