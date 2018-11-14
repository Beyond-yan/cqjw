package com.gdssoft.core.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class EncryptSha256Util {

	/**
	 * 密码加密
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String encryptSha256(String inputStr) {
		byte digest[];
		MessageDigest md;
		String password = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
			digest = md.digest(inputStr.getBytes("UTF-8"));
			password = new String(Base64.encodeBase64(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Logger.getLogger(EncryptSha256Util.class).debug("=======加密后密码：" + password);
		System.out.println("=======加密后密码：" + password);
		return password;
	}
	
	public static void main(String[] args) {
		EncryptSha256Util.encryptSha256("123");
	}
}
