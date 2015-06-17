package io.shuqi.graduation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.BbsContentDao;
import io.shuqi.graduation.domain.BbsBlock;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class BbsContentDaoImpl extends BbsContentDao {

	@SuppressWarnings("unchecked")
	@Override
	public Pager<BbsContent> getTiePagerByUser(Pager<BbsContent> pager, User currentUser) {
		List<BbsContent> datas = getSession().createQuery("from  " + BbsContent.class.getName() + " bbc where bbc.createUser=:user")
				.setParameter("user", currentUser)
				.setFirstResult(((Number) ((pager.getCurrentPage()-1)*pager.getPagesize()) ).intValue())
				.setMaxResults(pager.getPagesize())
				.list();
		pager.setDatas(datas);
		Long total = getTieCountByUser(currentUser);
		pager.setTotal(total);
		pager.setTotalPage(total % pager.getPagesize() == 0 ? total / pager.getPagesize() : total / pager.getPagesize() + 1);
		return pager;
	}

	@Override
	public Long getTieCountByUser(User currenTUser) {
		return (Long) getSession().createQuery("select count(*) from  " + BbsContent.class.getName() + " bbc where bbc.createUser=:user")
				.setParameter("user", currenTUser)
				.uniqueResult();
	}

	@Override
	public Long getTieCountByBbsBlock(BbsBlock bbsBlock) {
		return (Long) getSession().createQuery("select count(*) from  " + BbsContent.class.getName() + " bbc where bbc.bbsBlock=:block")
				.setParameter("block", bbsBlock)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<BbsContent> getTiePagerByBbsBlock(Pager<BbsContent> pager, BbsBlock bbsBlock) {
		List<BbsContent> datas = getSession().createQuery("from  " + BbsContent.class.getName() + " bbc where bbc.bbsBlock=:block  order by bbc.createTime desc")
											 .setParameter("block", bbsBlock)
											 .setFirstResult(((Number) ((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
											 .setMaxResults(pager.getPagesize())
											 .list();
		pager.setDatas(datas);
		Long total = getTieCountByBbsBlock(bbsBlock);
		pager.setTotal(total);
		pager.setTotalPage(total % pager.getPagesize() == 0 ? total / pager.getPagesize() : total / pager.getPagesize() + 1);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsContent> getTopNewBbs(int size) {
		return getSession().createQuery(" from "+BbsContent.class.getName()+" as bbc where bbc.access=1 order by bbc.createTime desc").setFirstResult(0).setMaxResults(size).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsContent> getTop10Bbs(int size) {
		return getSession().createQuery(" from "+BbsContent.class.getName()+" as bbc where bbc.access=1 order by bbc.readTimes desc").setFirstResult(0).setMaxResults(size).list();
	}

	
}
