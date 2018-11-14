package com.gdssoft.cqjt.service.content;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.content.Magazine;

public interface MagazineService {
	/**
	 * 根据magazineId查询资料   
	 * @param magazineId
	 * @return
	 */
	public Magazine getMagazineByMagazineId(String magazineId);
	
	/**
	 * 更新杂志刊物信息
	 * @param 
	 */
	public void updateMagazine(Magazine magazine);
	
	/**
	 * 保存信息
	 * @param magazine
	 * @author 
	 */
	public void save(Magazine magazine);
	
	/**
	 * 根据日期范围查询视频信息
	 * @author H2602965
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List<Magazine> getMagazineList( Date entryDateS, Date entryDateE,int pageIndex,int pageSize);
	/**
	 * 默认查询视频审核信息
	 * @author H2602965
	 * @param entryDateS
	 * @param entryDateE
	 * @param flag
	 * @return
	 */
	List<Magazine> getMagazineList(String title, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
}

