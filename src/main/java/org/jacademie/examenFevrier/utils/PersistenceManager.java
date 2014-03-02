package org.jacademie.examenFevrier.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface PersistenceManager {
	public SessionFactory getSessionFactory();
	public void openSession();
	public void closeSession() ;
	
	public Session getSession();
	public Session beginTransaction();
	public void commitTransaction();
	public void flush();
	public void rollbackTransaction();
}
