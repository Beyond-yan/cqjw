/**
 * 
 */
package com.gdssoft.cqjt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.MenuDAO;
import com.gdssoft.cqjt.pojo.Menu;
import com.gdssoft.cqjt.service.MenuService;

/**
 * @author F3219058
 * 
 */
@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

	@Resource(name = "menuDAO")
	private MenuDAO menuDao;


	@Override
	public Menu getMenu(String menuId) {
		return menuDao.getMenu(menuId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gdssoft.core.service.MenuService#getMenu()
	 */
	@Override
	public List<Menu> getMenus(String roleID, String parentID) {
		return menuDao.getMenu(roleID,parentID);
	}
	
	
	@Override
	public List<Menu> getTopMenus(String roleID,String parentID) {
		return menuDao.getTopMenu(roleID,parentID);
	}
}
