package com.gdssoft.cqjt.dao.content;

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
import com.gdssoft.cqjt.pojo.content.TextData;

/**
 * 文字类信息管理
 * @author  gyf 20140709
 * @return
 */
@Service("textDataDao")
public class TextDataDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<TextData> getAllTextData(String title,String author,Date startDate,Date endDate,
			String isDel,int pageIndex,int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("author", author);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);
		
		return (List<TextData>) this.sqlMapClientTemplate
				.queryForList("TextData.getTextDataAll", map, pageIndex, pageSize);

	}
	
	/**
	 * 根据Id查询   
	 * @param categoryId
	 * @return
	 */
	public TextData getTextDataById(String textId){
		return  (TextData) this.sqlMapClientTemplate.queryForObject("TextData.getTextDataById",textId);
	}
	
	/**
	 * 更新   
	 * @param textData
	 */
	public void updateTextData(TextData textData){
		logger.debug("id："+textData.getTextId()+",是否删除："+textData.getIsDel()+",Status:"+textData.getStatus());
		this.sqlMapClientTemplate.update("TextData.updateTextData", textData);
	}

	/**
	 * 更新   状态
	 * @param textData
	 */
	public void updateStatus(TextData textData){
		logger.debug("id："+textData.getTextId()+",Status:"+textData.getStatus());
		this.sqlMapClientTemplate.update("TextData.updateStatus", textData);
	}
	
	/**
	 * 保存
	 */
	public void insertTextData(TextData textData) {
		this.sqlMapClientTemplate.insert("TextData.addTextData",textData);
	}
}
