package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.DepartmentScore;
import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextNews;
import com.gdssoft.cqjt.pojo.enums.ScoreItem;
import com.gdssoft.cqjt.pojo.videoNews.VideoNews;

public interface DepartmentScoreService {
	
	DepartmentScore getDeptScoreDetail(String newsId);
	List<DepartmentScore> getDeptScoreList(String deptName, String deptId);
	public void addSiteScore(String newsId, String deptId, String deptName, ScoreItem scoreItem);
	public void addGovScore(String newsId, String deptId, String deptName, ScoreItem scoreItem);
	/**
	 * 删除计分记录
	 * @author H2602975
	 * @param scoreId
	 */
	public void delete(String scoreId);
	/**
	 * 投稿时的计分
	 * @author H2602975
	 * @param textNews
	 */
	public void sendInfoScore(TextNews textNews);
	/**
	 * 政务信息采编时计分
	 * @author H2602975
	 * @param textGovInfo
	 */
	public void govInfoScore(TextGovInfo textGovInfo);
	/**
	 * 政务信息上报管理中上报时计分
	 * @author H2602975
	 * @param textGovReportInfo 
	 */
	public void govInfoReportScore(TextGovInfo textGovReportInfo);

	/**
	 * 按照时间间隔,得分类型查询总分
	 * @author H2602965
	 * @param scoreType
	 * @param createDateS
	 * @param createDateE
	 * @return
	 */
	List<DepartmentScore> getScoreSumList(String scoreType,Date createDateS, Date createDateE);
	/**
	 * 根据得分类型查询总分
	 * @author H2602965
	 * @param scoreType
	 * @return
	 */
	
	List<DepartmentScore> getScoreSumList(String scoreType);
	
    public void sendVideo(VideoNews videoNews);
    
	public void deletebyScore(String newsId);
}
