package com.gdssoft.cqjt.service.cms;

import java.util.List;
import com.gdssoft.cqjt.pojo.cms.Program;

/**
 * @author Jimxu
 */
public interface ProgramService {

	List<Program> getAll(String type);

}
