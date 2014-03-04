package org.jacademie.examenFevrier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.bo.Album;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.services.AlbumService;
import org.jacademie.examenFevrier.services.ArtisteService;
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
public class AlbumController {
	private static final Logger logger = Logger.getLogger(AlbumController.class);
	
	@Autowired
	AlbumService albumService;
	
	@Autowired
	ArtisteService artisteService;
	
	private PersistenceManager persistenceManager = new HibernateManager();

	
	@ModelAttribute("albums") 
	public List<Album> getAlbumsList(){
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		List<Album> albums = albumService.getAll();
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		return albums;
	}
	
	public List<Album> getAlbumsList(int idArtiste){
		persistenceManager.openSession();
		
		List<Album> albums = albumService.getByArtistId(idArtiste);
		persistenceManager.closeSession();
		return albums;
	}
	
	public Artiste getArtiste(int idArtiste){
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Artiste artiste = artisteService.getById(idArtiste);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		return artiste;
	}
	
	@ModelAttribute(value="album")
	public Album getAlbum(){
		return new Album();
	}
	
	@RequestMapping(value="/albums", method= RequestMethod.GET)
	public String listAlbum(@RequestParam("artiste") int idArtiste,Model model){

		List<Album> albums = getAlbumsList(idArtiste);
		model.addAttribute("albums",albums);
		model.addAttribute("artiste",getArtiste(idArtiste));
		
		
		return "artist"; 
		
	}
	
	@RequestMapping(value="/delete_album", method= RequestMethod.GET)
	public String deleteArtiste(@RequestParam("album") int idAlbum,@RequestParam("artiste") int idArtiste,HttpServletRequest request){


		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Album album = albumService.getById(idAlbum);
		albumService.delete(album);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		
		String redirectUrl = "/albums.do?artiste="+idArtiste;
	    return "redirect:" + redirectUrl;
		
	}
	
	@RequestMapping(value="/addAlbum", method= RequestMethod.POST)
	public String createArtiste(@ModelAttribute(value="album") Album album,@RequestParam("artiste") int idArtiste){
		
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Artiste artiste = artisteService.getById(idArtiste);
		artiste.addAlbum(album);
		artisteService.save(artiste);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		
		String redirectUrl = "/albums.do?artiste="+idArtiste;
	    return "redirect:" + redirectUrl;
		
	}

	
}
