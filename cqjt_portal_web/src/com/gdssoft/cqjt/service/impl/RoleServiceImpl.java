package com.gdssoft.cqjt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.RoleDAO;
import com.gdssoft.cqjt.pojo.Role;
import com.gdssoft.cqjt.pojo.RolePermission;
import com.gdssoft.cqjt.service.RoleService;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	@Resource(name="roleDAO")
	private RoleDAO roleDAO;

	@Override
	public List<Role> getRoleList() {
		return roleDAO.getRoleList(new Role());
	}
	@Override
	public List<Role> getRoleList(Role role) {
		return roleDAO.getRoleList(role);
	}

	@Override
	public void insertRole(Role role) {
		roleDAO.insertRole(role);
	}

	@Override
	public void updateRole(Role role) {
		roleDAO.updateRole(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleDAO.deleteRole(role);
	}

	@Override
	public Role getRoleDetail(Role role) {
		return roleDAO.getRoleDetail(role);
	}
	
	@Override
	public 	String getAllTreeInfo(RolePermission rolePermission){
		List<RolePermission> rolePermissionList = roleDAO.getRolePermissionList(rolePermission);

		StringBuffer ztree = new StringBuffer("[");
		//StringBuffer moduleNameBuffer = new StringBuffer();

		String nodeTemp = "{id:'resourceID',pId:'parentID',name:'resourceName',open:'isOpen',checked:'isCheck'},";
		for (RolePermission rolePermissionTmp : rolePermissionList) {
			//if(rolePermissionTmp.getParentID().equals("0")) {
			//	moduleNameBuffer.append(rolePermissionTmp.getResourceID()).append(",");
			//}
			ztree.append(nodeTemp.replace("resourceID", rolePermissionTmp.getResourceID())
					.replace("parentID", rolePermissionTmp.getParentID())
					.replace("resourceName", rolePermissionTmp.getResourceName())
					.replace("isOpen", rolePermissionTmp.getIsOpen())
					.replace("isCheck", rolePermissionTmp.getIsCheck()));
		}
		if (ztree.length() > 1) {
			ztree.deleteCharAt(ztree.length() - 1);
		}
		ztree.append("]");
		return ztree.toString();
	}
	
	@Override
	public void insertRolePermission(RolePermission rolePermission){
		roleDAO.insertRolePermission(rolePermission);
	}
	
	@Override
	public void deleteRolePermission(RolePermission rolePermission){
		roleDAO.deleteRolePermission(rolePermission);
	}
}
