package com.gdssoft.core.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传实用类.
 * 
 *
 */
public class FileUploader {
	protected transient final Log logger = LogFactory.getLog(getClass());
	private HttpServletRequest request;
	private String realpath; // 系统运行的根路径信息
	private String storepath; // 文件存储目录，相对系统目录而言
	private int sizeThreshold = 4096; // 允许存放在内存中的最大字节数
	private long sizeMax = -1; // 允许用户上传的最大字节数 -1为没有限制
	private boolean isError; // 是否发生了错误
	private FileItem fileItem; // 存储文件域的FileItem
	private String[][] attributes; // 非文件域的参数存储列表
	private ServletFileUpload uploader;
	private String encoding = "UTF-8"; // 参数的编码方式
	private String originalName; // 文件的原始文件名称
	private String newName; // 文件的新名称（如果没有指定则自动生成）
	private boolean covered; // 是否覆盖已经存在的同名文件
	
	private String type;          //附件類型
	private String version;       //版本
	private String staName;       //標準件名稱
	private String facName;       //廠區名稱

	/**
	 * 构造方法
	 */
	public FileUploader(HttpServletRequest request, String storepath) {
		this(request, storepath, -1, 4096);
	}

	public FileUploader(HttpServletRequest request, String storepath,
			long sizeMax) {
		this(request, storepath, sizeMax, 4096);
	}

	public FileUploader(HttpServletRequest request, String storepath,
			long sizeMax, int sizeThreshold) {
		this.request = request;
		this.realpath = standardPath("D:/FoxTool/Files".replaceAll("\\\\", "/"), false);
		
		this.storepath = standardPath(storepath.replaceAll("\\\\", "/"), true);
		logger.debug("Session------------------" + request.getSession());
       logger.debug("Session------------------" + request.getSession().getAttribute("type"));
       logger.debug("Request------------------" + request.getAttribute("type"));
		logger.debug("-----------------------" + this.storepath);
		logger.debug("-----------------------" + this.realpath);
		this.sizeMax = sizeMax;
		this.sizeThreshold = sizeThreshold;
		init();
	}
	
	public String getFilePath() {
		return this.storepath + "/" + this.newName;
	}

	public String getOriginalName() {
		return this.originalName;
	}

	public String getParameter(String paraName) {
		return this.getParameter(paraName, encoding);
	}

