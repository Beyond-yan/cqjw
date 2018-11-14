package com.gdssoft.cqjt.service.content;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.content.Picture;
public interface PicNewsService {
	/**
	 * 根据输入条件查询
	 * @author H2603282
	 * @param picTitle
	 * @param entryDateS
	 * @param entryDateE
	 * @return
	 * 
	 */
	List <Picture> getPicList(String picTitle, String entryUser, Date entryDateS, Date entryDateE, String[] flag,int pageIndex,int pageSize);
	/**
	 * 更新图片内容
	 * @param picture
	 */
	public void updatePicture(Picture picture);
	
	/**
	 * 根据picId查询资料   
	 * @param picId
	 * @return
	 */
	public Picture getPicByPicId(String picId);
	
	/**
	 * 保存信息
	 * @param picture
	 * @author 
	 */
	public void save(Picture picture);

}
