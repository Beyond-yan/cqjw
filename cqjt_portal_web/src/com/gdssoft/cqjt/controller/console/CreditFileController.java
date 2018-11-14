package com.gdssoft.cqjt.controller.console;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Menu;
import com.gdssoft.cqjt.service.MenuService;

@Controller
@RequestMapping("/console/creditFile.xhtml")
public class CreditFileController extends BaseController {
	
	@Autowired
	@Resource(name = "menuServiceImpl")
	private MenuService menuServiceImpl;

	@RequestMapping(params = "face=creditFileList")
	public String getCreditFileList(Model model) {
		
		return "console/creditFile/listCreditFile";
	}
	@RequestMapping(params = "face=addCreditFile")
	public String addCreditFile(Model model) {
		
		return "console/creditFile/addCreditFile";
	}
	
	
	
	@RequestMapping(params = "face=menu")
	public String showMenu(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		model = fillCommonData(model);

		String menuId = request.getParameter("menuId");
		Menu thisMenu = menuServiceImpl.getMenu(menuId);
		model.addAttribute("thisMenu", thisMenu);
		
		return "console/frame/index";
		
	}
	
	/**
	 * 
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(params="face=childMenus", method = RequestMethod.POST)
	public List<Menu> getChildMenus(HttpServletRequest request) {
		String parentID = request.getParameter("parentId");
		List<Menu> listm = menuServiceImpl.getMenus(SystemContext.getRoleID(), parentID);
		return listm;
	}
	
	private Model fillCommonData(Model model) {
		//String strRoleDesc = SystemContext.getRoleDesc();
		//String curDate = DateFormat.getDateInstance(DateFormat.DEFAULT).format(SystemContext.getSystemDate());
		List<Menu> topMenu = menuServiceImpl.getTopMenus(SystemContext.getRoleID(), "0");
		model.addAttribute("userNo", SystemContext.getUserNO());
		model.addAttribute("userName", SystemContext.getUserName());
		//model.addAttribute("roleDesc", strRoleDesc);
		//model.addAttribute("curDate", curDate);
		model.addAttribute("topMenu", topMenu);
		return model;
	}

	// modified by Cube @130724
	// added the user id info
	@RequestMapping(params = "face=top")
	public String top(HttpServletRequest request, HttpServletResponse response,
			Model model) {
		String strUserNO = SystemContext.getUserNO();
		String strRoleDesc = SystemContext.getRoleDesc();
		model.addAttribute("userNO", strUserNO);
		model.addAttribute("roleDesc", strRoleDesc);
		return "console/frame/top";
	}

	@RequestMapping(params = "face=left")
	public String left(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return "console/frame/left";
	}

	@RequestMapping(params = "face=main")
	public String getMain(Model model) {
		return "console/frame/right";
	}
	/*
	@RequestMapping(params = "face=workPlaceField")
	public String jumpToWorkPlace(Model model) {
		return "workspace/workPlaceField";
	}

	@RequestMapping(params = "face=publicPlaceField")
	public String jumpToPublicPlace(Model model) {
		return "publicspace/publicPlaceField";
	}

	@RequestMapping(params = "face=setUpField")
	public String jumpToSetUpField(Model model) {
		return "managespace/setUpField";
	}
	*/

	@RequestMapping(params = "face=leftMenu")
	public String leftMenu(HttpServletRequest request,
			HttpServletResponse response, Model model) {
//		String parentID = request.getParameter("0");
		model.addAttribute("parentID", "0");
		return "console/frame/leftMenu";
	}
}
