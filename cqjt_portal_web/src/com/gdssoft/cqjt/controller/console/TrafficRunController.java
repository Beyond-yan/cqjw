package com.gdssoft.cqjt.controller.console;

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

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.TrafficRunVo;
import com.gdssoft.cqjt.service.TrafficRunService;
import com.gdssoft.cqjt.util.PageUtils;
/**
 * 交通运行信息
 * @author  gyf 20140612
 * @return
 */
@Controller
@RequestMapping("/console/trafficRun.xhtml")
public class TrafficRunController extends BaseController {
	@Autowired
	@Resource(name = "trafficRunService")
	private TrafficRunService trafficRunService;
	
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
		String runnTitle = request.getParameter("runnTitle");
		Date beginDate = DateUtil.dateParse(request.getParameter("beginDate"),DateUtil.DATE_FORMAT_YMD);
		Date endDate = parseEndDate(request,"endDate");
		System.out.println(beginDate+"-----------"+endDate);
		int pageIndex =  parseInt(request,"pageIndex");
		int limitCount =  parseInt(request,"limitCount");//每页显示条数
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("runnTitle", runnTitle);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		
		int count = trafficRunService.queryCount(map);
		PageUtils page = new PageUtils(count, pageIndex, limitCount);
		
		map.put("beginLimit", page.getLimitBegin());
		map.put("endLimit", page.getLimitEnd());
		List<TrafficRunVo> runVoList = trafficRunService.queryPageList(map);
		
		model.addAttribute("runVoList", runVoList);
		model.addAttribute("page", page);
		model.addAttribute("runnTitle", runnTitle);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		return "console/sysMgt/trafficRun/list";
	}
	
	
	/**
	 * 新增加载页面
	 * @return
	 */
	@RequestMapping(params = "face=addPage")
	public String addPage(Model model) {
		return "console/sysMgt/trafficRun/add";
	}
	
	/**
	 * 新增信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		String runnTitle = request.getParameter("runnTitle");
		String runnContent = request.getParameter("runnContent");
		int isTop = parseInt(request,"isTop");
		
		String msg = "";
		TrafficRunVo runVo = new TrafficRunVo();
		String id = UUID.randomUUID().toString().replace("-", "");
		runVo.setRunnId(id);
		runVo.setRunnTitle(runnTitle);
		runVo.setRunnContent(runnContent);
		Date nowTime = DateUtil.getCurrentDate();
		runVo.setRunnCreateDate(nowTime);
		runVo.setRunnCreateUserName(SystemContext.getUserName());
		if(isTop == 1){
			runVo.setRunnTop(nowTime);
		}
		boolean result = false;
		try {
			result = trafficRunService.insert(runVo);
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
		String runnId = request.getParameter("runnId");
		TrafficRunVo runVo = trafficRunService.query(runnId);
		model.addAttribute("runVo", runVo);
		return "console/sysMgt/trafficRun/edit";
	}
	
	/**
	 * 编辑信息
	 * @param fileId
	 * @param response
	 */
	@RequestMapping(params = "action=edit")
	public void edit(HttpServletRequest request, HttpServletResponse response){
		String runnId = request.getParameter("runnId");
		String runnTitle = request.getParameter("runnTitle");
		String runnContent = request.getParameter("runnContent");
		int isTop = parseInt(request,"isTop");
		
		String msg = "";
		TrafficRunVo runVo = new TrafficRunVo();
		runVo.setRunnId(runnId);
		runVo.setRunnTitle(runnTitle);
		runVo.setRunnContent(runnContent);
		Date nowTime = DateUtil.getCurrentDate();
		if(isTop == 1){
			runVo.setRunnTop(nowTime);
		} else {
			runVo.setRunnTop(null);
		}
		runVo.setRunnEditDate(nowTime);
		boolean result = false;
		try {
			result = trafficRunService.edit(runVo);
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
		String runnId = request.getParameter("runnId");
		String  msg = "";
		boolean result = false;
		try {
			result = trafficRunService.del(runnId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		msg = result  ? "删除成功！" : "删除失败！";
		this.writeMessage(response, msg);
	}
	
}



