package org.jacademie.examenFevrier.services;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Chanson;

public interface ChansonService {
	Chanson getById(Serializable id);
	List<Chanson> searchByName(String searchPattern);
	Chanson getOneByName(String searchPattern);
	List<Chanson> getAll();
	public List<Chanson> getByAlbum (int albumId);
	
	void save(Chanson entity);
	void update(Chanson entity);
	void delete(Chanson entity);
}
