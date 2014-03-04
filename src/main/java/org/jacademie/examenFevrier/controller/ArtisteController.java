package org.jacademie.examenFevrier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.bo.Artiste;
import org.jacademie.examenFevrier.services.ArtisteService;
import org.jacademie.examenFevrier.utils.HibernateManager;
import org.jacademie.examenFevrier.utils.PersistenceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArtisteController {
	private static final Logger logger = Logger.getLogger(ArtisteController.class);
	
	@Autowired
	ArtisteService artisteService;
	
	
	
	private PersistenceManager persistenceManager = new HibernateManager();

	
	@ModelAttribute("artistes") 
	public List<Artiste> getArtistesList(){
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		List<Artiste> artistes = artisteService.getAll();
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		return artistes;
	}
	
	@ModelAttribute(value="artiste")
	public Artiste getArtiste(){
		return new Artiste();
	}
	
	@RequestMapping(value="/artistes", method= RequestMethod.GET)
	public ModelAndView listArtiste(@ModelAttribute(value="artistes") List<Artiste> artistes){

		ModelAndView modelAndView = new ModelAndView("list-artiste", "artistes",artistes);
		
		return modelAndView; 
		
	}
	
	
	@RequestMapping(value="/delete_artiste", method= RequestMethod.GET)
	public String deleteArtiste(@RequestParam("artiste") int idArtiste,HttpServletRequest request){


		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		Artiste artiste = artisteService.getById(idArtiste);
		artisteService.delete(artiste);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		
		String redirectUrl = "/artistes.do";
	    return "redirect:" + redirectUrl;
		
	}

		
	

	@RequestMapping(value="/addArtiste", method= RequestMethod.POST)
	public String createArtiste(@ModelAttribute(value="artiste") Artiste artiste){
		
		persistenceManager.openSession();
		persistenceManager.beginTransaction();
		artisteService.save(artiste);
		persistenceManager.commitTransaction();
		persistenceManager.closeSession();
		
		String redirectUrl = "/artistes.do";
	    return "redirect:" + redirectUrl;
		
	}
}
