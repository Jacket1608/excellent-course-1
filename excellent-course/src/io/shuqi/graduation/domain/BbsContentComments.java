package io.shuqi.graduation.domain;

import java.util.Date;

/**
 * 论坛帖子回复表
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class BbsContentComments {

	private Long id;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private boolean access =true;
	/**
	 * 关联关系
	 */
	private BbsContent bbsContent;
	private User createUser;
	private BbsBlock bbsBlock;
	
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
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public BbsContent getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(BbsContent bbsContent) {
		this.bbsContent = bbsContent;
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
	public BbsContentComments(String content, Date createTime, Date modifyTime, BbsContent bbsContent, User createUser, BbsBlock bbsBlock) {
		super();
		this.content = content;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.bbsContent = bbsContent;
		this.createUser = createUser;
		this.bbsBlock = bbsBlock;
	}
	public BbsContentComments() {
		super();
	}
	
	
}
