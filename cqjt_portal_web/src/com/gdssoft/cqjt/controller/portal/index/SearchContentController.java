package com.gdssoft.cqjt.controller.portal.index;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.core.tools.WordSegmentTool;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.TextPerson;
import com.gdssoft.cqjt.pojo.TextPublication;
import com.gdssoft.cqjt.pojo.TextStatute;
import com.gdssoft.cqjt.pojo.TrafficRunVo;
import com.gdssoft.cqjt.pojo.report.GovSiteCheckReport;
import com.gdssoft.cqjt.pojo.search.InnerSiteSearch;
import com.gdssoft.cqjt.pojo.search.PublicArchivesSearch;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.FileDownloadService;
import com.gdssoft.cqjt.service.SpecialInformationService;
import com.gdssoft.cqjt.service.TextNewsReportService;
import com.gdssoft.cqjt.service.TextNewsService;
import com.gdssoft.cqjt.service.TextPersonService;
import com.gdssoft.cqjt.service.TextPublicationService;
import com.gdssoft.cqjt.service.TextStatuteService;
import com.gdssoft.cqjt.service.TrafficRunService;
import com.gdssoft.cqjt.service.search.SearchEngineService;
import com.gdssoft.cqjt.service.videoNews.VideoNewsService;

@Controller
@RequestMapping("/guest/SearchContent.xhtml")
public class SearchContentController extends BaseController {

