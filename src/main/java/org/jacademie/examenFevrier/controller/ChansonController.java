package org.jacademie.examenFevrier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.bo.Chanson;
import org.jacademie.examenFevrier.services.AlbumService;
import org.jacademie.examenFevrier.services.ArtisteService;
import org.jacademie.examenFevrier.services.ChansonService;
import org.jacademie.examenFevrier.utils.HibernateManager;
import org.jacademie.examenFevrier.utils.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChansonController {
	private static final Logger logger = Logger.getLogger(ChansonController.class);
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ChansonService chansonService;
	
	@Autowired
	ArtisteService artisteService;
	
	private PersistenceManager persistenceManager = new HibernateManager();

	
	@ModelAttribute("chansons") 
	public List<Chanson> getChansonsList(){
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		List<Chanson> albums = chansonService.getAll();
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		return albums;
	}
	
	public List<Chanson> getChansonsList(int idAlbum){
		persistenceManager.openSession();
		List<Chanson> chansons = chansonService.getByAlbum(idAlbum);
		persistenceManager.closeSession();
		return chansons;
	}
	
	public Album getAlbum(int idAlbum){
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Album album = albumService.getById(idAlbum);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		return album;
	}
	
	public Artiste getArtiste(int idAlbum){
		persistenceManager.openSession();
		Artiste artiste = artisteService.getArtisteByIDAlbum(idAlbum);
		persistenceManager.closeSession();
		return artiste;
	}
	
	
	@ModelAttribute(value="chanson")
	public Chanson getChanson(){
		return new Chanson();
	}
	
	@RequestMapping(value="/chansons", method= RequestMethod.GET)
	public String listChanson(@RequestParam("album") int idAlbum,Model model){

		List<Chanson> chansons = getChansonsList(idAlbum);
		model.addAttribute("chansons",chansons);
		model.addAttribute("album",getAlbum(idAlbum));
		model.addAttribute("artisteId",getArtiste(idAlbum).getCodeArtiste());
		
		return "album"; 
		
	}
	
	@RequestMapping(value="/delete_chanson", method= RequestMethod.GET)
	public String deleteArtiste(@RequestParam("chanson") int idChanson,@RequestParam("album") int idAlbum ,HttpServletRequest request){


		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Chanson chanson = chansonService.getById(idChanson);
		chansonService.delete(chanson);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		
		String redirectUrl = "/chansons.do?album="+idAlbum;
	    return "redirect:" + redirectUrl;
		
	}

	@RequestMapping(value="/addChanson", method= RequestMethod.POST)
	public String createArtiste(@ModelAttribute(value="chanson") Chanson chanson,@RequestParam("album") int idAlbum){
		
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		
		logger.info(idAlbum);
		Album album = albumService.getById(idAlbum);
		logger.info(album);
		album.addChanson(chanson);
		 
		try {
			albumService.save(album);
			persistenceManager.commitTransaction();
			persistenceManager.closeSession();
			
		} catch (Exception e) {
			logger.error(e);
			persistenceManager.rollbackTransaction();
			persistenceManager.closeSession();
		}
	
		
		String redirectUrl = "/chansons.do?album="+idAlbum;
	    return "redirect:" + redirectUrl;
		
	}
}
