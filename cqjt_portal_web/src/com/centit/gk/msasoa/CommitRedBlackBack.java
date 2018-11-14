
package com.centit.gk.msasoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>commitRedBlackBack complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="commitRedBlackBack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="redblackBackList" type="{http://msasoa.gk.centit.com/}blackRedBackRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commitRedBlackBack", propOrder = {
    "sysId",
    "loginname",
    "personname",
    "departmentname",
    "redblackBackList"
})
public class CommitRedBlackBack {

    protected String sysId;
    protected String loginname;
    protected String personname;
    protected String departmentname;
    protected BlackRedBackRequest redblackBackList;

    /**
     * ��ȡsysId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * ����sysId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysId(String value) {
        this.sysId = value;
    }

    /**
     * ��ȡloginname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginname() {
        return loginname;
    }

    /**
     * ����loginname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginname(String value) {
        this.loginname = value;
    }

    /**
     * ��ȡpersonname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * ����personname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonname(String value) {
        this.personname = value;
    }

    /**
     * ��ȡdepartmentname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentname() {
        return departmentname;
    }

    /**
     * ����departmentname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentname(String value) {
        this.departmentname = value;
    }

    /**
     * ��ȡredblackBackList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BlackRedBackRequest }
     *     
     */
    public BlackRedBackRequest getRedblackBackList() {
        return redblackBackList;
    }

    /**
     * ����redblackBackList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BlackRedBackRequest }
     *     
     */
    public void setRedblackBackList(BlackRedBackRequest value) {
        this.redblackBackList = value;
    }

}
