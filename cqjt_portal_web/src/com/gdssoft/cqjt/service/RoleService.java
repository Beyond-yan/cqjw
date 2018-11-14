package com.gdssoft.cqjt.service;

import java.util.List;

import com.gdssoft.cqjt.pojo.Role;
import com.gdssoft.cqjt.pojo.RolePermission;

public interface RoleService {
	List<Role> getRoleList();
	List<Role> getRoleList(Role role);
	void insertRole(Role role);
	void updateRole(Role role);
	void deleteRole(Role role);
	Role getRoleDetail(Role role);
	String getAllTreeInfo(RolePermission rolePermission);
	void insertRolePermission(RolePermission rolePermission);
	void deleteRolePermission(RolePermission rolePermission);
}
