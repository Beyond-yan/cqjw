package com.gdssoft.core.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gdssoft.core.security.SecurityManager;
import com.gdssoft.cqjt.dao.AuthorityResourceDAO;
import com.gdssoft.cqjt.pojo.AuthorityResource;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.service.UserDetailService;

@Service("securityManager")
public class SecurityManagerService implements UserDetailsService,
		SecurityManager {

	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;	
	
	@Resource(name = "authorityResourceDAO")
	private AuthorityResourceDAO resourceDAO;
	
	protected transient final Log logger = LogFactory.getLog(getClass());

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		UserDetail user  = null;
		try {
			user = userService.getUserByCode(username);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		UserDetail user = userService.getUserByCode(username);
		System.out.println(user);
		if (null == user) {
			throw new UsernameNotFoundException("用戶 " + username + "没有权限");
		}
		return user;
	}
	
	public Map<String, String> loadUrlAuthorities() {		
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		List<AuthorityResource> urlResources = resourceDAO.getAVResourceByType("URL");
		for (AuthorityResource resource : urlResources) {
			urlAuthorities.put(resource.getResourceValue(),
					resource.getRoleAuthorities());
		}
		return urlAuthorities;
	}

	public Map<String, String> loadMethodAuthorities() {		
		Map<String, String> methodAuthorities = new HashMap<String, String>();
		List<AuthorityResource> methodResources = resourceDAO.getAVResourceByType("METHOD");
		for (AuthorityResource resource : methodResources) {
			methodAuthorities.put(resource.getResourceValue(),
					resource.getRoleAuthorities());
		}

		return methodAuthorities;
	}
	
	public boolean checkAuthority(String roleID,String url) {
		return resourceDAO.checkAuthority(roleID, url);
	}
}
