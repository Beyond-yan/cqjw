package com.gdssoft.cqjt.pojo;


import com.google.gson.annotations.Expose;

/**
 * 资料下载
 * @author  gyf 20140612
 * @return
 */
public class FileDownload extends BasePojo{
	@Expose
	private String fileId;  //资料id
	@Expose
	private String fileName;  //资料名称
	@Expose
	private String fileDesc;  //资料描述
	@Expose
	private String filePath;  //资料路径
	@Expose
	private String isDel;    //是否删除  1未删除   0删除
	@Expose
	private String createDate;  //创建时间
	@Expose
	private String createBy;   //创建人
	@Expose
	private String isTop;   //是否置顶
	@Expose
	private String attachment;   //附件
	@Expose
	private String fileSize;   //文件大小
	@Expose
	private String path;   //文件大小
	@Expose
	private int fileType;   //类型：0水位，1气象
	@Expose
	private String fileTypeString;   //类型：0水位，1气象
	
	public String getFileTypeString() {
		if(fileType == 0){
			fileTypeString = "水位";
		} else {
			fileTypeString = "气象";
		}
		return fileTypeString;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIsTop() {
		return isTop;
	}
	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
