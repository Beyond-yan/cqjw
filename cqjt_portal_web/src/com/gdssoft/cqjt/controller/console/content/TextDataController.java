package com.gdssoft.cqjt.controller.console.content;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.FileUploader;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.content.TextData;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.content.TextDataService;
/**
 * 文字类信息管理
 * @author  gyf 20140709
 * @return
 */
@Controller
@RequestMapping("/console/textData.xhtml")
public class TextDataController extends BaseController {
	@Autowired
	@Resource(name = "textDataService")
	private TextDataService textDataService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
	
	
	/**
	 * 页面首次加载列表数据
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getTextDataList")
	public String getTextDataList(Model model){
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		int pageIndex = 0;
		List<TextData> textDataList = textDataService.getAllTextData("", "", start, end, "0",pageIndex,SystemContext.getDefaultPageSize());
		
		model.addAttribute("paginations", textDataList);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		
		return "console/textDataManager/textDataList";
	}
	
	/**
	 * 用于查询
	 * @param title
	 * @param author
	 * @param startDate
	 * @param endDate
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "face=searchTextDataList")
	public String searchTextDataList(@RequestParam("title") String title,
			@RequestParam("author") String author,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex,HttpServletResponse response,Model model) throws UnsupportedEncodingException{
		
		
		if (startDate.equals("")||endDate.equals("")){
			 { 
				         title=URLDecoder.decode(title,"utf-8");
						 author=URLDecoder.decode(author,"utf-8");
						 logger.debug("title=========="+title);
           List<TextData> textDataList = textDataService.getAllTextData(title, author, null, null, "0",pageIndex,SystemContext.getDefaultPageSize());
					     model.addAttribute("title", title);
						 model.addAttribute("author", author);
						 model.addAttribute("paginations", textDataList);

							     }
						}
	  else if (startDate.equals("")&&endDate.equals("")){
				        title=URLDecoder.decode(title,"utf-8");
					    author=URLDecoder.decode(author,"utf-8");
						logger.debug("title=========="+title);
			List<TextData> textDataList = textDataService.getAllTextData(title, author,null, null, "0",pageIndex,SystemContext.getDefaultPageSize());
				          model.addAttribute("title", title);
						model.addAttribute("author", author);
						model.addAttribute("paginations", textDataList);

	 					    }
	  else {
					        title=URLDecoder.decode(title,"utf-8");
							author=URLDecoder.decode(author,"utf-8");
							Date start = this.getStartDate(startDate);
							Date end = this.getEndDate(endDate);
							logger.debug("title=========="+title);
	        List<TextData> textDataList = textDataService.getAllTextData(title, author, start, end, "0",pageIndex,SystemContext.getDefaultPageSize());
							 
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("title", title);
							model.addAttribute("author", author);
							model.addAttribute("paginations", textDataList);
		  }
							logger.debug("开始时间"+startDate+"结束时间"+endDate);


		/*
		title=URLDecoder.decode(title,"utf-8");
		author=URLDecoder.decode(author,"utf-8");
		Date start = this.getStartDate(startDate);
		Date end = this.getEndDate(endDate);
		List<TextData> textDataList = textDataService.getAllTextData(title, author, start, end, "0",pageIndex,SystemContext.getDefaultPageSize());
		
		model.addAttribute("title", title);
		model.addAttribute("author", author);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("paginations", textDataList);
		*/
		return "console/textDataManager/textDataList";
	}
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addTextData")
	public String addTextData(Model model) {
		String createBy = SystemContext.getUserName();
		String createDate = DateUtil.getCurrentDateStr();
		logger.debug("创建人"+createBy+"时间"+createDate);
		model.addAttribute("createBy", createBy);
		model.addAttribute("createDate",createDate);
		
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail()
				.getDeptName());

		model.addAttribute("approvers", userDetailService.getApprovers(userNo));
		
		return "console/textDataManager/textDataAdd";
	}
	
	/**
	 * 修改加载页面
	 * @param textId
	 * @return
	 */
	@RequestMapping(params = "face=editTextData")
	public String editTextData(Model model,@RequestParam("textId") String textId){
		logger.debug("textId="+textId);
		model.addAttribute("textData",textDataService.getTextDataById(textId));
		logger.debug("标题："+textDataService.getTextDataById(textId).getTitle());
		
		return "console/textDataManager/textDataEdit";
	}
	
	/**
	 * 逻辑删除
	 * @param textId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteTextData")
	public void deleteTextData(@RequestParam("textId") String textId,
			HttpServletResponse response){
		String msg = "";
		logger.debug("textId="+textId);
		try {
			TextData textData = new TextData();
			textData.setIsDel("1");
			textData.setTextId(textId);
			textDataService.updateTextData(textData);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}		
		this.writeMessage(response, msg);
	}
	/**
	 * 发布
	 * @param textId
	 * @param response
	 */
	@RequestMapping(params = "action=updateStatus")
	public void updateStatus(@RequestParam("textId") String textId,
			HttpServletResponse response){
		String msg = "";
		logger.debug("textId="+textId);
		try {
			TextData textData = new TextData();
			textData.setStatus("1");
			textData.setTextId(textId);
			textDataService.updateStatus(textData);
			msg = "发布成功！";
		} catch (Exception e) {
			msg = "发布失败！";
		}		
		this.writeMessage(response, msg);
	}
	//保存
	@RequestMapping(params = "action=save", method = RequestMethod.POST)
	public void save(@ModelAttribute("textData") TextData textData,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		logger.debug("图片路径："+textData.getPhotoUrl());
		MultipartFile newsImage = request.getFile("photo");
		String serverPath = request.getSession().getServletContext().getRealPath("/") 
				+ "resource/textnews/" + FileUploader.getTimeFolder() + "/";
		if (textData.getPhotoUrl()==null||textData.getPhotoUrl().equals(""))
			{textData.setPhotoUrl(textData.getPhotos());}
		else {
			textData.setPhotoUrl(FileUploader.uploadFile(request, newsImage,serverPath));
		}
		if(textData.getTextId()==null){//新增
			textData.setTextId(UUID.randomUUID().toString().replace("-", ""));
			textData.setIsDel("0");
			textData.setCreateDate(DateUtil.getCurrentDateStr());
			if (textData.getStatus().equals("0")){
				textDataService.insertTextData(textData);
				this.writeMessage(response, "保存草稿成功！");
			}
			else if (textData.getStatus().equals("1")){
				textDataService.insertTextData(textData);
				this.writeMessage(response, "发布成功！");
			}
			
		}else{//修改
			//if (null == newsImage){textData.setPhotoUrl(textData.getPhotoUrl());}
			textDataService.updateTextData(textData);
			if (textData.getStatus().equals("0")){
				this.writeMessage(response, "保存草稿成功！");
			}
			else if (textData.getStatus().equals("1")){
				this.writeMessage(response, "发布成功！");
			}
		}
		
	}
}
