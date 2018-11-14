/**
 * 
 */
package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Menu;

/**
 * @author F3219058
 *
 */
@Repository("menuDAO")
public class MenuDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;

	public Menu getMenu(String menuId){
		return (Menu)sqlMapClientTemplate.queryForObject("Menu.getMenuById", menuId);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenu(String roleID, String parentID){
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleID", roleID);
		map.put("parentID",parentID);
		return (List<Menu>) sqlMapClientTemplate.queryForList("Menu.getMenus",map);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Menu> getTopMenu(String roleID,String parentID){
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleID", roleID);
		map.put("parentID",parentID);
		return (List<Menu>) sqlMapClientTemplate.queryForList("Menu.getTopMenus",map);
	}
}
