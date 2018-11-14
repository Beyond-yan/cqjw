package com.gdssoft.cqjt.controller.console;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.CategoryRelation;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.Role;
import com.gdssoft.cqjt.pojo.RolePermission;
import com.gdssoft.cqjt.pojo.TextCategory;
import com.gdssoft.cqjt.pojo.UserDetail;
import com.gdssoft.cqjt.pojo.UserRole;
import com.gdssoft.cqjt.pojo.cms.Program;
import com.gdssoft.cqjt.service.CheckStandardService;
import com.gdssoft.cqjt.service.ColumnService;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.RoleService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.UserRoleService;
import com.gdssoft.cqjt.service.cms.ProgramService;

/**
 * @author F3219058
 * 
 */

@Controller
@RequestMapping("/console/sysMgt.xhtml")
public class SysMgtController extends BaseController {

	@Autowired
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userService;

	@Autowired
	@Resource(name = "roleServiceImpl")
	private RoleService roleServiceImpl;

	// 新增service wl 2014.05.30
	@Autowired
	@Resource(name = "userRoleServiceImpl")
	private UserRoleService userRoleService;

	// 新增service wl 2014.05.30
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	@Autowired
	@Resource(name = "checkStandardServiceImpl")
	private CheckStandardService checkStandardService;
	@Autowired
	@Resource(name = "columnService")
	private ColumnService columnService;
	@Autowired
	@Resource(name = "programService")
	private ProgramService programService;

	/*
	 * @RequestMapping(params = "face=userMgt") public String
	 * userMgt(HttpServletRequest request, HttpServletResponse response, Model
	 * model) {
	 * 
	 * String userNO = request.getParameter("userNO"); String role =
	 * request.getParameter("role"); String dept = request.getParameter("dept");
	 * 
	 * String strCurpage = request.getParameter("curpage"); int curpage; if
	 * (null == strCurpage || "".equals(strCurpage.trim())) curpage = 1; else
	 * curpage = Integer.parseInt(strCurpage);
	 * 
	 * model.addAttribute("userNO", userNO); model.addAttribute("roleID", role);
	 * model.addAttribute("infoID", dept); Map<String, String> map = new
	 * HashMap<String, String>(); map.put("userNO", userNO); map.put("roleID",
	 * role); map.put("infoID", dept);
	 * 
	 * int cnt = userService.getUserCount(map); model.addAttribute("count",
	 * cnt);
	 * 
	 * List<UserDetail> userList = userService.SelectUser(map, curpage,
	 * pageSize); model.addAttribute("userList", userList);
	 * model.addAttribute("pagesize", pageSize); model.addAttribute("curpage",
	 * curpage);
	 * 
	 * List<SelectBean> deptList = utilService.getBaseDataList("7", true);
	 * model.addAttribute("deptList", deptList);
	 * 
	 * Map<String, String> mapPara = new HashMap<String, String>();
	 * mapPara.put("valueCol", "role_id"); mapPara.put("textCol",
	 * "description"); mapPara.put("tableName", "traffic_role");
	 * List<SelectBean> roleList = utilService.getGeneralDataList(mapPara,
	 * true); model.addAttribute("roleList", roleList);
	 * 
	 * return "console/sysMgt/userMgt"; }
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(params = "face=deleteUser") public boolean
	 * deleteUser(HttpServletRequest request) { boolean flag = false; String
	 * userID = request.getParameter("userID"); flag =
	 * userService.DeleteUser(userID); return flag; }
	 */
	/*
	 * @RequestMapping(params = "face=addUser") public String
	 * addUser(HttpServletRequest request, HttpServletResponse response, Model
	 * model) {
	 * 
	 * String userNo = request.getParameter("userNo"); String title; if (null ==
	 * userNo || userNo.equalsIgnoreCase("")) { title = "新增用户";
	 * model.addAttribute("type", "add"); } else { title = "编辑用户"; UserDetail
	 * user = userService.getUserByCode(userNo).get(0);
	 * model.addAttribute("userNo", userNo); model.addAttribute("roleID",
	 * user.getRoleID()); model.addAttribute("infoID", user.getDeptID());
	 * model.addAttribute("userName", user.getUserName());
	 * model.addAttribute("type", "edit"); } model.addAttribute("title", title);
	 * List<SelectBean> deptList = utilService.getBaseDataList("7", true);
	 * model.addAttribute("deptList", deptList);
	 * 
	 * Map<String, String> mapPara = new HashMap<String, String>();
	 * mapPara.put("valueCol", "role_id"); mapPara.put("textCol",
	 * "description"); mapPara.put("tableName", "traffic_role");
	 * List<SelectBean> roleList = utilService.getGeneralDataList(mapPara,
	 * true); model.addAttribute("roleList", roleList);
	 * 
	 * return "console/sysMgt/addUser"; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(params = "face=saveUser") public Map<String, String>
	 * saveUser(HttpServletRequest request) { Map<String, String> map = new
	 * HashMap<String, String>();
	 * 
	 * String userNO = request.getParameter("userNO").trim(); String dept =
	 * request.getParameter("dept").trim(); String password =
	 * request.getParameter("password").trim(); String role =
	 * request.getParameter("role").trim(); String name =
	 * request.getParameter("name").trim();
	 * 
	 * if (userNO.equals("")) { map.put("result", "0"); map.put("msg",
	 * "用户名不能为空！"); return map; } if (dept.equals("")) { map.put("result", "0");
	 * map.put("msg", "请选择部门！"); return map; } if (role.equals("")) {
	 * map.put("result", "0"); map.put("msg", "请选择角色！"); return map; } if
	 * (name.equals("")) { map.put("result", "0"); map.put("msg", "姓名不能为空！");
	 * return map; }
	 * 
	 * String type = request.getParameter("type"); if ("edit".equals(type)) {
	 * try { UserDetail u = new UserDetail(); u.setDeptID(dept);
	 * u.setUpdateId(SystemContext.getUserId()); u.setUserName(name);
	 * u.setUserNo(userNO); userService.UpdateUser(u); map.put("result", "1");
	 * map.put("msg", "保存成功！"); return map; } catch (Exception e) {
	 * map.put("result", "0"); map.put("msg", "保存失败:" + e.getMessage()); return
	 * map; } } else { if (password.equals("") || password.length() < 8) {
	 * map.put("result", "0"); map.put("msg", "密码的最小长度为8位！"); return map; }
	 * 
	 * if (userService.getUserByCode(userNO).size() > 0) { map.put("result",
	 * "0"); map.put("msg", "此用户名已被使用！"); return map; }
	 * 
	 * try { String userId = UUID.randomUUID().toString().replace("-", "");
	 * UserDetail u = new UserDetail(); u.setUserId(userId);
	 * u.setUserNo(request.getParameter("userNO"));
	 * u.setCreateUserno(SystemContext.getUserId());
	 * u.setDeptID(request.getParameter("dept"));
	 * u.setPassword(userService.GenPwd(request.getParameter("userNO")
	 * .toUpperCase(), request.getParameter("password"))); u.setUserStatus("1");
	 * u.setUpdateId(SystemContext.getUserId());
	 * u.setUserName(request.getParameter("name")); userService.AddUser(u);
	 * 
	 * UserRole ur = new UserRole(); ur.setUserId(userId); ur.setRoleId(role);
	 * ur.setCreateUserNo(SystemContext.getUserId());
	 * ur.setUpdateUserNo(SystemContext.getUserId());
	 * 
	 * userRoleServiceImpl.insertUserRole(ur);
	 * 
	 * map.put("result", "1"); map.put("msg", "新增成功！"); } catch (Exception e) {
	 * map.put("result", "0"); map.put("msg", "新增失败:" + e.getMessage()); return
	 * map; } } return map; }
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(params = "face=resetPwd") public Map<String, String>
	 * resetPwd(HttpServletRequest request) { Map<String, String> map = new
	 * HashMap<String, String>(); try { String strPassword = userService.GenPwd(
	 * request.getParameter("userNO").toUpperCase(),
	 * request.getParameter("password")); UserDetail userDetail = new
	 * UserDetail(); userDetail.setPassword(strPassword);
	 * userDetail.setUserNo(request.getParameter("userNO"));
	 * userDetail.setUpdateId(SystemContext.getUserId());
	 * userService.updatePassword(userDetail);
	 * 
	 * map.put("result", "1"); map.put("msg", "重置成功！"); } catch (Exception e) {
	 * map.put("result", "0"); map.put("msg", "重置失败:" + e.getMessage()); }
	 * return map; }
	 * 
	 * @RequestMapping(params = "face=openUpdatePassword") public String
	 * openUpdatePassword(HttpServletRequest request, Model model) {
	 * 
	 * return "console/sysMgt/updatePassword"; }
	 */

