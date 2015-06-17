package io.shuqi.graduation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.AttachDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class AttachDaoImpl extends AttachDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Attach> getNewAttachs(int size) {
		return getSession().createQuery(" from "+Attach.class.getName()+" atta where atta.fromName<>:name and atta.fileType=:fileType order by atta.createTime desc")
						   .setParameter("name", "unknow")
						   .setParameter("fileType", "classDetail")
						   .setFirstResult(0)
						   .setMaxResults(size)
						   .list();
	}

	@Override
	public void updateToUnknowById(Long fromid, Long fromSubId,String fileType) {
		getSession().createQuery("update "+Attach.class.getName()+" as  atta set atta.fromName='unknow',atta.fromId=-1,fromSubId=-1 where atta.fromId=:mid and fromSubId=:subid and  atta.fileType=:fileType")
				   .setParameter("mid", fromid)
				   .setParameter("subid",fromSubId)
				   .setParameter("fileType", fileType)
				   .executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Attach> getPagerByPager(Pager<Attach> pager) {
		List<Attach> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+Attach.class.getName()+" atta where atta.fromName<>:name and atta.fileType=:fileType order by atta.createTime desc")
				.setParameter("name", "unknow")
				.setParameter("fileType", "classDetail")
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
		return (Long) getSession().createQuery("select count(*) from "+Attach.class.getName()+" atta where atta.fromName<>:name and atta.fileType=:fileType")
								  .setParameter("name", "unknow")
								  .setParameter("fileType", "classDetail")
								  .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Attach> getAttachByCourseClassId(Long courseClassId) {
		return  getSession().createQuery("from "+Attach.class.getName()+" atta where atta.fromName<>:name and atta.fileType=:fileType and atta.fromId =:courseClassId order by atta.createTime desc")
				.setParameter("name", "unknow")
				.setParameter("courseClassId", courseClassId)
				.setParameter("fileType", "classDetail")
				.list();
	}
	
	
}
