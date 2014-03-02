package org.jacademie.examenFevrier.dao;

import java.io.Serializable;
import java.util.List;

public interface GeneriqueDAO<T> {
	T getById(Serializable id);
	List<T> searchByName(String searchPattern);
	T getOneByName(String searchPattern);
	List<T> getAll();
	
	void save(T entity);
	void update(T entity);
	void delete(T entity);
}
