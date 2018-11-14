package com.gdssoft.cqjt.controller.portal.rank;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdssoft.core.tools.SystemContext;
import com.gdssoft.cqjt.controller.BaseController;
import com.gdssoft.cqjt.pojo.CheckHistory;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.service.CheckHistoryService;
import com.gdssoft.cqjt.service.DepartmentService;
import com.gdssoft.cqjt.service.TextNewsReportService;

@Controller
@RequestMapping("/guest/rank.xhtml")
public class RankController extends BaseController {
	
	//@Resource(name = "deptScoreServiceImpl")
	//private DepartmentScoreService departmentScoreService;
	
	@Resource(name = "departmentServiceImpl")
	private DepartmentService departmentService;
	
	@Autowired
	@Resource(name = "checkHistoryService")
	private CheckHistoryService checkHistoryService;
	
	@Autowired
	@Resource(name = "textNewsReportServiceImpl")
	private TextNewsReportService textNewsReportService;
	
	@RequestMapping(params="face=queryRankView")
	public String queryRankList(Model model,HttpServletRequest request) {
		/*Date start = this.getStartDate("");
		Date end = this.getEndDate("");*/
		String months = request.getParameter("months");
		String flag = request.getParameter("flag");
//		//查询交委机关
//		List<Department> rankList = departmentService.getScoreSumList(new String[]{"市交委机关"});
//		//查询委属单位
//		List<Department> rankListS= departmentService.getScoreSumList(new String[]{"委属单位","区县交通局","市属相关交通企业"});
//		//查询区县单位
//		/*List<Department> rankListE= departmentService.getScoreSumList("区县单位");*/
//		model.addAttribute("rankList",rankList);
//		model.addAttribute("rankListS",rankListS);
//		model.addAttribute("months",months);
//		model.addAttribute("flag",flag);
		if("1".equals(flag)){
			textNewsReportService.queryGovSiteCheckStat(model,1);
		} else {
			textNewsReportService.queryGovSiteCheckStat(model,2);
		}
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum",this.getCount());
		model.addAttribute("flag",flag);
		return "portal/rank/listRank";
	}
	/**
	 * 获取考核历史信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params="face=getRankHistory")
	public String getRankHistory(Model model,HttpServletRequest request) {	
		String type = request.getParameter("type");
		model.addAttribute("type",type);
		/*if(type.equals("1")){
			type="委机关";
		}else if(type.equals("2")){
			type="委属单位";
		}else if(type.equals("3")){
			type="区县交通局";
		}else if(type.equals("4")){
			type="市属相关交通企业";
		}*/
		
		int pageIndex = 0;
		if (null!=request.getParameter("pageIndex")) {
			try {
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			} catch(Exception e) { }
		}
		List<CheckHistory> checkHistory = checkHistoryService.getFileList(type, "0", pageIndex, SystemContext.getDefaultPageSize());
		logger.debug(checkHistory.size());
		model.addAttribute("paginations", checkHistory);
		/* ==========当前访问人数========== */
		model.addAttribute("visiterNum",this.getCount());
		return "portal/rank/listRankHistory";  
	}
	@RequestMapping(params = "action=serachRankHistory")
	public void getfileView(@RequestParam("type") String type,@RequestParam("pageIndex") int pageIndex,
			HttpServletResponse response) {
		List<CheckHistory> checkHistory = checkHistoryService.getFileList(type, "0", pageIndex, SystemContext.getDefaultPageSize());

		this.writeResult(response, checkHistory);
	}
}
