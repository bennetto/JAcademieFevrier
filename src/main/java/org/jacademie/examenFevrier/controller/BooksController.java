package org.jacademie.examenFevrier.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jacademie.examenFevrier.bo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class BooksController {
	/*
	private static final Logger logger = Logger.getLogger(BooksController.class);
	
 
	

	
	@ModelAttribute("books")
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		Book book = new Book(1,"Ben","java");
		Book book2 = new Book(2,"jm","painture");
		books.add(book);
		books.add(book2);
		
		return books;
	}
	
	

	
	@RequestMapping(value="/books", method= RequestMethod.GET)
	public ModelAndView book(@ModelAttribute(value="books") List<Book> books){
		logger.info("register user: "+books.toString());
		return new ModelAndView("list-book", "book",books.get(0));
		
	}
	
	@RequestMapping(value="/books", method= RequestMethod.GET)
	public ModelAndView listBook(@ModelAttribute(value="books") List<Book> books){
		logger.info("register user: "+books.toString());
		return new ModelAndView("list-book", "books",books);
		
	}
	
*/
}
