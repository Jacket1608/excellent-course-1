package io.shuqi.graduation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.BbsContentCommentsDao;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.BbsContentComments;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class BbsContentCommentsDaoImpl extends BbsContentCommentsDao {

	@SuppressWarnings("unchecked")
	@Override
	public Pager<BbsContentComments> getUserTieCommentsPagerByUser(Pager<BbsContentComments> pager, User currentUser) {
		List<BbsContentComments> datas = getSession().createQuery("from  " + BbsContentComments.class.getName() + " bcc where bcc.createUser=:user")
				.setParameter("user", currentUser)
				.setFirstResult(((Number) ((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
				.setMaxResults(pager.getPagesize())
				.list();
		pager.setDatas(datas);
		Long total = getUserTieCommentsCountByUser(currentUser);
		pager.setTotal(total);
		pager.setTotalPage(total % pager.getPagesize() == 0 ? total / pager.getPagesize() : total / pager.getPagesize() + 1);
		return pager;
	}

	@Override
	public Long getUserTieCommentsCountByUser(User currentUser) {
		return (Long) getSession().createQuery("select count(*) from "+BbsContentComments.class.getName()+" as bcc where bcc.createUser=:user ")
				.setParameter("user", currentUser)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<BbsContentComments> getTieCommentsPagerByBbsContent(Pager<BbsContentComments> pager, BbsContent bbsContent) {
		List<BbsContentComments> datas = getSession().createQuery("from  " + BbsContentComments.class.getName() + " bcc where bcc.bbsContent=:bbsContent")
				.setParameter("bbsContent", bbsContent)
				.setFirstResult(((Number) ((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
				.setMaxResults(pager.getPagesize())
				.list();
		pager.setDatas(datas);
		Long total = getTieCommentsCountByBbsContent(bbsContent);
		pager.setTotal(total);
		pager.setTotalPage(total % pager.getPagesize() == 0 ? total / pager.getPagesize() : total / pager.getPagesize() + 1);
		return pager;
	}

	@Override
	public Long getTieCommentsCountByBbsContent(BbsContent bbsContent) {
		return (Long) getSession().createQuery("select count(*) from "+BbsContentComments.class.getName()+" as bcc where bcc.bbsContent=:bbsContent ")
				.setParameter("bbsContent", bbsContent)
				.uniqueResult();
	}

}
