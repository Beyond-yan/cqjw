package com.gdssoft.cqjt.service.content;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.content.EmpShow;
public interface EmpShowService {
	/**
	 * 根据输入条件查询
	 * @author H2603282
	 * @param EmpName
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List <EmpShow> getEmpList(String EmpName, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize,String deptName,String categoryName);
	/**
	 * 更新图片内容
	 * @param EmpShow
	 */
	public void updateEmpShow(EmpShow EmpShow);
	
	/**
	 * 根据EmpId查询资料   
	 * @param EmpId
	 * @return
	 */
	public EmpShow getEmpByEmpId(String EmpId);
	
	/**
	 * 保存信息
	 * @param EmpShow
	 * @author 
	 */
	public void save(EmpShow EmpShow);
	void updateEmpSort(String empSort, String empId);

	List<EmpShow> queryCatNameList(String EmpName, String entryUser, Date entryDateS, Date entryDateE, String[] flag,
			int pageIndex, int pageSize, String deptName, String categoryName);
	EmpShow queryEmp(String empId);

}
