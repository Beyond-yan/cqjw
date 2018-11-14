package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
 




import com.gdssoft.cqjt.pojo.TextGovInfo;
import com.gdssoft.cqjt.pojo.TextGovReport;
import com.gdssoft.cqjt.pojo.report.GovNewsReport;

public interface TextGovInfoService {
	
	public List<TextGovInfo> getTextGovInfoList(String newsTitle, Date entryDateS, Date entryDateE, String status,int pageIndex, int pageSize);
	public void save(TextGovInfo textGovInfo);

	public TextGovInfo getTextGovInfo(String giId);
	public TextGovInfo getTextInfo(String newsId);
	public List<TextGovInfo> getTextGovInfoByNewsId(String newsId);
	public List<TextGovInfo> getTextGovInfoListByNewsId(String newsId);
	
	public  List<TextGovInfo> getGov(String pubId);
	
	//政务上报管理编辑模块    保存功能 H2602965 2014.06.05
	public void update(TextGovInfo textGovInfo);
	
	//政务上报管理删除模块  h2902992 20140606
	public void deleteInfoReport(TextGovInfo textGovInfo);
	
	public void savePubDetail(String[] pubIdArray, String[] sequenceNumArray);

	
	/**
	 * 获取没有关联刊物的已采用的政务信息
	 * @param titleKey 标题筛选关键字
	 * @param startDate 起始日期
	 * @param endDate 结束日期
	 * @return
	 */
	List<TextGovInfo> getUnPubTextGovInfoList(String titleKey, Date startDate, Date endDate);
	
	/**
	 * 用于政务信息报表查询
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<TextGovReport> getGovReport(Date startDate, Date endDate, String status);
	

	
	/**
	 * 导出excel
	 * @param textGovReportList
	 * @return
	 */
	public void exportGovInfoReportListAll(List<TextGovReport> textGovReportList,String start,String end,HttpServletResponse response);

	/**
	 * 刊物编辑对应政务信息删除   gyf 2014.07.23
	 * @param textGovInfo
	 */
	public void deleteGovPubRef(TextGovInfo textGovInfo);
	/**
	 * 政务信息回收站复原
	 * @param textGovInfo
	 */
	public void reUpdate(TextGovInfo textGovInfo);
	/**
	 * 合并信息
	 * @param textGovInfo
	 */
	public void mergeInformation(String newsId,String isSelected);
	
	/**
	 * 合并新闻
	 * @param textGovInfo
	 */
	public void mergeNews(String newsId,String mainNewsId);
	
	/**
	 * 查询政务信息统计
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 */
	public List<TextGovReport> queryGovReportStat(Date startDate, Date endDate, String status);
	
	/***
	 * 通过gidi删除政务信息 by llj
	 * @param giId
	 */
	public void delGovInfoById(String giId);
	
	public Map<String,Float> getCheckStandardScore();
	
	public List<GovNewsReport> queryGovNewsReport(Date startDate, Date endDate, String status, Integer isUse);
	/**
	 * 查询当年1号至开始时间的上报条数
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @param isUse
	 * @return
	 */
	public Map<Integer,Integer> queryDeptShangbaoStat(Date startDate, Date endDate, String status, Integer isUse);
	
	/**
	 * 政务信息报送和政务信息采编同步内容
	 * @param textGovInfo
	 */
	public void syncGovInfoContent(TextGovInfo textGovInfo);
	
}
