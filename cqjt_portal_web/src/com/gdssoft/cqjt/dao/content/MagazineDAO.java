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
import com.gdssoft.cqjt.pojo.content.Magazine;

@Service("magazineDao")
public class MagazineDAO {

	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	/**
	 * 根据magazineId查询资料   
	 * @param magazineId
	 * @return
	 */
	public Magazine getMagazineByMagazineId(String magazineId){
		return(Magazine) this.sqlMapClientTemplate.queryForObject("Magazine.getMagazineByMagazineId",magazineId);
	}
	
	/**
	 * 更新杂志刊物信息
	 * @param 
	 */
	public void updateMagazine(Magazine magazine){
		logger.debug("资料id："+magazine.getMagazineId()+",资料是否更新："+magazine.getFlag());
	this.sqlMapClientTemplate.update("Magazine.updateMagazine", magazine);
		
	}
	

	/**
	 * 保存上传资料
	 */
	public void insertMagazine(Magazine magazine) {
		this.sqlMapClientTemplate.insert("Magazine.insertMagazine",magazine);
	}
	
	/**
	 * 视频查询
	 * @author H2603282
	 * @param title 标题关键字
	 * @param entryUser  投稿人关键字
	 * @param entryDateS 投稿起始日期
	 * @param entryDateE 投稿结束日志
	 * @param flag 状态标识(0 草稿,1 已投稿,2 已审核)
	 * @param isDel 删除标识(0 未删除,1 已删除 )
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Magazine> getMagazineList(String title, String entryUser,Date entryDateS, Date entryDateE,String[] flag,
			String isDel,int pageIndex,int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("title", title);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		
		return (List<Magazine>) this.sqlMapClientTemplate
				.queryForList("Magazine.getAllMagazine", map, pageIndex, pageSize);
		
}
}
