package org.jacademie.examenFevrier.utils;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author leonidas
 */
public class HibernateManager implements PersistenceManager{
	private Logger logger = Logger.getLogger(HibernateManager.class);
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;


	public HibernateManager() {
		init();
	}
	
	public void init(){
		if(sessionFactory == null){
			try{
				Configuration cfg = new Configuration().configure();
				serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();        
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			} catch (Throwable ex) {
				logger.error("Initial SessionFactory creation failed.", ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void openSession(){
		sessionFactory.openSession();
	}

	public Session getSession() {
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}

	public Session beginTransaction() {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public void commitTransaction() {
		getSession().getTransaction().commit();

	}

	public void flush() {
		getSession().flush();

	}

	public void rollbackTransaction() {
		getSession().getTransaction().rollback();
	}

	public void closeSession() {
		getSession().close();
	}

}