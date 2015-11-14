package io.shuqi.graduation.domain;

import java.util.Date;

public class Notification {
	
	/**
	 * 基本信息
	 */
	//id
	private Long id;
	//title
	private String title;
	//content
	private String content;
	//创建时间
	private Date createTime;
	//更新时间
	private Date modifyTime;
	//公告类型type
	private String type;
	//公告阅读次数 readTimes
	private Long readTimes = 0L;
	//公告展示图片
	private String ntfImage;
	//关键字
	private String nitificationKey;
	/**
	 * 关联关系
	 */
	private User createUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Long readTimes) {
		this.readTimes = readTimes;
	}
	public String getNtfImage() {
		return ntfImage;
	}
	public void setNtfImage(String ntfImage) {
		this.ntfImage = ntfImage;
	}
	public String getNitificationKey() {
		return nitificationKey;
	}
	public void setNitificationKey(String nitificationKey) {
		this.nitificationKey = nitificationKey;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public Notification(String title, String content, Date createTime, Date modifyTime, String ntfImage, String nitificationKey, User createUser) {
		super();
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.ntfImage = ntfImage;
		this.nitificationKey = nitificationKey;
		this.createUser = createUser;
	}
	public Notification() {
		super();
	}
	
	
	
}
