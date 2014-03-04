package org.jacademie.examenFevrier.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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

/*
	@Override
	public List<T> getAll() {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
		List<T> list = null;
        Query query = hibernateSession.createQuery("from " + clazz.getName());
        list = query.list();
        hibernateSession.close();
       
        return list;
	}
*/


	@Override
	public List<Album> getByArtistId(int id) {
		
		Session hibernateSession = getSession();
		
    	Query hqlQuery = hibernateSession.getNamedQuery("getAlbumByIDArtiste");
    	hqlQuery.setInteger("artiste_code", id);
    	List<Album> albums = hqlQuery.list();
    	
    	return albums;
    	
	}






	@Override
	public Album getAlbumByIDChanson(int idChanson) {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
    	Query hqlQuery = hibernateSession.getNamedQuery("getAlbumByIDChanson");
    	hqlQuery.setInteger("artiste_code", idChanson);
    	List<Album> albums = hqlQuery.list();
    	
    	hibernateSession.close();
    	if(albums.size() == 1){
    		return albums.get(0);
    	}
    	return null;
	}
	
	
}
