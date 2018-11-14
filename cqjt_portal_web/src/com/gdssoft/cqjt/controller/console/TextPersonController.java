package com.gdssoft.cqjt.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.TextPerson;
import com.gdssoft.cqjt.pojo.videoNews.VideoRecord;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextPersonService;
import com.gdssoft.cqjt.service.UserDetailService;
import com.gdssoft.cqjt.service.videoNews.VideoRecordService;

@Controller
@RequestMapping("/console/textPerson.xhtml")
public class TextPersonController extends BaseController {
	
	@Autowired
	@Resource(name = "textPersonServiceImpl")
	private TextPersonService textPersonService;
	
	@Resource(name = "userDetailServiceImpl")
	private UserDetailService userDetailService;
	
	@Autowired
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	@Autowired
	@Resource(name = "videoRecordServiceImpl")
	private VideoRecordService videoRecordService;
	
	@RequestMapping(params = "face=PersonlistView")
	public String queryPersonlistView(HttpServletRequest request, Model model) {
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextPerson> textPersonList = new ArrayList<TextPerson>();
		int pageIndex = 0;
			// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
			if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
					|| roleName.equals("GovEditor")) {
				textPersonList = textPersonService.getPersonlistView(null, null,
						pageIndex, SystemContext.getDefaultPageSize());
			} else {// 查询当前用户报送信息
				textPersonList = textPersonService.getPersonlistView("",
						SystemContext.getUserName(), null, null, pageIndex,
						SystemContext.getDefaultPageSize());
			}
			model.addAttribute("paginations", textPersonList);
			return "console/textnews/news/TextPersonList";
	}
	

	
	
	@RequestMapping(params = "face=PersonView")
	public String queryPersonViewByUserDate(@RequestParam("newsTitle") String newsTitle,
			@RequestParam("entryDateS") String entryDateS,
			@RequestParam("entryDateE") String entryDateE,
			@RequestParam("pageIndex") int pageIndex,
			@RequestParam("personCat") String personCat,
			HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		newsTitle = URLDecoder.decode(newsTitle, "utf-8");
 
		//解决中文乱码
		String sc = "";
		try {
			sc = new String(personCat.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		// 获取当前用户角色
		String roleName = SystemContext.getRoleName();
		List<TextPerson> textPersonList = new ArrayList<TextPerson>();
		Date start = this.getStartDate(entryDateS);
		Date end = this.getEndDate(entryDateE);
		if ((entryDateS.equals("") || entryDateE.equals(""))
				|| (entryDateS.equals("") && entryDateE.equals(""))) {
			start = null;
			end = null;
		}
		logger.debug("newsTitle==========" + newsTitle);
 
		// 系统管理员、网站校编员、政务校编员，这三个角色的人员可查询所有的信息
		if (roleName.equals("Admin") || roleName.equals("WebsiteEditor")
				|| roleName.equals("GovEditor")) {
			textPersonList = textPersonService.queryPersonlistView(newsTitle,
					"", start, end, pageIndex,"",new String[]{},new int[]{},
					SystemContext.getDefaultPageSize(),sc);
		} else {// 查询当前用户报送信息
			textPersonList = textPersonService.queryPersonlistView(newsTitle,
					SystemContext.getUserName(), start, end, pageIndex,"",new String[]{},new int[]{},
					SystemContext.getDefaultPageSize(),sc);
		}
		model.addAttribute("start", start);
		model.addAttribute("end", end);
		model.addAttribute("newsTitle", newsTitle);
		model.addAttribute("paginations", textPersonList);
		model.addAttribute("PersonCat", sc);
		logger.debug("开始时间" + entryDateS + "结束时间" + entryDateE);
 
		return "console/textnews/news/TextPersonList";
		 
	}
	
	
	
	
	
	/**
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "face=addPersonView")
	public String addPerson(Model model) {
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();

		model.addAttribute("userNo", userNo);
		model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail()
				.getDeptName());

		model.addAttribute("approvers", userDetailService.getApprovers(userNo));

		model.addAttribute("textCategorys",
				departmentService.getTextCategorys(deptId));
		List<Department> list = new ArrayList<Department>();
		list = departmentService.getCategoryName(deptId);
		/**
		 * 添加是否滚定显示权限管控，如果CategoryName名字改变，这里会出问题，表设计。 管理员，网站校编员、政务校编员，可以滚动显示
		 */
		if (list.get(0).getCategoryName().equals("市交委机关")) {
			model.addAttribute("deptCategory", true);
		} else if (SystemContext.getRoleName().equals("Admin")
				|| SystemContext.getRoleName().equals("WebsiteEditor")
				|| SystemContext.getRoleName().equals("GovEditor")) {
			model.addAttribute("deptCategory", true);
		}
		return "console/textnews/news/addPerson";
	}
	
	
	/**
	 * 
	 * @param textPerson
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=savePerson", method = RequestMethod.POST)
	public void savePerson(@ModelAttribute("textNews") TextPerson textPerson,
			MultipartHttpServletRequest request, HttpServletResponse response) {
		
		textPerson.setPersonId(UUID.randomUUID().toString().replace("-", ""));
		textPerson.setEntryDate(DateUtil.getCurrentDate());
		textPerson.setIsDel("0");
		textPerson.setCreateBy(textPerson.getEntryUser());
		textPerson.setCreateDate(DateUtil.getCurrentDateStr());
		
		try {
			textPersonService.save(textPerson);
			this.writeMessage(response, "保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(this.getClass().getName(), e);
			this.writeMessage(response, "ERROR，保存失败！");
		}
	}
	
	@RequestMapping(params = "action=deleteInfo")
	public void deleteInfo(Model model, @RequestParam("personId") String personId,
			HttpServletResponse response) {

		String msg = "";
		try {
			TextPerson textPerson = new TextPerson();
			textPerson.setPersonId(personId);
			textPersonService.delete(personId);
 
			msg = "删除成功！";
		} catch (Exception e) {
			msg = "刪除失败！";
		}

		this.writeMessage(response, msg);
	}
	
	@RequestMapping(params = "face=editPersonView")
	public String editPerson(Model model, @RequestParam("personId") String PersonId) {

		logger.debug("得到PersonId的值" + PersonId);
		String userNo = SystemContext.getUserNO();
		String deptId = SystemContext.getUserDetail().getDeptID();
		String roleName = SystemContext.getRoleName();
		List<VideoRecord> newsRecord = videoRecordService.getVideoRecordList(PersonId);
		model.addAttribute("newsRecord", newsRecord);

		model.addAttribute("userNo", userNo);
		// model.addAttribute("userName", SystemContext.getUserName());
		model.addAttribute("userDeptID", deptId);
		model.addAttribute("userDeptName", SystemContext.getUserDetail().getDeptName());
		
		
		TextPerson textPerson = textPersonService.getTextPersonDetail(PersonId);
		if(null!=textPerson.getPubDate() || "".equals(textPerson.getPubDate())){
			textPerson.setPubDate(textPerson.getPubDate().substring(0,10));	
		}
		model.addAttribute("textNewsEditInfo", textPerson);
		model.addAttribute("textCategorys",departmentService.getTextCategorys(deptId));
		String userName = null;
		List<String> photoList = new ArrayList<String>();
		if(textPerson != null){
			userName = textPerson.getEntryUser();
		}
		model.addAttribute("photoList", photoList);
		model.addAttribute("userName", userName);
		model.addAttribute("roleName", roleName);
		logger.debug("yonghu:" + userName);
		model.addAttribute("approvers", userDetailService.getApp(userName));
		logger.debug("qianfaren" + userDetailService.getApp(userName));

		return "console/textnews/news/editTextPerson";
	}
	
	/**
	 * 法律法规编辑操作
	 * @param textNews
	 * @param imageUrl
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "action=updateTextPerson")
	public void updatePersonNews(@ModelAttribute("textNews") TextPerson textPerson,
			MultipartHttpServletRequest request, HttpServletResponse response) {


		DateFormat fds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date entryDate = null;
		try {
			entryDate = fds.parse(textPerson.getCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		textPerson.setEntryDate(entryDate);
		if (!StringUtils.isBlank(textPerson.getPersonId())) {
			//logger.debug("flag=" + textNews.getFlag());
			textPerson.setIsDel("0");
			 
			VideoRecord videoRecord = new VideoRecord();
			videoRecord.setVrId(UUID.randomUUID().toString().replace("-", ""));
			videoRecord.setVideoId(textPerson.getPersonId());
			if (textPerson.getFlag().equals("1")) {
				videoRecord.setFlag("4");// 信息处理记录，flag为4表示修改
			}
			if (textPerson.getFlag().equals("0")) {
				videoRecord.setFlag("0");// 信息处理记录，flag为0表示存草稿
			}
			if (textPerson.getFlag().equals("2")) {
				videoRecord.setFlag("2");// 信息处理记录，flag为2表示分拣
			}
			if (textPerson.getFlag().equals("8")) {
				videoRecord.setFlag("18");// 信息处理记录，flag为18表示归档
			}
			if (textPerson.getFlag().equals("7")) {
				String rejectReason = request.getParameter("rejectReason");
				videoRecord.setRejectReason(rejectReason);
				videoRecord.setFlag("13");
			} else {
				videoRecord.setRejectReason("");
			}

			videoRecord.setUpdateUser(SystemContext.getUserName());
			videoRecord.setUpdateDate(DateUtil.getCurrentDateStr());
			videoRecordService.update(videoRecord);
			if (textPerson.getFlag().equals("7")) {
				this.writeMessage(response, "退稿成功！");
			} else {
				this.writeMessage(response, "保存成功！");
			}
			textPersonService.update(textPerson);
		} else {
			this.writeMessage(response, "保存失败！");
		}
	}

}
