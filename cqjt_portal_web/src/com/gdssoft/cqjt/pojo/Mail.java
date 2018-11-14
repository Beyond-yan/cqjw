package com.gdssoft.cqjt.pojo;
/*
 *  捷达世软件(深圳)有限公司
*/
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

public class Mail {

	@Expose
	protected String mid;
	@Expose
	protected String msid;
	@Expose
	protected String fid;
	@Expose
	protected String flag;
	@Expose
	protected String from;
	@Expose
	protected String to;
	@Expose
	protected String subject;
	@Expose
	protected String size;
	@Expose
	protected Date date;
	

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMsid() {
		return msid;
	}

	public void setMsid(String msid) {
		this.msid = msid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Mail)) {
			return false;
		}
		Mail rhs = (Mail) object;
		return new EqualsBuilder()
				.append(this.mid, rhs.mid)
				.append(this.msid, rhs.msid)
				.append(this.fid, rhs.fid)
				.append(this.flag, rhs.flag)
				.append(this.from, rhs.from)
				.append(this.to, rhs.to)
				.append(this.subject, rhs.subject)
				.append(this.size, rhs.size)
				.append(this.date, rhs.date)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.mid) 
				.append(this.msid) 
				.append(this.fid) 
				.append(this.flag) 
				.append(this.from) 
				.append(this.to) 
				.append(this.subject) 
				.append(this.size) 
				.append(this.date) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("mid", this.mid) 
				.append("msid", this.msid) 
				.append("fid", this.fid) 
				.append("flag", this.flag) 
				.append("from", this.from) 
				.append("to", this.to) 
				.append("subject", this.subject) 
				.append("size", this.size) 
				.append("date", this.date) 
				.toString();
	}

}
