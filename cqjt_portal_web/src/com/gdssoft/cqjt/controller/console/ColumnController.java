package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.service.ColumnService;

/**
 * 栏目管理
 * 
 * @author gyf 20140703
 * @return
 */
@Controller
@RequestMapping("/console/column.xhtml")
public class ColumnController extends BaseController {
	@Autowired
	@Resource(name = "columnService")
	private ColumnService columnService;

	/**
	 * 页面首次加载列表数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getColumnList")
	public String getColumnList(Model model, HttpServletRequest request) {
		Date start = this.getStartDate("");
		Date end = this.getEndDate("");
		logger.debug(start + "," + end);
		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<TextCategory> textCategoryList = columnService.getAllColumn("",
				null, null, "0", pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("paginations", textCategoryList);
		return "console/sysMgt/column/columnManagerList";
	}

	/**
	 * 用于查询
	 * 
	 * @param fileName
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=searchColumnList")
	public String searchFileList(
			@RequestParam("categoryName") String categoryName,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		categoryName = URLDecoder.decode(categoryName, "utf-8");
		Date start = null;
		Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			String startTime = startDate;
			startTime += " 00:00:01";
			logger.debug("startTime===" + startTime);
			start = DateUtil.parseDate(startTime);
		}/*
		 * else{ Calendar calendar = Calendar.getInstance(); //默认取当前日期时间开始时间
		 * start = calendar.getTime(); arg0 += " 00:00:01"; start =
		 * DateUtil.dateParse(arg0); }
		 */
		if (endDate != null && !"".equals(endDate)) {
			String endTime = endDate;
			endTime += " 23:59:59";
			end = DateUtil.parseDate(endTime);
		}/*
		 * else{
		 * 
		 * end = this.getEndDate(endDate); }
		 */
		logger.debug("start==" + start + "," + "end==" + end);
		List<TextCategory> textCategoryList = columnService.getAllColumn(
				categoryName, start, end, "0", pageIndex,
				SystemContext.getDefaultPageSize());
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("categoryName", categoryName);
		model.addAttribute("paginations", textCategoryList);
		return "console/sysMgt/column/columnManagerList";
	}

	/**
	 * 新增加载页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "face=addColumn")
	public String addFile(Model model) {
		String createBy = SystemContext.getUserName();
		String createDate = DateUtil.getCurrentDateStr();
		logger.debug("创建人" + createBy + "时间" + createDate);
		model.addAttribute("createBy", createBy);
		model.addAttribute("createDate", createDate);
		// 加载上一级栏目

		return "console/sysMgt/column/columnManagerAdd";
	}

	/**
	 * 修改加载页面
	 * 
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(params = "face=editColumn")
	public String editFile(Model model,
			@RequestParam("categoryId") Long categoryId) {
		logger.debug("categoryId=" + categoryId);
		model.addAttribute("column", columnService.getColumnById(categoryId));
		logger.debug("栏目名称："
				+ columnService.getColumnById(categoryId).getCategoryName());
		// 加载上一级栏目

		return "console/sysMgt/column/columnManagerEdit";
	}

	/**
	 * 逻辑删除
	 * 
	 * @param categoryId
	 * @param response
	 */
	@RequestMapping(params = "action=deleteColumn")
	public void deleteFile(@RequestParam("categoryId") Long categoryId,
			HttpServletResponse response) {
		String msg = "";
		logger.debug("categoryId=" + categoryId);
		try {
			TextCategory textCategory = new TextCategory();
			textCategory.setIsDel("1");
			textCategory.setCategoryId(categoryId);
			columnService.updateColumn(textCategory);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}

	/**
	 * 保存
	 * 
	 * @param textCategory
	 * @param response
	 */
	@RequestMapping(params = "action=save", method = RequestMethod.POST)
	public void save(@ModelAttribute("textCategory") TextCategory textCategory,
			HttpServletResponse response) {
		if (null == textCategory.getCategoryId()) {// 新增
			textCategory.setIsDel("0");
			columnService.insertColumn(textCategory);
			this.writeMessage(response, "保存成功！");
		} else {// 修改
			columnService.updateTextCategoryById(textCategory);
			this.writeMessage(response, "保存成功！");
		}
	}

	/**
	 * 首页模块查询 对应栏目关系
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "face=getColumnSelect")
	public String getColumnSelect(Model model, HttpServletRequest request) {
		return "console/sysMgt/column/columnSelect4index";
	}

	/**
	 * 
	 * @param categoryId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=queryCategoryUnSelectList")
	public String queryCategoryDeptList(@RequestParam("id") String id,
			Model model) {
		List<TextCategory> list = columnService.getColunmSelect(id);
		model.addAttribute("page", list);
		model.addAttribute("id", id);
		return "console/sysMgt/column/columnSelect4IndexList";
	}

	/**
	 * 根据ID查询未选定的栏目的数量
	 * 
	 * @author H2602965
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=getCategoryUnSelectList")
	public String listUnSelectInfo(Model model, HttpServletRequest request,@RequestParam("id") Long id)
			throws UnsupportedEncodingException {
		String columnName = request.getParameter("columnName");
		String startDate = request.getParameter("entryDateS");
		String endDate = request.getParameter("entryDateE");
		if (StringUtils.isBlank(columnName))
			columnName = "";
		columnName = URLDecoder.decode(columnName, "utf-8");

		Date start = null;
		Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			String startTime = startDate;
			startTime += " 00:00:01";
			start = DateUtil.parseDate(startTime);
		}
		if (endDate != null && !"".equals(endDate)) {
			String endTime = endDate;
			endTime += " 23:59:59";
			end = DateUtil.parseDate(endTime);
		}

		List<TextCategory> list = columnService.getColunmUnSelect(id,
				columnName, start, end);
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("id", id);
		model.addAttribute("columnName", columnName);
		model.addAttribute("paginations", list);
		return "console/sysMgt/column/columnSelect4IndexAdd";
	}
	
	@RequestMapping(params = "action=saveSelectInfo")
	public void saveSelectInfo(
			@RequestParam("categoryId") Long categoryId,
			@RequestParam("id") Long id, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		try {
			TextCategory  textCategory= new TextCategory();
			// 设置对应的栏目id
			textCategory.setCategoryId(categoryId);
			// 设置demoID
			textCategory.setId(id);
			columnService.insertSelectColumn(textCategory);
			this.writeMessage(response,id.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params = "action=delete")
	public void delete(@RequestParam("categoryId") Long categoryId,
			@RequestParam("id") Long id, HttpServletResponse response) {
		String msg = "";
		try {
			TextCategory  textCategory= new TextCategory();
			// 设置对应的栏目id
			textCategory.setCategoryId(categoryId);
			textCategory.setId(id);
			columnService.deleteSelect(textCategory);
			msg = id.toString();
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}
}
