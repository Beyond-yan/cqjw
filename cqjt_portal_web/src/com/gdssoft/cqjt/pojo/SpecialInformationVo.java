package com.gdssoft.cqjt.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.gdssoft.core.tools.DateUtil;


public class SpecialInformationVo extends BasePojo{

	private String specialId;//专报信息表ID
	
	private String specialTitle;//标题
	
	private Object specialContent;//内容
	private String specialContentStr;//内容
	
	private int specialDel;//是否删除：0表示不删除，1表示删除
	
	private Date specialTop;//是否置顶：不为空表示置顶
	private String isTop;
	
	private Date specialCreateDate;//创建时间	
	private String specialCreateDateStr;
	
	private Date specialEditDate;//修改时间
	private String specialEditDateStr;
	
	private String specialCreateUserName;//创建人
	
	private Integer specialPublish;//是否发布：0表示发布，1表示不发布
	private String isPublish;
	private String specialAccessoryUrl;
	private String specialAccessoryPdfUrl;
	private String specialAccessoryName;
	
	public String getSpecialAccessoryPdfUrl() {
		if(StringUtils.isNotBlank(specialAccessoryUrl)){
			specialAccessoryPdfUrl = specialAccessoryUrl.substring(0, specialAccessoryUrl.lastIndexOf(".")) + ".pdf";
		}
		return specialAccessoryPdfUrl;
	}

	public void setSpecialAccessoryPdfUrl(String specialAccessoryPdfUrl) {
		this.specialAccessoryPdfUrl = specialAccessoryPdfUrl;
	}

	public String getSpecialAccessoryName() {
		return specialAccessoryName;
	}

	public void setSpecialAccessoryName(String specialAccessoryName) {
		this.specialAccessoryName = specialAccessoryName;
	}

	public String getSpecialAccessoryUrl() {
		return specialAccessoryUrl;
	}

	public void setSpecialAccessoryUrl(String specialAccessoryUrl) {
		this.specialAccessoryUrl = specialAccessoryUrl;
	}

	public String getSpecialContentStr() {
		if(specialContent != null){
			try {
				specialContentStr = ClobToString( (Clob)specialContent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return specialContentStr;
	}
	
	public String getSpecialCreateDateStr() {
		if(specialCreateDate != null){
			specialCreateDateStr = DateUtil.dateFormat(specialCreateDate);
		}
		return specialCreateDateStr;
	}
	public String getSpecialEditDateStr() {
		if(specialEditDate != null){
			specialEditDateStr = DateUtil.dateFormat(specialEditDate);
		}
		return specialEditDateStr;
	}
	
	public String getIsTop() {
		isTop = specialTop == null ? "否" : "是";
		return isTop;
	}
	
	public String getIsPublish(){
		isPublish = specialPublish == 0 ? "已发布" : "未发布";
		return isPublish;
	}
	
	public String ClobToString(Clob clob) throws SQLException, IOException {

		String reString = "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
		sb.append(s);
		s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}
	
	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getSpecialTitle() {
		return specialTitle;
	}

	public void setSpecialTitle(String specialTitle) {
		this.specialTitle = specialTitle;
	}

	public Object getSpecialContent() {
		return specialContent;
	}

	public void setSpecialContent(Object specialContent) {
		this.specialContent = specialContent;
	}

	public int getSpecialDel() {
		return specialDel;
	}

	public void setSpecialDel(int specialDel) {
		this.specialDel = specialDel;
	}

	public Date getSpecialTop() {
		return specialTop;
	}

	public void setSpecialTop(Date specialTop) {
		this.specialTop = specialTop;
	}

	public Date getSpecialCreateDate() {
		return specialCreateDate;
	}

	public void setSpecialCreateDate(Date specialCreateDate) {
		this.specialCreateDate = specialCreateDate;
	}

	public Date getSpecialEditDate() {
		return specialEditDate;
	}

	public void setSpecialEditDate(Date specialEditDate) {
		this.specialEditDate = specialEditDate;
	}

	public String getSpecialCreateUserName() {
		return specialCreateUserName;
	}

	public void setSpecialCreateUserName(String specialCreateUserName) {
		this.specialCreateUserName = specialCreateUserName;
	}

	public Integer getSpecialPublish() {
		return specialPublish;
	}

	public void setSpecialPublish(Integer specialPublish) {
		this.specialPublish = specialPublish;
	}
	
	
}