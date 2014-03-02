package org.jacademie.examenFevrier.dao.impl;

import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.dao.AlbumDAO;
import org.springframework.stereotype.Repository;

@Repository("albumDAO")
public class AlbumHibernateDAO extends GeneriqueHibernateDAO<Album> implements AlbumDAO {

	public AlbumHibernateDAO() {
		super(Album.class);
	}
	
	
	

	@Override
	public List<Album> searchByName(String searchPattern) {
		return searchByPattern("nom", searchPattern);
	}

	@Override
	public Album getOneByName(String searchPattern) {
		return searchOneByPattern("nom", searchPattern);
	}
	
	
}
