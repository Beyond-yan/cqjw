
package com.centit.gk.msasoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackRedbackUnionPair complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackRedbackUnionPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unionPairContent" type="{http://msasoa.gk.centit.com/}blackRedbackUnionPairContent" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackRedbackUnionPair", propOrder = {
    "unionPairContent"
})
public class BlackRedbackUnionPair {

    protected List<BlackRedbackUnionPairContent> unionPairContent;

    /**
     * Gets the value of the unionPairContent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the unionPairContent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUnionPairContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BlackRedbackUnionPairContent }
     * 
     * 
     */
    public List<BlackRedbackUnionPairContent> getUnionPairContent() {
        if (unionPairContent == null) {
            unionPairContent = new ArrayList<BlackRedbackUnionPairContent>();
        }
        return this.unionPairContent;
    }

	public void setUnionPairContent(
			List<BlackRedbackUnionPairContent> unionPairContent) {
		this.unionPairContent = unionPairContent;
	}

}
