package io.shuqi.graduation.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository("userDao")
public class UserDaoImpl extends UserDao {
	public User getUserByLoginName(String loginName) {
		return (User) getSession().createQuery("FROM " + User.class.getName()+ " user WHERE user.loginName=:loginname")
								  .setString("loginname",loginName)
								  .uniqueResult();
	}

	@Override
	public User getUserByEmail(String email) {
		return (User) getSession().createQuery("FROM " + User.class.getName()+ " user WHERE user.email=:email")
				  .setString("email",email)
				  .uniqueResult();
	}

	@Override
	public int addOneBbs(Long userId) {
		return getSession().createQuery("UPDATE "+User.class.getName()+" user set user.bbsCount =(bbsCount+1) WHERE user.id=:id")
					.setLong("id", userId)
					.executeUpdate();
	}

	@Override
	public int updateLoginTime(Long userId) {
		return getSession().createQuery("UPDATE "+User.class.getName()+" user set user.lastLoginTime =:lastLoginTime WHERE user.id=:id")
				.setTimestamp("lastLoginTime", new Timestamp(System.currentTimeMillis()))
				.setLong("id", userId)
				.executeUpdate();
	}


	@Override
	public Long countTotalUser() {
		List<String> filters = new ArrayList<String>();
		filters.add("student");
		filters.add("teacher");
		return Count(filters);
	}

	@Override
	public Long countTotalTeacher() {
		List<String> filters = new ArrayList<String>();
		filters.add("teacher");
		return Count(filters);
	}

	@Override
	public Long countTotalStudent() {
		List<String> filters = new ArrayList<String>();
		filters.add("student");
		return Count(filters);
	}

	private Long Count(List<String> userFilters){
		return (long) getSession().createQuery("select count(*) from "+User.class.getName()+" as u where u.userType in(:userFilters)")
				.setParameterList("userFilters", userFilters)
				.uniqueResult();
	}

	@Override
	public List<User> getListByStatusAndType(boolean access, boolean confirm, String userType) {
		return getListByStatusAndTypeAndLimit(access, confirm,userType, 10);
	}

	@Override
	public List<User> getListByStatusAndTypeAndLimit(boolean access, boolean confirm, String userType, int Size) {
		return getListByStatusAndTypeAndLimit(access, confirm, userType, 0L, Size);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getListByStatusAndTypeAndLimit(boolean access, boolean confirm, String userType, Long start, int Size) {
		return getSession().createQuery("FROM "+User.class.getName()+" u WHERE u.access=:access AND u.confirm=:confirm and u.userType=:userType")
				.setBoolean("access", access)
				.setBoolean("confirm", confirm)
				.setString("userType", userType)
				.setMaxResults(Size)
				.setFirstResult(((Number)start).intValue())
				.list();
	}
	@Override
	public Pager<User> getPagerByStatusAndType(boolean access, boolean confirm, String userType, Pager<User> pager) {
		pager.setDatas(getListByStatusAndTypeAndLimit(access, confirm,userType, pager.getCurrent(),pager.getPagesize()));
		pager.setTotal(getCountByStatusAndType(access,confirm,userType));
		return pager;
	}
	/**
	 * 统计总条数
	 */
	private Long getCountByStatusAndType(boolean access, boolean confirm, String userType){
		return (Long)getSession().createQuery("count(*) FROM "+User.class.getName()+" u WHERE u.access=:access AND u.confirm=:confirm and u.userType =:userType")
				.setBoolean("access", access)
				.setBoolean("confirm", confirm)
				.setString("userType", userType)
				.uniqueResult();
	}

	@Override
	public int changeUserStates(boolean access,boolean confirm,String userType,Long userId) {
		return getSession().createQuery("UPDATE "+User.class.getName()+" u SET u.confirm =:confirm,u.access=:access WHERE u.userType=:userType AND u.id=:id")
				.setParameter("confirm", confirm)
				.setParameter("access", access)
				.setParameter("userType", userType)
				.setParameter("id", userId)
				.executeUpdate();
	}
	@Override
	public int changeUserStates(boolean access,boolean confirm,Long userId) {
		return getSession().createQuery("UPDATE "+User.class.getName()+" u SET u.confirm =:confirm,u.access=:access WHERE u.id=:id")
				.setParameter("confirm", confirm)
				.setParameter("access", access)
				.setParameter("id", userId)
				.executeUpdate();
	}


}
