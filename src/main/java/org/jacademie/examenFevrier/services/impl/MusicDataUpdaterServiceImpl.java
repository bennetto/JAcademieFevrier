package org.jacademie.examenFevrier.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.bo.Chanson;
import org.jacademie.examenFevrier.dao.AlbumDAO;
import org.jacademie.examenFevrier.dao.ArtisteDAO;
import org.jacademie.examenFevrier.dao.ChansonDAO;
import org.jacademie.examenFevrier.services.MusicData;
import org.jacademie.examenFevrier.services.MusicDataException;
import org.jacademie.examenFevrier.services.MusicDataUpdaterService;
import org.jacademie.examenFevrier.utils.HibernateManager;
import org.jacademie.examenFevrier.utils.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("musicDataUpdaterService")
public class MusicDataUpdaterServiceImpl implements MusicDataUpdaterService{
	private static final Logger logger = Logger.getLogger(MusicDataUpdaterServiceImpl.class);

	private List<MusicData> datas;
	
	@Autowired
	private ArtisteDAO artisteDAO;
	
	@Autowired
	private ChansonDAO chansonDAO;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	
	private PersistenceManager persistenceManager = new HibernateManager();

	public MusicDataUpdaterServiceImpl(){
		this(new ArrayList<MusicData>());
	}

	public MusicDataUpdaterServiceImpl(List<MusicData> datas){
		this.datas = datas;
	}


	public void update(List<MusicData> datas) throws MusicDataException{

		this.datas = datas;  

		update();
	}

	public void update() throws MusicDataException{

		logger.info("openSession");
		persistenceManager.openSession();
		logger.info("beginTransaction");
		persistenceManager.beginTransaction();
	
		for( MusicData data : datas ){
			logger.info("update : " + datas);
			if(!isDataComplete(data)){
				persistenceManager.rollbackTransaction();
				persistenceManager.closeSession();

				throw new MusicDataException();
			}


			Artiste artist = artisteDAO.getById(data.getCodeArtist());
			if (artist == null) {
				artist = new Artiste(data.getCodeArtist(), data.getNomArtiste());
			}
			else
			{
				artist.setNom(data.getNomArtiste());
			}


			Album album = albumDAO.getById(data.getCodeAlbum());
			if (album == null) {
				album = new Album(data.getCodeAlbum(),data.getNomAlbum());
				artist.addAlbum(album);
			}
			else
			{
				album.setNom(data.getNomAlbum());
			}

			Chanson chanson = chansonDAO.getByAlbumAndNum(album,data.getNumeroChanson());
			if (chanson == null) {
				chanson = new Chanson(data.getNumeroChanson(), data.getTitreChanson(),data.getDureeChanson());
				album.addChanson(chanson);
			}
			else
			{
				chanson.setTitre(data.getTitreChanson());
				chanson.setDuree(data.getDureeChanson());
			}

			artisteDAO.save(artist);
			persistenceManager.flush();
		}

		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
	}



	private boolean isDataComplete(MusicData data) {
		if(data.getCodeArtist() == null)
			return false;
		if(data.getNomArtiste() == null || data.getNomArtiste() == "")
			return false;
		if(data.getCodeAlbum() == null)
			return false;
		if(data.getNomAlbum() == null || data.getNomAlbum() == "")
			return false;
		if(data.getNumeroChanson() == null)
			return false;
		if(data.getTitreChanson() == null || data.getTitreChanson() == "")
			return false;
		if(data.getDureeChanson() == null || data.getDureeChanson() == -1)
			return false;
		
		return true;
	}

}
