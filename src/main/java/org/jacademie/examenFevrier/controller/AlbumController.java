package org.jacademie.examenFevrier.controller;

import java.util.List;

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
		List<Album> albums = albumService.getAll();
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
		Artiste artiste = artisteService.getById(idArtiste);
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
	

	@RequestMapping(value="/create-album", method= RequestMethod.POST)
	public ModelAndView createAlbum(@ModelAttribute(value="album") Album album, @ModelAttribute(value="albums") List<Album> albums){
		albumService.save(album);
		return new ModelAndView("list-album", "albums",albums);
		
		
	}
}
