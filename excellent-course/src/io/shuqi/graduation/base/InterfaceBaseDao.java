package io.shuqi.graduation.base;

import io.shuqi.graduation.exception.DaoException;

import java.util.List;

/**
 * 数据访问的最基础接口，提供pojo的最基本的数据库操作方法
 * BaseDao.java
 * @author shuqi
 * @date   2015-4-24
 * @version since 1.0
 * @param <T>
 */
public interface InterfaceBaseDao<T> {
	/**
	 * 保存方法
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param t 要保存的对象
	 * @return
	 */
	public void save(T t);
	/**
	 * 通过ID查找对象
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param id 要查找的id
	 * @return
	 */
	public T getById(Long id);
	/**
	 * 通过多个id查找多个对象
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param ids 要查找的id数组
	 * @return
	 */
	public List<T> getByIds(Long ...ids);
	/**
	 * 最基本的更新对象方法
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param t 要更新的对象
	 * @return
	 */
	public void update(T t);
	/**
	 * 
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param id 要删除的对象的id
	 * @return
	 * @throws DaoException 
	 */
	public T deleteById(Long id) throws DaoException;
	/**
	 * 同时删除多个对象
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param ids
	 * @return
	 */
	public int deleteByIds(Long ...ids);
	
}
