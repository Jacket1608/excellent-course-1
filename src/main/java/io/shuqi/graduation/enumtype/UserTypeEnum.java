package io.shuqi.graduation.enumtype;

public enum UserTypeEnum {
	ADMIN(1L,"admin","管理员"),TEACHER(2L,"teacher","教师"),STUDENT(3L,"student","学生");
	
	private String name;
	private Long id;
	private String description;
	private UserTypeEnum(Long id,String name,String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}
	public String getDescription() {
		return description;
	}
	public static UserTypeEnum getUserTypeEnum(String userType){
		if(ADMIN.getName().equals(userType)){
			return ADMIN;
		}else if(TEACHER.getName().equals(userType)){
			return TEACHER;
		}else if(STUDENT.getName().equals(userType)){
			return STUDENT;
		}else{
			return null;
		}
	}
	
}
