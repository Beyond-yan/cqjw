/**
 * 
 */
package com.gdssoft.cqjt.dao;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.Department;
import com.gdssoft.cqjt.pojo.RedBlackQueryRecord;
import com.gdssoft.cqjt.pojo.TextCategory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lgaoyi
 * @date: 2018/6/21 18:13
 */
@Repository("redBlackQueryRecordDao")
public class RedBlackQueryRecordDao {

	protected transient final Log logger = LogFactory.getLog(getClass());

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;


	/**
	 * 保存
	 *
	 * @param pojo
	 */
	public void insert(RedBlackQueryRecord pojo) {
		this.sqlMapClientTemplate.insert("RedBlackQueryRecord.insert", pojo);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	public void delete(String id){
		this.sqlMapClientTemplate.update("RedBlackQueryRecord.delete",id);
	}

	/**
	 *
	 * 更新
	 * @param pojo
	 */
	public void update(RedBlackQueryRecord pojo){
		this.sqlMapClientTemplate.update("RedBlackQueryRecord.update", pojo);
	}

	public List<RedBlackQueryRecord> getByUserId(String userId){
		return  (List<RedBlackQueryRecord>) this.sqlMapClientTemplate.queryForList("RedBlackQueryRecord.getByUserId", userId);
	}

	/**
	 * 根据主键查询
	 *
	 * @param id
	 * @return
	 */
	public RedBlackQueryRecord getById(String id){
		return  (RedBlackQueryRecord) this.sqlMapClientTemplate.queryForObject("RedBlackQueryRecord.getById", id);
	}

	/**
	 * 分页查询数量
	 *
	 * @param redBlackQueryRecord
	 * @return
	 */
	public int queryCount(RedBlackQueryRecord redBlackQueryRecord){
		if (redBlackQueryRecord == null) {
			throw new IllegalArgumentException("Can't select by a null data object.");
		}
		Map param = new HashMap();
		param.put("redBlackQueryRecord", redBlackQueryRecord);
		Integer retObj = (Integer) this.sqlMapClientTemplate.queryForObject("RedBlackQueryRecord.queryCount", param);
		if (retObj == null) {
			return 0;
		} else {
			return retObj.intValue();
		}
	}

	/**
	 * 分页查询
	 *
	 * @param redBlackQueryRecord
	 * @param beginLimit
	 * @param endLimit
	 * @return
	 */
	public List<RedBlackQueryRecord> queryPageList(RedBlackQueryRecord redBlackQueryRecord, int beginLimit, int endLimit){
		if (redBlackQueryRecord == null) {
			throw new IllegalArgumentException("Can't select by a null data object.");
		}
		Map param = new HashMap();
		param.put("redBlackQueryRecord", redBlackQueryRecord);
		param.put("beginLimit", new Integer(beginLimit));
		param.put("endLimit", new Integer(endLimit));
		return  (List<RedBlackQueryRecord>) this.sqlMapClientTemplate.queryForList("RedBlackQueryRecord.queryPageList", param);
	}

}
