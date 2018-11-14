package com.gdssoft.cqjt.controller.portal.index;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.content.EmpShow;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.content.EmpShowService;

@Controller
@RequestMapping("/guest/empShow.xhtml")
public class EmpController extends BaseController {

	@Autowired
	@Resource(name = "empShowServiceImpl")
	private EmpShowService empShowService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
	
	
	/**
	 * 员工展示
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "face=empDisplay")
	public String empDisplay(Model model, HttpServletResponse response) {

	    List<EmpShow> listEmpShow= empShowService.getEmpList("", "", null, null,new String[]{},0,5,"","委领导");
	    model.addAttribute("categoryName", "委领导");
	   // model.addAttribute("fasts", "fasts0");
	    model.addAttribute("paginations", listEmpShow);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
	    
		return "portal/index/empDisplay";
	}
	
	@RequestMapping(params = "action=queryEmpList")
	public void getEmpList(@RequestParam("deptName") String deptName,
			HttpServletResponse response){
		logger.debug("输入参数:deptCategory="
				+ deptName);
		Date start  = this.getStartDate("");
		Date end  = this.getEndDate("");
	    List<EmpShow> listEmpShow= empShowService.getEmpList("", "", start, end,new String[]{},0,8,"",deptName);
		this.writeResult(response, listEmpShow);
	}
	
	@RequestMapping(params = "action=queryEmpInfo")
	public void queryEmpInfo(@RequestParam("empId") String empId,
			HttpServletResponse response){
		logger.debug("输入参数:deptCategory="
				+ empId);
 
	    EmpShow emp= empShowService.queryEmp(empId);
		this.writeResult(response, emp);
	}
	
	
	@RequestMapping(params = "action=queryCatNameList")
	public void queryCatNameList(@RequestParam("deptName") String deptName,
			HttpServletResponse response){
		logger.debug("输入参数:deptCategory="
				+ deptName);
	 
	    List<EmpShow> listEmpShow= empShowService.queryCatNameList("", "", null, null,new String[]{},0,10000,"",deptName);
		this.writeResult(response, listEmpShow);
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
			@RequestParam("fasts") String fasts,
			HttpServletResponse response) throws UnsupportedEncodingException{
	 
		/*
		 * http://localhost:8080/cqjt_portal_web/guest/empShow.xhtml?face=searchEmpList&
		 * empName=&
		 * entryDateS=&
		 * entryDateE=&
		 * pageIndex=0&
		 * deptName=&
		 * categoryName=%E7%BA%AA%E6%A3%80%E7%9B%91%E5%AF%9F%E5%AE%A4&
		 * fasts=fasc17
		 * 
		 * 
		 * 
		 * */
		
//		try {
//			deptName = new String(deptName.getBytes("iso-8859-1"),"utf-8");
//			categoryName = new String(categoryName.getBytes("iso-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//		}
		
		if (entryDateS.equals("")||entryDateE.equals("")){
			 { 
				 empName=URLDecoder.decode(empName,"utf-8");
				 categoryName=URLDecoder.decode(categoryName,"utf-8");
						 logger.debug("EmpName=========="+empName);
             List<EmpShow> listEmpShow= empShowService.getEmpList(empName,"",null ,null,new String[]{},pageIndex,5,deptName,categoryName);
             if(listEmpShow.size()==0){
					model.addAttribute("msgs", categoryName+"暂无员工信息");
				}			
             model.addAttribute("deptName", deptName);
             			 model.addAttribute("categoryName", categoryName);
             			 model.addAttribute("EmpName", empName);
						 model.addAttribute("paginations", listEmpShow);

							     }
						}
	  else if (entryDateS.equals("")&&entryDateE.equals("")){
		  empName=URLDecoder.decode(empName,"utf-8");
						logger.debug("EmpName=========="+empName);
			List<EmpShow> listEmpShow= empShowService.getEmpList (empName,"",null ,null,new String[]{},pageIndex,5,deptName,categoryName);
			if(listEmpShow.size()==0){
					model.addAttribute("msgs", categoryName+"暂无员工信息");
				} 			
			model.addAttribute("deptName", deptName);
			 			model.addAttribute("categoryName", categoryName);
						model.addAttribute("EmpName", empName);
						model.addAttribute("paginations", listEmpShow);

	 					    }
	  else {
		  empName=URLDecoder.decode(empName,"utf-8");
							Date start = this.getStartDate(entryDateS);
							Date end = this.getEndDate(entryDateE);
							logger.debug("EmpName=========="+empName);
	      List<EmpShow> listEmpShow= empShowService.getEmpList (empName,"",start,end,new String[]{},pageIndex,5,deptName,categoryName);
			 				if(listEmpShow.size()==0){
			 					model.addAttribute("msgs", categoryName+"暂无员工信息");
			 				}
	      					model.addAttribute("deptName", deptName);
			 				model.addAttribute("categoryName", categoryName);
							model.addAttribute("start", start);
							model.addAttribute("end", end);
							model.addAttribute("EmpName", empName);
							model.addAttribute("paginations", listEmpShow);
		  }
							logger.debug("开始时间"+entryDateS+"结束时间"+entryDateE);

				model.addAttribute("categoryName", categoryName);
				model.addAttribute("fasts", fasts);
				/* ==========当前访问人数========== */
				model.addAttribute("visiterNum", this.getCount());
				return "portal/index/empDisplay";
	}
}
