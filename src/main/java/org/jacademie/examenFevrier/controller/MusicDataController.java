package org.jacademie.examenFevrier.controller;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.services.MusicDataProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MusicDataController {
	
	private static final Logger logger = Logger.getLogger(MusicDataController.class);
	
	@Autowired
	private MusicDataProcessService musicDataProcessService ; 
	
	
	@RequestMapping(value="/musicExtractor", method= RequestMethod.GET)
	public String helloWorld(){

		logger.info("Start  updateMusicFromFiles ");
		musicDataProcessService.updateMusicFromFiles();
		logger.info("Stop updateMusicFromFiles ");

		return "hello";
		
	}
	

	
	

}
