package org.jacademie.examenFevrier.services;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;

public interface AlbumService {
	Album getById(Serializable id);
	List<Album> searchByName(String searchPattern);
	Album getOneByName(String searchPattern);
	List<Album> getAll();
	List<Album> getByArtistId(int id);
	Album getAlbumByIDChanson(int idChanson);
	
	
	void save(Album entity);
	void update(Album entity);
	void delete(Album entity);
}
