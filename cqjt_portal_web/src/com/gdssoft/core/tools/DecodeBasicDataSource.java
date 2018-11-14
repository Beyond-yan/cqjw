package com.gdssoft.core.tools;

/**
 * 系统默认对数据库链接字符串等数据进行加密以防止帐密泄漏，<code>DecodeBasicDataSource</code> 扩展
 * <code>BasicDataSource</code>实现解密这些关键数据
 * 
 * @author Jimxu
 * 
 */
public class DecodeBasicDataSource extends
		org.apache.commons.dbcp.BasicDataSource {

	@Override
	public synchronized void setPassword(String password) {

		DESUtil desUtil = new DESUtil("gds+88");
		String decodePassword = desUtil.decrypt(password);
		super.setPassword(decodePassword);
	}

	public static void main(String[] args) {
		DESUtil desUtil = new DESUtil("gds+88");
		String miwen = desUtil.encrypt("Wangshan123");
		String mingwen = desUtil.decrypt(miwen);
		
		System.out.println(mingwen);
		System.out.println(miwen);
	}
}