	@Value("${oa.server}")
	private String oaServerUrl;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "textPublicationServiceImpl")
	private TextPublicationService textPublicationService;

	@Resource(name = "fileDownloadService")
	private FileDownloadService fileDownloadService;
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	@Resource(name = "videoNewsServiceImpl")
	private VideoNewsService videoNewsService;

	@Resource(name = "searchEngineServiceImpl")
	private SearchEngineService searchEngineService;
	
	@Resource(name = "trafficRunService")
	private TrafficRunService trafficRunService;
	
	@Resource(name = "specialInformationService")
	private SpecialInformationService specialInformationService;
	
	@Autowired
	@Resource(name = "textNewsReportServiceImpl")
	private TextNewsReportService textNewsReportService;
	
	@Autowired
	@Resource(name = "textStatuteServiceImpl")
	private TextStatuteService textStatuteService;
	
	@Autowired
	@Resource(name = "textPersonServiceImpl")
	private TextPersonService textPersonService;

	
	/**
	 * 页面初始化
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping
	public String home(Model model, HttpServletRequest request) {
		
		int pageIndex = 0;
		Calendar calendar = Calendar.getInstance();
		Date end = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date start = calendar.getTime();
		
		model.addAttribute("oaServerUrl", oaServerUrl);
 
		
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		return "portal/index/searchContent";
	}
	

 
	
	
	
	/**
	 * 部门按条件查询
	 * 
	 * @param deptName
	 * @param deptCategory
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=queryDeptInfoView")
	public void queryDeptInfo(@RequestParam("deptCategory") String deptCategory,
			HttpServletResponse response) {
		logger.debug("输入参数:deptCategory="
				+ deptCategory);
		List<Department> departmentInfoList = departmentService.getDeptNameByCat(deptCategory);
		this.writeResult(response, departmentInfoList);
	}
	
	/**
	 * 根据部门Id查询栏目
	 * 
	 * @param deptId
	 * @param response
	 */
	@RequestMapping(params = "face=queryCategoryDeptList")
	@ResponseBody
	public void queryCategoryDeptList(@RequestParam("deptID") String deptId,
			HttpServletResponse response) {
		List<Department> categoryList = departmentService
				.getCategoryName(deptId);
		Department dt  = categoryList.get(0);
		categoryList.set(0, categoryList.get(2));
		categoryList.set(2, dt);

		Map<String,Object> searchViewInfo= new HashMap<String,Object>();
		searchViewInfo.put("categoryList", categoryList);
		
		//根据栏目的大小查询渲染页面的数据
		int catSize = categoryList.size();
		List<TextNews> oneNewsList = null;
		List<TextNews> towNewsList = null;
		List<TextNews> threeNewsList = null;
		List<TextStatute> textStatuteList = null;
		List<TextPerson> textPersonList = null;
		if(catSize>0){
			if("112".equals(deptId)){
				textPersonList = textPersonService.getPersonlistView(null, null, 0, 10);
			}else{
				String category = categoryList.get(0).getCategoryId().toString();
				oneNewsList = textNewsService.getTextNewsListByCatID(categoryList.get(0).getDeptName(),category, 10);
			}
		}
		if(catSize>3){
			if("116".equals(deptId)){
				textStatuteList = textStatuteService.getStatutelistView(null, null, 0, 10);
			}else{
				String category = categoryList.get(3).getCategoryId().toString();
				towNewsList = textNewsService.getTextNewsListByCatID(categoryList.get(3).getDeptName(),category, 10);
			}
		}
		if(catSize>6){
			String category = categoryList.get(6).getCategoryId().toString();
			threeNewsList = textNewsService.getTextNewsListByCatID(categoryList.get(6).getDeptName(),category, 10);
		}
		if("112".equals(deptId)){
			searchViewInfo.put("oneNewsList", textPersonList);
		}else{
			searchViewInfo.put("oneNewsList", oneNewsList);
		}
		if("116".equals(deptId)){
			searchViewInfo.put("towNewsList", textStatuteList);
		}else{
			searchViewInfo.put("towNewsList", towNewsList);
		}
		searchViewInfo.put("threeNewsList", threeNewsList);
		this.writeResult(response, searchViewInfo);
	}
	
	/**
	 * 根据栏目ID查询TextNexs
	 * @param categoryId
	 * @param response
	 */
	@RequestMapping(params = "face=queryTextNewsByCatId")
	@ResponseBody
	public void queryTextNewsByCatId(@RequestParam("categoryId") String categoryId,
			@RequestParam("deptName") String deptName,
			HttpServletResponse response) {
		 List<TextNews> NewsList = null;
		 List<TextStatute> textStatuteList = null;
		 List<TextPerson> textPersonList = null;
		 if("21".equals(categoryId)){
			 textStatuteList = textStatuteService.getStatutelistView(null, null, 0, 10);
		 }else if("58".equals(categoryId)){
			 textPersonList = textPersonService.getPersonlistView(null, null, 0, 10);
		 }else{
			 NewsList = textNewsService.getTextNewsListByCatID(deptName,categoryId, 10);
		 }
		 
		 if("21".equals(categoryId)){
			 this.writeResult(response, textStatuteList);
		 }else if("58".equals(categoryId)){
			 this.writeResult(response, textPersonList);
		 }else{
			 this.writeResult(response, NewsList);
		 } 
	}
	
	/**
	 * 根据ID查询TextNexs的详细信息
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=queryTextNewsByID")
	public String queryTextNewsByID(Model model,
			@RequestParam("newsId") String newsId){
		TextNews textNews = textNewsService.getTextNewsDetail(newsId);
		model.addAttribute("textNews", textNews);
		return "portal/index/contentInfo";
	}
	
	
	/**
	 * 栏目的更多信息加载数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=contentMoreView")
	public String contentMoreView(@RequestParam("categoryId") String categoryId,
			@RequestParam("deptName") String deptName, Model model) {
		String dn = "";
		try {
			dn = new String(deptName.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		int pageIndex = 0;
		List<TextNews> publicNews = textNewsService.getSiteNewsList(null, null,
				new String[] {"3"}, new String[]{}, pageIndex,
				SystemContext.getDefaultPageSize(),dn,categoryId);
		model.addAttribute("paginations", publicNews);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum", this.getCount());
		
		return "portal/index/contentMore";
	}
	
	
	
	/**
	 * 栏目的更多信息分页时查询数据
	 * 
	 * @param pageIndex
	 * @param response
	 */
	@RequestMapping(params = "action=getContentMoreView")
	public void getPublicView(@RequestParam("pageIndex") int pageIndex, @RequestParam("categoryId") String categoryId,
			@RequestParam("deptName") String deptName, HttpServletResponse response) {

		List<TextNews> publicNews = textNewsService.getSiteNewsList(null, null,
				new String[] {"3"}, new String[]{}, pageIndex,
				SystemContext.getDefaultPageSize(),deptName,categoryId);
		this.writeResult(response, publicNews);
	}
	

	/**
	 * 加载温馨提示页面
	 */
	@RequestMapping(params = "face=tipWindow")
	public String updatePassword() {

		return "portal/index/tipWindow";
	}
	
	/**
	 * 2016-12-30
	 * @param 新年贺词
	 * @return
	 */
	@RequestMapping(params = "action=jumpNewYear")
	public String jumpNewYear(HttpServletRequest request) {
	
		return "portal/index/happyNewYear";
	}
	
}
