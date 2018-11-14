package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.InfoWorkNotice;
import com.gdssoft.cqjt.pojo.SubscribeVo;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.service.SubscribeSerivce;
import com.gdssoft.cqjt.util.PageUtils;

@Controller
@RequestMapping("/console/subscribe.xhtml")
public class SubscribeController extends BaseController{
	@Autowired
	@Resource(name="subscribeSerivce")
	private SubscribeSerivce subscribeSerivce;
	
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
	 * 约稿通知列表显示-发稿人
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=subscribeVoListView")
	public String subscribeVoList(Model model, HttpServletRequest request) {
		int pageIndex = parseInt(request.getParameter("pageIndex"),0);
		int pageSize = parseInt(request.getParameter("pageSize"),20);
		
		//约稿通知
		Map<String, Object> map = new HashMap<String, Object>();
		int count = subscribeSerivce.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, pageSize);
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<SubscribeVo> subscribeVoList = subscribeSerivce.queryPageList(map);
		List<UserDetail> userList = subscribeSerivce.queryUsers();
		model.addAttribute("userList",userList);
		model.addAttribute("subscribeVoList", subscribeVoList);
		model.addAttribute("visiterNum", this.getCount());
		return "console/sysMgt/subscribe/list";
	}
	
	
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addPage")
	public String addPage(Model model) {
		return "console/sysMgt/subscribe/add";
	}
	
	/**
	 * 部门选择
	 * 
	 */
	@RequestMapping(params = "face=deptList")
	public String deptList(Model model, HttpServletRequest request,HttpServletResponse response){
		List<Department> deptCategoryList = subscribeSerivce.queryDeptCategory();
		List<Department> deptList = subscribeSerivce.queryDept();
	//	System.out.println(subsRecvDeptName);
		//request.setAttribute("subsRecvDeptName", subsRecvDeptName);
		try {
			String subsRecvDeptName = request.getParameter("subsRecvDeptName");
			System.out.println(subsRecvDeptName);
			if(StringUtils.isNotBlank(subsRecvDeptName)){
				subsRecvDeptName = URLDecoder.decode(subsRecvDeptName, "utf-8");
				model.addAttribute("subsRecvDeptName", subsRecvDeptName);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		model.addAttribute("deptCategoryList", deptCategoryList);
		model.addAttribute("deptList", deptList);
		return "console/sysMgt/subscribe/arrPermission";
	}
	
	
	/**
	 * 新增信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		System.out.println("=======******************************************************");
		String subsTitle = request.getParameter("subsTitle");
		String subsContent = request.getParameter("subsContent");
		int isTop = parseInt(request,"isTop");
		String deptName = request.getParameter("deptName");
		int isPublish = parseInt(request,"isPublish");
		
		System.out.println(SystemContext.getUserId());
		System.out.println("=======******************************************************");
		String msg = "";
		SubscribeVo subscribeVo = new SubscribeVo();
		String id = UUID.randomUUID().toString().replace("-", "");
		subscribeVo.setSubsId(id);
		subscribeVo.setSubsTitle(subsTitle);
		subscribeVo.setSubsContent(subsContent);
		subscribeVo.setSubsRecvDeptName(deptName);
		subscribeVo.setSubssendUserId(SystemContext.getUserId());
		subscribeVo.setSubsPublish(isPublish);
		
		Date nowTime = DateUtil.getCurrentDate();
		subscribeVo.setSubsCreateDate(nowTime);
		if(isTop == 1){
			subscribeVo.setSubsTop(nowTime);
		}
		boolean result = false;
		try {
			result = subscribeSerivce.insert(subscribeVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "新增成功！" : "新增失败！";
		this.writeMessage(response, msg);
	}
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=editPage")
	public String editPage(Model model,HttpServletRequest request) {
		String subsId = request.getParameter("subsId");
		System.out.println(subsId);
		SubscribeVo subscribeVo = subscribeSerivce.query(subsId);
		model.addAttribute("subscribeVo", subscribeVo);
		return "console/sysMgt/subscribe/edit";
	}
	/**
	 * 编辑信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=del")
	public void del(HttpServletRequest request, HttpServletResponse response){
		String subsId = request.getParameter("subsId");
		String  msg = "";
		boolean result = false;
		try {
			result = subscribeSerivce.del(subsId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "删除成功！" : "删除失败！";
		this.writeMessage(response, msg);
	}
	
	
	/**
	 * 编辑信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=edit")
	public void edit(HttpServletRequest request, HttpServletResponse response){
		String subsId = request.getParameter("subsId");
		String subsTitle = request.getParameter("subsTitle");
		String subsContent = request.getParameter("subsContent");
		String deptName = request.getParameter("deptName");
		int isTop = parseInt(request,"isTop");
		int isPublish = parseInt(request,"isPublish");
		
		System.out.println(deptName);
		
		String msg = "";
		SubscribeVo subscribeVo = new SubscribeVo();
		subscribeVo.setSubsId(subsId);
		subscribeVo.setSubsTitle(subsTitle);
		subscribeVo.setSubsContent(subsContent);
		subscribeVo.setSubsRecvDeptName(deptName);
		subscribeVo.setSubsPublish(isPublish);
		Date nowTime = DateUtil.getCurrentDate();
		if(isTop == 1){
			subscribeVo.setSubsTop(nowTime);
		} else {
			subscribeVo.setSubsTop(null);
		}
		subscribeVo.setSubsEditDate(nowTime);
		
		
		boolean result = false;
		try {
			result = subscribeSerivce.edit(subscribeVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "编辑成功！" : "编辑失败！";
		this.writeMessage(response, msg);
	}
	/**
	 * 页面首次加载列表数据
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=queryPageList")
	public String queryPageList(Model model,HttpServletRequest request){
		String subsTitle = "";
		try {
			String title = request.getParameter("subsTitle");
			System.out.println(title);
			if(StringUtils.isNotBlank(title)){
				subsTitle = URLDecoder.decode(title, "utf-8");
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
		map.put("subsTitle", subsTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		
		int count = subscribeSerivce.queryCount(map);
		
		PageUtils page = new PageUtils(count, pageIndex, limitCount);
		
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<SubscribeVo> subscribeVoList = subscribeSerivce.queryPageList(map);
		List<UserDetail> userList = subscribeSerivce.queryUsers();
		model.addAttribute("userList",userList);
		model.addAttribute("subscribeVoList", subscribeVoList);
		model.addAttribute("page", page);
		model.addAttribute("subsTitle", subsTitle);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return "console/sysMgt/subscribe/list";
	}
	
	/**
	 * 约稿通知列表显示-接收人
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=subsVoListView")
	public String subsVoList(Model model, HttpServletRequest request) {
		String subsTitle = "";
		try {
			String title = request.getParameter("subsTitle");
			System.out.println(title);
			if(StringUtils.isNotBlank(title)){
				subsTitle = URLDecoder.decode(title, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Date beginDate = DateUtil.dateParse(request.getParameter("beginDate"),DateUtil.DATE_FORMAT_YMD);
		Date endDate = parseEndDate(request,"endDate");
		System.out.println(beginDate+"-----------"+endDate);
		int pageIndex =  parseInt(request,"pageIndex");
		int limitCount =  parseInt(request,"limitCount");//每页显示条数
		UserDetail user = subscribeSerivce.getUserEditInfo(SystemContext.getUserId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subsTitle", subsTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("subsRecvDeptName", user.getDeptName());
		map.put("subsPublish",0);
		
		int count = subscribeSerivce.queryCount(map);
		
		PageUtils page = new PageUtils(count, pageIndex, limitCount);
		
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<SubscribeVo> subscribeVoList = subscribeSerivce.queryPageList(map);
		List<UserDetail> userList = subscribeSerivce.queryUsers();
		model.addAttribute("userList",userList);
		model.addAttribute("subscribeVoList", subscribeVoList);
		model.addAttribute("page", page);
		model.addAttribute("subsTitle", subsTitle);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		
		
		return "console/sysMgt/subscribe/queryList";
	}
	
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
	
	/**
	 * 约稿通知查看详情
	 * 
	 * @param model
	 * @param newsId
	 * @return
	 */
	@RequestMapping(params = "face=subscribeVoDetails")
	public String specialVoDetails(Model model, @RequestParam("subsId") String subsId) {

		SubscribeVo subsVoList = subscribeSerivce.query(subsId);
		List<UserDetail> userList = subscribeSerivce.queryUsers();
		model.addAttribute("userList",userList);
		model.addAttribute("subsVoList", subsVoList);

		return "console/sysMgt/subscribe/details";
	}
	/**
	 * 置顶功能
	 * @param request
	 * @param model
	 */
	@RequestMapping(params = "face=topSubscribeVo")
	public void topWorkNotice(HttpServletRequest request,
			HttpServletResponse response,Model model)
	{
		String subsId = request.getParameter("subsId");
		Date nowTime = DateUtil.getCurrentDate();
		
		SubscribeVo subscribe = new SubscribeVo();
		subscribe.setSubsId(subsId);
		subscribe.setSubsTop(nowTime);
		
		String msg="";
		boolean result = subscribeSerivce.top(subscribe);
		msg = result  ? "修改成功！" : "修改失败！";
		
		this.writeMessage(response, msg);
	}
	
	/**
	 * 撤销置顶
	 */
	@RequestMapping(params = "face=revoketop")
	public void revoketop(HttpServletRequest request,
			HttpServletResponse response,Model model)
	{
		String subsId = request.getParameter("subsId");
		
		SubscribeVo subscribe = new SubscribeVo();
		subscribe.setSubsId(subsId);
		subscribe.setSubsTop(null);
		
		String msg="";
		boolean result = subscribeSerivce.top(subscribe);
		msg = result  ? "修改成功！" : "修改失败！";
		
		this.writeMessage(response, msg);
	}
}