	/**
	 * add by david on date 2013/08/03 to validate password
	 * 
	 * @param oldPassword
	 * @param response
	 */
	/*
	 * @RequestMapping(params = "face=validatePassword") public void
	 * validatePassword(
	 * 
	 * @RequestParam("oldPassword") String oldPassword, HttpServletResponse
	 * response) {
	 * 
	 * String passWordData = SystemContext.getPassword(); // 从资料库查询密码 String
	 * passWordUserFill = userService.GenPwd(SystemContext.getUserNO(),
	 * oldPassword); // 用户填写的进行加密
	 * 
	 * String msg = ""; if (passWordData.equals(passWordUserFill)) { msg = "1";
	 * } else { msg = "0"; }
	 * 
	 * this.writeMessage(response, msg); }
	 */

	/**
	 * 密码修改
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @param response
	 */
	/*
	 * @RequestMapping(params = "face=savePassword") public void
	 * savePassword(@RequestParam("oldPassword") String oldPassword,
	 * 
	 * @RequestParam("newPassword") String newPassword, HttpServletResponse
	 * response) {
	 * 
	 * String strPassword = userService.GenPwd(SystemContext.getUserNO(),
	 * newPassword); UserDetail userDetail = new UserDetail();
	 * userDetail.setPassword(strPassword);
	 * userDetail.setUserNo(SystemContext.getUserNO());
	 * userDetail.setUpdateId(SystemContext.getUserId());
	 * userService.updatePassword(userDetail);
	 * response.setCharacterEncoding("UTF-8"); }
	 */
	/*
	 * @RequestMapping(params = "face=queryRoleListByPageIndex") public void
	 * queryRoleListByPageIndex(
	 * 
	 * @RequestParam("pageIndex") String pageIndex,
	 * 
	 * @RequestParam("pageSize") String pageSize,
	 * 
	 * @RequestParam("roleName") String roleName, HttpServletResponse response)
	 * { Role role = new Role(); try {
	 * role.setRoleName(java.net.URLDecoder.decode(roleName, "UTF-8")); } catch
	 * (Exception ex) { ex.printStackTrace(); } List<Role> roleList =
	 * roleServiceImpl.getRoleList(role); PageBean page = listToPage(roleList,
	 * Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
	 * 
	 * this. }
	 */

