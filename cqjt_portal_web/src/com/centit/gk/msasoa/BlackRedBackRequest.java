
package com.centit.gk.msasoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackRedBackRequest complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackRedBackRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redblackRequestId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redblackBack" type="{http://msasoa.gk.centit.com/}blackRedbackRedblackBack" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackRedBackRequest", propOrder = {
    "requestId",
    "redblackRequestId",
    "redblackBack"
})
public class BlackRedBackRequest {

    protected String requestId;
    protected String redblackRequestId;
    protected List<BlackRedbackRedblackBack> redblackBack;

    /**
     * ��ȡrequestId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * ����requestId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * ��ȡredblackRequestId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedblackRequestId() {
        return redblackRequestId;
    }

    /**
     * ����redblackRequestId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedblackRequestId(String value) {
        this.redblackRequestId = value;
    }

    /**
     * Gets the value of the redblackBack property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the redblackBack property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRedblackBack().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlackRedbackRedblackBack }
     * 
     * 
     */
    public List<BlackRedbackRedblackBack> getRedblackBack() {
        if (redblackBack == null) {
            redblackBack = new ArrayList<BlackRedbackRedblackBack>();
        }
        return this.redblackBack;
    }

	public void setRedblackBack(List<BlackRedbackRedblackBack> redblackBack) {
		this.redblackBack = redblackBack;
	}
    
}
