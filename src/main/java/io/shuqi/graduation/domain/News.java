package io.shuqi.graduation.domain;

import java.util.Date;

public class News {

	/**
	 * 基本信息
	 */
	//id
	private Long id;
	//标题
	private String title;
	//内容
	private String content;
	//热门新闻
	private boolean hot = true;
	//来源
	private String source;
	//类型
	private String type;
	//阅读次数
	private Long readTimes =0L;
	//新闻的展示图片
	private String images;
	//新闻的关键字，用于搜索
	private String newsKey;
	//是否可查看
	private boolean access = true;
	//评论次数
	private Long commentsCount = 0L;
	//创建时间
	private Date createTime;
	//更新时间
	private Date modifyTime;
	
	/**
	 * 关联信息
	 */
	//发布人
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

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getNewsKey() {
		return newsKey;
	}

	public void setNewsKey(String newsKey) {
		this.newsKey = newsKey;
	}

	public boolean isAccess() {
		return access;
	}

	public void setAccess(boolean access) {
		this.access = access;
	}

	public Long getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Long commentsCount) {
		this.commentsCount = commentsCount;
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public News(String title, String content, String images, String newsKey, Date createTime, Date modifyTime, User createUser) {
		super();
		this.title = title;
		this.content = content;
		this.images = images;
		this.newsKey = newsKey;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createUser = createUser;
	}

	public News() {
		super();
	}
	
	
}
