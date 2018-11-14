package com.gdssoft.cqjt.service.content.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.content.MagazineDAO;
import com.gdssoft.cqjt.pojo.content.Magazine;
import com.gdssoft.cqjt.service.content.MagazineService;

@Service("magazineServiceImpl")
public class MagazineServiceImpl implements MagazineService {
	@Autowired
	@Resource(name = "magazineDao")
	private MagazineDAO magazineDao;

	/**
	 * 根据magazineId查询资料   
	 * @param magazineId
	 * @return
	 */
	public Magazine getMagazineByMagazineId(String magazineId){
		return magazineDao.getMagazineByMagazineId(magazineId);
	}
	
	/**
	 * 更新杂志刊物信息
	 * @param 
	 */
	public void updateMagazine(Magazine magazine){
	   magazineDao.updateMagazine(magazine);
		
	}

	/**
	 * 保存信息
	 * @param magazine
	 * @author 
	 */
        public void save (Magazine magazine){
           magazineDao.insertMagazine(magazine);
	}
	
        /**
         * @author 20140/07/08
         * 默认查询刊物杂志信息
         */
        @Override
    	public List<Magazine> getMagazineList(String title, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
    		return magazineDao.getMagazineList(title,entryUser,entryDateS,entryDateE,new String[]{},"0", pageIndex, pageSize);
    	}
        
        /**
         * @author 20140/07/08
         * 默认查询刊物杂志信息
         */
        	@Override
      public List<Magazine> getMagazineList(Date entryDateS, Date entryDateE,int pageIndex,int pageSize) {
        		return magazineDao.getMagazineList("","",entryDateS,entryDateE,new String[]{},"0", pageIndex, pageSize);
        		}
}
