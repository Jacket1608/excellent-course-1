package io.shuqi.graduation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ClassDetailComments {

	private Long id;
	private String content;
	private Date createTime;
	
	/**
	 * 关联
	 */
	private User createUser;
	private ClassDetailComments parents;
	private Set<ClassDetailComments> children = new HashSet<ClassDetailComments>(0);
	private ClassDetail classDetail;
	private CourseClass courseClass;
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
	public User getCreateUser() {
		return createUser;
	}
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	public ClassDetailComments getParents() {
		return parents;
	}
	public void setParents(ClassDetailComments parents) {
		this.parents = parents;
	}
	public Set<ClassDetailComments> getChildren() {
		return children;
	}
	public void setChildren(Set<ClassDetailComments> children) {
		this.children = children;
	}
	public ClassDetail getClassDetail() {
		return classDetail;
	}
	public void setClassDetail(ClassDetail classDetail) {
		this.classDetail = classDetail;
	}
	public CourseClass getCourseClass() {
		return courseClass;
	}
	public void setCourseClass(CourseClass courseClass) {
		this.courseClass = courseClass;
	}
	public ClassDetailComments(String content, Date createTime, User createUser, ClassDetailComments parents, ClassDetail classDetail, CourseClass courseClass) {
		super();
		this.content = content;
		this.createTime = createTime;
		this.createUser = createUser;
		this.parents = parents;
		this.classDetail = classDetail;
		this.courseClass = courseClass;
	}
	public ClassDetailComments() {
		super();
	}
	
	
	
}
