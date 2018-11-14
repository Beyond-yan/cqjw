
package com.centit.gk.msasoa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getRedBlackBack complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getRedBlackBack">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sysId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="personname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="feedbackType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unitedType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="param" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRedBlackBack", propOrder = {
    "sysId",
    "loginname",
    "personname",
    "departmentname",
    "startTime",
    "endTime",
    "feedbackType",
    "unitedType",
    "param",
    "page",
    "size"
})
public class GetRedBlackBack {

    protected String sysId;
    protected String loginname;
    protected String personname;
    protected String departmentname;
    protected String startTime;
    protected String endTime;
    protected String feedbackType;
    protected String unitedType;
    protected String param;
    protected long page;
    protected long size;

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
     * 获取startTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * 设置startTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartTime(String value) {
        this.startTime = value;
    }

    /**
     * 获取endTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * 设置endTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndTime(String value) {
        this.endTime = value;
    }

    /**
     * 获取feedbackType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeedbackType() {
        return feedbackType;
    }

    /**
     * 设置feedbackType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeedbackType(String value) {
        this.feedbackType = value;
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

    /**
     * 获取param属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置param属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParam(String value) {
        this.param = value;
    }

    /**
     * 获取page属性的值。
     * 
     */
    public long getPage() {
        return page;
    }

    /**
     * 设置page属性的值。
     * 
     */
    public void setPage(long value) {
        this.page = value;
    }

    /**
     * 获取size属性的值。
     * 
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置size属性的值。
     * 
     */
    public void setSize(long value) {
        this.size = value;
    }

}
