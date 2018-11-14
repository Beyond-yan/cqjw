/**
 * 
 */
package com.gdssoft.cqjt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.UserRoleDAO;
import com.gdssoft.cqjt.pojo.UserRole;
import com.gdssoft.cqjt.service.UserRoleService;

/**
 * @author F3219058
 * 
 */
@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {

	@Resource(name = "userRoleDAO")
	private UserRoleDAO UserRoleDao;

	@Override
	public void insertUserRole(UserRole ur) {
		UserRoleDao.insertUserRole(ur);
	}

	@Override
	public void updateUserRole(UserRole ur) {
		UserRoleDao.updateUserRole(ur);
	}
	
	@Override
	public void updateUserRoleInfo(UserRole ur) {
		UserRoleDao.updateUserRoleInfo(ur);
	}

}
