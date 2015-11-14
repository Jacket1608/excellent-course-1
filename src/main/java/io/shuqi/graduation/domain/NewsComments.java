package io.shuqi.graduation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewsComments {

	/**
	 * 评论的基本信息
	 */
	//id
	private Long id;
	//评论类容
	private String content;
	//评论时间
	private Date createTime;
	//是否允许查看
	private boolean access = true;
	/**
	 * 评论的关联信息
	 */
	//新闻
	private News news;
	//子评论
	private Set<NewsComments> children = new HashSet<NewsComments>(0);
	//父评论
	private NewsComments parent;
	//评论人
	private User createUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public Set<NewsComments> getChildren() {
		return children;
	}
	public void setChildren(Set<NewsComments> children) {
		this.children = children;
	}
	public NewsComments getParent() {
		return parent;
	}
	public void setParent(NewsComments parent) {
		this.parent = parent;
	}
	public User getCreateUser() {
		return this.createUser;
	}
	public void setCreateUser(User user) {
		this.createUser = user;
	}
	public NewsComments(String content, Date createTime, News news,User createUser) {
		super();
		this.content = content;
		this.createTime = createTime;
		this.news = news;
		this.createUser = createUser;
	}
	public NewsComments() {
		super();
	}
	
	
}
