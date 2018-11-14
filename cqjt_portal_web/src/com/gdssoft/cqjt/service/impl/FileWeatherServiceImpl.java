package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.FileWeatherDao;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.service.FileWeatherService;
/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */

@Service("fileWeatherService")
public class FileWeatherServiceImpl implements FileWeatherService{
	@Autowired
	@Resource(name = "fileWeatherDao")
	private FileWeatherDao fileWeatherDao;
	
	public List<FileDownload> getAllFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize,String fileType){
		return fileWeatherDao.getAllFile(fileName,startDate,endDate,isDel,pageIndex,pageSize,fileType);
	}

	@Override

	public FileDownload getFileByFileId(String fileId) {
		return fileWeatherDao.getFileByFileId(fileId);
	}

	@Override
	public void updateFile(FileDownload fileDownload) {
		fileWeatherDao.updateFile(fileDownload);
	}

	/*@Override
	public void deleteFile(Long fileId) {
		fileDownloadDao.deleteFile(fileId);
	}*/
	public void save(FileDownload fileDownload) {
		fileWeatherDao.insertFile(fileDownload);
		
	}
	/*获取时间倒序的后10条
	 */
	public List<FileDownload> getFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize,String fileType){
		return fileWeatherDao.getFile(fileName,startDate,endDate,isDel,pageIndex,pageSize,fileType);
	}
}
