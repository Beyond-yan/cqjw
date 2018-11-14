
package com.centit.gk.msasoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getRedBlackType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getRedBlackType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRedBlackType", propOrder = {
    "sysId",
    "loginname",
    "personname",
    "departmentname",
    "unitedType"
})
public class GetRedBlackType {

    protected String sysId;
    protected String loginname;
    protected String personname;
    protected String departmentname;
    protected String unitedType;

    /**
     * 获取sysId属性的值。
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
     * 设置sysId属性的值。
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
     * 获取loginname属性的值。
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
     * 设置loginname属性的值。
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
     * 获取personname属性的值。
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
     * 设置personname属性的值。
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
     * 获取departmentname属性的值。
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
     * 设置departmentname属性的值。
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
     * 获取unitedType属性的值。
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
     * 设置unitedType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitedType(String value) {
        this.unitedType = value;
    }

}
