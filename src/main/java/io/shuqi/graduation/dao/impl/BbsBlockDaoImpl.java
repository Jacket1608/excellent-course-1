package io.shuqi.graduation.dao.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.BbsBlockDao;
import io.shuqi.graduation.domain.BbsBlock;

@Repository
public class BbsBlockDaoImpl extends BbsBlockDao {

	@SuppressWarnings("unchecked")
	@Override
	public Set<BbsBlock> getTopBbsBlock() {
		return new HashSet<BbsBlock>( getSession().createQuery("from "+BbsBlock.class.getName()+" bbsb where bbsb.parent is NULL")
										   .list());
	}

	@Override
	public BbsBlock getByNem(String name) {
		return (BbsBlock) getSession().createQuery("from "+BbsBlock.class.getName()+" as tbbb where tbbb.name=:name")
				.setString("name", name)
				.uniqueResult();
	}

}
