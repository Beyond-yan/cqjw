package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.FileUploader;
import com.gdssoft.core.tools.StringUtils;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.service.FileDownloadService;
/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */
@Controller
@RequestMapping("/console/download.xhtml")
public class FileDownloadController extends BaseController {
	@Autowired
	@Resource(name = "fileDownloadService")
	private FileDownloadService fileDownloadService;
	/**
	 * 页面首次加载列表数据
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getFileList")
	public String getFileList(Model model,HttpServletRequest request){
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		int pageIndex = 0;
		if (null!=request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch(Exception e) { }
		}
		List<FileDownload> fileList = fileDownloadService.getAllFile("",start,end, "0",pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", fileList);
		return "console/sysMgt/download/listDownload";
	}
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addFile")
	public String addFile(Model model) {
		String createBy = SystemContext.getUserName();
		String createDate = DateUtil.getCurrentDateStr();
		logger.debug("创建人"+createBy+"时间"+createDate);
		model.addAttribute("createBy", createBy);
		model.addAttribute("createDate",createDate);
		return "console/sysMgt//download/addFile";
	}
	
	/**
	 * 用于查询
	 * @param fileName
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchFileList")
	public String searchFileList(@RequestParam("fileName") String fileName,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		
					if (startDate.equals("")||endDate.equals("")){
						     if (fileName.equals(""))
					         { List<FileDownload> fileList = fileDownloadService.getAllFile("", null, null, "0",pageIndex, SystemContext.getDefaultPageSize());
					           model.addAttribute("paginations", fileList);
						      logger.debug("开始时间"+startDate+"结束时间"+endDate);
					          }
						     else {
						    	   fileName=URLDecoder.decode(fileName,"utf-8");
								    List<FileDownload> fileList = fileDownloadService.getAllFile(fileName, null, null, "0",pageIndex, SystemContext.getDefaultPageSize());
									model.addAttribute("fileName", fileName);
									model.addAttribute("paginations", fileList);
									logger.debug("开始时间"+startDate+"结束时间"+endDate);
						     }
					}
				   else if (startDate.equals("")&&endDate.equals("")){
					     if (fileName.equals(""))
				         { List<FileDownload> fileList = fileDownloadService.getAllFile("", null, null, "0",pageIndex, SystemContext.getDefaultPageSize());
				           model.addAttribute("paginations", fileList);
					      logger.debug("开始时间"+startDate+"结束时间"+endDate);
				          }
				        }
				   else {
							fileName=URLDecoder.decode(fileName,"utf-8");
							Date start = this.getStartDate(startDate);
							Date end = this.getEndDate(endDate);
								List<FileDownload> fileList = fileDownloadService.getAllFile(fileName, start, end, "0",pageIndex, SystemContext.getDefaultPageSize());
							model.addAttribute("fileName", fileName);
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("paginations", fileList);
							logger.debug("开始时间"+start+"结束时间"+end);
					 }
							logger.debug("开始时间"+startDate+"结束时间"+endDate);
		
					
		return "console/sysMgt/download/listDownload";
	}
	
	/**
	 * 逻辑删除资料
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteFile")
	public void deleteFile(@RequestParam("fileId") String fileId,
			HttpServletResponse response){
		String msg = "";
		logger.debug("fileId="+fileId);
		try {
			FileDownload fileDownload = new FileDownload();
			fileDownload.setIsDel("1");
			fileDownload.setFileId(fileId);
			fileDownloadService.updateFile(fileDownload);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		
		this.writeMessage(response, msg);
	}
	@RequestMapping(params = "action=saveFileInfo")
	public void saveFileInfo(MultipartHttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileId") String fileId,
			@RequestParam("fileName") String fileName,
			@RequestParam("isTop") String isTop,
			@RequestParam("attachment") String attachment,
			@RequestParam("fileSize") String fileSize,
			@RequestParam("path") String path,
			@RequestParam("fileUrl") String fileUrl,
			@RequestParam("imageUrl") String imageUrl,
			@RequestParam("fileDesc") String fileDesc){
		MultipartFile file = request.getFile("filePath");
		String serverPath = request.getSession().getServletContext().getRealPath("/") 
				+ "resource/textnews/" + FileUploader.getTimeFolder() + "/";
		
		logger.debug("serverPath：" + serverPath);
		FileDownload fileDownload = new FileDownload();
		fileDownload.setFileName(fileName);
		fileDownload.setIsTop(isTop);
		fileDownload.setFileDesc(fileDesc);
		
		if (null == fileUrl||fileUrl.equals("")){
			fileDownload.setFilePath(imageUrl);
			fileDownload.setAttachment(attachment);
			fileDownload.setFileSize(fileSize);
		}
		else {
			fileDownload.setFilePath(FileUploader.uploadFile(request, file, serverPath));
			fileDownload.setFileSize(String.valueOf(file.getSize()/1024)+"kb");
			fileDownload.setAttachment(file.getOriginalFilename());
		}
		
		logger.debug(fileDownload.getCreateBy()+"="+
				fileDownload.getCreateDate()+"="+
				fileDownload.getFileDesc()+"="+
				fileDownload.getFileId()+"="+
				fileDownload.getFileName());
		if(StringUtils.isEmpty(fileId)){
			fileDownload.setFileId(UUID.randomUUID().toString().replace("-", ""));
			fileDownload.setCreateBy(SystemContext.getUserName());
			fileDownload.setCreateDate(DateUtil.getCurrentDateStr());
			fileDownloadService.save(fileDownload);
		}
		else{
			if(StringUtils.isEmpty(fileDownload.getFilePath())){
				fileDownload.setFilePath(path);
			}
			fileDownload.setFileId(fileId);
			fileDownloadService.updateFile(fileDownload);
		}
		this.writeMessage(response, "保存成功！");
	}
	/**
	 * 修改加载页面
	 * @param fileId
	 * @return
	 */
	@RequestMapping(params = "face=editFile")
	public String editFile(Model model,@RequestParam("fileId") String fileId){
		logger.debug("fileId="+fileId);
		model.addAttribute("file",fileDownloadService.getFileByFileId(fileId));
		logger.debug("资料名称："+fileDownloadService.getFileByFileId(fileId).getFileName());
		return"console/sysMgt//download/editDownload";
	}
	
}



