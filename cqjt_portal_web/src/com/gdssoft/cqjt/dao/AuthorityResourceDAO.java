package com.gdssoft.cqjt.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.AuthorityResource;
import com.gdssoft.cqjt.pojo.Menu;

@Repository("authorityResourceDAO")
public class AuthorityResourceDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	@SuppressWarnings({ "unchecked" })
	public List<AuthorityResource> getAVResourceByType(String resourceType) {
		return (List<AuthorityResource>) sqlMapClientTemplate
				.queryForList("AuthorityResource.getAVResourceByType", resourceType);
	}
	
	public boolean checkAuthority(String roleID,String url) {

		Map<String,String> map=new HashMap<String,String>();
		url = url.substring(url.length()-9, url.length());
		map.put("roleID", roleID);
		map.put("url", url);
		Menu menu = (Menu)sqlMapClientTemplate.queryForObject("AuthorityResource.checkAuthority", map);
		if (menu != null)
			return true;
		else
			return false;
	}
}
