
package com.centit.gk.msasoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackRedbackRedblackDetail complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackRedbackRedblackDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="redblackDetailContent" type="{http://msasoa.gk.centit.com/}blackRedbackRedblackDetailContent" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackRedbackRedblackDetail", propOrder = {
    "redblackDetailContent"
})
public class BlackRedbackRedblackDetail {

    protected List<BlackRedbackRedblackDetailContent> redblackDetailContent;

    /**
     * Gets the value of the redblackDetailContent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the redblackDetailContent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRedblackDetailContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlackRedbackRedblackDetailContent }
     * 
     * 
     */
    public List<BlackRedbackRedblackDetailContent> getRedblackDetailContent() {
        if (redblackDetailContent == null) {
            redblackDetailContent = new ArrayList<BlackRedbackRedblackDetailContent>();
        }
        return this.redblackDetailContent;
    }

	public void setRedblackDetailContent(
			List<BlackRedbackRedblackDetailContent> redblackDetailContent) {
		this.redblackDetailContent = redblackDetailContent;
	}

}
