package com.gdssoft.cqjt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.service.CheckStandardService;

public class PortalUtils {
	
	private static Log logger = LogFactory.getLog(PortalUtils.class);
	
	public static final String encrypt(String s){
		String result = "";
		if(StringUtils.isNotBlank(s)){
			BigInteger biginteger = new BigInteger(s.getBytes());
		    BigInteger biginteger1 = new BigInteger("0933910847463829232312312");
		    BigInteger biginteger2 = biginteger1.xor(biginteger);
		    result = biginteger2.toString(16);
		}
	    return result;
	}

	public static final String decrypt(String s){
		if(s == null)
			return "";
	    if(s.length() == 0)
	        return "";
	    BigInteger biginteger = new BigInteger("0933910847463829232312312");
	    try {
            BigInteger biginteger1 = new BigInteger(s, 16);
            BigInteger biginteger2 = biginteger1.xor(biginteger);
            return new String(biginteger2.toByteArray());
	    } catch(Exception exception) {
	            return "";
	    }
	}
	
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
		System.out.println("=======加密后密码：" + password);
		return password;
	}
	
	public static String encodeMD5SHA256(String s){
		StringBuffer password = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte digest[] = md.digest(s.getBytes("UTF-8"));
		      for (byte b : digest) {
		    	  password.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
		      }
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("=======加密后密码：" + password.toString());
		return password.toString();
	}
	
	public static String httpGet(String url) {
		StringBuilder stringBulider = new StringBuilder();
		try {
			// 創建一個鏈接遠端服務的客戶端
			HttpURLConnection httpurlconnection = null;
			URL my_url = new URL(url);
			httpurlconnection = (HttpURLConnection) my_url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("GET");
			httpurlconnection.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
			// 接受結果
			BufferedReader br = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream(), "UTF-8"));
			String linerep = null;
			while ((linerep = br.readLine()) != null) {
				stringBulider.append(linerep);
			}
			br.close();
			br = null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("httpGet",e);
		}
		return stringBulider.toString();
	}
	
	public static void copyFile(String oldFilePath, String newFilePath){
		System.out.println("oldFilePath:"+oldFilePath+",newFilePath:"+newFilePath);
		FileInputStream input= null;
		FileOutputStream output = null;
		try{
			File inputFile = new File(oldFilePath);
			if(inputFile.exists()){
				input=new FileInputStream(inputFile);//可替换为任何路径何和文件名
				newFilePath = newFilePath.replaceAll("\\\\", "/");
				String dirUrl = newFilePath.substring(0,newFilePath.lastIndexOf("/"));
				File dirfile = new File(dirUrl);
				if(!dirfile.exists()){
					dirfile.mkdirs();
				}
				output=new FileOutputStream(newFilePath);//可替换为任何路径何和文件名
				int in=input.read();
				while(in!=-1){
					output.write(in);
					in=input.read();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
			if(input != null){
				try {
					input.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(output != null){
				try {
					output.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		String username = encrypt("portal");
		System.out.println("portal:"+username);
		String sybmol = encrypt("inspur");
		System.out.println("sybmol:"+sybmol);
		String token = encrypt("cqsjwghjhcd"+"inspur");
		System.out.println("token:"+token);
		String target = encrypt("jw");
		System.out.println("target:"+target);
		String userName = encrypt("刘幸");
		System.out.println("userName:"+userName);
//		String returnStr = httpGet("http://23.99.127.114:8081/web/chq/approval/getUserBusinessInfo?username=63b4b002e5f7f65d923c9c&sybmol=c5c36892f9f0448a2a8a&token=6371736a776768afab0bf6f9f0448a2a8a");
//		String returnStr = httpGet("http://23.99.127.114:8081/web/chq/approval/getUserBusinessInfo?username=c5c368f1e1ed5d8d3380&sybmol=c5c36892f9f0448a2a8a&token=637173afb404eaf9f0448a2a8a");
//		System.out.println("returnStr:"+returnStr);
		System.out.println(decrypt("+7khDVyQAPc="));
//		String s = encodeMD5SHA256("yunzheng");
//		String st = Base64.decodeBase64(s.getBytes()).toString();
//		System.out.println(st);
				
	}
	
	
	/**
	 * 计算采用类别
	 * @param adoptType 
	 * @param checkStandards
	 * @return
	 */
	public static  String computeAdoptType(String adoptType, List<CheckStandard> checkStandards) {
		
		String codes = "";
		Map<String, String> itemNameMap = new HashMap<>();
		
		for (int n = 0; n < checkStandards.size(); n++) {
			itemNameMap.put(checkStandards.get(n).getCode(), checkStandards.get(n).getItemName());
		}
		
		if (adoptType != null) {
			String[] adoptTypes = adoptType.split(",");
			for (int k = 0; k < adoptTypes.length; k++) {
				if (adoptTypes[k] != null && !"".equals(adoptTypes[k]) ) {
					if (adoptTypes[k].equals("dailyInfo")) {// 每日信息
						codes += itemNameMap.get("pubtypedynamic") + ",";// 刊物工作动态
					} else if (adoptTypes[k].equals("subjectInfo")) {// 专报信息
						codes += itemNameMap.get("jwdbsubjectInfo") + ",";// 交委独编专报信息
					} else if (adoptTypes[k].equals("cityCommittee")) {// 市委采用
						codes += itemNameMap.get("swdayinfo") + ",";// 市委每日要情
					} else if (adoptTypes[k].equals("cityGovernment")) {// 市政府采用
						codes += itemNameMap.get("workdynamic") + ",";// 市府工作动态
					} else if (adoptTypes[k].equals("trafficDept")) {// 交通部采用
						codes += itemNameMap.get("trafsitcomm") + ",";//交通部简报情况交流
					} else if (adoptTypes[k].equals("dayinfon")) {// 交委每日信息
						codes += ""; //  不要
					} else {
						codes += itemNameMap.get(adoptTypes[k]) + ",";
					}
				}
			}
		}
		return codes;
	}
	
}
