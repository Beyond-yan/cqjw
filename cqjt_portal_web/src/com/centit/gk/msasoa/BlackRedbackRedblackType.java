
package com.centit.gk.msasoa;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>blackRedbackRedblackType complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="blackRedbackRedblackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="originDept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitedId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedbackResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unionPairList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="unionPair" type="{http://msasoa.gk.centit.com/}blackRedbackUnionPair" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="redblackDetailList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="redblackDetail" type="{http://msasoa.gk.centit.com/}blackRedbackRedblackDetail" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "blackRedbackRedblackType", propOrder = {
    "originDept",
    "unitedId",
    "unitedType",
    "unitedName",
    "feedbackResult",
    "remark",
    "action",
    "handleType",
    "clTime",
    "unionPairList",
    "redblackDetailList"
})
public class BlackRedbackRedblackType {

    protected String originDept;
    protected String unitedId;
    protected String unitedType;
    protected String unitedName;
    protected String feedbackResult;
    protected String remark;
    protected String action;
    protected String handleType;
    protected String clTime;
    protected BlackRedbackRedblackType.UnionPairList unionPairList;
    protected BlackRedbackRedblackType.RedblackDetailList redblackDetailList;

    /**
     * ��ȡoriginDept���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginDept() {
        return originDept;
    }

    /**
     * ����originDept���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginDept(String value) {
        this.originDept = value;
    }

    /**
     * ��ȡunitedId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitedId() {
        return unitedId;
    }

    /**
     * ����unitedId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitedId(String value) {
        this.unitedId = value;
    }

    /**
     * ��ȡunitedType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitedType() {
        return unitedType;
    }

    /**
     * ����unitedType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitedType(String value) {
        this.unitedType = value;
    }

    /**
     * ��ȡunitedName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitedName() {
        return unitedName;
    }

    /**
     * ����unitedName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitedName(String value) {
        this.unitedName = value;
    }

    /**
     * ��ȡfeedbackResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeedbackResult() {
        return feedbackResult;
    }

    /**
     * ����feedbackResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeedbackResult(String value) {
        this.feedbackResult = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * ��ȡaction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * ����action���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * ��ȡhandleType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandleType() {
        return handleType;
    }

    /**
     * ����handleType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandleType(String value) {
        this.handleType = value;
    }

    /**
     * ��ȡclTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClTime() {
        return clTime;
    }

    /**
     * ����clTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClTime(String value) {
        this.clTime = value;
    }

    /**
     * ��ȡunionPairList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BlackRedbackRedblackType.UnionPairList }
     *     
     */
    public BlackRedbackRedblackType.UnionPairList getUnionPairList() {
        return unionPairList;
    }

    /**
     * ����unionPairList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BlackRedbackRedblackType.UnionPairList }
     *     
     */
    public void setUnionPairList(BlackRedbackRedblackType.UnionPairList value) {
        this.unionPairList = value;
    }

    /**
     * ��ȡredblackDetailList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BlackRedbackRedblackType.RedblackDetailList }
     *     
     */
    public BlackRedbackRedblackType.RedblackDetailList getRedblackDetailList() {
        return redblackDetailList;
    }

    /**
     * ����redblackDetailList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BlackRedbackRedblackType.RedblackDetailList }
     *     
     */
    public void setRedblackDetailList(BlackRedbackRedblackType.RedblackDetailList value) {
        this.redblackDetailList = value;
    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="redblackDetail" type="{http://msasoa.gk.centit.com/}blackRedbackRedblackDetail" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "redblackDetail"
    })
    public static class RedblackDetailList {

        protected List<BlackRedbackRedblackDetail> redblackDetail;

        /**
         * Gets the value of the redblackDetail property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the redblackDetail property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRedblackDetail().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BlackRedbackRedblackDetail }
         * 
         * 
         */
        public List<BlackRedbackRedblackDetail> getRedblackDetail() {
            if (redblackDetail == null) {
                redblackDetail = new ArrayList<BlackRedbackRedblackDetail>();
            }
            return this.redblackDetail;
        }

		public void setRedblackDetail(List<BlackRedbackRedblackDetail> redblackDetail) {
			this.redblackDetail = redblackDetail;
		}

    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�����ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="unionPair" type="{http://msasoa.gk.centit.com/}blackRedbackUnionPair" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "unionPair"
    })
    public static class UnionPairList {

        protected List<BlackRedbackUnionPair> unionPair;

        /**
         * Gets the value of the unionPair property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the unionPair property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUnionPair().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BlackRedbackUnionPair }
         * 
         * 
         */
        public List<BlackRedbackUnionPair> getUnionPair() {
            if (unionPair == null) {
                unionPair = new ArrayList<BlackRedbackUnionPair>();
            }
            return this.unionPair;
        }

		public void setUnionPair(List<BlackRedbackUnionPair> unionPair) {
			this.unionPair = unionPair;
		}

    }

}
