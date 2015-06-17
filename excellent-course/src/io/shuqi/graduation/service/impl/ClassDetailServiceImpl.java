package io.shuqi.graduation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.shuqi.graduation.dao.ClassDetailDao;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.service.ClassDetailService;

@Service("classDetailService")
public class ClassDetailServiceImpl extends ClassDetailService {

	@Autowired
	private ClassDetailDao classDetailDao;
	@Override
	public List<ClassDetail> getNewClassDetail(int size) {
		return classDetailDao.getNewClassDetail(size);
	}

}
