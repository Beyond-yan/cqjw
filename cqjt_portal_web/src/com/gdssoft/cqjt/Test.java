/**
 * 
 */
package com.gdssoft.cqjt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author acer
 *
 */
public class Test implements Cloneable{

	public static void main(String[] args) {
//		String s = ",123213,";
//		String[] st = s.split(",");
//		
//		System.out.println("--->"+st.length+","+st[0]+","+st[1]);
//		
//		copyFile("D:\\temp\\2003.doc", "D:\\upload\\test\\tes\\2003_1.doc");
		
		String s = "<p>test</p><p style=\"line-height: 16px;\"><img src=\"/cqjt-portal-web/resource/textnews/fileTypeImages/icon_txt.gif\"/><a href=\"/cqjt-portal-web/resource/textnews/20160825/71251472118492720.txt\">新建文本文档.txt</a></p><p style=\"line-height: 16px;\"><img src=\"/cqjt-portal-web/resource/textnews/fileTypeImages/icon_txt.gif\"/><a href=\"/cqjt-portal-web/resource/textnews/20160825/71251472118492720.txt\">新建文本文档.txt</a></p><p><br/></p>";
		String bireinfo = "<IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103010830.jpg 300px; HEIGHT: 201px></SPAN></FONT><SPAN style=FONT-FAMILY:  New FONT-SIZE: 10.5pt Roman?,?serif?; ?Times><IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103058264.jpg 300px; HEIGHT: 201px></SPAN><SPAN style=FONT-FAMILY:  New FONT-SIZE: 10.5pt Roman?,?serif?; ?Times><IMG border=0 align=center src=/epbwebeditor/uploadfile/20140509103323797.jpg 300px; HEIGHT: 201px></SPAN></P>";
//		Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
		Pattern img = Pattern.compile("(?:src=\"?)(.*?)\"");
		Pattern href = Pattern.compile("(?:href=\"?)(.*?)\"");
		Matcher m = img.matcher(s);
		String[] arr = new String[10];
		int i = 0;
		while(m.find()) {
			arr[i] = m.group(1);
			System.out.println("--------->"+arr[i]);
			i++;
		}
		Matcher m1 = href.matcher(s);
		while(m1.find()) {
			arr[i] = m1.group(1);
			System.out.println("--------->"+arr[i]);
			i++;
		}
	}
	
	public static void copyFile(String oldFilePath, String newFilePath){
		System.out.println("oldFilePath:"+oldFilePath+",newFilePath:"+newFilePath);
		FileInputStream input= null;
		FileOutputStream output = null;
		try{
			File inputFile = new File(oldFilePath);
			 //判断目标文件所在的目录是否存在  
	        if(!inputFile.getParentFile().exists()) { 
	        	inputFile.getParentFile().mkdirs();
	        }
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
	
	/** 
	 * 复制一个目录及其子目录、文件到另外一个目录 
	 * @param src 
	 * @param dest 
	 * @throws IOException 
	 */  
	private void copyFolder(File src, File dest) throws IOException {  
	    if (src.isDirectory()) {  
	        if (!dest.exists()) {  
	            dest.mkdir();  
	        }  
	        String files[] = src.list();  
	        for (String file : files) {  
	            File srcFile = new File(src, file);  
	            File destFile = new File(dest, file);  
	            // 递归复制  
	            copyFolder(srcFile, destFile);  
	        }  
	    } else {  
	        InputStream in = new FileInputStream(src);  
	        OutputStream out = new FileOutputStream(dest);  
	  
	        byte[] buffer = new byte[1024];  
	  
	        int length;  
	          
	        while ((length = in.read(buffer)) > 0) {  
	            out.write(buffer, 0, length);  
	        }  
	        in.close();  
	        out.close();  
	    }  
	}  

}
