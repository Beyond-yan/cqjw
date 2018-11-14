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
import com.gdssoft.cqjt.service.DepartmentService;

/**
 * 栏目部門管理
 * 
 * @author wl 20140704
 * @return
 */
@Controller
@RequestMapping("/console/columnDept.xhtml")
public class ColumnDeptController extends BaseController {
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;

	/**
	 * 页面首次加载列表数据
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=getColumnDeptView")
	public String getColumnList(Model model, HttpServletRequest request) {
		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<Department> deptCategoryList = departmentService.getDeptCategory(
				"", "", pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("page", deptCategoryList);
		return "console/sysMgt/column/columnDeptManage";
	}

	/**
	 * 查询部门名称,栏位
	 * 
	 * @author H2602965
	 * @param deptCategory
	 * @param response
	 */
	@RequestMapping(params = "action=queryColumnDeptList")
	public void queryColumnList(
			@RequestParam("deptCategory") String deptCategory,
			HttpServletResponse response, HttpServletRequest request) {
		logger.debug("deptCategory===============" + deptCategory);
		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<Department> deptCategoryList = departmentService
				.getDeptCategory(deptCategory, "", pageIndex,
						SystemContext.getDefaultPageSize());
		this.writeResult(response, deptCategoryList);
	}

	/**
	 * 部门按条件查询
	 * 
	 * @param deptName
	 * @param deptCategory
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=queryDeptInfoView")
	public void queryDeptInfo(@RequestParam("deptName") String deptName,
			@RequestParam("deptCategory") String deptCategory,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) {
		logger.debug("输入参数:deptName=" + deptName + " ,deptCategory="
				+ deptCategory + " ,pageIndex=" + pageIndex);
		List<Department> departmentInfoList = departmentService
				.getDeptCategory(deptCategory, deptName, pageIndex,
						SystemContext.getDefaultPageSize());

		this.writeResult(response, departmentInfoList);
	}

	/**
	 * 根据部门Id查询栏目
	 * 
	 * @param deptId
	 * @param response
	 */
	@RequestMapping(params = "face=queryCategoryDeptList")
	public String queryCategoryDeptList(@RequestParam("deptID") String deptId,
			Model model) {
		logger.debug("deptId===============" + deptId);
		List<Department> categoryList = departmentService
				.getCategoryName(deptId);
		// PageBean page = new PageBean(categoryList, 1, categoryList.size());
		model.addAttribute("page", categoryList);
		model.addAttribute("deptId", deptId);
		return "console/sysMgt/column/columnDeptMgtList";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addColumnDept")
	public String addFile(Model model, @RequestParam("deptId") Integer deptId,
			HttpServletRequest request) {

		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}
		List<TextCategory> TextCategorys = departmentService.getTextCategorys();
		List<Department> deptCategoryList = departmentService.getDeptCategory(
				"", "", pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("TextCategorys", TextCategorys);
		model.addAttribute("dept", deptCategoryList);
		model.addAttribute("deptDetail", departmentService.getDeptName(deptId));
		logger.debug("deptId====" + deptId);
		return "console/sysMgt/column/columnDeptManageAdd";
	}

	/**
	 * 删除方法
	 * 
	 * @author H2602965
	 * @param categoryId
	 * @param response
	 */
	@RequestMapping(params = "action=delete")
	public void delete(@RequestParam("categoryId") Long categoryId,
			@RequestParam("deptID") Integer deptId, HttpServletResponse response) {
		String msg = "";
		logger.debug("categoryId=" + categoryId);
		logger.debug("deptId====" + deptId);
		try {
			Department department = new Department();
			department.setIsDel("1");
			department.setCategoryId(categoryId);
			department.setDeptID(deptId);
			departmentService.delete(department);
			msg = deptId.toString();
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}

	/**
	 * 保存更新方法
	 * 
	 * @author H2602965
	 * @param department
	 * @param response
	 */
	@RequestMapping(params = "action=save", method = RequestMethod.POST)
	public void save(@ModelAttribute("department") Department department,
			HttpServletResponse response) {
		logger.debug("dcId====:" + department.getDcId());
		logger.debug("deptID==" + department.getDeptID());
		String deptId = department.getDeptID().toString();
		String msg = "";
		if (department.getDcId() == null) {// 新增
			if (null == departmentService.getCategoryDeptDetail(
					department.getCategoryId(), department.getDeptID())) {
				department.setIsDel("0");
				departmentService.insertColumn(department);

				this.writeMessage(response, deptId);
			} else {
				msg = "error";
				this.writeMessage(response, msg);
			}

		} else {// 更新方法
			if (null == departmentService.getCategoryDeptDetail(
					department.getCategoryId(), department.getDeptID())) {
				departmentService.update(department);
				this.writeMessage(response, deptId);
			} else {
				msg = "error";
				this.writeMessage(response, msg);
			}

		}
	}

	@RequestMapping(params = "action=saveColDeptRelation")
	public void saveColDeptRelation(
			@RequestParam("categoryId") Long categoryId,
			@RequestParam("deptId") Integer deptId, HttpServletResponse response) {
		Department department = new Department();
		// 设置对应的栏目id
		department.setCategoryId(categoryId);
		// 设置对应的部门id
		department.setDeptID(deptId);
		// 设置is_del
		department.setIsDel("0");
		// 新增
		departmentService.insertColumn(department);

		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=editColumnDept")
	public String editColumnDept(Model model,
			@RequestParam("categoryId") Long categoryId,
			@RequestParam("deptID") Integer deptId, HttpServletRequest request) {
		logger.debug("deptId======" + deptId);
		logger.debug("categoryId===" + categoryId);
		List<TextCategory> TextCategorys = departmentService.getTextCategorys();
		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<Department> deptCategoryList = departmentService.getDeptCategory(
				"", "", pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("categoryDeptDetail",
				departmentService.getCategoryDeptDetail(categoryId, deptId));
		model.addAttribute("TextCategorys", TextCategorys);
		model.addAttribute("dept", deptCategoryList);
		return "console/sysMgt/column/columnDeptManageEdit";
	}

	/**
	 * 根据部门ID查询未对应部门的数量
	 * 
	 * @author H2602965
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=listColunmDeptInfo")
	public String listGovInfoForPub(Model model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String deptId = request.getParameter("deptId");
		String columnName = request.getParameter("columnName");
		String startDate = request.getParameter("entryDateS");
		String endDate = request.getParameter("entryDateE");
		if (StringUtils.isBlank(columnName))
			columnName = "";
		columnName = URLDecoder.decode(columnName, "utf-8");
		logger.debug("输入参数：deptId=" + deptId + ", columnName=" + columnName
				+ " , startDate=" + startDate + ", entryDateE=" + endDate);

		Date start = null;
		Date end = null;
		if (startDate != null && !"".equals(startDate)) {
			String startTime = startDate;
			startTime += " 00:00:01";
			logger.debug("startTime===" + startTime);
			start = DateUtil.parseDate(startTime);
		}
		if (endDate != null && !"".equals(endDate)) {
			String endTime = endDate;
			endTime += " 23:59:59";
			end = DateUtil.parseDate(endTime);
		}

		logger.debug("输入参数：deptId=" + deptId + ",columnName=" + columnName
				+ ", start=" + start + ", end=" + end);

		List<Department> list = departmentService.getColunmDeptUnInfo(deptId,
				columnName, start, end);

		logger.debug("根据部门Id查询未对应部门数量：" + list.size());

		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("deptId", deptId);
		model.addAttribute("columnName", columnName);
		model.addAttribute("paginations", list);

		return "console/sysMgt/column/columnDeptMgtAdd";
	}
}
