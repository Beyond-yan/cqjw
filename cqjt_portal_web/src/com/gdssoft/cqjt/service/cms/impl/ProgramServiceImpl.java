package com.gdssoft.cqjt.service.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gdssoft.cqjt.dao.cms.ProgramDao;
import com.gdssoft.cqjt.pojo.cms.Program;
import com.gdssoft.cqjt.service.cms.ProgramService;

/**
 * @author Jimxu
 */
@Service("programService")
public class ProgramServiceImpl implements ProgramService {

	@Resource(name="programDao")
	private ProgramDao programDao;
	
	protected transient final Log logger = LogFactory.getLog(getClass());

	@Override
	public List<Program> getAll(String type) {
		return programDao.getAll(type);
	}

}
