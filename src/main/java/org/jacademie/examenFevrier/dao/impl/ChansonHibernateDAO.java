package org.jacademie.examenFevrier.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Chanson;
import org.jacademie.examenFevrier.dao.ChansonDAO;
import org.springframework.stereotype.Repository;

@Repository("chansonDAO")
public class ChansonHibernateDAO extends GeneriqueHibernateDAO<Chanson> implements
		ChansonDAO {


	
	public ChansonHibernateDAO() {
		super(Chanson.class);
	}

	@Override
	public List<Chanson> searchByName(String searchPattern) {
		return searchByPattern("titre", searchPattern);
	}

	@Override
	public Chanson getOneByName(String searchPattern) {
		return searchOneByPattern("titre", searchPattern);
	}

	@Override
	public Chanson getByAlbumAndNum(Album album, Integer numero) {
		Session hibernateSession = getSession();
    	Query hqlQuery = hibernateSession.getNamedQuery("getChansonByID");
    	hqlQuery.setInteger("chanson_numero", numero);
    	hqlQuery.setInteger("album_code", album.getCodeAlbum());
    	List<Chanson> chansons = hqlQuery.list();
    	if(chansons.size() == 1){
    		return chansons.get(0);
    	}
    	return null;
		
	}

	@Override
	public List<Chanson> getByAlbum(int albumId) {
		Session hibernateSession = getSession();
		hibernateSession.beginTransaction();
    	Query hqlQuery = hibernateSession.getNamedQuery("getChansonByAlbum");
    	hqlQuery.setInteger("album_code", albumId);
    	List<Chanson> chansons = hqlQuery.list();
    	hibernateSession.close();
    	return chansons;
	}

}
