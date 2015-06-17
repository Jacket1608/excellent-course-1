package io.shuqi.graduation.enumtype;

/**
 * EnumMimeType.java
 * 
 * @author shuqi
 * @date 2015-4-23
 * @description mime类型图标枚举，全局通用的ico图标
 * @version since 1.0
 */
public enum EnumMimeType {
	PDF("pdf", "commons/img/mimeType/reader.png"), WORD("word", "commons/img/mimeType/word.png"), EXECL("execl", "commons/img/mimeType/excel.png"), POWERPOINT("powerpoint", "commons/img/mimeType/powerpoint.png"), ACCESS("powerpoint", "commons/img/mimeType/access.png"),

	FOLDER("folder", "commons/img/mimeType/folder.png"), ZIP("zip", "commons/img/mimeType/zip.png"), RAR("rar", "commons/img/mimeType/rar.png"), TEXT("text", "commons/img/mimeType/text.png"),

	WEBPAGE("webpage", "commons/img/mimeType/webpage.png"), OTHER("other", "commons/img/mimeType/file.png"), IMAGE("image", "commons/img/mimeType/image.png");

	private String path;
	private String key;

	private EnumMimeType(String keyString, String pathString) {
		this.key = keyString;
		this.path = pathString;
	}

	/**
	 * EnumMimeType.java
	 * 
	 * @author shuqi
	 * @date 2015-4-23
	 * @description 得到文件类型
	 * @version since 1.0
	 * @return
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * EnumMimeType.java
	 * 
	 * @author shuqi
	 * @date 2015-4-23
	 * @description 得到文件的PNG图片的服务器(相对)地址,如：{pageContext.request.contextPath}+"/"+"acb/aa.png"
	 * @version since 1.0
	 * @return
	 */
	public String getPngImage() {
		return this.path;
	}

	/**
	 * 得到文件类型的图片
	 * 
	 * @author shuqi
	 * @date 2015年5月12日
	 * @version since 1.0
	 * @param ext
	 * @return
	 */
	public static String getFileMIMEImagePath(String ext) {
		return getFileMIME(ext).path;
	}

	/**
	 * 得到文件的类型
	 * 
	 * @author shuqi
	 * @date 2015年5月12日
	 * @version since 1.0
	 * @param ext
	 * @return
	 */
	public static EnumMimeType getFileMIME(String ext) {
		EnumMimeType finalfile;
		if ("pdf".equals(ext)) {
			finalfile = EnumMimeType.PDF;
		} else if ("xls".equals(ext) || "xlsx".equals(ext)) {
			finalfile = EnumMimeType.EXECL;
		} else if ("doc".equals(ext) || "docx".equals(ext)) {
			finalfile = EnumMimeType.WORD;
		} else if ("ppt".equals(ext) || "pptx".equals(ext)) {
			finalfile = EnumMimeType.POWERPOINT;
		} else if ("rar".equals(ext)) {
			finalfile = EnumMimeType.RAR;
		} else if ("zip".equals(ext)) {
			finalfile = EnumMimeType.ZIP;
		} else if ("html".equals(ext) || "htm".equals(ext) || "xhtml".equals(ext)) {
			finalfile = EnumMimeType.WEBPAGE;
		} else if ("txt".equals(ext)) {
			finalfile = EnumMimeType.TEXT;
		} else if ("accdb".equals(ext)) {
			finalfile = EnumMimeType.ACCESS;
		} else {
			finalfile = EnumMimeType.OTHER;
		}

		return finalfile;
	}
}
