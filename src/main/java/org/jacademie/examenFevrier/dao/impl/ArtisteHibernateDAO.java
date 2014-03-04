package org.jacademie.examenFevrier.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.dao.ArtisteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("artisteDAO")
public class ArtisteHibernateDAO extends GeneriqueHibernateDAO<Artiste> implements ArtisteDAO {


	public ArtisteHibernateDAO() {
		super(Artiste.class);
	}

	@Override
	public List<Artiste> searchByName(String searchPattern) {
		return searchByPattern("nom", searchPattern);
	}

	@Override
	public Artiste getOneByName(String searchPattern) {
		return searchOneByPattern("nom", searchPattern);
	}

	@Override
	public Artiste getArtisteByIDAlbum(int idAlbum) {
		Session hibernateSession = getSession();
		
    	Query hqlQuery = hibernateSession.getNamedQuery("getArtisteByIDAlbum");
    	hqlQuery.setInteger("album_code", idAlbum);
    	List<Artiste> artistes = hqlQuery.list();
    	
    	
    	if(artistes.size() == 1){
    		return artistes.get(0);
    	}
    	return null;
	}
	
	


}
