package com.gdssoft.cqjt.dao.cms;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.gdssoft.cqjt.pojo.cms.Program;

/**
 * 外网栏目Dao
 * @author F3219058
 */
@Repository("programDao")
public class ProgramDao {
	
	@Autowired
	@Resource(name = "cmsSqlMapClientTemplate")
	private SqlMapClientTemplate cmsSqlMapClientTemplate;

	@SuppressWarnings("unchecked")
	public List<Program> getAll(String type){
		return cmsSqlMapClientTemplate.queryForList("Program.getAll",type);
	}	
	
}
