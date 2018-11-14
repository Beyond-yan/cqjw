package com.gdssoft.cqjt.controller.portal.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.SpecialInformationVo;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.service.SpecialInformationService;
import com.gdssoft.cqjt.util.PageUtils;


@Controller
@RequestMapping("/guest/specialInformation.xhtml")
public class SpecialInformation extends BaseController {

	

	@Resource(name = "specialInformationService")
	private SpecialInformationService specialInformationService;
	
//	/**
//	 * 专报信息查看详情
//	 * 
//	 * @param model
//	 * @param newsId
//	 * @return
//	 */
//	@RequestMapping(params = "face=specialVoDetails")
//	public String specialVoDetails(Model model, @RequestParam("giId") String giId) {
//
//		TextGovInfo specialVo = specialInformationService.query(giId);
//		model.addAttribute("specialVo", specialVo);
//
//		//专报信息
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("adoptType","subjectInfo");
////		Map<String, Object> map = new HashMap<String, Object>();
////		map.put("beginLimit", 0);
////		map.put("endLimit", 20);
////		map.put("specialPublish",0);
//		List<TextGovInfo> specialVoList = specialInformationService.getGovNewsList(map, 0, 20);
//		
//		model.addAttribute("specialVoList", specialVoList);
//		model.addAttribute("visiterNum", this.getCount());
//		return "portal/specialInformation/details";
//	}
//	
//	/**
//	 * 专报信息列表显示
//	 * 
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(params = "face=specialVoListView")
//	public String specialVoList(Model model, HttpServletRequest request) {
//		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
//		int pageSize = SystemContext.getDefaultPageSize();
//		
//		//专报信息
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		map.put("adoptType","subjectInfo");
////		int count = specialInformationService.queryCount(map);
////		map.put("startRow",0);
////		map.put("endRow",0);
//		//PageUtils page = new PageUtils(count, pageIndex, pageSize);
//		//map.put("beginLimit", page.getLimitBegin());
//		//map.put("endLimit", page.getLimitEnd());
//		List<TextGovInfo> specialVoList = specialInformationService.getGovNewsList(map, pageIndex, pageSize);
//		model.addAttribute("specialVoList", specialVoList);
//		model.addAttribute("visiterNum", this.getCount());
//		return "portal/specialInformation/list";
//	}
//	
//	/**
//	 * 专报信息列表
//	 * @param response
//	 */
//	@RequestMapping(params = "face=specialVoList")
//	public void specialVoList(HttpServletResponse response,HttpServletRequest request) {
//		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
//		int pageSize = parseInt(request.getParameter("pageSize"),20);
//		
//		//交通运行动态
//		Map<String, Object> map = new HashMap<String, Object>();
//		int count = specialInformationService.queryCount(map);
//		PageUtils page = new PageUtils(count, pageIndex, pageSize);
//		map.put("beginLimit", page.getLimitBegin());
//		map.put("endLimit", page.getLimitEnd());
//		List<SpecialInformationVo> specialVoList = specialInformationService.queryPageList(map);
//		response.setCharacterEncoding("UTF-8");
//		try {
//			this.writeResult(response, specialVoList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 字符串转换INT
//	 * @param s
//	 * @param defaultStr
//	 * @return
//	 */
//	private int parseInt(String s, int defaultStr){
//		int result = defaultStr;
//		if(StringUtils.isNotBlank(s)){
//			result = Integer.parseInt(s);
//		}
//		return result;
//	}

	/**
	 * 专报信息查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=specialVoDetails")
	public String specialVoDetails(Model model, @RequestParam("specialId") String specialId) {

		SpecialInformationVo specialVo = specialInformationService.query(specialId);
		model.addAttribute("specialVo", specialVo);

		//专报信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginLimit", 0);
		map.put("endLimit", 20);
		map.put("specialPublish",0);
		List<SpecialInformationVo> specialVoList = specialInformationService.queryPageList(map);
		
		model.addAttribute("specialVoList", specialVoList);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/specialInformation/details";
	}
	
	/**
	 * 专报信息列表显示
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=specialVoListView")
	public String specialVoList(Model model, HttpServletRequest request) {
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		
		//专报信息
		Map<String, Object> map = new HashMap<String, Object>();
		int count = specialInformationService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, pageSize);
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		map.put("specialPublish",0);
		List<SpecialInformationVo> specialVoList = specialInformationService.queryPageList(map);
		model.addAttribute("specialVoList", specialVoList);
		model.addAttribute("visiterNum", this.getCount());
		return "portal/specialInformation/list";
	}
	
	/**
	 * 专报信息列表
	 * @param response
	 */
	@RequestMapping(params = "face=specialVoList")
	public void specialVoList(HttpServletResponse response,HttpServletRequest request) {
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		
		//交通运行动态
		Map<String, Object> map = new HashMap<String, Object>();
		int count = specialInformationService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, pageSize);
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<SpecialInformationVo> specialVoList = specialInformationService.queryPageList(map);
		response.setCharacterEncoding("UTF-8");
		try {
			this.writeResult(response, specialVoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符串转换INT
	 * @param s
	 * @param defaultStr
	 * @return
	 */
	private int parseInt(String s, int defaultStr){
		int result = defaultStr;
		if(StringUtils.isNotBlank(s)){
			result = Integer.parseInt(s);
		}
		return result;
	}
	
}
