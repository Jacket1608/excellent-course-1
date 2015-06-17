package io.shuqi.graduation.domain;

import java.util.Date;

public class Attach {
	private Long id;
	private String fileType;
	private Date createTime;
	private String filePath;
	private String fileName;
	private Long downloadCount = 0L;
	private String fileImages;
	/**
	 * 关联属性
	 */
	//来源
	private String fromName;
	//来源id
	private Long fromId;
	//来源子id
	private Long fromSubId;
	//附件的创建人
	private User createUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public Long getFromId() {
		return fromId;
	}
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	public Long getFromSubId() {
		return fromSubId;
	}
	public void setFromSubId(Long fromSubId) {
		this.fromSubId = fromSubId;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public Attach() {
		super();
	}
	
	public Long getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getFileImages() {
		return fileImages;
	}
	public void setFileImages(String fileImages) {
		this.fileImages = fileImages;
	}
	public Attach(String fileType, Date createTime, String filePath, String fileName, String fileImages, String fromName, Long fromId, Long fromSubId, User createUser) {
		super();
		this.fileType = fileType;
		this.createTime = createTime;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileImages = fileImages;
		this.fromName = fromName;
		this.fromId = fromId;
		this.fromSubId = fromSubId;
		this.createUser = createUser;
	}

	
}
