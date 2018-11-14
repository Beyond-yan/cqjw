package com.gdssoft.cqjt.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdssoft.core.dao.MySqlMapClientTemplate;
import com.gdssoft.cqjt.pojo.FileDownload;

/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */
@Service("fileDownloadDao")
public class FileDownloadDao {
	@Autowired
	@Resource(name = "sqlMapClientTemplate")
	private MySqlMapClientTemplate sqlMapClientTemplate;
	
	protected transient final Log logger = LogFactory.getLog(getClass());
	/**
	 * 查询所有资料   updateFile
	 */
	@SuppressWarnings("unchecked")
	public List<FileDownload> getAllFile(String fileName,Date startDate,Date endDate,String isDel, int pageIndex, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileName", fileName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);
		
		return (List<FileDownload>) sqlMapClientTemplate
				.queryForList("FileDownload.getFileAll", map, pageIndex, pageSize);
				
	}
	
	/**
	 * 根据fileId查询资料   
	 * @param fileId
	 * @return
	 */
	public FileDownload getFileByFileId(String fileId){
		return  (FileDownload) this.sqlMapClientTemplate.queryForObject("FileDownload.getFileByFileId",fileId);
	}
	
	/**
	 * 更新资料   
	 * @param fileDownload
	 */
	public void updateFile(FileDownload fileDownload){
		logger.debug("资料id："+fileDownload.getFileId()+",资料是否删除："+fileDownload.getIsDel());
		this.sqlMapClientTemplate.update("FileDownload.updateFile", fileDownload);
	}
	
	/**
	 * 逻辑删除资料
	 * @param fileId
	 
	public void deleteFile(Long fileId){
		this.sqlMapClientTemplate.update("FileDownload.deleteFile", fileId);
	}*/

	/**
	 * 保存上传资料
	 */
	public void insertFile(FileDownload fileDownload) {
		this.sqlMapClientTemplate.insert("FileDownload.addFile",fileDownload);
	}
	/**
	 * 查询后10条记录资料   
	 */
	@SuppressWarnings("unchecked")
	public List<FileDownload> getFile(String fileName,Date startDate,Date endDate,String isDel, int pageIndex, int pageSize){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileName", fileName);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("isDel", isDel);
		
		return (List<FileDownload>) sqlMapClientTemplate
				.queryForList("FileDownload.getFile", map, pageIndex, pageSize);
				
	}

}
