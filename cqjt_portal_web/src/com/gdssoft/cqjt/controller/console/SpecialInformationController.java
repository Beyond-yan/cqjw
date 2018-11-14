package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.FileUploader;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.SpecialInformationVo;
import com.gdssoft.cqjt.service.SpecialInformationService;
import com.gdssoft.cqjt.util.JacobUtils;
import com.gdssoft.cqjt.util.PageUtils;
import com.gdssoft.cqjt.util.PortalUtils;

/**
 * 专报信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/console/specialInformation.xhtml")
public class SpecialInformationController extends BaseController{
	@Autowired
	@Resource(name = "specialInformationService")
	private SpecialInformationService specialInformationService;
	@Value("${upload.temp.file.url}")
	private String uploadTempFileUrl;//浪潮单点登录地址
	
	public Date parseEndDate(HttpServletRequest request, String key){
		Date result = null;
		String value = request.getParameter(key);
		if(StringUtils.isNotBlank(value)){
			
			try {
				result = DateUtil.dateParse(value+" 23:59:59");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public int parseInt(HttpServletRequest request, String key){
		int result = -1;
		String value = request.getParameter(key);
		if(StringUtils.isNotBlank(value)){
			try {
				result = Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 页面首次加载列表数据
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=queryPageList")
	public String queryPageList(Model model,HttpServletRequest request){
		String specialTitle = "";
		try {
			String title = request.getParameter("specialTitle");
			System.out.println(title);
			if(StringUtils.isNotBlank(title)){
				specialTitle = URLDecoder.decode(title, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Date beginDate = DateUtil.dateParse(request.getParameter("beginDate"),DateUtil.DATE_FORMAT_YMD);
		Date endDate = parseEndDate(request,"endDate");
		System.out.println(beginDate+"-----------"+endDate);
		int pageIndex =  parseInt(request,"pageIndex");
		int limitCount =  parseInt(request,"limitCount");//每页显示条数
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("specialTitle", specialTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		
		int count = specialInformationService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, limitCount);
		
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<SpecialInformationVo> specialVoList = specialInformationService.queryPageList(map);
		
		model.addAttribute("specialVoList", specialVoList);
		model.addAttribute("page", page);
		model.addAttribute("specialTitle", specialTitle);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return "console/sysMgt/specialInformation/list";
	}
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addPage")
	public String addPage(Model model) {
		return "console/sysMgt/specialInformation/add";
	}
	
	private String getFilePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/specialReport/"
				+ FileUploader.getTimeFolder() + "/";
	}
	/**
	 * 新增信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=insert")
	public void insert(MultipartHttpServletRequest request, HttpServletResponse response){
		try {
			String specialTitle = request.getParameter("specialTitle");
			String specialContent = request.getParameter("specialContent");
			int isTop = parseInt(request,"isTop");
			int isPublish = parseInt(request,"isPublish");
			String accessoryUrl = null,accessoryName = null;
			MultipartFile newsImage = request.getFile("accessoryUrl");
			if(newsImage != null){
				accessoryUrl = FileUploader.uploadFile(request, newsImage,  getFilePath(request.getSession().getServletContext()));
//				accessoryUrl = FileUploader.uploadFile(request, newsImage,  uploadFileUrl);
				String pdfUrl = uploadTempFileUrl+accessoryUrl;
				System.out.println("pdf-------------->"+pdfUrl);
				System.out.println("pdf-------------->"+pdfUrl.replaceAll("\\\\", "/"));
//				JacobUtils.doc2Pdf(pdfUrl.replaceAll("\\\\", "/"));
				
				//复制pdf到resource中
				String pdfUrlRes = request.getSession().getServletContext().getRealPath("/")+accessoryUrl;
				PortalUtils.copyFile(pdfUrlRes, pdfUrl);
				System.out.println("----------e---------->");
				JacobUtils.doc2Pdf(pdfUrl);
				PortalUtils.copyFile(pdfUrl.substring(0,pdfUrl.lastIndexOf("."))+".pdf", pdfUrlRes.substring(0,pdfUrlRes.lastIndexOf("."))+".pdf");
				accessoryName = newsImage.getOriginalFilename();
			}
			String msg = "";
			SpecialInformationVo specialVo = new SpecialInformationVo();
			String id = UUID.randomUUID().toString().replace("-", "");
			specialVo.setSpecialId(id);
			specialVo.setSpecialTitle(specialTitle);
			specialVo.setSpecialContent(specialContent);
			Date nowTime = DateUtil.getCurrentDate();
			specialVo.setSpecialCreateDate(nowTime);
			specialVo.setSpecialCreateUserName(SystemContext.getUserName());
			specialVo.setSpecialPublish(isPublish);
			specialVo.setSpecialAccessoryUrl(accessoryUrl);
			specialVo.setSpecialAccessoryName(accessoryName);
			if(isTop == 1){
				specialVo.setSpecialTop(nowTime);
			}
			boolean result = false;
			try {
				result = specialInformationService.insert(specialVo);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
			msg = result  ? "新增成功！" : "新增失败！";
			this.writeMessage(response, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=editPage")
	public String editPage(Model model,HttpServletRequest request) {
		String specialId = request.getParameter("specialId");
		SpecialInformationVo specialVo = specialInformationService.query(specialId);
		model.addAttribute("specialVo", specialVo);
		return "console/sysMgt/specialInformation/edit";
	}
	
	/**
	 * 编辑信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=edit")
	public void edit(MultipartHttpServletRequest request, HttpServletResponse response){
		String specialId = request.getParameter("specialId");
		String specialTitle = request.getParameter("specialTitle");
		String specialContent = request.getParameter("specialContent");
		int isTop = parseInt(request,"isTop");
		int isPublish = parseInt(request,"isPublish");
		String accessoryUrl = request.getParameter("accessoryUrl");
		String accessoryName = request.getParameter("accessoryName");
		
		String msg = "";
		SpecialInformationVo specialVo = new SpecialInformationVo();
		specialVo.setSpecialId(specialId);
		specialVo.setSpecialTitle(specialTitle);
		specialVo.setSpecialContent(specialContent);
		specialVo.setSpecialPublish(isPublish);
		Date nowTime = DateUtil.getCurrentDate();
		if(isTop == 1){
			specialVo.setSpecialTop(nowTime);
		} else {
			specialVo.setSpecialTop(null);
		}
		specialVo.setSpecialEditDate(nowTime);
		boolean result = false;
		try {
			MultipartFile newsImage = request.getFile("accessoryFileUrl");
			if(newsImage != null){
				accessoryUrl = FileUploader.uploadFile(request, newsImage,  getFilePath(request.getSession().getServletContext()));
				String pdfUrl = request.getSession().getServletContext().getRealPath("/")+accessoryUrl;
				JacobUtils.doc2Pdf(pdfUrl.replaceAll("\\\\", "/"));
				accessoryName = newsImage.getOriginalFilename();
			}
			specialVo.setSpecialAccessoryUrl(accessoryUrl);
			specialVo.setSpecialAccessoryName(accessoryName);
			result = specialInformationService.edit(specialVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "编辑成功！" : "编辑失败！";
		this.writeMessage(response, msg);
	}
	
	/**
	 * 编辑信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=del")
	public void del(HttpServletRequest request, HttpServletResponse response){
		String specialId = request.getParameter("specialId");
		String  msg = "";
		boolean result = false;
		try {
			result = specialInformationService.del(specialId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "删除成功！" : "删除失败！";
		this.writeMessage(response, msg);
	}
}
