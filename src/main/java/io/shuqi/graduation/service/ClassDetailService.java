package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.ClassDetail;

import java.util.List;

public abstract class ClassDetailService {
	/**
	 * 得到最新课程更新size条
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<ClassDetail> getNewClassDetail(int size);

}
