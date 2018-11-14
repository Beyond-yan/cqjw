package com.gdssoft.cqjt.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import com.gdssoft.core.tools.DateUtil;
import com.google.gson.annotations.Expose;

public class SubscribeVo extends BasePojo{

	@Expose
	private String subsId;			//id
	@Expose
	private String subsTitle;		//标题
	@Expose
	private String subsContent;		//内容
	@Expose
	private Date subsTop;			//是否置顶
	@Expose
	private String isTop;
	@Expose
	private int subsDel;			//是否删除	0表示删除	1表示不删除
	@Expose
	private Date subsCreateDate;	//创建时间
	@Expose
	private String subsCreateDateStr;
	@Expose
	private Date subsEditDate;		//修改时间
	@Expose
	private String subsEditDateStr;
	@Expose
	private String subssendUserId;	//发送人ID
	@Expose
	private String subsRecvDeptId;	//接收部门ID
	@Expose
	private String subsRecvUserId;	//接收人ID
	@Expose
	private String subsRecvDeptName; //接收部门名称
	@Expose
	private Integer subsPublish;	//是否发布
	private String isPublish;
	
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
	
	public String getSubsCreateDateStr() {
		if(subsCreateDate != null){
			subsCreateDateStr = DateUtil.dateFormat(subsCreateDate);
		}
		return subsCreateDateStr;
	}
	public String getSubsEditDateStr() {
		if(subsEditDate != null){
			subsEditDateStr = DateUtil.dateFormat(subsEditDate);
		}
		return subsEditDateStr;
	}
	public String getIsTop() {
		isTop = subsTop == null ? "否" : "是";
		return isTop;
	}
	public String getIsPublish(){
		isPublish = subsPublish == 0 ? "已发布" : "未发布";
		return isPublish;
	}
	public String getSubsId() {
		return subsId;
	}
	public void setSubsId(String subsId) {
		this.subsId = subsId;
	}
	public String getSubsTitle() {
		return subsTitle;
	}
	public void setSubsTitle(String subsTitle) {
		this.subsTitle = subsTitle;
	}
	
	public Date getSubsTop() {
		return subsTop;
	}

	public Object getSubsContent() {
		return subsContent;
	}
	public void setSubsContent(String subsContent) {
		this.subsContent = subsContent;
	}
	public int getSubsDel() {
		return subsDel;
	}
	public void setSubsDel(int subsDel) {
		this.subsDel = subsDel;
	}
	public Date getSubsCreateDate() {
		return subsCreateDate;
	}
	public void setSubsCreateDate(Date subsCreateDate) {
		this.subsCreateDate = subsCreateDate;
	}
	public Date getSubsEditDate() {
		return subsEditDate;
	}
	public void setSubsEditDate(Date subsEditDate) {
		this.subsEditDate = subsEditDate;
	}
	public String getSubssendUserId() {
		return subssendUserId;
	}
	public void setSubssendUserId(String subssendUserId) {
		this.subssendUserId = subssendUserId;
	}
	public String getSubsRecvDeptId() {
		return subsRecvDeptId;
	}
	public void setSubsRecvDeptId(String subsRecvDeptId) {
		this.subsRecvDeptId = subsRecvDeptId;
	}
	public String getSubsRecvUserId() {
		return subsRecvUserId;
	}
	public void setSubsRecvUserId(String subsRecvUserId) {
		this.subsRecvUserId = subsRecvUserId;
	}
	public void setSubsTop(Date subsTop) {
		this.subsTop = subsTop;
	}
	public SubscribeVo() {
		super();
	}

	public String getSubsRecvDeptName() {
		return subsRecvDeptName;
	}

	public void setSubsRecvDeptName(String subsRecvDeptName) {
		this.subsRecvDeptName = subsRecvDeptName;
	}

	public Integer getSubsPublish() {
		return subsPublish;
	}

	public void setSubsPublish(Integer subsPublish) {
		this.subsPublish = subsPublish;
	}
	
}