	public String getParameter(String paraName, String encoding) {
		if (paraName == null || paraName.equals("")) return null;
		if (this.attributes == null) this.parseRequest();
		// 遍历所有非文件域参数并取其值
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i][0].equalsIgnoreCase(paraName)) {
				return attributes[i][1];
			}
		}
		return null;
	}

	public void setEncoding(String encoding) {
		if (trim(encoding).intern() != "") this.encoding = encoding;
	}

	public String upload() {
		if (this.isError) return "";
		if (this.attributes == null) this.parseRequest();
		if (this.fileItem == null) return "";
		
		// 进行文件上传
		try {
			// 文件的原始名称
			this.originalName = trim(URLDecoder.decode(this.fileItem.getName(), "UTF-8"));
			//取得applet傳來的參數
			String tempath = trim(URLDecoder.decode(fileItem.getFieldName(), "UTF-8"));
			this.setParams(tempath);
			
			
			if (this.newName == null) this.newName = this.createRandomFile();						
			File f = new File(this.realpath + "/" + this.storepath + "/" + this.type + "/" + this.newName);
			
			/*
			String p = "";		
			p =  URLDecoder.decode(this.fileItem.getName(), "UTF-8");
			logger.debug("WEN_URLENCODER:===========" + p);
			*/
			
			
			// 如果没有指定覆盖同名文件则抛出异常
			if (!this.covered && f.exists()) throw new RuntimeException("文件" + f.getAbsolutePath() + "已存在！");
			this.fileItem.write(f);
			return f.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}
	
	public void setParams(String params) {
		logger.debug("-------------Params:" + params);
		int t;
		t = params.indexOf("?");
		this.type = params.substring(0, t);
		params = params.substring(t+1, params.length());
		
		t = params.indexOf("?");
		this.staName = params.substring(0, t);
		params = params.substring(t+1, params.length());
		
		t = params.indexOf("?");
		this.facName = params.substring(0, t);
		this.version = params.substring(t+1, params.length());
		logger.debug("-------------Type:" + this.type);
		logger.debug("-------------StandardName:" + this.staName);
		logger.debug("-------------FactoryName:" + this.facName);
		logger.debug("-------------Version:" + this.version);
		if(this.facName.equals("\"\"")) {
			this.storepath = this.storepath + this.staName;
		} else {
			this.storepath = this.storepath + this.staName + "_" + this.facName;
		}
	}

	public void setFileName(String fileName, boolean covered) {
		if (trim(fileName).intern() != "") this.newName = fileName;
		this.covered = covered;
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		DiskFileItemFactory dfileFac = new DiskFileItemFactory();
		dfileFac.setRepository(new File(realpath + storepath + "/temp"));
		dfileFac.setSizeThreshold(sizeThreshold);

		uploader = new ServletFileUpload(dfileFac);
		uploader.setSizeMax(sizeMax);
		uploader.setHeaderEncoding("utf-8");

		// 检查指定的文件夹是否存在，不存在则创建
		try {
			File myfile = new File(realpath + storepath);
			if (!myfile.isDirectory()) myfile.mkdirs();
			myfile = new File(realpath + storepath + "/temp");
			if (!myfile.isDirectory()) myfile.mkdirs();
		} catch (SecurityException ex) {
			ex.printStackTrace();
			isError = true;
		}
	}

	/**
	 * 规范路径，路径必须最前加/最后不要/
	 */
	private String standardPath(String path, boolean stIndex) {
		if (path == null) return null;
		if (stIndex && !path.startsWith("/")) return "/" + path;
		if (path.endsWith("/")) return path.substring(0, path.length());
		return path;
	}

	/**
	 * 解析HttpServletRequest请求。
	 * 
	 */
	private void parseRequest() {
		List<FileItem> paraList = new ArrayList<FileItem>();
		try {	
			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			@SuppressWarnings("rawtypes")
			List fileItems = uploader.parseRequest(request);
			@SuppressWarnings("rawtypes")
			Iterator it = fileItems.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				// 存储所有文件域的参数
				if (!item.isFormField()) {
					if (!"".equals(item.getName()) && item.getSize() > 0) {
						fileItem = item;
					}
				}
				// 存储所有非文件域的参数
				else paraList.add(item); 
			}
			// 解析非文件域参数
			attributes = new String[paraList.size()][2];			
			int icount = 0;
			it = paraList.iterator();
			while (it.hasNext()) {
				FileItem fileItem = (FileItem) it.next();
				attributes[icount][0] = fileItem.getFieldName();				
				try {
					attributes[icount][1] = fileItem.getString(encoding);					
				} catch (UnsupportedEncodingException ex) {
					attributes[icount][1] = fileItem.getString();
					ex.printStackTrace();
				}
				++icount;
			}
		} catch (FileUploadException ex) {
			ex.printStackTrace();
			isError = true;
		}
	}
	
	/**
	 * 生成随机文件名
	 */
	private String createRandomFile() throws IOException {
		this.covered = true;
		// 获取原始文件的后缀
		int index = this.originalName.lastIndexOf('.');
		if (index == this.originalName.length() - 1) index = -1;
		String suffix = index == -1 ? "" : ("." + this.originalName.substring(index + 1));
		
		// 检查指定的文件夹是否存在，不存在则创建
		try {
			File myfile = new File(realpath + storepath + "/" + this.type);
			if (!myfile.isDirectory()) myfile.mkdirs();			
		} catch (SecurityException ex) {
			ex.printStackTrace();
			isError = true;
		}
		
		if(index != -1) {
			this.originalName = this.originalName.substring(0 ,index);
		}
		
		while (true) {
			String fileName = System.currentTimeMillis() +
				"" + new Double(899999 * Math.random() + 100000).intValue();
			File file = null;
			if(this.facName.equals("\"\"")) {
				file = new File(this.realpath + "/" + this.storepath + "/" + this.type + "/" + this.originalName + "_" + fileName + suffix);
				logger.debug("---------------FilePath:" + this.realpath + "/" + this.storepath + "/" + this.type + "/" + this.originalName + "_" + fileName + suffix);
			} else {
				file = new File(this.realpath + "/" + this.storepath + "/" + this.type + "/" + this.originalName + "_" + fileName + "_" + this.version + suffix);
			}	
			if (file.exists() && file.isFile()) continue;
			if (!file.createNewFile()) continue;
			if(this.facName.equals("\"\"")) {
				return this.originalName+ "_" + fileName + suffix;
			} else {
				return this.originalName+ "_" + fileName + "_" + this.version + suffix;
			}
		}
	}

	public long getFileSize() {
		return this.isError ? 0 : (this.fileItem == null ? 0 : this.fileItem.getSize());
	}

	private String trim(String str) {
		return str == null ? "" : str.trim();
	}
	public static String uploadFile(HttpServletRequest request,MultipartFile file,String path){
		 
			/*	String projectPath=request.getSession().getServletContext().getRealPath("/"); 
				String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
				String path = diskSign+"/CMS/upload/file/";*/
		  
		  

		    	File myfile = new File(path);
				if (!myfile.isDirectory()) 
					{
					myfile.mkdirs();	
					
					} 
			int i;
				i = file.getOriginalFilename().lastIndexOf(".");
				
				//String fileName = file.getOriginalFilename().substring(0, i);
				String newFileName = System.currentTimeMillis() +
				"" + new Double(899999 * Math.random() + 100000).intValue()+file.getOriginalFilename().substring(i);	
				File newfile=new File(path+newFileName);
				/*String fileURL=path+fileName+file.getOriginalFilename().substring(i);
				File newfile=new File(fileURL);*/
				//String relativePath=path.substring(path.indexOf("resource")+9);

				//modified by Cube @130816
				String projectPath = request.getSession().getServletContext()
						.getRealPath("/");
				String relativePath=path.replace(projectPath, "");
				
		    	try {
					InputStream in = file.getInputStream();
					OutputStream out = new FileOutputStream(newfile);
					byte []bytes = new byte[2048];
					int len = 0;
					while((len=in.read(bytes))!=-1){
						out.write(bytes, 0, len);
					}
					out.flush();
					in.close();
					out.close();
					/*return path+newFileName;*/
					return relativePath+newFileName;
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return "error";
				  
				}
				
			}
	public static String uploadEmpFile(HttpServletRequest request,MultipartFile file,String path){
		 
		/*	String projectPath=request.getSession().getServletContext().getRealPath("/"); 
			String diskSign=projectPath.substring(0,projectPath.indexOf(".")-1) ;
			String path = diskSign+"/CMS/upload/file/";*/
	  BufferedImage bi;
	  int ew = 0;
	  int eh = 0;
	  
		try {
			bi = ImageIO.read(file.getInputStream());
			ew = bi.getWidth();
			eh = bi.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  if(ew<90||ew>110){
		  return "1";
	  }
	  if(eh<130||eh>150){
		  return "2";
	  }

	    	File myfile = new File(path);
			if (!myfile.isDirectory()) 
				{
				myfile.mkdirs();	
				
				} 
		int i;
			i = file.getOriginalFilename().lastIndexOf(".");
			
			//String fileName = file.getOriginalFilename().substring(0, i);
			String newFileName = System.currentTimeMillis() +
			"" + new Double(899999 * Math.random() + 100000).intValue()+file.getOriginalFilename().substring(i);	
			File newfile=new File(path+newFileName);
			/*String fileURL=path+fileName+file.getOriginalFilename().substring(i);
			File newfile=new File(fileURL);*/
			//String relativePath=path.substring(path.indexOf("resource")+9);

			//modified by Cube @130816
			String projectPath = request.getSession().getServletContext()
					.getRealPath("/");
			String relativePath=path.replace(projectPath, "");
			
	    	try {
				InputStream in = file.getInputStream();
				OutputStream out = new FileOutputStream(newfile);
				byte []bytes = new byte[2048];
				int len = 0;
				while((len=in.read(bytes))!=-1){
					out.write(bytes, 0, len);
				}
				out.flush();
				in.close();
				out.close();
				/*return path+newFileName;*/
				return relativePath+newFileName;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "error";
			  
			}
			
		}
	//返回以当前年月为名的字符串
	public static String getTimeFolder(){
		int year = 0;
		int month = 0;
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
		return month>10?year+""+month:year+"0"+month;
	}
	
}
