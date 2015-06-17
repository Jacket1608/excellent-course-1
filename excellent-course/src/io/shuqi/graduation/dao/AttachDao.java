package io.shuqi.graduation.dao;


import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class AttachDao extends AbstractBaseDao<Attach> implements InterfaceBaseDao<Attach> {
	/**
	 * 得到最新的size个附件
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<Attach> getNewAttachs(int size);
	/**
	 * 把关联的资源变为不可用
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param fromid
	 * @param fromSubId
	 */
	public abstract void updateToUnknowById(Long fromid, Long fromSubId,String fileType);
	/**
	 * 得到附件的分页
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Attach> getPagerByPager(Pager<Attach> pager);
	/**
	 * 统计附件总数
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @return
	 */
	public abstract Long getCount();
	/**
	 * 查出课程的相关附件
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @return
	 */
	public abstract List<Attach> getAttachByCourseClassId(Long courseClassId);

}
