package org.jacademie.examenFevrier.dao.impl;

import java.util.List;

import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.dao.ArtisteDAO;
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
	


}
