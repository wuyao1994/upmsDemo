package com.upms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.upms.domain.Book;
import com.upms.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {
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
		bookService.insertBook(book);
		return "redirect:/book/all";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		return "newBook";
	}



	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book findBookById(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
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
		bookService.updateBook(book);
		return "redirect:/book/all";
	}



	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String allBook(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "books";
	}
}
