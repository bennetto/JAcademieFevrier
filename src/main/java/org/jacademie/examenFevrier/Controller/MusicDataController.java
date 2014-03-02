package org.jacademie.examenFevrier.Controller;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MusicDataController {
	
	private static final Logger logger = Logger.getLogger(MusicDataController.class);
	
	
	
	@RequestMapping(value="/musicExtractor", method= RequestMethod.GET)
	public String helloWorld(){

		logger.info("Start  updateMusicFromFiles ");
		Main.updateMusicFromFiles();
		logger.info("Stop updateMusicFromFiles ");

		return "hello";
		
	}
	

	
	

}
