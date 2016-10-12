package io.shuqi.graduation.domain;

import java.util.Date;

/**
 * User对象，用于网站的User对象
 * @author shuqi
 * @date   2015年4月27日
 * @version since 1.0
 */
public class User {

	private Long id;
	private String realName;
	private String nickName;
	private String password;
	private String loginName;
	private String userqq;
	private String phoneNumber;
	private String email;
	private String headImage="commons/img/user.png";
	private boolean access = true; 
	private boolean confirm = false;
	private String userType;
	private String description;
	private Date registerTime;
	private Date lastLoginTime;
	private String location;
	private String birthLocation;
	private Date birthday;
	private String gender;
	private String personalPage;
	private String personalTitle;
	private String personalFavorite;
	private Long bbsCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserqq() {
		return userqq;
	}
	public void setUserqq(String userqq) {
		this.userqq = userqq;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImg) {
		this.headImage = headImg;
	}
	public boolean isAccess() {
		return access;
	}
	public void setAccess(boolean access) {
		this.access = access;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getBirthLocation() {
		return birthLocation;
	}
	public void setBirthLocation(String birthLocation) {
		this.birthLocation = birthLocation;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPersonalPage() {
		return personalPage;
	}
	public void setPersonalPage(String personalPage) {
		this.personalPage = personalPage;
	}
	public String getPersonalTitle() {
		return personalTitle;
	}
	public void setPersonalTitle(String personalTitle) {
		this.personalTitle = personalTitle;
	}
	public String getPersonalFavorite() {
		return personalFavorite;
	}
	public void setPersonalFavorite(String personalFavorite) {
		this.personalFavorite = personalFavorite;
	}
	public Long getBbsCount() {
		return bbsCount;
	}
	public void setBbsCount(Long bbsCount) {
		this.bbsCount = bbsCount;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	/**
	 * 最小构造方法
	 * @param password
	 * @param loginName
	 * @param email
	 * @param headImg
	 * @param access
	 * @param userType
	 * @param register
	 * @param bbsCount
	 */
	public User(String password, String loginName, String email, boolean access, String userType, Date registerTime, Long bbsCount) {
		super();
		this.password = password;
		this.loginName = loginName;
		this.email = email;
		this.access = access;
		this.userType = userType;
		this.registerTime = registerTime;
		this.bbsCount = bbsCount;
	}
	/**
	 * 提供给hibernate使用的构造方法
	 */
	public User() {
		super();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User && obj!=null){
			return this.id==((User)obj).getId();
		}else{
			return false;
		}
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
