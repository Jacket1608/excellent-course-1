package io.shuqi.graduation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.NewsCommentDao;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.NewsComments;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class NewsCommentDaoImpl extends NewsCommentDao {

	@SuppressWarnings({ "unchecked" })
	@Override
	public Pager<NewsComments> getMyNewsComments(Pager<NewsComments> pager, User currentUser) {
		List<NewsComments> data = getSession().createQuery("FROM "+NewsComments.class.getName()+" newscs WHERE  newscs.createUser=:user")
					.setParameter("user", currentUser)
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(data);
		Long total = countUserNewsComments(currentUser);
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@Override
	public Long countUserNewsComments(User currentUser) {
		return (Long) getSession().createQuery("select count(*) from "+NewsComments.class.getName()+" as newscs where newscs.createUser=:user ")
				.setParameter("user", currentUser)
				.uniqueResult();
	}

	@Override
	public Long getCount() {
		return (Long) getSession().createQuery("select count(*) from "+NewsComments.class.getName())
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsComments> getByNewsId(News news) {
		return getSession().createQuery(" from "+NewsComments.class.getName()+" as nc where nc.news =:news" )
						   .setParameter("news", news)
						   .list();
	}

	
}
