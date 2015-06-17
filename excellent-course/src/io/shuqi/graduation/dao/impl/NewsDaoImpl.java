package io.shuqi.graduation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.NewsDao;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class NewsDaoImpl extends NewsDao {

	@SuppressWarnings("unchecked")
	@Override
	public Pager<News> getNewsPager(Pager<News> pager) {
		List<News> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+News.class.getName()+" ns order by ns.modifyTime DESC")
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCount();
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@Override
	public Long getCount() {
		return (Long) getSession().createQuery("select count(*) from "+News.class.getName()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getNewNews(int size) {
		return getSession().createQuery("from  "+News.class.getName()+" as  nns  order by nns.modifyTime desc,nns.createTime desc")
				   .setMaxResults(size)
				   .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> gethotNews(int size) {
		return getSession().createQuery("from  "+News.class.getName()+" as  nns  order by nns.readTimes desc")
				   .setMaxResults(size)
				   .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<News> getNewsPagerASC(Pager<News> pager) {
		List<News> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+News.class.getName()+" ns order by ns.modifyTime ASC")
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCount();
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<News> getNewsPagerById(Long id) {
		Pager<News> pager = new Pager<>(id, 1);
		List<News> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+News.class.getName()+" ns where ns.id=:id order by ns.modifyTime ASC")
					.setParameter("id", id)
					.setMaxResults(pager.getPagesize())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCount();
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}
	
	
}