	@RequestMapping(params = "face=getRoleList")
	public String getRoleList(Model model, HttpServletRequest request,
			@RequestParam("roleName") String roleName) {

		Role role = new Role();
		role.setRoleName(roleName);
		/*
		 * int pageIndex = 1; if (null!=request.getParameter("pageIndex")) { try
		 * { pageIndex = Integer.parseInt(request.getParameter("pageIndex")); }
		 * catch(Exception e) { } }
		 */
		List<Role> roleList = roleServiceImpl.getRoleList(role);

		// PageBean page = new PageBean(roleList, pageIndex, 999999);
		model.addAttribute("paginations", roleList);

		return "console/sysMgt/roleMgt";
	}

	@RequestMapping(params = "face=deleteRole")
	public void deleteRole(Model model, @RequestParam("roleId") String roleId,
			HttpServletResponse response) {

		String msg = "";
		try {
			Role role = new Role();
			role.setRoleId(roleId);
			role.setUpdateId(SystemContext.getUserNO());
			roleServiceImpl.deleteRole(role);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}

	@RequestMapping(params = "face=turnToAddRole")
	public String turnToAddRole(@RequestParam("roleId") String roleId,
			Model model) {
		Role role = new Role();
		Role role1 = new Role();

		if (roleId != null && !("".equals(roleId))) {
			role.setRoleId(roleId);
			role1 = roleServiceImpl.getRoleDetail(role);
		}

		model.addAttribute("roleId", roleId);
		model.addAttribute("roleName", role1.getRoleName());
		model.addAttribute("description", role1.getDescription());
		return "console/sysMgt/addRole";
	}

	@RequestMapping(method = RequestMethod.POST, params = "face=addOrEditRole")
	public void addOrEditRole(Model model, MultipartHttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Role role = new Role();
		boolean isInsert = true;
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		String description = request.getParameter("description");

		String msg = "";

		if (roleId != null && !("".equals(roleId))) {
			isInsert = false;
			role.setRoleId(roleId);
		} else {
			isInsert = true;
			role.setCreateUserno(SystemContext.getUserNO()); // 登錄用戶，後續修改
		}
		role.setUpdateId(SystemContext.getUserNO()); // 登錄用戶，後續修改
		role.setRoleName(roleName);
		role.setDescription(description);

		try {
			if (isInsert == true) {
				roleServiceImpl.insertRole(role);
			} else {
				roleServiceImpl.updateRole(role);
			}
			msg = "保存成功！";
		} catch (Exception e) {
			msg = "保存失败！";
			e.printStackTrace();

		}

		this.writeMessage(response, msg);

	}

	@RequestMapping(params = "face=turnToArrPermission")
	public String turnToArrPermission(HttpServletRequest request,
			@RequestParam("roleId") String roleId, ModelMap modelMap) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleID(roleId);
		String treeContents = roleServiceImpl.getAllTreeInfo(rolePermission);

		modelMap.put("treeContents", treeContents);
		modelMap.put("roleID", roleId);

		return "console/sysMgt/arrPermission";
	}

	@RequestMapping(params = "face=savePermission")
	public void savePermission(@RequestParam("roleID") String roleID,
			@RequestParam("nodesIDArr") String nodesIDArr,
			HttpServletResponse response) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setRoleID(roleID);
		roleServiceImpl.deleteRolePermission(rolePermission);
		if (nodesIDArr != null && nodesIDArr.length() > 0) {
			String strArray[] = nodesIDArr.split(";");
			for (int i = 0; i < strArray.length; i++) {
				rolePermission.setResourceID(strArray[i]);
				roleServiceImpl.insertRolePermission(rolePermission);
			}
		}
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * 跳转到用户管理list页面
	 * 
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=userMgt")
	public String userMgt(Model model) {
		int pageIndex = 0;
		List<UserDetail> getSelectUserList = userService.getSelectUserList("",
				"", "", pageIndex, SystemContext.getDefaultPageSize());
		model.addAttribute("userRoleName", roleServiceImpl.getRoleList());
		model.addAttribute("userDeptName", departmentService.getDeptAllList());

		model.addAttribute("paginations", getSelectUserList);
		return "console/sysMgt/userMgt";
	}

