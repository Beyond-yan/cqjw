package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gdssoft.core.tools.DateUtil;
import com.gdssoft.cqjt.dao.DepartmentScoreDao;
import com.gdssoft.cqjt.pojo.CheckStandard;
import com.gdssoft.cqjt.pojo.DepartmentScore;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.enums.ScoreItem;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;
import com.gdssoft.cqjt.service.CheckStandardService;
import com.gdssoft.cqjt.service.DepartmentScoreService;
import com.gdssoft.cqjt.service.TextNewsService;

/**
 * 部门计分功能方法实现
 * 
 * @author H2602975 zhpeng 20140616
 */
@Service("deptScoreServiceImpl")
public class DepartmentScoreServiceImpl implements DepartmentScoreService {
	protected transient final Log logger = LogFactory.getLog(getClass());

	@Resource(name = "deptScoreDao")
	private DepartmentScoreDao deptScoreDao;

	@Resource(name = "textNewsServiceImpl")
	private TextNewsService textNewsService;

	@Resource(name = "checkStandardServiceImpl")
	private CheckStandardService checkStandardService;
	
	@Override
	public DepartmentScore getDeptScoreDetail(String newsId) {
		return deptScoreDao.getDeptScoreDetail(newsId);
	}

	@Override
	public List<DepartmentScore> getDeptScoreList(String deptName, String deptId) {
		return deptScoreDao.getDeptScoreList(deptName, deptId);
	}

	/***
	 * 删除积分信息 by llj
	 */
	@Override
	public void delete(String newsId) {
		//删除积分
		deptScoreDao.delDeptScore(newsId);
	}
	
	/***
	 * 删除积分信息 by llj
	 */
	@Override
	public void deletebyScore(String newsId) {
		//删除积分
		deptScoreDao.delbyScoreDeptScore(newsId);
	}

	// 网站信息计分
	@Override
	public void addSiteScore(String newsId, String deptId, String deptName,
			ScoreItem scoreItem) {

	}

	// 政务信息计分
	@Override
	public void addGovScore(String newsId, String deptId, String deptName,
			ScoreItem scoreItem) {

	}

	// 投稿时计分
	@Override
	public void sendInfoScore(TextNews textNews) {
		DepartmentScore deptScore = new DepartmentScore();
		String newsTag = textNews.getNewsTagsStr();

		//String ispublic = textNews.getIsPublic();
		String flag = textNews.getFlag();
		deptScore.setCreateDate(DateUtil.getCurrentDateStr());
		deptScore.setDeptName(textNews.getDeptName());
		deptScore.setDeptId(textNews.getEntryDept());
		deptScore.setNewsId(textNews.getNewsId());

		if (newsTag.indexOf("SiteNews") >= 0 || newsTag.equals("SiteNews")) {
			if (flag.equals("1")||flag.equals("9")) {
				deptScore.setScore(checkStandardService.getCheckStandardDetail("sendSite").getScore());
				deptScore.setScoreId(UUID.randomUUID().toString()
						.replace("-", ""));
				deptScore.setScoreType("SiteNews");
				deptScore.setScoreInfo("sendSite");// 表示投稿
				deptScoreDao.save(deptScore);
				logger.debug("网站信息得分" + deptScore.getScore());
			}
			/*if (ispublic.equals("1")) {
				deptScore.setScore(checkStandardService.getCheckStandardDetail("isPublic").getScore());
				deptScore.setScoreId(UUID.randomUUID().toString()
						.replace("-", ""));
				deptScore.setScoreType("SiteNews");
				deptScore.setScoreInfo("isPublic");// 表示投稿
				deptScoreDao.save(deptScore);
				logger.debug("上网得分" + deptScore.getScore());
			}*/

		}
		if (newsTag.indexOf("GovNews") >= 0 || newsTag.equals("GovNews")) {

			if (flag.equals("1")) {
				deptScore.setScore(checkStandardService.getCheckStandardDetail("sendGov").getScore());
				deptScore.setScoreId(UUID.randomUUID().toString()
						.replace("-", ""));
				deptScore.setScoreType("GovNews");
				deptScore.setScoreInfo("sendGov");// 表示投稿
				deptScoreDao.save(deptScore);
			}
			logger.debug("政务信息投稿得分" + deptScore.getScore());
		}

	}

