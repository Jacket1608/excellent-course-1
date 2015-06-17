package io.shuqi.graduation.base;

import io.shuqi.graduation.exception.DaoException;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class AbstractBaseDao<T> extends Object implements InterfaceBaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractBaseDao() {
		super();
		clazz =  (Class) (((ParameterizedType) getClass().getSuperclass().getGenericSuperclass()).getActualTypeArguments())[0]; 
	}
	public T deleteById(Long id) throws DaoException {
		T t  = getById(id);
		if(t!=null){
			getSession().createQuery("DELETE FROM "+clazz.getName()+" WHERE id=:id").setLong("id", id).executeUpdate();
		}else{
			throw new DaoException("删除的数据不存在！");
		}
		return t;
	}
	

	public int deleteByIds(Long... ids) {
		return getSession().createQuery("DELETE FROM "+clazz.getName()+" WHERE id in(:ids)").setParameterList("ids", ids).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Long... ids) {
		return getSession().createQuery("FROM "+clazz.getName()+" WHERE id in(:ids)").setParameterList("ids", ids).list();
	}

	public void save(T t) {
		getSession().save(t);
	}

	public void update(T t) {
		getSession().update(t);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
