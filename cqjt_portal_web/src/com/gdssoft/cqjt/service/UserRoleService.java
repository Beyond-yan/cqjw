/**
 * 
 */
package com.gdssoft.cqjt.service;

import com.gdssoft.cqjt.pojo.UserRole;

/**
 * @author F3219058
 * 
 */
public interface UserRoleService {
	public void insertUserRole(UserRole ur);

	public void updateUserRole(UserRole ur);
	//H2902992 20140604
	public void updateUserRoleInfo(UserRole ur);
}
