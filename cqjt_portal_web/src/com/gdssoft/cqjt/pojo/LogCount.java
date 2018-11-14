package com.gdssoft.cqjt.pojo;

import java.util.Date;

import com.google.gson.annotations.Expose;

public class LogCount  extends BasePojo{
	/* 自增ID */
	@Expose
	protected int id;

	/* 用户名 */
	@Expose
	protected String userName;

	/* IP地址 */
	@Expose
	protected String ip;

	/* 更新时间 */
	@Expose
	protected Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
