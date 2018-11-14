package com.gdssoft.cqjt.service.content.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.content.PictureNewsDAO;
import com.gdssoft.cqjt.pojo.content.Picture;
import com.gdssoft.cqjt.service.content.PicNewsService;

@Service("picNewsServiceImpl")
public class PicNewsServiceImpl implements PicNewsService {
	/***
	 * 查询条件查询
	 */
	@Autowired
	@Resource(name = "PictureNewsDAO")
	private PictureNewsDAO pictureNewsDAO;

	@Override
	public List<Picture> getPicList(String picTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize) {
		return pictureNewsDAO.getPicList(picTitle,entryUser,entryDateS,entryDateE,new String[]{},"0", pageIndex, pageSize);
	}
	
	/***
	 * 保存图片的数据信息
	 */
	@Override
	public void updatePicture(Picture  picture) {
		pictureNewsDAO.updatePicture(picture);
	}

	/**
	 * 根据picId查询图片   
	 * @param picId
	 * @return
	 */
	public Picture getPicByPicId(String picId){
		
		return pictureNewsDAO.getPicByPicId(picId);
		
	}
	
	/**
	 * 保存信息
	 * @param picture
	 * @author 
	 */
        public void save (Picture picture){
        	pictureNewsDAO.insertPicture(picture);
	}
}



