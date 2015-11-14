package io.shuqi.graduation.domain.bo;

/**
 * 站点信息统计
 * @author shuqi
 * @date   2015年4月28日
 * @version since 1.0
 */
public class SiteInfoCount {

	private Long count;
	private String name;
	private String ico_class = "icon-database";
	public static String ico_news = "icon-file-text";
	public static String ico_user = "icon-user";
	public static String ico_file = "icon-file";
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIco_class() {
		return ico_class;
	}
	public void setIco_class(String ico_class) {
		this.ico_class = ico_class;
	}
	public SiteInfoCount(String name,Long count,  String ico_class) {
		super();
		this.count = count;
		this.name = name;
		this.ico_class = ico_class;
	}
	
	public SiteInfoCount(Long count, String name) {
		super();
		this.count = count;
		this.name = name;
	}
	public SiteInfoCount() {
		super();
	}
	
}
