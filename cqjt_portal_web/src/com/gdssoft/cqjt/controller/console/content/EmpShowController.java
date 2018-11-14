package com.gdssoft.cqjt.controller.console.content;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.gdssoft.cqjt.pojo.content.EmpShow;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.content.EmpShowService;

@Controller
@RequestMapping("/console/emp.xhtml")
public class EmpShowController extends BaseController {
	
	@Autowired
	@Resource(name = "empShowServiceImpl")
	private EmpShowService empShowService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
    

	/**
	 *首次加载图片信息默认查询
	 * @author H2603282
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getEmplistView")
	public String queryEmplist(Model model) {
	    List<EmpShow> listEmpShow= empShowService.getEmpList("", "", null, null,new String[]{},0,8,"","");
		
 
		model.addAttribute("paginations", listEmpShow);
		
		return "console/emp/listEmp";
	}
	/**
	 *按搜索条件查询
	 * @author H2603282
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=searchEmpList")
	public String searchEmpList(Model model,@RequestParam("empName") String empName,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("deptName") String deptName,
			@RequestParam("categoryName") String categoryName,
			HttpServletResponse response) throws UnsupportedEncodingException{
	 
  
		if (entryDateS.equals("")&&entryDateE.equals("")){
		  empName=URLDecoder.decode(empName,"utf-8");
		  categoryName=URLDecoder.decode(categoryName,"utf-8");
						logger.debug("EmpName=========="+empName);
			List<EmpShow> listEmpShow= empShowService.getEmpList (empName,"",null ,null,new String[]{},pageIndex,8,deptName,categoryName);
			 			model.addAttribute("deptName", deptName);
			 			model.addAttribute("categoryName", categoryName);
						model.addAttribute("EmpName", empName);
						model.addAttribute("paginations", listEmpShow);
						

	 	}else {
	 						empName=URLDecoder.decode(empName,"utf-8");
	 						Date start = null;
	 						if(!("".equals(entryDateS))){
	 							start = this.getStartDate(entryDateS);
	 						}
							
							Date end = this.getEndDate(entryDateE);
							logger.debug("EmpName=========="+empName);
	      List<EmpShow> listEmpShow= empShowService.getEmpList (empName,"",start,end,new String[]{},pageIndex,8,deptName,categoryName);
			 				model.addAttribute("deptName", deptName);
			 				model.addAttribute("categoryName", categoryName);
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("EmpName", empName);
							model.addAttribute("paginations", listEmpShow);
		  }
							logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);

		
		 
				return "console/emp/listEmp";
	}
	
	/**
	 * 新增图片加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addEmp")
	public String addFile(Model model) {
		String entryUser = SystemContext.getUserName();
		String entryDate = DateUtil.getCurrentDateStr();
		String userNo = SystemContext.getUserNO();
		logger.debug("创建人"+entryUser+"时间"+entryDate);
		
		model.addAttribute("approvers", userDetailService.getApprovers(userNo));
		model.addAttribute("entryUser", entryUser);
		model.addAttribute("entryDate",entryDate);
		return "console/emp/addEmp";
	}
	
	/**
	 * 逻辑删除资料
	 * @param EmpId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteEmp")
	public void deleteEmp(@RequestParam("empId") String EmpId,
			HttpServletResponse response){
		    String msg = "";
		   logger.debug("EmpId="+EmpId);
		   try {
			EmpShow EmpShow = new EmpShow();
			    EmpShow.setIsDel("1");
			    EmpShow.setEmpId(EmpId);
			    empShowService.updateEmpShow(EmpShow);
				msg = "删除成功";
			}
		 catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}	
	
	/**
	 * 加载编辑页面
	 * @param EmpId
	 * @return
	 */
	@RequestMapping(params = "face=editEmp")
	public String editEmp(Model model,@RequestParam("empId") String EmpId){
		logger.debug("EmpId="+EmpId);
		model.addAttribute("EmpShow",empShowService.getEmpByEmpId(EmpId));
		logger.debug(EmpId);
		logger.debug(empShowService.getEmpByEmpId(EmpId));
		logger.debug("资料名称："+ empShowService.getEmpByEmpId(EmpId).getEmpName());
		logger.debug("资料名称："+ empShowService.getEmpByEmpId(EmpId).getEntryDate());
		logger.debug("资料名称："+ empShowService.getEmpByEmpId(EmpId).getEntryUser());
		return"console/emp/editEmp";
	}
	
	/**
	 * 图片存草稿和发布
	 * 修改 添加编辑方法
	 * @param EmpShow
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=saveEmpInfo", method = RequestMethod.POST)
	public void saveMagazine(@ModelAttribute("EmpShow") EmpShow EmpShow,
			@RequestParam("photo") String photo,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		//图片URL
		MultipartFile photoUrl = request.getFile("picFile");
		if (EmpShow.getEmpUrl()== null||EmpShow.getEmpUrl().equals("")){
			EmpShow.setEmpUrl(photo);
		}else{
			String rst = FileUploader.uploadEmpFile(request, photoUrl,
					getImagePath(request.getSession().getServletContext()));
			
			if("1".equals(rst)){
				this.writeMessage(response, "图片宽度不符合,操作失败！");
				return;
			}else if("2".equals(rst)){
				this.writeMessage(response, "图片高度不符合,操作失败！");
				return;
			}else{
				EmpShow.setEmpUrl(rst);
			}
		}
		//保存sava方法
		if (StringUtils.isBlank(EmpShow.getEmpId())) {
			EmpShow.setEmpId(UUID.randomUUID().toString().replace("-", ""));
			logger.debug("flag====="+ EmpShow.getFlag());
			logger.debug("EmpUrl====="+ EmpShow.getEmpUrl());
			EmpShow.setIsDel("0");
			if(EmpShow.getFlag().equals("0")){
				empShowService.save(EmpShow);
				this.writeMessage(response, "保存草稿成功！");
			}
			else if(EmpShow.getFlag().equals("1")){
				empShowService.save(EmpShow);
				this.writeMessage(response, "发布成功！");
			}
		} else {
			empShowService.updateEmpShow(EmpShow);
			this.writeMessage(response, "修改成功！");
		}
	}

	private String getImagePath(ServletContext servletContext) {
		return servletContext.getRealPath("/") + "resource/empShow/"
				+ FileUploader.getTimeFolder() + "/";
	}
	
	
	@RequestMapping(params = "action=updateEmpSort")
	public void updateempSort( Model model,  HttpServletRequest request,
			 HttpServletResponse response) {
		String empId = request.getParameter("empId");
		String empSort = request.getParameter("empSort");
		String msg = "成功";
		 
		if (StringUtils.isNotBlank(empId) && StringUtils.isNotBlank(empSort)) {
			try {
				empShowService.updateEmpSort(empSort, empId);
			} catch (Exception e) {
				e.printStackTrace();
				msg = "修改失败";
			}
			
		} else {
			msg = "参数错误";
		}
		this.writeMessage(response, msg);
	}
	
}
