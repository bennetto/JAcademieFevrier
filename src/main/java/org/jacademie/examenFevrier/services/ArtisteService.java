package org.jacademie.examenFevrier.services;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Artiste;

public interface ArtisteService {
	Artiste getById(Serializable id);
	List<Artiste> searchByName(String searchPattern);
	Artiste getOneByName(String searchPattern);
	List<Artiste> getAll();
	Artiste getArtisteByIDAlbum(int idAlbum);
	
	void save(Artiste entity);

	void update(Artiste entity);
	void delete(Artiste entity);
}
