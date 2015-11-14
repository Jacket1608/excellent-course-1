package io.shuqi.graduation.domain;
/**
 * 课程的科目（如：物理学）
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class Course {

	private Long id;
	private String name;
	private CourseLevel courseLevel;
	private String description;
	private String teacherTeam;
	private String teachContent;
	private String teachMethod;
	private String teachEnvironment;
	private String teachOutline;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CourseLevel getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(CourseLevel courseLevel) {
		this.courseLevel = courseLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTeacherTeam() {
		return teacherTeam;
	}
	public void setTeacherTeam(String teacherTeam) {
		this.teacherTeam = teacherTeam;
	}
	public String getTeachContent() {
		return teachContent;
	}
	public void setTeachContent(String teachContent) {
		this.teachContent = teachContent;
	}
	public String getTeachMethod() {
		return teachMethod;
	}
	public void setTeachMethod(String teachMethod) {
		this.teachMethod = teachMethod;
	}
	public String getTeachEnvironment() {
		return teachEnvironment;
	}
	public void setTeachEnvironment(String teachEnvironment) {
		this.teachEnvironment = teachEnvironment;
	}
	public String getTeachOutline() {
		return teachOutline;
	}
	public void setTeachOutline(String teachOutline) {
		this.teachOutline = teachOutline;
	}
	public Course(String name, CourseLevel courseLevel, String description, String teacherTeam, String teachContent, String teachMethod, String teachEnvironment, String teachOutline) {
		super();
		this.name = name;
		this.courseLevel = courseLevel;
		this.description = description;
		this.teacherTeam = teacherTeam;
		this.teachContent = teachContent;
		this.teachMethod = teachMethod;
		this.teachEnvironment = teachEnvironment;
		this.teachOutline = teachOutline;
	}
	public Course() {
		super();
	}
	
	
	
	
}
