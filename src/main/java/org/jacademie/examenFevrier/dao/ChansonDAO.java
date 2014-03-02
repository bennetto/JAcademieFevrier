package org.jacademie.examenFevrier.dao;

import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Chanson;


public interface ChansonDAO extends GeneriqueDAO<Chanson>{
	public Chanson getByAlbumAndNum(Album album, Integer numero);
}
