package com.gdssoft.cqjt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdssoft.cqjt.dao.FileDownloadDao;
import com.gdssoft.cqjt.pojo.FileDownload;
import com.gdssoft.cqjt.service.FileDownloadService;
/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */

@Service("fileDownloadService")
public class FileDownloadServiceImpl implements FileDownloadService{
	@Autowired
	@Resource(name = "fileDownloadDao")
	private FileDownloadDao fileDownloadDao;
	
	public List<FileDownload> getAllFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize){
		return fileDownloadDao.getAllFile(fileName,startDate,endDate,isDel,pageIndex,pageSize);
	}

	@Override

	public FileDownload getFileByFileId(String fileId) {
		return fileDownloadDao.getFileByFileId(fileId);
	}

	@Override
	public void updateFile(FileDownload fileDownload) {
		fileDownloadDao.updateFile(fileDownload);
	}

	/*@Override
	public void deleteFile(Long fileId) {
		fileDownloadDao.deleteFile(fileId);
	}*/
	public void save(FileDownload fileDownload) {
		fileDownloadDao.insertFile(fileDownload);
		
	}
	/*获取时间倒序的后10条
	 */
	public List<FileDownload> getFile(String fileName,Date startDate,Date endDate,String isDel,int pageIndex, int pageSize){
		return fileDownloadDao.getFile(fileName,startDate,endDate,isDel,pageIndex,pageSize);
	}
}