	// 政务信息采编时计分
	@Override
	//TODO:配置事务
	public void govInfoScore(TextGovInfo textGovInfo) {

		String adoptType = textGovInfo.getAdoptType();

		String newsId = textGovInfo.getNewsId();
		String deptId = textNewsService.getTextNewsDetail(newsId).getEntryDept();


		if(StringUtils.isNotBlank(adoptType)){
			String[] adoptTypes = adoptType.split(",");
			List<CheckStandard> standardList = checkStandardService.queryCheckStandard(adoptTypes);
			if(standardList != null && standardList.size() > 0){
				//删除积分
				deptScoreDao.delDeptScore(newsId);
				//重新积分
				for (CheckStandard csd : standardList) {
					List<DepartmentScore> deptScoreList = deptScoreDao.queryScoreByCodeAndNewsId(csd.getCode(), newsId);
					if(!(deptScoreList != null && deptScoreList.size() > 0)){
						DepartmentScore deptScore = new DepartmentScore();
						deptScore.setCreateDate(DateUtil.getCurrentDateStr());
						deptScore.setDeptId(deptId);
						deptScore.setDeptName(textGovInfo.getEntryDept());
						deptScore.setNewsId(newsId);
						deptScore.setScoreInfo(csd.getCode());
						deptScore.setScore(csd.getScore());
						deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
						deptScore.setScoreType("GovNews");
						deptScoreDao.save(deptScore);
					}
				}
			}
		}
		
	}
//	// 政务信息采编时计分
//	@Override
//	public void govInfoScore(TextGovInfo textGovInfo) {
//		
//		DepartmentScore deptScore = new DepartmentScore();
//		String adoptType = textGovInfo.getAdoptType();
//		
//		deptScore.setCreateDate(DateUtil.getCurrentDateStr());
//		String newsId = textGovInfo.getNewsId();
//		String deptId = textNewsService.getTextNewsDetail(newsId).getEntryDept();
//		
//		deptScore.setDeptId(deptId);
//		deptScore.setDeptName(textGovInfo.getEntryDept());
//		deptScore.setNewsId(textGovInfo.getNewsId());
//		
//		if(StringUtils.isNotBlank(adoptType)){
//			String[] adoptTypes = adoptType.split(",");
//			List<CheckStandard> standardList = checkStandardService.queryCheckStandard(adoptTypes);
//			if(standardList != null && standardList.size() > 0){
//				for (CheckStandard csd : standardList) {
//					deptScore.setScoreInfo(csd.getCode());
//					deptScore.setScore(csd.getScore());
//					deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
//					deptScore.setScoreType("GovNews");
//					deptScoreDao.save(deptScore);
//				}
//			}
//		}
//		
//		// 每日信息
//		if (adoptType.indexOf("dailyInfo") >= 0) {
//			deptScore.setScoreInfo("dailyInfo");
//			deptScore.setScore(checkStandardService.getCheckStandardDetail("dailyInfo").getScore());
//			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
//			deptScore.setScoreType("GovNews");
//			deptScoreDao.save(deptScore);
//		}
//		// 专题信息
//		if (adoptType.indexOf("subjectInfo") >= 0) {
//			deptScore.setScoreInfo("subjectInfo");
//			deptScore.setScore(checkStandardService.getCheckStandardDetail("subjectInfo").getScore());
//			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
//			deptScore.setScoreType("GovNews");
//			deptScoreDao.save(deptScore);
//		}
//	}

	// 政务信息上报时计分
	@Override
	public void govInfoReportScore(TextGovInfo textGovInfo) {

		DepartmentScore deptScore = new DepartmentScore();
		String adoptType = textGovInfo.getAdoptType();

		deptScore.setCreateDate(DateUtil.getCurrentDateStr());
		String newsId = textGovInfo.getNewsId();
		String deptId = textNewsService.getTextNewsDetail(newsId)
				.getEntryDept();

		deptScore.setDeptId(deptId);
		deptScore.setDeptName(textGovInfo.getEntryDept());
		deptScore.setNewsId(textGovInfo.getNewsId());
		deptScore.setScoreType("GovNews");
		deptScore.setScore(10);
		// 交通部
		if (adoptType.indexOf("trafficDept") >= 0) {
			deptScore.setScoreInfo("trafficDept");
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
		// 市委    
		if (adoptType.indexOf("cityCommittee") >= 0) {
			deptScore.setScoreInfo("cityCommittee");
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
		// 市府 
		if (adoptType.indexOf("cityGovernment") >= 0) {
			deptScore.setScoreInfo("cityGovernment");
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
		// 被市领导批示 
		
		if (adoptType.indexOf("govLeaderAppr") >= 0) {
			deptScore.setScoreInfo("govLeaderAppr");
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
		// 被委领导批示 
		if (adoptType.indexOf("comLeaderAppr") >= 0) {
			deptScore.setScoreInfo("comLeaderAppr");
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
		//被交通部领导批示
		if (adoptType.indexOf("trafLeaderAppr") >= 0) {
			deptScore.setScoreInfo("trafLeaderAppr");
			deptScore.setScore(checkStandardService.getCheckStandardDetail("trafLeaderAppr").getScore());
			deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
			deptScoreDao.save(deptScore);
		}
	}

	/**
	 * 获取部门得分 加总 20140619
	 * 
	 * @author H2602965
	 */
	@Override
	public List<DepartmentScore> getScoreSumList(String scoreType,
			Date createDateS, Date createDateE) {
		return deptScoreDao
				.getScoreSumList(scoreType, createDateS, createDateE);
	}

	/**
	 * 获取部门得分 ,按照新闻和政务信息分类
	 */
	@Override
	public List<DepartmentScore> getScoreSumList(String scoreType) {
		return deptScoreDao.getScoreSumList("", null, null);
	}

	@Override
	public void sendVideo(VideoNews videoNews) {
		DepartmentScore deptScore = new DepartmentScore();
		
		deptScore.setScoreId(UUID.randomUUID().toString().replace("-", ""));
		deptScore.setDeptId(videoNews.getEntryDeptId());
		deptScore.setDeptName(videoNews.getEntryDeptName());
		deptScore.setIsDel("0");
		deptScore.setNewsId(videoNews.getVideoId());
		deptScore.setCreateDate(videoNews.getCreateDate());
		deptScore.setScore(checkStandardService.getCheckStandardDetail("sendVideo").getScore());
		deptScore.setScoreType("VideoNews");
		deptScore.setScoreInfo("sendVideo");// 表示投稿
		deptScoreDao.save(deptScore);
	}
}
