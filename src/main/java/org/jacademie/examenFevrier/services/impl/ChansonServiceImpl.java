package org.jacademie.examenFevrier.services.impl;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Chanson;
import org.jacademie.examenFevrier.dao.ChansonDAO;
import org.jacademie.examenFevrier.services.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChansonServiceImpl implements ChansonService{

	@Autowired
	ChansonDAO chansonDAO;

	@Override
	public Chanson getById(Serializable id) {
		return chansonDAO.getById(id);
	}

	@Override
	public List<Chanson> searchByName(String searchPattern) {
		return chansonDAO.searchByName(searchPattern);
	}

	@Override
	public Chanson getOneByName(String searchPattern) {
		return chansonDAO.getOneByName(searchPattern);
	}

	@Override
	public List<Chanson> getAll() {
		return chansonDAO.getAll();
	}

	@Override
	public void save(Chanson entity) {
		chansonDAO.save(entity);
	}

	@Override
	public void update(Chanson entity) {
		chansonDAO.update(entity);

	}

	@Override
	public void delete(Chanson entity) {
		chansonDAO.delete(entity);

	}

	@Override
	public List<Chanson> getByAlbum(int albumId) {
		
		return chansonDAO.getByAlbum(albumId);
	}

}
