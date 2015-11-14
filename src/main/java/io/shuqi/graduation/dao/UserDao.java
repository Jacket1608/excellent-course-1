package io.shuqi.graduation.dao;


import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class UserDao extends AbstractBaseDao<User>  implements InterfaceBaseDao<User>{
	/**
	 * 通过用户名查询用户
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param loginName
	 * @return
	 */
	public abstract User getUserByLoginName(String loginName);
	/**
	 * 通过用户的email来查询用户
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param email
	 * @return
	 */
	public abstract User getUserByEmail(String email);
	/**
	 * l论坛帖子增加是添加一个统计
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param userId
	 * @return
	 */
	public abstract int addOneBbs(Long userId);
	/**
	 * l论坛帖子增加是添加一个统计
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param userId
	 * @return
	 */
	public abstract int updateLoginTime(Long userId);
	
	/**
	 * 统计所有用户
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param ids
	 * @return
	 */
	public abstract Long  countTotalUser();
	/**
	 * 统计所有学生
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param ids
	 * @return
	 */
	public abstract Long  countTotalTeacher();
	/**
	 * 统计所有教师
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param ids
	 * @return
	 */
	public  abstract Long  countTotalStudent();
	/**
	 * 通过状态和用户类型查询对应的List集合（默认查询10条记录）
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param access 是否允许登录
	 * @param confirm	是否已经认证
	 * @param userType	用户类型
	 * @return
	 */
	public  abstract List<User>  getListByStatusAndType(boolean access,boolean confirm,String userType);
	/**
	 * 通过状态和用户类型查询对应的List集合(给查询的个数)
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param access 是否允许登录
	 * @param confirm	是否已经认证
	 * @param userType	用户类型
	 * @param Size List大小
	 * @return
	 */
	public  abstract List<User>  getListByStatusAndTypeAndLimit(boolean access,boolean confirm,String userType,int Size);
	/**
	 * 通过状态和用户类型查询对应的List集合(给查询的个数)
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param access 是否允许登录
	 * @param confirm	是否已经认证
	 * @param userType	用户类型
	 * @param start   起始位置
	 * @param Size List大小
	 * @return
	 */
	public  abstract List<User>  getListByStatusAndTypeAndLimit(boolean access,boolean confirm,String userType,Long start,int Size);
	/**
	 * 通过状态和用户类型查询对应的分页对象(给查询的个数)
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param access 是否允许登录
	 * @param confirm	是否已认证
	 * @param userType 用户类型
	 * @param pager 分页类(必须包含当前行以及分页大小)
	 * @return
	 */
	public  abstract Pager<User>  getPagerByStatusAndType(boolean access,boolean confirm,String userType,Pager<User> pager);
	/**
	 * 改变用户的状态
	 * @author shuqi
	 * @date   2015年4月29日
	 * @version since 1.0
	 * @param access
	 * @param confirm
	 * @param userType
	 * @param teacherId
	 * @return
	 */
	public abstract int changeUserStates(boolean access,boolean confirm,String userType,Long teacherId);
	/**
	 * 改变用户的状态
	 * @author shuqi
	 * @date   2015年4月29日
	 * @version since 1.0
	 * @param access
	 * @param confirm
	 * @param userType
	 * @param teacherId
	 * @return
	 */
	public abstract int changeUserStates(boolean access,boolean confirm,Long teacherId);
}
