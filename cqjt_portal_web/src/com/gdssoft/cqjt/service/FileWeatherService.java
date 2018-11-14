package com.gdssoft.cqjt.service;

import java.util.Date;
import java.util.List;

import com.gdssoft.cqjt.pojo.FileDownload;
/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */
public interface FileWeatherService {
	/**
	 * 查询所有资料
	 * @param fileName
	 * @param isDel
	 * @return
	 */
	public List<FileDownload> getAllFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize, String fileType);

	/**
	 * 根据fileId查询资料   
	 * @param fileId
	 * @return
	 */
	public FileDownload getFileByFileId(String fileId);
	/**
	 * 更新资料
	 * @param fileDownload
	 */
	public void updateFile(FileDownload fileDownload);
	/**
	 * 逻辑删除资料
	 * @param fileId
	 
	public void deleteFile(String fileId);*/

	public void save(FileDownload fileDownload);

	/**
	 * 查询所有后10条记录
	 * @param fileName
	 * @param isDel
	 * @return
	 */
	public List<FileDownload> getFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize, String fileType);

}
