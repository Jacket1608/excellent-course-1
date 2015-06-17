package io.shuqi.graduation.domain;

/**
 * 课程级别
 * @author shuqi
 * @date   2015年5月4日
 * @version since 1.0
 */
public class CourseLevel {

	//id
	private Long id;
	//name
	private String name;
	//description
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CourseLevel(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public CourseLevel() {
		super();
	}
	
	
}
