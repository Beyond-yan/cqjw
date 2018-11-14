package com.gdssoft.cqjt.dao.content;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.content.Picture;

@Service("PictureNewsDAO")
public class PictureNewsDAO { 
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	/**
	 * 图片信息查询
	 * @author H2603282
	 * @param picTitle 
	 * @param entryUser  投稿人关键字
	 * @param entryDateS 投稿起始日期
	 * @param entryDateE 投稿结束日志
	 * @param flag 状态标识(0 草稿,1 已投稿,2 已审核)
	 * @param isDel 删除标识(0 未删除,1 已删除 )
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Picture> getPicList(String picTitle, String entryUser,Date entryDateS, Date entryDateE,
			String[] flag,String isDel,int pageIndex,int pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("picTitle", picTitle);
		map.put("entryUser", entryUser);
		map.put("entryDateS", entryDateS);
		map.put("entryDateE", entryDateE);
		map.put("flag", flag);
		map.put("isDel", isDel);
		
		
		return (List<Picture>) this.sqlMapClientTemplate
				.queryForList("Picture.getAllPicture", map, pageIndex, pageSize);
		
	}
	/**
	 * 更新图片信息
	 * @param picture
	 */
	public void updatePicture(Picture picture){
		//logger.debug("资料id："+picture.getPicId()+",资料是否删除："+picture.getIsDel());
		this.sqlMapClientTemplate.update("Picture.updatePic", picture);
	}
	
	/**
	 * 新增图片信息
	 * @param picture
	 */
	public void insertPicture(Picture picture) {
		this.sqlMapClientTemplate.insert("Picture.insertPic", picture);
	}
	
	/**
	 * 根据picId查询图片信息
	 * @param picId
	 * @return
	 */
	public Picture getPicByPicId(String picId){
		return (Picture) this.sqlMapClientTemplate.queryForObject("Picture.getPicByPicId",picId);
	}
	
}
