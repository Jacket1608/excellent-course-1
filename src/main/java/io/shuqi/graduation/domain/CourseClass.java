package io.shuqi.graduation.domain;

import java.util.Date;

/**
 * 课程的具体课程（如：网络工程的：网络基础）
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class CourseClass {

	private Long id;
	private String title;
	private String courseClassImage;
	private String description;
	private Date craeteTime;
	/**
	 * 关联项
	 */
	private Course course;
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

	public String getCourseClassImage() {
		return courseClassImage;
	}

	public void setCourseClassImage(String courseClassImage) {
		this.courseClassImage = courseClassImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCraeteTime() {
		return craeteTime;
	}

	public void setCraeteTime(Date craeteTime) {
		this.craeteTime = craeteTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	public CourseClass(String title, String courseClassImage, String description, Date craeteTime, Course course, User createUser) {
		super();
		this.title = title;
		this.courseClassImage = courseClassImage;
		this.description = description;
		this.craeteTime = craeteTime;
		this.course = course;
		this.createUser = createUser;
	}

	public CourseClass() {
		super();
	}
	
	
}
