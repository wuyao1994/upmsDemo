package com.upms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.upms.domain.book.Book;
import com.upms.service.book.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
	private final static Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	@Autowired
	private BookService bookService;



	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBookList() {
		return bookService.findAll();
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createBook(@ModelAttribute Book book, Model model) {
		LOGGER.debug("create book");
		bookService.insertBook(book);
		return "redirect:/book/all";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		LOGGER.debug("add new book");
		return "newBook";
	}



	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book findBookById(@PathVariable("id") Long id) {
		LOGGER.debug("find book by id={0}", id);
		return bookService.findById(id);
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		LOGGER.debug("delete book by id={0}", id);
		bookService.deleteBook(id);
		return "redirect:/book/all";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateBookPage(Model model, @PathVariable Long id) {
		model.addAttribute("book", bookService.findById(id));
		return "updateBook";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBook(@ModelAttribute Book book, Model model) {
		LOGGER.debug("update book");
		bookService.updateBook(book);
		return "redirect:/book/all";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String allBook(Model model) {
		LOGGER.debug("get all books");
		model.addAttribute("books", bookService.findAll());
		return "books";
	}
}
