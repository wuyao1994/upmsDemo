package com.upms.service.book.impl;

import java.util.List;

import com.upms.domain.book.Book;
import com.upms.repository.book.BookRepository;
import com.upms.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;



    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }



    @Override
    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }



    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }



    @Override
    public Boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }



    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
