package org.jacademie.examenFevrier.controller;

import java.util.List;

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
		List<Artiste> artistes = artisteService.getAll();
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
	

	@RequestMapping(value="/create-artiste", method= RequestMethod.POST)
	public ModelAndView createArtiste(@ModelAttribute(value="artiste") Artiste artiste, @ModelAttribute(value="artistes") List<Artiste> artistes){
		artisteService.save(artiste);
		return new ModelAndView("list-artiste", "artistes",artistes);
		
		
	}
}
