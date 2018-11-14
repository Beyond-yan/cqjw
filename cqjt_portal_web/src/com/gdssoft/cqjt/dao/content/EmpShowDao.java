package com.gdssoft.cqjt.dao.content;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.content.EmpShow;

@Repository("EmpShowDao")
public class EmpShowDao { 
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 图片信息查询
	 * @author H2603282
	 * @param EmpName 
	 * @param entryUser  投稿人关键字
	 * @param entryDateS 投稿起始日期
	 * @param entryDateE 投稿结束日志
	 * @param flag 状态标识(0 草稿,1 已投稿,2 已审核)
	 * @param isDel 删除标识(0 未删除,1 已删除 )
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmpShow> getEmpList(String EmpName, String entryUser,Date entryDateS, Date entryDateE,
			String[] flag,String isDel,int pageIndex,int pageSize,String deptName,String categoryName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("empName", EmpName);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		map.put("deptName", deptName);
		map.put("categoryName", categoryName);
		
		
		return (List<EmpShow>) this.sqlMapClientTemplate
				.queryForList("EmpShow.getAllEmpShow", map, pageIndex, pageSize);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EmpShow> queryCatNameList(String EmpName, String entryUser,Date entryDateS, Date entryDateE,
			String[] flag,String isDel,int pageIndex,int pageSize,String deptName,String categoryName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("empName", EmpName);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		map.put("deptName", deptName);
		map.put("categoryName", categoryName);
		
		
		return (List<EmpShow>) this.sqlMapClientTemplate
				.queryForList("EmpShow.getAllEmpShow", map,pageIndex,pageSize);
		
	}
	/**
	 * 更新图片信息
	 * @param EmpShow
	 */
	public void updateEmpShow(EmpShow EmpShow){
		 
		this.sqlMapClientTemplate.update("EmpShow.updateEmp", EmpShow);
	}
	
	/**
	 * 新增图片信息
	 * @param EmpShow
	 */
	public void insertEmpShow(EmpShow EmpShow) {
		this.sqlMapClientTemplate.insert("EmpShow.insertEmp", EmpShow);
	}
	
	/**
	 * 根据EmpId查询图片信息
	 * @param EmpId
	 * @return
	 */
	public EmpShow getEmpByEmpId(String EmpId){
		return (EmpShow) this.sqlMapClientTemplate.queryForObject("EmpShow.getEmpById",EmpId);
	}
	
	public void updateEmpSort(Map<String, Object> param) {
		this.sqlMapClientTemplate.update("EmpShow.updateEmpSort", param);
	}
	
	
	
}
