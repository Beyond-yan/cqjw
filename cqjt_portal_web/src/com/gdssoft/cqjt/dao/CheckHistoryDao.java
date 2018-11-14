package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.CheckHistory;

/**
 * 考核历史信息
 * @author  gyf 20150116
 * @return
 */
@Service("checkHistoryDao")
public class CheckHistoryDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	/**
	 * 查询所有资料   
	 */
	@SuppressWarnings("unchecked")
	public List<CheckHistory> getAllFile(String checkName,Date startDate,Date endDate,String isDel, int pageIndex, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("checkName", checkName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);
		
		return (List<CheckHistory>) sqlMapClientTemplate
				.queryForList("CheckHistory.getFileAll", map, pageIndex, pageSize);
				
	}
	@SuppressWarnings("unchecked")
	public List<CheckHistory> getFileList(String checkGroup,String isDel, int pageIndex, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("checkGroup", checkGroup);
		map.put("isDel", isDel);
		
		return (List<CheckHistory>) sqlMapClientTemplate
				.queryForList("CheckHistory.getFileAll", map, pageIndex, pageSize);
				
	}
	
	/**
	 * 根据checkId查询资料   
	 * @param checkId
	 * @return
	 */
	public CheckHistory getFileByFileId(String checkId){
		return  (CheckHistory) this.sqlMapClientTemplate.queryForObject("CheckHistory.getFileByFileId",checkId);
	}
	
	/**
	 * 更新资料   
	 * @param checkHistory
	 */
	public void updateFile(CheckHistory checkHistory){
		logger.debug("资料id："+checkHistory.getCheckId()+",资料是否删除："+checkHistory.getIsDel());
		this.sqlMapClientTemplate.update("CheckHistory.updateFile", checkHistory);
	}

	/**
	 * 保存上传资料
	 */
	public void insertFile(CheckHistory checkHistory) {
		this.sqlMapClientTemplate.insert("CheckHistory.addFile",checkHistory);
	}
}
