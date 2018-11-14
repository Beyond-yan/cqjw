package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.InfoWorkNotice;
import com.gdssoft.cqjt.service.InfoWorkNoticeService;
/**
 * 信息工作通知功能的控制类
 * @author GuoY
 *
 */
@Controller
@RequestMapping("/console/infoNotice.xhtml")
public class infoWorkNoticeController extends BaseController
{
	@Resource(name = "infoWorkNoticeServiceImpl")
	private InfoWorkNoticeService infoNoticeService;
	
	//格式化结束时间
	public Date parseEndDate(String value){
		Date result = null;
		//判断字符串是否不为null不为空串长度不为0
		if(StringUtils.isNotBlank(value)){
			try {
				result = DateUtil.dateParse(value+" 23:59:59");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//格式化开始时间
	public Date parseStrDate(String value){
		Date result = null;
		//判断字符串是否不为null不为空串长度不为0
		if(StringUtils.isNotBlank(value)){
			try {
				result = DateUtil.dateParse(value+" 00:00:00");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//字符串转int
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
	 * 信息工作通知---首次加载
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listInfoWorkNoticeView")
	public String listInfoWorkNoticeView(Model model) {
		int pageIndex = 0;
		List<InfoWorkNotice> infoNoticeList = infoNoticeService
				.queryInfoNoticeList("", null, null, pageIndex,
						SystemContext.getDefaultPageSize());
		model.addAttribute("infoNoticeList", infoNoticeList);
		
		return "console/textnews/govInfo/listInfoWorkNotice";
	}
	
	/**
	 * 信息工作通知---搜索页面
	 * @param workTitle
	 * @param beginDate
	 * @param endDate
	 * @param pageIndex
	 */
	@RequestMapping(params = "face=queryInfoWorkNotice")
	public String queryInfoWorkNotice(@RequestParam("workTitle") String workTitle,
			@RequestParam("beginDate") String beginDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletRequest response, Model model)
			throws UnsupportedEncodingException {
		
		//只有开始时间或结束时间的信息检索
		if (beginDate.equals("") || endDate.equals("")) {
			{
				Date start = parseStrDate(beginDate);
				Date end = parseEndDate(endDate);
				workTitle = URLDecoder.decode(workTitle, "utf-8");
				List<InfoWorkNotice> infoNoticeList = infoNoticeService.queryInfoNoticeList(
						workTitle, start, end,pageIndex, SystemContext.getDefaultPageSize());
				
				model.addAttribute("beginDate", start);
				model.addAttribute("endDate", end);
				model.addAttribute("workTitle", workTitle);
				model.addAttribute("infoNoticeList", infoNoticeList);
			}
		 }
		//开始时间和结束时间都为空的信息检索
		else if (beginDate.equals("") && endDate.equals("")) {
			workTitle = URLDecoder.decode(workTitle, "utf-8");
			List<InfoWorkNotice> infoNoticeList = infoNoticeService.queryInfoNoticeList(
					workTitle, null, null,pageIndex, SystemContext.getDefaultPageSize());
			
			model.addAttribute("workTitle", workTitle);
			model.addAttribute("infoNoticeList", infoNoticeList);
		 } 
		//全部信息都不为空的检索
		else {
			workTitle = URLDecoder.decode(workTitle, "utf-8");
			Date start = parseStrDate(beginDate);
			Date end = parseEndDate(endDate);
			List<InfoWorkNotice> infoNoticeList = infoNoticeService.queryInfoNoticeList(
					workTitle,  start, end,pageIndex, SystemContext.getDefaultPageSize());
			
			model.addAttribute("beginDate", start);
			model.addAttribute("endDate", end);
			model.addAttribute("workTitle", workTitle);
			model.addAttribute("infoNoticeList", infoNoticeList);
		 }
		 return "console/textnews/govInfo/listInfoWorkNotice";
	}

	/**
	 * 信息工作通知---新增页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addInfoWorkNotice")
	public String addInfoWorkNotice(Model model) {
		return "console/textnews/govInfo/addInfoWorkNotice";
	}
	
	/**
	 * 信息工作通知---新增通知
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "face=insertInfoWorkNotice")
	public void insertInfoWorkNotice(HttpServletRequest request, HttpServletResponse response){
		String workTitle = request.getParameter("workTitle");
		String workContent = request.getParameter("workContent");
		int isTop = parseInt(request,"isTop");
		int isPublish = parseInt(request,"isPublish");
		
		String msg = "";
		InfoWorkNotice infoNotice = new InfoWorkNotice();
		String id = UUID.randomUUID().toString().replace("-", "");
		infoNotice.setWorkId(id);
		infoNotice.setWorkTitle(workTitle);
		infoNotice.setWorkContent(workContent);
		
		Date nowTime = DateUtil.getCurrentDate();
		
		infoNotice.setWorkCreateDate(nowTime);
		infoNotice.setWorkCreateUserName(SystemContext.getUserName());
		infoNotice.setWorkPublish(isPublish);
		if(isPublish ==1)
		{
			infoNotice.setWorkPublishDate(nowTime);
		}
		if(isTop == 1){
			infoNotice.setWorkTop(nowTime);
		}
		boolean result = false;
		try {
			result = infoNoticeService.insert(infoNotice);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "新增成功！" : "新增失败！";
		this.writeMessage(response, msg);
	}
	
	/**
	 * 信息工作通知---删除通知
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "face=deleteInfoNotice")
	public void deleteInfoNotice(HttpServletRequest request, HttpServletResponse response) 
	{
		String workId = request.getParameter("workId");
		String msg="";
		boolean result = infoNoticeService.del(workId);
		msg = result  ? "删除成功！" : "删除失败！";
		this.writeMessage(response, msg);
	}
	
	/**
	 * 信息工作通知--修改页面首次加载
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "face=modifyInfoView")
	public String modifyInfoView(HttpServletRequest request,Model model) 
	{
		String workId = request.getParameter("workId");
		InfoWorkNotice infoNotice = infoNoticeService.query(workId);
		
		model.addAttribute("workTitle", infoNotice.getWorkTitle());
		model.addAttribute("workContent", infoNotice.getWorkContent());
		model.addAttribute("workTop",infoNotice.getWorkTop());
		model.addAttribute("workPublish",infoNotice.getWorkPublish());
		model.addAttribute("workId", infoNotice.getWorkId());
		return "console/textnews/govInfo/modifyInfoWorkNotice";
	}
	
	/**
	 * 信息工作通知---修改通知
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "face=modifyInfoWorkNotice")
	public void modifyInfoNotice(HttpServletRequest request,HttpServletResponse response)
	{
		String workId=request.getParameter("workId");
		String workTitle = request.getParameter("workTitle");
		String workContent = request.getParameter("workContent");
		int workTop = parseInt(request,"isTop");
		int workPublish = parseInt(request,"isPublish");
		Date editTime = DateUtil.getCurrentDate();
		
		InfoWorkNotice infoNotice = new InfoWorkNotice();
		infoNotice.setWorkId(workId);
		infoNotice.setWorkTitle(workTitle);
		infoNotice.setWorkContent(workContent);
		if(workTop == 0){
			infoNotice.setWorkTop(null);
		}else if(workTop == 1){
			infoNotice.setWorkTop(editTime);
		}
		//如果不发布则无发布时间
		if(workPublish == 1)
		{
			infoNotice.setWorkPublishDate(DateUtil.getCurrentDate());
		}
		infoNotice.setWorkPublish(workPublish);
		infoNotice.setWorkEditDate(editTime);
		String msg="";
		boolean result = infoNoticeService.edit(infoNotice);
		msg = result  ? "修改成功！" : "修改失败！";
		
		this.writeMessage(response, msg);
	}
	
	/**
	 * 信息工作通知---详情页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=infoNoticeDetail")
	public String infoNoticeDetail(HttpServletRequest request,Model model)
	{
		String workId = request.getParameter("workId"); 
		InfoWorkNotice infoNotice = infoNoticeService.query(workId);
		
		model.addAttribute("workTitle", infoNotice.getWorkTitle());
		model.addAttribute("workContent", infoNotice.getWorkContent());
		model.addAttribute("workCreateDate",infoNotice.getWorkCreateDateStr());
		model.addAttribute("workCreateUserName", infoNotice.getWorkCreateUserName());
		return "console/textnews/govInfo/infoNoticeDetail";
	}
	
	
	/**
	 * 信息工作通知展示页面---首次加载
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=infoWorkNoticeView")
	public String infoWorkNoticeView(Model model)
	{
		int pageIndex = 0;
		List<InfoWorkNotice> infoNoticeList = infoNoticeService
				.queryPublishInfoNotice("", null, null, pageIndex,
						SystemContext.getDefaultPageSize());
		model.addAttribute("infoNoticeList", infoNoticeList);
		
		return "console/textnews/govInfo/infoWorkNotice";
	}
	
	/**
	 * 信息工作通知展示页面---搜索、分页功能
	 * @param model
	 * @param request
	 * @param workTitle
	 * @param beginDate
	 * @param endDate
	 * @param pageIndex
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryInfoNotice")
	public String queryInfoNotice(Model model, HttpServletRequest request,
			@RequestParam("workTitle") String workTitle, 
			@RequestParam("beginDate") String beginDate, 
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex) throws UnsupportedEncodingException
	{
		if(beginDate.equals("") || endDate.equals(""))
		{
			Date start = parseStrDate(beginDate);
			Date end = parseEndDate(endDate);
			workTitle = URLDecoder.decode(workTitle, "utf-8");
			List<InfoWorkNotice> infoNoticeList = infoNoticeService.queryPublishInfoNotice(
					workTitle, start, end,pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("beginDate", start);
			model.addAttribute("endDate", end);
			model.addAttribute("workTitle", workTitle);
			model.addAttribute("infoNoticeList", infoNoticeList);
			
		}else if(beginDate.equals("") && endDate.equals(""))
		{
			workTitle = URLDecoder.decode(workTitle, "utf-8");
			List<InfoWorkNotice> infoNoticeList= 
					infoNoticeService.queryPublishInfoNotice(workTitle, null, null, pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("workTitle", workTitle);
			model.addAttribute("infoNoticeList", infoNoticeList);
		}else
		{
			Date start = parseStrDate(beginDate);
			Date end = parseEndDate(endDate);
			workTitle = URLDecoder.decode(workTitle, "utf-8");
			List<InfoWorkNotice> infoNoticeList = 
					infoNoticeService.queryPublishInfoNotice(workTitle, start, end, pageIndex, SystemContext.getDefaultPageSize());
			model.addAttribute("beginDate", start);
			model.addAttribute("endDate", end);
			model.addAttribute("workTitle", workTitle);
			model.addAttribute("infoNoticeList",infoNoticeList);
		}
		return "console/textnews/govInfo/infoWorkNotice";
	}
	/**
	 * 置顶功能
	 * @param request
	 * @param model
	 */
	@RequestMapping(params = "face=topInfoNotice")
	public void topWorkNotice(HttpServletRequest request,
			HttpServletResponse response,Model model)
	{
		String workId = request.getParameter("workId");
		Date nowTime = DateUtil.getCurrentDate();
		
		InfoWorkNotice infoNotice = new InfoWorkNotice();
		infoNotice.setWorkId(workId);
		infoNotice.setWorkTop(nowTime);
		
		String msg="";
		boolean result = infoNoticeService.top(infoNotice);
		msg = result  ? "修改成功！" : "修改失败！";
		
		this.writeMessage(response, msg);
	}
	
	/**
	 * 撤销置顶功能
	 * @param request
	 * @param response
	 * @param model
	 */

	@RequestMapping(params = "face=revoketop")
	public void revoketop(HttpServletRequest request,
			HttpServletResponse response,Model model)
	{
		String workId = request.getParameter("workId");
		
		InfoWorkNotice infoNotice = new InfoWorkNotice();
		infoNotice.setWorkId(workId);
		infoNotice.setWorkTop(null);
		
		String msg="";
		boolean result = infoNoticeService.top(infoNotice);
		msg = result  ? "修改成功！" : "修改失败！";
		
		this.writeMessage(response, msg);
	}
	
}
