package com.gdssoft.core.security.interceptor;

import java.beans.PropertyEditorSupport;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.DelegatingMethodSecurityMetadataSource;

import com.gdssoft.core.security.service.SecurityManagerService;

public class SecureMethodFilterInvocationDefinitionSource implements InitializingBean {
	
	@Resource(name="securityManager")
	private SecurityManagerService securityManagerService;
	
	private DelegatingMethodSecurityMetadataSource delegatingMethodDefinitionSource;
		
	public DelegatingMethodSecurityMetadataSource getDelegatingMethodDefinitionSource() {
		return delegatingMethodDefinitionSource;
	}

	public void setDelegatingMethodDefinitionSource(
			DelegatingMethodSecurityMetadataSource delegatingMethodDefinitionSource) {
		this.delegatingMethodDefinitionSource = delegatingMethodDefinitionSource;
	}


	
	@SuppressWarnings("unused")
	private Map<String, ConfigAttribute> methodMap(){
		LinkedHashMap<String, ConfigAttribute> map=new LinkedHashMap<String, ConfigAttribute>();
		PropertyEditorSupport editor=new PropertyEditorSupport();
		Map<String, String> resourceMap=(Map<String, String>)securityManagerService.loadMethodAuthorities();		
      
        
		for(Map.Entry<String, String> entry:resourceMap.entrySet()){
			editor.setAsText(entry.getValue());
			map.put(entry.getKey(), (ConfigAttribute)editor.getValue());
		}
		
		return map;		
		
	}
	
	public void afterPropertiesSet()throws Exception{
//		if(delegatingMethodDefinitionSource!=null){
//			MapBasedMethodSecurityMetadataSource source=new MapBasedMethodSecurityMetadataSource(methodMap());
//			List<MethodDefinitionSource> sources=new ArrayList<MethodDefinitionSource>();
//			sources.add(source);
//			delegatingMethodDefinitionSource.setMethodDefinitionSources(sources);
//		}
	}

}
