package org.jacademie.examenFevrier.dao;

import java.util.List;

import org.jacademie.examenFevrier.bo.Album;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumDAO extends GeneriqueDAO<Album>{
	List<Album> getByArtistId(int id);
	Album getAlbumByIDChanson(int idChanson);
}
