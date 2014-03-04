package org.jacademie.examenFevrier.services.impl;

import java.io.Serializable;
import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.dao.AlbumDAO;
import org.jacademie.examenFevrier.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	AlbumDAO albumDAO;

	@Override
	public Album getById(Serializable id) {
		return albumDAO.getById(id);
	}

	@Override
	public List<Album> searchByName(String searchPattern) {
		return albumDAO.searchByName(searchPattern);
	}

	@Override
	public Album getOneByName(String searchPattern) {
		return albumDAO.getOneByName(searchPattern);
	}

	@Override
	public List<Album> getAll() {
		return albumDAO.getAll();
	}

	@Override
	public void save(Album entity) {
		albumDAO.save(entity);
	}
	

	@Override
	public void update(Album entity) {
		albumDAO.update(entity);

	}

	@Override
	public void delete(Album entity) {
		albumDAO.delete(entity);

	}

	@Override
	public List<Album> getByArtistId(int id) {
		return albumDAO.getByArtistId(id);
		
	}

	@Override
	public Album getAlbumByIDChanson(int idChanson) {
		return albumDAO.getAlbumByIDChanson(idChanson);
	}

	

}
