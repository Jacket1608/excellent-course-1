package io.shuqi.graduation.enumtype;

/**
 * 浏览器枚举
 * 
 * @author shuqi
 * @date 2015年4月27日
 * @version since 1.0
 */
public enum BrowserEnum {
	CHROME("chrome", false), IE("IE", true), FIREFOX("fireFox", false), UNKNOW("unknow", true);

	private String browserName;
	private boolean isIE;

	private BrowserEnum(String browserName, boolean isIE) {
		this.browserName = browserName;
		this.isIE = isIE;
	}

	public String getBrowserName() {
		return browserName;
	}

	public boolean isIE() {
		return isIE;
	}

}
