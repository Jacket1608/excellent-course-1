package io.shuqi.graduation.domain;

import java.util.Date;

/**
 * 论坛帖子
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class BbsContent {

	private Long id;
	private String title;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private Long readTimes = 0L;
	private boolean top = false;
	private boolean access = true;
	//附件Id
	private String attachIds;
	/**
	 * 关联版块
	 */
	private User createUser;
	private BbsBlock bbsBlock;
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
	public Long getReadTimes() {
		return readTimes;
	}
	public void setReadTimes(Long readTimes) {
		this.readTimes = readTimes;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public String getAttachIds() {
		return attachIds;
	}
	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public BbsBlock getBbsBlock() {
		return bbsBlock;
	}
	public void setBbsBlock(BbsBlock bbsBlock) {
		this.bbsBlock = bbsBlock;
	}
	public BbsContent(String title, String content, Date createTime, Date modifyTime, String attachIds, User createUser, BbsBlock bbsBlock) {
		super();
		this.title = title;
		this.content = content;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.attachIds = attachIds;
		this.createUser = createUser;
		this.bbsBlock = bbsBlock;
	}
	public BbsContent() {
		super();
	}
	
	
}
