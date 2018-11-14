package com.gdssoft.cqjt.service.content.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.content.EmpShowDao;
import com.gdssoft.cqjt.pojo.content.EmpShow;
import com.gdssoft.cqjt.service.content.EmpShowService;

@Service("empShowServiceImpl")
public class EmpShowServiceImpl implements EmpShowService {
	/***
	 * 查询条件查询
	 */
	@Autowired
	@Resource(name = "EmpShowDao")
	private EmpShowDao EmpShowDAO;

	@Override
	public List<EmpShow> getEmpList(String EmpName, String entryUser, Date entryDateS, Date entryDateE, String[] flag,
			int pageIndex, int pageSize,String deptName,String categoryName) {
		return EmpShowDAO.getEmpList(EmpName, entryUser, entryDateS, entryDateE, new String[] {}, "0", pageIndex,
				pageSize,deptName,categoryName);
	}
	@Override
	public List<EmpShow> queryCatNameList(String EmpName, String entryUser, Date entryDateS, Date entryDateE, String[] flag,
			int pageIndex, int pageSize,String deptName,String categoryName) {
		return EmpShowDAO.queryCatNameList(EmpName, entryUser, entryDateS, entryDateE, new String[] {}, "0", pageIndex,
				pageSize,deptName,categoryName);
	}
	
	@Override
	public EmpShow queryEmp(String empId){
		return EmpShowDAO.getEmpByEmpId(empId);
	}
	/***
	 * 保存图片的数据信息
	 */
	@Override
	public void updateEmpShow(EmpShow EmpShow) {
		EmpShowDAO.updateEmpShow(EmpShow);
	}

	/**
	 * 根据EmpId查询图片
	 * 
	 * @param EmpId
	 * @return
	 */
	public EmpShow getEmpByEmpId(String EmpId) {

		return EmpShowDAO.getEmpByEmpId(EmpId);

	}

	/**
	 * 保存信息
	 * 
	 * @param EmpShow
	 * @author
	 */
	public void save(EmpShow EmpShow) {
		EmpShowDAO.insertEmpShow(EmpShow);
	}

	@Override
	public void updateEmpSort(String empSort, String empId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("empSort", empSort);
		param.put("empId", empId);
		EmpShowDAO.updateEmpSort(param);
	}

}
