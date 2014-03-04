package org.jacademie.examenFevrier.services.impl;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.dao.ArtisteDAO;
import org.jacademie.examenFevrier.services.ArtisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtisteServiceImpl implements ArtisteService{

	@Autowired
	ArtisteDAO artisteDAO;

	@Override
	public Artiste getById(Serializable id) {
		return artisteDAO.getById(id);
	}

	@Override
	public List<Artiste> searchByName(String searchPattern) {
		return artisteDAO.searchByName(searchPattern);
	}

	@Override
	public Artiste getOneByName(String searchPattern) {
		return artisteDAO.getOneByName(searchPattern);
	}

	@Override
	public List<Artiste> getAll() {
		return artisteDAO.getAll();
	}

	@Override
	public void save(Artiste entity) {
		artisteDAO.save(entity);
	}

	@Override
	public void update(Artiste entity) {
		artisteDAO.update(entity);

	}

	@Override
	public void delete(Artiste entity) {
		artisteDAO.delete(entity);

	}

	@Override
	public Artiste getArtisteByIDAlbum(int idAlbum) {
		return artisteDAO.getArtisteByIDAlbum(idAlbum);
	}

}
