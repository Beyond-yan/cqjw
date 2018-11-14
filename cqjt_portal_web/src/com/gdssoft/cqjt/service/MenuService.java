/**
 * 
 */
package com.gdssoft.cqjt.service;

import java.util.List;

import com.gdssoft.cqjt.pojo.Menu;

/**
 * @author F3219058
 * 
 */
public interface MenuService {
	public Menu getMenu(String menuId);
	
	public List<Menu> getMenus(String roleID,String parentID);
	
	public List<Menu> getTopMenus(String roleID,String parentID);
}
