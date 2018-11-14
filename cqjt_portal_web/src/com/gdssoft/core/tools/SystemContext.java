package com.gdssoft.core.tools;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.gdssoft.cqjt.pojo.UserDetail;

@Component("systemContext")
public class SystemContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	// 用戶ID
	private static String userId; //修改userID 为String类型 2014.05.29
	// 系統日期
	private static Date systemDate;
	// 人员ID
	// private static String personnelId;

	// added by by Cube @130724
	private static String userNO;

	private static String userName;
	// added by Cube @130802
	private static String password;

	private static String roleID;
	private static String roleName;
	private static String roleDesc;
	
	public static Boolean isAuthenticated() {
		boolean b = false;
		try {
			b = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
			if (b) {
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				b = !"anonymousUser".equals(principal.toString());
			}
		} catch(Exception e) {
			
		}
		return b;
	}
	
	public static String getPassword() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		password = userDetail.getPassword();
		return password;
	}

	public static String getUserNO() {
		try{
			UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			userNO = userDetail.getUserNo();
		} catch(Exception e){
			e.printStackTrace();
		}
		return userNO;
	}
	
	public static String getUserName(){
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		userName = userDetail.getUserName();
		return userName;
	}  

	public static String getRoleID() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleID = userDetail.getRoleID();
		return roleID;
	}

	public static String getRoleName() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleName = userDetail.getRoleName();
		return roleName;
	}

	public static String getRoleDesc() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		roleDesc = userDetail.getRoleDesc();
		return roleDesc;
	}
	//修改userID 为String类型 2014.05.29
	public static String getUserId() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		userId = userDetail.getUserId();
		return userId;
	}

	public static Collection<GrantedAuthority> getAuthorities() {
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userDetail.getAuthorities();
	}

	public static UserDetail getUserDetail() {
		return (UserDetail) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
	}
	//修改userID 为String类型 2014.05.29
	public static void setUserId(String userId) {
		SystemContext.userId = userId;
	}

	public static Date getSystemDate() {
		systemDate = new Date();
		return systemDate;
	}

	public static void setSystemDate(Date systemDate) {
		SystemContext.systemDate = systemDate;
	}

	/*
	 * public static void setPersonnelId(String personnelId) {
	 * SystemContext.personnelId = personnelId; }
	 */

	/**
	 * 装填ApplicationContext
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}
	/**
	 * 获取Spring容器中的Bean
	 */
	public static Object getBean(String id) {
		Object object = null;
		object = applicationContext.getBean(id);
		return object;
	}
	
	/**
	 * 以下内容是自动装填 webapp.properties中的配置
	 */
	@Value("${default.page.size}")
	private int defaultPageSize;
	public static int getDefaultPageSize() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.defaultPageSize;
	}
	
	@Value("${sso.enabled}")
	private boolean enableSSO;
	public static boolean getEnableSSO() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.enableSSO;
	}
	
	@Value("${sso.service.url}")
	private String ssoServiceUrl;
	public static String getSsoServiceUrl() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.ssoServiceUrl;
	}
	
	@Value("${uploader.file.path}")
	private String uploaderFilePath;
	public static String getUploaderFilePath() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.uploaderFilePath;
	}
	
	@Value("${uploader.file.types}")
	private String uploaderFileTypes;
	public static String getUploaderFileTypes() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.uploaderFileTypes;
	}
	
	@Value("${uploader.file.maxsize}")
	private int uploaderFileMaxsize;
	public static int getUploaderFileMaxsize() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.uploaderFileMaxsize;
	}
	
	@Value("${uploader.image.types}")
	private String uploaderImageTypes;
	public static String getUploaderImageTypes() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.uploaderImageTypes;
	}
	
	@Value("${uploader.image.maxsize}")
	private int uploaderImageMaxsize;
	public static int getUploaderImageMaxsize() {
		SystemContext sc = (SystemContext)getBean("systemContext");
		return sc.uploaderImageMaxsize;
	}
}
