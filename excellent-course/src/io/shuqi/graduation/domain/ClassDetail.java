package io.shuqi.graduation.domain;

import java.util.Date;

public class ClassDetail {

	private Long id;
	private String title;
	private String content;
	//附件ID
	private String attachIds;
	private Date createTime;
	
	/**
	 * 关联属性
	 */
	private User createUser;
	private CourseClass courseClass;
	private Course course;
	
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

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
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

	public CourseClass getCourseClass() {
		return courseClass;
	}

	public void setCourseClass(CourseClass courseClass) {
		this.courseClass = courseClass;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ClassDetail(String title, String content, String attachIds, Date createTime, User createUser, CourseClass courseClass, Course course) {
		super();
		this.title = title;
		this.content = content;
		this.attachIds = attachIds;
		this.createTime = createTime;
		this.createUser = createUser;
		this.courseClass = courseClass;
		this.course = course;
	}

	public ClassDetail() {
		super();
	}
	
	
}
