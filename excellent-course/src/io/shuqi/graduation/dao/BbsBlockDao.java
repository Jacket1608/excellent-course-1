package io.shuqi.graduation.dao;

import java.util.Set;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.BbsBlock;


public abstract class BbsBlockDao extends AbstractBaseDao<BbsBlock> implements InterfaceBaseDao<BbsBlock> {

	/**
	 * 得到知识社区的父板块，即父版块为空的版块
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @return
	 */
	public abstract Set<BbsBlock> getTopBbsBlock();
	/**
	 * 通过版块名查询版块
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param name
	 * @return
	 */
	public abstract BbsBlock getByNem(String name);

}
