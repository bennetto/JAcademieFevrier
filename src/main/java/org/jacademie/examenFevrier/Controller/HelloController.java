package org.jacademie.examenFevrier.Controller;

import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.Main;
import org.jacademie.examenFevrier.bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	private static final Logger logger = Logger.getLogger(HelloController.class);
	
	@RequestMapping(value="/hello", method= RequestMethod.GET)
	public String helloWorld(){
		logger.info("Show hello-worls page");
		return "hello-world";
		
	}
	
	
	
	@RequestMapping(value="/helloName", method= RequestMethod.GET)
	public String hello(@PathParam("name") String name, Model model){
		logger.info("Show hello with name : "+name);
		logger.info("Start  updateMusicFromFiles ");
		Main.updateMusicFromFiles();
		logger.info("Stop updateMusicFromFiles ");
		model.addAttribute("name",name);
		return "hello";
		
	}
	
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	@RequestMapping(value="/register", method= RequestMethod.GET)
	public String registerPage(){
		logger.info("Show register page");
		return "register";
		
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public ModelAndView submitForm(@ModelAttribute(value="user") User user){
		logger.info("register user: "+user.toString());
		return new ModelAndView("user-registered", "user",user);
		
	}
	

}
