package io.shuqi.graduation.enumtype;

public enum CourseLevel {

	XX(1L,"小学",""),CZ(2L,"",""),GZ(3L,"",""),BK(4L,"",""),SS(5L,"","");
	private Long id;
	private String name;
	private String description;
	private CourseLevel(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	
}
