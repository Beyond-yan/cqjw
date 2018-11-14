package com.gdssoft.cqjt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Role;
import com.gdssoft.cqjt.pojo.RolePermission;

@Repository("roleDAO")
public class RoleDAO {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	 @SuppressWarnings("unchecked")
	public List<Role> getRoleList(Role role){
		 return  (List<Role>) this.sqlMapClientTemplate.queryForList("role.getRoleList",role);
	 }
	 
	 public void insertRole(Role role){
		 this.sqlMapClientTemplate.insert("role.insertRole",role);
	 }	 
	 
	 public void updateRole(Role role){
		 this.sqlMapClientTemplate.update("role.updateRole",role);
	 }	 
	 
	 public void deleteRole(Role role){
		 this.sqlMapClientTemplate.update("role.deleteRole",role);
	 }	 
	 
	 public Role getRoleDetail(Role role){
		 return  (Role)this.sqlMapClientTemplate.queryForObject("role.getRoleDetail",role);
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<RolePermission> getRolePermissionList(RolePermission rolePermission){
		 return  (List<RolePermission>) this.sqlMapClientTemplate
				 .queryForList("role.getRolePermissionList",rolePermission);
	 }
	 
	 public void insertRolePermission(RolePermission rolePermission){
		 this.sqlMapClientTemplate.insert("role.insertRolePermission",rolePermission);
	 }	
	 
	 public void deleteRolePermission(RolePermission rolePermission){
		 this.sqlMapClientTemplate.insert("role.deleteRolePermission",rolePermission);
	 }
		
	 
}
