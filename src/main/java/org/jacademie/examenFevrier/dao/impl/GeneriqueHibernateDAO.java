package org.jacademie.examenFevrier.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jacademie.examenFevrier.dao.GeneriqueDAO;
import org.jacademie.examenFevrier.utils.HibernateManager;

abstract public class GeneriqueHibernateDAO<T extends Object> implements GeneriqueDAO<T>{

	protected Class<T> clazz;
	protected Logger logger = Logger.getLogger(GeneriqueHibernateDAO.class);
	protected HibernateManager manager = new HibernateManager();
	public GeneriqueHibernateDAO(Class<T> clazz) {
		this.clazz = clazz; 
	}

	@Override
	abstract public java.util.List<T> searchByName(String searchPattern);
	abstract public T getOneByName(String searchPattern);
	
	protected Session getSession(){
		return manager.getSession();
	}

	protected List<T> searchByPattern(String table, String searchPattern){
		Session hibernateSession = getSession();
		Criteria criteria = hibernateSession.createCriteria(clazz);
    	criteria.add(Restrictions.ilike(table, searchPattern));
    	return criteria.list();
	}
	
	protected T searchOneByPattern(String table, String searchPattern){
		Session hibernateSession = getSession();
		Criteria criteria = hibernateSession.createCriteria(clazz);
    	criteria.add(Restrictions.ilike(table, searchPattern));
    	return (T) criteria.uniqueResult();
	}
	
	@Override
	public T getById(Serializable id) {
		Session hibernateSession = getSession();
		T t = null;
		try{
			t = (T) hibernateSession.get(clazz, id);
			return t;
		}catch(RuntimeException ex){
			logger.error("GenericHibernateDAO<"+clazz.getSimpleName()+">.getById unexpected error.", ex );//,ex
			throw ex;
		}
		
	}


	@Override
	public List<T> getAll() {
		Session hibernateSession = getSession();
		List<T> list = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        list = query.list();
        return list;
	}
	
	
	@Override
	public void save(T entity) {
		Session hibernateSession = getSession();
		hibernateSession.saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		Session hibernateSession = getSession();
		hibernateSession.update(entity);
	}

	@Override
	public void delete(T entity) {
		Session hibernateSession = getSession();
		hibernateSession.delete(entity);
	}
}
