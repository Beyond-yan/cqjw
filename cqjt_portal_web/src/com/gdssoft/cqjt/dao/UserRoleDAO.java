/**
 * 
 */
package com.gdssoft.cqjt.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.UserRole;

/**
 * @author F3219058
 * 
 */
@Repository("userRoleDAO")
public class UserRoleDAO {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	public void insertUserRole(UserRole ur) {
		sqlMapClientTemplate.insert("UserRole.insertUserRole", ur);
		
	}

	public void updateUserRole(UserRole ur) {
		sqlMapClientTemplate.insert("UserRole.updateUserRole", ur);
	}
	
	public void updateUserRoleInfo(UserRole ur) {
		sqlMapClientTemplate.update("UserRole.updateUserRoleInfo", ur);
	}
}
