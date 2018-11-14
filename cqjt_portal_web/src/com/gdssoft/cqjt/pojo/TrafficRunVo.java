package com.gdssoft.cqjt.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import com.gdssoft.core.tools.DateUtil;

public class TrafficRunVo extends BasePojo{
	
	private String runnId;
	private String runnTitle; //标题
	private Object runnContent;//内容
	private String runnContentStr;//内容

	private int runnDel;//删除 0正常，1删除
	private Date runnTop;//置顶，降序排序
	private Date runnCreateDate;//创建时间
	private Date runnEditDate;//修改时间
	private String runnCreateUserName;
	
	public String getRunnContentStr() {
		if(runnContent != null){
			try {
				runnContentStr = ClobToString( (Clob)runnContent);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return runnContentStr;
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
	
	public String getRunnCreateUserName() {
		return runnCreateUserName;
	}
	public void setRunnCreateUserName(String runnCreateUserName) {
		this.runnCreateUserName = runnCreateUserName;
	}
	private String isTop;
	private String runnCreateDateStr;
	public String getRunnCreateDateStr() {
		if(runnCreateDate != null){
			runnCreateDateStr = DateUtil.dateFormat(runnCreateDate);
		}
		return runnCreateDateStr;
	}
	public String getRunnEditDateStr() {
		if(runnEditDate != null){
			runnEditDateStr = DateUtil.dateFormat(runnEditDate);
		}
		return runnEditDateStr;
	}
	private String runnEditDateStr;
	
	public String getIsTop() {
		isTop = runnTop == null ? "否" : "是";
		return isTop;
	}
	public String getRunnId() {
		return runnId;
	}
	public void setRunnId(String runnId) {
		this.runnId = runnId;
	}
	public String getRunnTitle() {
		return runnTitle;
	}
	public void setRunnTitle(String runnTitle) {
		this.runnTitle = runnTitle;
	}
	public Object getRunnContent() {
		return runnContent;
	}
	public void setRunnContent(Object runnContent) {
		this.runnContent = runnContent;
	}
	public int getRunnDel() {
		return runnDel;
	}
	public void setRunnDel(int ruunDel) {
		this.runnDel = ruunDel;
	}
	public Date getRunnTop() {
		return runnTop;
	}
	public void setRunnTop(Date runnTop) {
		this.runnTop = runnTop;
	}
	public Date getRunnCreateDate() {
		return runnCreateDate;
	}
	public void setRunnCreateDate(Date ruunCreateDate) {
		this.runnCreateDate = ruunCreateDate;
	}
	public Date getRunnEditDate() {
		return runnEditDate;
	}
	public void setRunnEditDate(Date ruunEditDate) {
		this.runnEditDate = ruunEditDate;
	}

	
}
