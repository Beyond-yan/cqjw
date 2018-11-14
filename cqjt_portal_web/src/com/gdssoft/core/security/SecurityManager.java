package com.gdssoft.core.security;

import java.util.Map;

public interface SecurityManager {
	
	public Map<String,String> loadUrlAuthorities();	
	
	public Map<String, String> loadMethodAuthorities();
	
	public boolean checkAuthority(String roleID,String url);
}
