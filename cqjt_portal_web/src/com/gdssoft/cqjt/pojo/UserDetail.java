package com.gdssoft.cqjt.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gson.annotations.Expose;

public class UserDetail extends BasePojo implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7921849202358855146L;

	@Expose
	private String userId;
	@Expose
	private String userNo;
	@Expose
	private String userName;
	@Expose
	private String userEmail;
	
	private String password;
	@Expose
	private String roleID;
	@Expose
	private String roleName;
	@Expose
	private String roleDesc;
	@Expose
	private String deptID;
	@Expose
	private String deptName;
	@Expose
	private String createDate;
	@Expose
	private String createUserNo;
	@Expose
	private String updateDate;
	@Expose
	private String updateUserNo;
	@Expose
	private String userStatus;
	
	@Expose
	private String userMobile;//电话
	@Expose
	private Integer userSex = 1;//性别：1男，0女
	
	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.userNo;
	}

	public boolean isEnabled() {
		return !Boolean.getBoolean(userStatus);
	}

	public String getAuthoritiesString() {
		List<String> authorities = new ArrayList<String>();
		for (GrantedAuthority authority : this.getAuthorities()) {
			authorities.add(authority.getAuthority());
		}
		return join(authorities, ",");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String string) {
		this.createDate = string;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	public String getUpdateUserNo() {
		return updateUserNo;
	}

	public void setUpdateUserNo(String updateUserNo) {
		this.updateUserNo = updateUserNo;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		if (StringUtils.isNotBlank(roleID)) {
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_CONSOLE");
			gas.add(ga);
		}
		return gas;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	private String join(List<String> list, String str) {
		if(list == null || list.isEmpty()) return "";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < list.size(); i++) {
			if(i != 0) buf.append(str);
			buf.append(list.get(i)); 
		}
		return buf.toString();
	}
}
