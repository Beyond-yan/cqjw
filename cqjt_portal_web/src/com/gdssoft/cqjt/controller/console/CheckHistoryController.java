package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.gdssoft.cqjt.pojo.CheckHistory;
import com.gdssoft.cqjt.service.CheckHistoryService;

/**
 * 考核历史信息
 * @author  gyf 20150116
 * @return
 */
@Controller
@RequestMapping("/console/checkHistory.xhtml")
public class CheckHistoryController extends BaseController {
	@Autowired
	@Resource(name = "checkHistoryService")
	private CheckHistoryService checkHistoryService;
	/**
	 * 页面首次加载列表数据
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getCheckHistoryList")
	public String getCheckHistoryList(Model model,HttpServletRequest request){
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		int pageIndex = 0;
		if (null!=request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch(Exception e) { }
		}
		List<CheckHistory> checkHistoryList = checkHistoryService.getAllFile("",start,end, "0",pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", checkHistoryList);
		return "console/sysMgt/checkHistory/listCheckHistory";
	}
	
	/**
	 * 用于查询
	 * @param checkName
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchCheckHistoryList")
	public String searchCheckHistoryList(@RequestParam("checkName") String checkName,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		logger.debug("开始时间" + startDate + "结束时间" + endDate);
		
		List<CheckHistory> fileList = new ArrayList<CheckHistory>();
		if (startDate.equals("") || endDate.equals("")) {
			if (checkName.equals("")) {
				fileList = checkHistoryService.getAllFile(
						"", null, null, "0", pageIndex,
						SystemContext.getDefaultPageSize());
				logger.debug("开始时间" + startDate + "结束时间" + endDate);
			} else {
				checkName = URLDecoder.decode(checkName, "utf-8");
				fileList = checkHistoryService.getAllFile(
						checkName, null, null, "0", pageIndex,
						SystemContext.getDefaultPageSize());
				model.addAttribute("checkName", checkName);
				logger.debug("开始时间" + startDate + "结束时间" + endDate);
			}
		} else if (startDate.equals("") && endDate.equals("")) {
			if (checkName.equals("")) {
				fileList = checkHistoryService.getAllFile(
						"", null, null, "0", pageIndex,
						SystemContext.getDefaultPageSize());
				logger.debug("开始时间" + startDate + "结束时间" + endDate);
			}
		} else {
			checkName = URLDecoder.decode(checkName, "utf-8");
			Date start = this.getStartDate(startDate);
			Date end = this.getEndDate(endDate);
			fileList = checkHistoryService.getAllFile(
					checkName, start, end, "0", pageIndex,
					SystemContext.getDefaultPageSize());
			model.addAttribute("checkName", checkName);
			model.addAttribute("start", start);
			model.addAttribute("end", end);
			logger.debug("开始时间" + start + "结束时间" + end);
		}
		model.addAttribute("paginations", fileList);

		return "console/sysMgt/checkHistory/listCheckHistory";
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
		return "console/sysMgt/checkHistory/addCheckHistory";
	}
	
	/**
	 * 逻辑删除资料
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteFile")
	public void deleteFile(@RequestParam("checkId") String checkId,
			HttpServletResponse response){
		String msg = "";
		logger.debug("checkId="+checkId);
		try {
			CheckHistory checkHistory = new CheckHistory();
			checkHistory.setIsDel("1");
			checkHistory.setCheckId(checkId);
			checkHistoryService.updateFile(checkHistory);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		
		this.writeMessage(response, msg);
	}
	@RequestMapping(params = "action=saveFileInfo")
	public void saveFileInfo(MultipartHttpServletRequest request,HttpServletResponse response,
			@RequestParam("checkId") String checkId,
			@RequestParam("checkName") String checkName,
			@RequestParam("checkType") String checkType,
			@RequestParam("attachment") String attachment,
			@RequestParam("checkGroup") String checkGroup,
			@RequestParam("path") String path,
			@RequestParam("fileUrl") String fileUrl,
			@RequestParam("imageUrl") String imageUrl,
			@RequestParam("checkDesc") String checkDesc){
		MultipartFile file = request.getFile("filePath");
		String serverPath = request.getSession().getServletContext().getRealPath("/") 
				+ "resource/textnews/" + FileUploader.getTimeFolder() + "/";
		
		logger.debug("serverPath：" + serverPath);
		CheckHistory checkHistory = new CheckHistory();
		checkHistory.setCheckName(checkName);
		checkHistory.setCheckType(checkType);
		checkHistory.setCheckDesc(checkDesc);
		checkHistory.setCheckGroup(checkGroup);
		
		try {
			if (null == fileUrl||fileUrl.equals("")){
				checkHistory.setFilePath(imageUrl);
				checkHistory.setAttachment(attachment);
			} else {
				checkHistory.setFilePath(FileUploader.uploadFile(request, file, serverPath));
				checkHistory.setAttachment(file.getOriginalFilename());
			}
			
			if(StringUtils.isEmpty(checkId)){
				checkHistory.setCheckId(UUID.randomUUID().toString().replace("-", ""));
				checkHistory.setCreateBy(SystemContext.getUserName());
				checkHistory.setCreateDate(DateUtil.getCurrentDateStr());
				checkHistoryService.save(checkHistory);
			} else{
				if(StringUtils.isEmpty(checkHistory.getFilePath())){
					checkHistory.setFilePath(path);
				}
				checkHistory.setCheckId(checkId);
				checkHistoryService.updateFile(checkHistory);
			}
			this.writeMessage(response, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			this.writeMessage(response, "保存失败！");
		}
		
		
	}
	
	/**
	 * 修改加载页面
	 * @param fileId
	 * @return
	 */
	@RequestMapping(params = "face=editFile")
	public String editFile(Model model,@RequestParam("checkId") String checkId){
		model.addAttribute("file",checkHistoryService.getFileByFileId(checkId));
		return"console/sysMgt/checkHistory/editCheckHistory";
	}
}