	/**
	 * 用户信息查询
	 * 
	 * @param userNo
	 * @param userName
	 * @param deptID
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=searchUserInfo")
	public String queryByTrafficUserDate(@RequestParam("userNo") String userNo,
			@RequestParam("roleName") String roleName,
			@RequestParam("deptID") String deptID,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		userNo = URLDecoder.decode(userNo, "utf-8");
		roleName = URLDecoder.decode(roleName, "utf-8");
		List<UserDetail> userDetailList = userService
				.getSelectUserList(userNo, roleName, deptID, pageIndex,
						SystemContext.getDefaultPageSize());
		/*
		 * if (userDetailList.size() <= SystemContext.getDefaultPageSize())
		 * pageIndex = 1;
		 * 
		 * PageBean page = new PageBean(userDetailList, pageIndex,
		 * SystemContext.getDefaultPageSize());
		 */
		model.addAttribute("userRoleName", roleServiceImpl.getRoleList());
		model.addAttribute("userDeptName", departmentService.getDeptAllList());
		model.addAttribute("userNo", userNo);
		model.addAttribute("roleName", roleName);
		model.addAttribute("deptID", deptID);
		model.addAttribute("paginations", userDetailList);
		return "console/sysMgt/userMgt";
	}

	@RequestMapping(params = "face=deleteUser")
	public void deleteNews(Model model, @RequestParam("userId") String userId,
			HttpServletResponse response) {
		String msg = "";
		try {
			UserDetail user = new UserDetail();
			user.setUserId(userId);
			userService.DeleteUser(userId);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}

	/**
	 * @author H2602965
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listAddUser")
	public String addMgt(Model model) {
		/*
		 * //部门下拉列表 List<Department> list = new ArrayList<Department>(); list
		 * =departmentService.getDeptAllList(); for (int i = 0; i < list.size();
		 * i++) { logger.debug("输出部门名称"+list.get(i).getDeptName()); }
		 */
		model.addAttribute("userDeptList", departmentService.getDeptAllList());
		model.addAttribute("userRoleName", roleServiceImpl.getRoleList());

		return "console/sysMgt/addUser";
	}

	/**
	 * 新增用户
	 * 
	 * @author H2602965 2014.05.30
	 * @param userNo
	 * @param userName
	 * @param roleName
	 * @param deptID
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "face=saveUserInfo")
	public Map<String, String> saveUserInfo(
			@RequestParam("userNo") String userNo,
			@RequestParam("userName") String userName,
			@RequestParam("roleId") String roleId,
			@RequestParam("deptID") String deptID, Model model,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();

		if (userNo.equals("")) {
			map.put("result", "0");
			map.put("msg", "用户名不能为空！");
			return map;
		}
		if (userName.equals("")) {
			map.put("result", "0");
			map.put("msg", "姓名不能为空！");
			return map;
		}
		if (roleId.equals("")) {
			map.put("result", "0");
			map.put("msg", "请选择角色！");
			return map;
		}
		if (deptID.equals("")) {
			map.put("result", "0");
			map.put("msg", "请选择部门！");
			return map;
		}

		UserDetail userDetail = new UserDetail();
		UserDetail ud = new UserDetail();
		UserRole userRole = new UserRole();
		userDetail.setUserNo(userNo);
		ud = userService.getUserByCode(userNo);// ud
												// 根据用户名查询oa_common中查询的用户姓名user_name
		if (null != ud) {
			int listsize = userService.queryUserUnique(userNo).size();
			if (listsize > 0) {
				map.put("result", "0");
				map.put("msg", "新增失败!此用户名已被使用！");
				logger.debug(map.get("msg"));
				return map;
			} else {
				try {
					userDetail.setUserId(UUID.randomUUID().toString()
							.replace("-", ""));
					userDetail.setUserName(userName); // userName 姓名
					userDetail.setDeptID(deptID); // 通过部门名称传部门ID
					userDetail.setUserStatus("1");
					userDetail.setCreateUserNo(SystemContext.getUserNO());
					userDetail.setCreateDate(DateUtil.getCurrentDateStr());
					userService.AddUser(userDetail);
					String userIdToRole = userDetail.getUserId();
					userRole.setUserId(userIdToRole);
					// 从角色表中获取Id
					/*
					 * userRole.setRoleId(UUID.randomUUID().toString().replace("-"
					 * , ""));
					 */
					userRole.setRoleId(roleId);
					userRole.setCreateUserNo(SystemContext.getUserNO());
					userRole.setCreateDate(DateUtil.getCurrentDateStr());
					userRoleService.insertUserRole(userRole);
				} catch (Exception e) {
					map.put("result", "0");
					map.put("msg", "新增失败!错误信息:" + e.getMessage());
					return map;
				}
			}
			map.put("result", "1");
			map.put("msg", "新增成功！");
			return map;

		}
		map.put("result", "0");
		map.put("msg", "新增失败！请使用已存在的用户名注册");
		return map;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "face=editView")
	public String edit(Model model, @RequestParam("userId") String userId) {
		logger.debug(userId);
		model.addAttribute("userEditInfo", userService.getUserEditInfo(userId));
		model.addAttribute("userDeptList", departmentService.getDeptAllList());
		model.addAttribute("userRoleName", roleServiceImpl.getRoleList());
		return "console/sysMgt/editUser";
	}

	/**
	 * 用户编辑
	 * 
	 * @param userNo
	 * @param userName
	 * @param roleId
	 * @param deptID
	 * @param model
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "face=saveEditUserInfo")
	public Map<String, String> saveEditUserInfo(
			@RequestParam("userNo") String userNo,
			@RequestParam("userName") String userName,
			@RequestParam("roleId") String roleId,
			@RequestParam("deptID") String deptID, Model model,
			HttpServletResponse response) {

		Map<String, String> map = new HashMap<String, String>();

		if (userNo.equals("")) {
			map.put("result", "0");
			map.put("msg", "用户名不能为空！");
			return map;
		}
		if (userName.equals("")) {
			map.put("result", "0");
			map.put("msg", "姓名不能为空！");
			return map;
		}
		if (roleId.equals("")) {
			map.put("result", "0");
			map.put("msg", "请选择角色！");
			return map;
		}
		if (deptID.equals("")) {
			map.put("result", "0");
			map.put("msg", "请选择部门！");
			return map;
		}

		UserDetail userDetail = new UserDetail();
		UserDetail ud = new UserDetail();
		UserRole userRole = new UserRole();
		userDetail.setUserNo(userNo);
		ud = userService.getUserByCode(userNo);
		if (null != ud) {
			try {
				userDetail.setUserName(userName); // userName 姓名
				userDetail.setDeptID(deptID); // 通过部门名称传部门ID
				userDetail.setUserStatus("1");
				userDetail.setUpdateUserNo(SystemContext.getUserNO());
				userDetail.setUpdateDate(DateUtil.getCurrentDateStr());
				userService.UpdateUserInfo(userDetail);
				// 从角色表中获取Id
				logger.debug("UserId=" + ud.getUserId());
				userRole.setUserId(ud.getUserId());
				/*
				 * userRole.setRoleId(UUID.randomUUID().toString().replace("-",
				 * ""));
				 */
				userRole.setRoleId(roleId);
				userRole.setUpdateUserNo(SystemContext.getUserNO());
				userRole.setUpdateDate(DateUtil.getCurrentDateStr());
				userRoleService.updateUserRoleInfo(userRole);
			} catch (Exception e) {
				map.put("result", "0");
				map.put("msg", "保存失败:" + e.getMessage());
				return map;
			}
		}
		map.put("result", "1");
		map.put("msg", "保存成功！");
		return map;

	}

	/**
	 * 新增点击用户列表 跳转到 新页面
	 * 
	 * @author H2602965 2014.06.09
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listUserForOA")
	public String showUserForOA(HttpServletRequest request, Model model) {

		int pageIndex = 0;
		if (null != request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch (Exception e) {
			}
		}

		List<UserDetail> userlList = userService.getUserAllList("", "",
				pageIndex, SystemContext.getDefaultPageSize());
		/*
		 * if (userlList.size() <= pageSize) pageIndex = 1;
		 */

		// PageBean page = new PageBean(userlList, pageIndex,
		// SystemContext.getDefaultPageSize());

		model.addAttribute("paginations", userlList);

		return "console/sysMgt/userList";
	}

	/**
	 * 新增用户,可以使用用户信息列表查询
	 * 
	 * @author H2602965
	 * @param userNo
	 * @param userName
	 * @param pageIndex
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryUserList")
	public String queryUserList(@RequestParam("userNo") String userNo,
			@RequestParam("userName") String userName,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		userNo = URLDecoder.decode(userNo, "utf-8");
		userName = URLDecoder.decode(userName, "utf-8");

		logger.debug("userNo=" + userNo + "," + "userName=" + userName);
		List<UserDetail> userlList = userService.getUserAllList(userNo,
				userName, pageIndex, SystemContext.getDefaultPageSize());
		// PageBean page = new PageBean(userlList, pageIndex,
		// SystemContext.getDefaultPageSize());
		model.addAttribute("userNo", userNo);
		model.addAttribute("userName", userName);
		model.addAttribute("paginations", userlList);
		return "console/sysMgt/userList";
	}

	/**
	 * 考核标准分页功能
	 * 
	 * @author 20140804
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listCheckStandardPage")
	public String listCheckStandardPage(
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model) {

		List<CheckStandard> checkList = checkStandardService
				.getCheckStandardList(pageIndex,
						SystemContext.getDefaultPageSize());
		// PageBean page = new PageBean(checkList, 1,
		// SystemContext.getDefaultPageSize());
		model.addAttribute("paginations", checkList);
		return "console/sysMgt/checkStandard/listCheckStandard";

	}

	/**
	 * 考核标准管理默认页面
	 * 
	 * @author zhangpeng 20140620
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listCheckStandardView")
	public String listCheckStandard(Model model) {

		List<CheckStandard> checkList = checkStandardService
				.getCheckStandardList(0, SystemContext.getDefaultPageSize());
		// PageBean page = new PageBean(checkList, 1,
		// SystemContext.getDefaultPageSize());
		model.addAttribute("paginations", checkList);

		return "console/sysMgt/checkStandard/listCheckStandard";

	}

	/**
	 * 考核标准管理跳转新增页面
	 * 
	 * @author zhangpeng 20140620
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, params = "face=addCheckInfoView")
	public String addCheckInfo(Model model) {
		model.addAttribute("userName", SystemContext.getUserName());
		return "console/sysMgt/checkStandard/addCheckStandard";
	}

	/**
	 * 考核标准管理新增验证编码为唯一型
	 * 
	 * @author 20140805
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=checkInfo", method = RequestMethod.POST)
	public void checkInfo(@RequestParam("code") String code,
			HttpServletResponse response) {
		logger.debug("进入到方法中" + code);
		String msg = "";
		CheckStandard checkCode = checkStandardService.getCode(code);
		logger.debug(checkCode);
		if (null == checkCode) {
			msg = "success";
			this.writeMessage(response, msg);
		} else {
			msg = "false";
			this.writeMessage(response, msg);
		}
		logger.debug("jieguoxinxi" + msg);
	}

	/**
	 * 考核标准管理新增保存
	 * 
	 * @author zhangpeng 20140624
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=saveCheckInfo", method = RequestMethod.POST)
	public void saveCheckInfo(
			@ModelAttribute("checkStandard") CheckStandard checkStandard,
			HttpServletResponse response) {
		logger.debug(checkStandard.getCheckId());
		if (StringUtils.isBlank(checkStandard.getCheckId())) {
			checkStandard.setCheckId(UUID.randomUUID().toString()
					.replace("-", ""));
			checkStandard.setCreateDate(DateUtil.getCurrentDateStr());
			checkStandard.setIsDel("0");
			logger.debug(checkStandard.getCheckId()
					+ checkStandard.getCreateDate() + checkStandard.getCode()
					+ checkStandard.getItemName()
					+ checkStandard.getCreateUser());
			checkStandardService.saveStandard(checkStandard);

			this.writeMessage(response, "保存成功！");
		} else {
			this.writeMessage(response, "ERROR，保存失败！");
		}
	}

	/**
	 * 考核标准管理编辑
	 * 
	 * @author zhangpeng 20140624
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=editCheckInfoView")
	public String editCheckInfo(Model model, @RequestParam("code") String code) {
		model.addAttribute("checkInfo",
				checkStandardService.getCheckStandardDetail(code));
		return "console/sysMgt/checkStandard/editCheckStandard";
	}

	/**
	 * 考核标准管理编辑保存
	 * 
	 * @author zhangpeng 20140624
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=updateCheckInfo", method = RequestMethod.POST)
	public void updateCheckInfo(
			@ModelAttribute("checkStandard") CheckStandard checkStandard,
			HttpServletResponse response) {

		if (!StringUtils.isBlank(checkStandard.getCheckId())) {
			checkStandardService.updateStandard(checkStandard);
		} else {
			this.writeMessage(response, "ERROR，保存失败！");
		}

	}

	/**
	 * 考核标准管理删除功能
	 * 
	 * @author zhangpeng 20140624
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=deleteCheckInfo")
	public void deleteCheckInfo(Model model,
			@RequestParam("checkId") String checkId,
			HttpServletResponse response) {
		logger.debug("id的值" + checkId);
		String msg = "";
		try {
			checkStandardService.deleteByCheckId(checkId);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);

	}

	/**
	 * 部门管理页面跳转 wcf 20140704
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=listDeptMgtView")
	public String listDeptMgt(Model model) {
		int pageIndex = 0;
		List<Department> departmentInfoList = departmentService
				.getDeptInfoList("", "", "", pageIndex,
						SystemContext.getDefaultPageSize());
		model.addAttribute("departmentInfoList", departmentInfoList);

		return "console/sysMgt/deptMgt/listDeptMgt";

	}

	/**
	 * 部门按条件查询显示
	 * 
	 * @param deptID
	 * @param deptName
	 * @param deptCategory
	 * @param pageIndex
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryDeptInfoView")
	public String queryDeptInfo(@RequestParam("deptID") String deptID,
			@RequestParam("deptName") String deptName,
			@RequestParam("deptCategory") String deptCategory,
			@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		deptName = URLDecoder.decode(deptName, "utf-8");
		deptCategory = URLDecoder.decode(deptCategory, "utf-8");
		List<Department> departmentInfoList = departmentService
				.getDeptInfoList(deptID, deptName, deptCategory, pageIndex,
						SystemContext.getDefaultPageSize());
		model.addAttribute("deptID", deptID);
		model.addAttribute("deptName", deptName);
		model.addAttribute("deptCategory", deptCategory);
		model.addAttribute("departmentInfoList", departmentInfoList);

		return "console/sysMgt/deptMgt/listDeptMgt";
	}

	/**
	 * 删除部门信息
	 * 
	 * @param model
	 * @param deptID
	 * @param response
	 */
	@RequestMapping(params = "action=deleteDeptInfo")
	public void deleteDept(Model model, @RequestParam("deptID") String deptID,
			HttpServletResponse response) {
		String msg = "";
		try {
			logger.debug("========" + deptID);
			departmentService.deleteDept(deptID);
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}
		this.writeMessage(response, msg);
	}

	/**
	 * 设置部门启用停用状态
	 * 
	 * @param model
	 * @param deptID
	 * @param response
	 */
	@RequestMapping(params = "action=updateDeptInfo")
	public void updateDept(Model model, @RequestParam("deptID") String deptID,
			HttpServletResponse response) {
		String msg = "";
		try {
			logger.debug("========" + deptID);
			departmentService.updateDept(deptID);
			msg = "设置成功！";
		} catch (Exception e) {
			msg = "设置失败！";
		}

		this.writeMessage(response, msg);
	}

	// 部门新增页面跳转
	@RequestMapping(params = "face=listAddDept")
	public String addDept(Model model) {

		return "console/sysMgt/deptMgt/addDept";
	}

	/**
	 * 新增部门页面保存功能,以及编辑保存功能
	 * 
	 * @edit H2602965
	 * @param department
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=save", method = RequestMethod.POST)
	public void save(@ModelAttribute("department") Department department,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		if (department.getDeptID() == null) {
			department.setCreateDate(DateUtil.getCurrentDateStr());
			department.setIsDel("0");
			department.setCreateUserNo(SystemContext.getUserNO());
			departmentService.save(department);
			this.writeMessage(response, "保存成功！");

		} else {
			// 编辑保存
			department.setUpdateDate(DateUtil.getCurrentDateStr());
			department.setUpdateUserNo(SystemContext.getUserNO());
			departmentService.updateDeptInfo(department);
			this.writeMessage(response, "保存成功！");
		}
	}

	/**
	 * 外网与内网栏目对应关系默认列表页面
	 * 
	 * @author H2602975 zhpeng
	 * @param model
	 * @param deptID
	 * @param response
	 */
	@RequestMapping(params = "face=listCategoryRelationView")
	public String listCategoryRelation(Model model, HttpServletRequest request) {
		String categoryId1 = request.getParameter("categoryId");
		List<TextCategory> textCategoryList = columnService.getAllColumn("",
				null, null, "0");
		model.addAttribute("textCategoryList", textCategoryList);
		model.addAttribute("categoryId", categoryId1);
		return "console/sysMgt/categoryRelation/listCategoryRelation";
	}

	/**
	 * 根据内网名称模糊查询内网栏目 wcf 20140721
	 * 
	 * @param model
	 * @param innerCategory
	 * @return
	 */
	@RequestMapping(params = "face=queryCategory")
	public String queryCategory(Model model,
			@RequestParam("categoryName") String categoryName,
			HttpServletRequest request) throws UnsupportedEncodingException {
		String categoryName1 = URLDecoder.decode(categoryName, "utf-8");
		List<TextCategory> textCategoryList = columnService.getAllColumn(
				categoryName1, null, null, "0");
		model.addAttribute("textCategoryList", textCategoryList);
		model.addAttribute("categoryName", categoryName1);
		return "console/sysMgt/categoryRelation/listCategoryRelation";

	}

	/**
	 * 外网与内网栏目对应关系查询
	 * 
	 * @author H2602975 zhpeng wcf
	 * @param model
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "face=queryCategoryRelation")
	public String queryCategoryRelation(Model model,
			@RequestParam("categoryId") String categoryId) {
		List<CategoryRelation> relationList = columnService
				.queryCategoryRelationList(categoryId);
		model.addAttribute("relationList", relationList);
		return "console/sysMgt/categoryRelation/listCategoryDetail";

	}

	/**
	 * 根据内网id找到对应外网id
	 * 
	 * @author H2601917
	 * @param categoryId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "action=searchCategoryRelation")
	public void searchCategoryRelation(HttpServletResponse response,HttpServletRequest request,
			@RequestParam("categoryId") String categoryId) {
		List<CategoryRelation> relationList = null;
		String type=request.getParameter("type");
		if (SystemContext.getRoleName().equals("WebsiteEditor")) {
			if (type.equals("add")) {
				relationList = columnService
						.queryCategoryRelationList(categoryId);
			} else {
				relationList = columnService.queryCategoryRelationList("");
			}

		} else {
			relationList = columnService.queryCategoryRelationList(categoryId);
		}
		StringBuffer buff = new StringBuffer("{\"Status\":1");
		buff.append(",\"Data\":[");
		for (int i = 0; i < relationList.size(); i++) {
			buff.append("{");
			buff.append("\"outerId\":\"" + relationList.get(i).getOuterId()
					+ "\"");
			buff.append(",\"outerCategory\":\""
					+ relationList.get(i).getOuterCategory() + "\"");
			buff.append("},");
		}
		if (relationList.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(buff.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 外网与内网栏目对应关系新增功能
	 * 
	 * @author H2602992 wcf
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "face=addCategoryRelationView")
	public String addCategoryRelation(Model model,
			@RequestParam("categoryId") Long categoryId,
			HttpServletRequest request) {
		List<TextCategory> textCategoryList = columnService.getAllColumn("",
				null, null, "0");
		TextCategory textCategoryById = columnService.getColumnById(categoryId);
		List<Program> programAllList = programService.getAll("1");
		List<Program> programSubAllList = programService.getAll("0");
		String createDate = DateUtil.getCurrentDateStr();
		String userNo = SystemContext.getUserName();

		model.addAttribute("createDate", createDate);
		model.addAttribute("userNo", userNo);
		model.addAttribute("textCategoryById", textCategoryById);
		model.addAttribute("programAllList", programAllList);
		model.addAttribute("programSubAllList", programSubAllList);
		model.addAttribute("textCategoryList", textCategoryList);
		return "console/sysMgt/categoryRelation/addCategoryRelation";
	}

	/**
	 * 外网与内网栏目对应关系新增保存功能
	 * 
	 * @author H2602992 wcf
	 * @param model
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "action=saveCategoryRelation")
	public void saveCategoryRelation(
			@ModelAttribute("categoryRelation") CategoryRelation categoryRelation,
			MultipartHttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		String createDate = categoryRelation.getCreateDate();
		String createUser = categoryRelation.getCreateUser();
		logger.debug("11111111111" + categoryRelation.getInnerId());
		String innerCategory = categoryRelation.getInnerCategory();
		String outerCategory = categoryRelation.getOuterCategory();
		String innerCategoryArray[] = innerCategory.split(",");
		String outerCategoryArray[] = outerCategory.split(",");
		String innerIdArray[] = categoryRelation.getInnerId().split(",");
		String outerIdArray[] = categoryRelation.getOuterId().split(",");

		if (innerIdArray.length != 0 && outerIdArray.length != 0) {
			for (int i = 0; i < innerIdArray.length; i++) {
				for (int j = 0; j < outerIdArray.length; j++) {
					// String innerName =
					// URLDecoder.decode(innerCategoryArray[i],"utf-8");
					// String outerName =
					// URLDecoder.decode(outerCategoryArray[j],"utf-8");
					logger.debug("内网名称：" + innerCategoryArray[i] + "，外网名称："
							+ outerCategoryArray[j]);
					CategoryRelation categoryRelation1 = new CategoryRelation();
					categoryRelation1.setCreateDate(createDate);
					categoryRelation1.setIsPublic(categoryRelation
							.getIsPublic());
					categoryRelation1.setCreateUser(createUser);
					categoryRelation1.setRelationId(UUID.randomUUID()
							.toString().replace("-", ""));
					categoryRelation1.setInnerCategory(innerCategoryArray[i]);
					categoryRelation1.setOuterCategory(outerCategoryArray[j]);
					categoryRelation1.setInnerId(innerIdArray[i]);
					categoryRelation1.setOuterId(outerIdArray[j]);
					columnService.saveCategoryRelation(categoryRelation1);
				}
			}
			this.writeMessage(response, categoryRelation.getInnerId());
		} else {
			this.writeMessage(response, "保存失败！");
		}

	}

	/**
	 * 外网与内网栏目对应关系编辑页面跳转
	 * 
	 * @author H2602992 wcf
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "face=editCategoryRelationView")
	public String editCategoryRelation(Model model,
			@RequestParam("relationId") String relationId) {
		CategoryRelation categoryRelation = columnService
				.getRelationDetail(relationId);
		List<Program> programSubAllList = programService.getAll("0");
		logger.debug(categoryRelation.getOuterId());
		model.addAttribute("programSubAllList", programSubAllList);
		model.addAttribute("categoryRelation", categoryRelation);
		return "console/sysMgt/categoryRelation/editCategoryRelation";
	}

	/**
	 * 外网与内网栏目对应关系编辑保存功能
	 * 
	 * @author H2602975 zhpeng
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "action=saveEditCategoryRelation", method = RequestMethod.POST)
	public void saveEditCategoryRelation(
			@ModelAttribute("categoryRelation") CategoryRelation categoryRelation,
			HttpServletResponse response) {
		String categoryId = categoryRelation.getInnerId();
		String msg = "";
		Boolean flag = true;
		List<CategoryRelation> relationList = columnService
				.queryCategoryRelationList(categoryId);
		for (int i = 0; i < relationList.size(); i++) {
			if (relationList
					.get(i)
					.getOuterId()
					.equals(categoryRelation.getOuterId().substring(1,
							categoryRelation.getOuterId().length()))) {
				flag = false;
			}
		}
		if (flag == true) {

			columnService.upateCategoryRelation(categoryRelation);
			this.writeMessage(response, categoryId);
		} else {
			msg = "error";
			this.writeMessage(response, msg);
		}
	}

	/**
	 * 外网与内网栏目对应删除功能
	 * 
	 * @author H2602975 zhpeng
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "action=deleteRelation")
	public void deleteCategoryRelation(
			@RequestParam("relationId") String relationId,
			HttpServletResponse response) {
		columnService.deleteCategoryRelation(relationId);

	}

	// 部门管理跳转编辑页面 wcf 20140709
	@RequestMapping(params = "face=editDeptView")
	public String editDept(Model model, @RequestParam("deptID") String deptID,
			@RequestParam("deptId") String deptId,
			@RequestParam("deptName") String deptName,
			@RequestParam("deptCategory") String deptCategory,
			@RequestParam("pageIndex") int page) {
		logger.debug("deptCategory====" + deptCategory);
		try {
			deptName = URLDecoder.decode(deptName, "utf-8");
			deptCategory = URLDecoder.decode(deptCategory, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.debug("格式转换失败！");
		}
		logger.debug("deptCategory====:" + deptCategory);
		int pageIndex = 0;
		List<Department> departmentInfoList = departmentService
				.getDeptInfoList(deptId, "", "", pageIndex,
						SystemContext.getDefaultPageSize());
		logger.debug("flag==========="
				+ departmentInfoList.get(0).getDeptName());
		model.addAttribute("departmentInfoList", departmentInfoList.get(0));
		model.addAttribute("deptId", deptID);
		model.addAttribute("deptName", deptName);
		model.addAttribute("deptCategory", deptCategory);
		model.addAttribute("pageIndex", page);
		return "console/sysMgt/deptMgt/editDept";
	}

	/**
	 * 加载修改密码页面
	 */
	@RequestMapping(params = "face=updatePassword")
	public String updatePassword() {

		return "console/sysMgt/updatePassword";
	}

	/**
	 * 修改密码保存方法
	 */
	@RequestMapping(params = "action=savePassword")
	public void savePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			HttpServletResponse response) {
		String password = SystemContext.getPassword();// 获取当前用户密码
		String userNo = SystemContext.getUserNO();// 获取当前用户账号
		String msg = "";

		oldPassword = encryptSha256(oldPassword);// 对前台旧密码加密
		// 判断旧密码是否正确
		if (password.equals(oldPassword)) {// 保存新密码
			newPassword = encryptSha256(newPassword);// 对前台新密码加密
			userService.updatePassword(userNo, newPassword);
			msg = "success";
		} else {
			msg = "false";
		}

		this.writeMessage(response, msg);
	}

	/**
	 * 密码加密
	 * 
	 * @param inputStr
	 * @return
	 */
	public String encryptSha256(String inputStr) {
		byte digest[];
		MessageDigest md;
		String password = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
			digest = md.digest(inputStr.getBytes("UTF-8"));
			password = new String(Base64.encodeBase64(digest));
			System.out.println("--->>"+new HexBinaryAdapter().marshal(digest));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.debug("=======加密后密码：" + password);
		System.out.println("=======加密后密码：" + password);
		return password;
	}
	public String decodeBase64(String encodePwd){
		byte digest[];
		MessageDigest md;
		String password = "";
		try {
			md = MessageDigest.getInstance("SHA-256");
			digest = md.digest(Base64.decodeBase64(encodePwd.getBytes("UTF-8")));
			System.out.println("--->>"+new HexBinaryAdapter().marshal(Base64.decodeBase64(encodePwd.getBytes("UTF-8"))));
			System.out.println("--->"+md.toString());
			password = new String(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		logger.debug("=======解密后密码：" + password);
		System.out.println("=======解密后密码：" + password);
		return password;
	}
	public static void main(String[] args) {
		SysMgtController sys = new SysMgtController();
		String encodePwd = sys.encryptSha256("123456");
		System.out.println(encodePwd.length());
		sys.decodeBase64(encodePwd);
	}
}
