package com.upms.service;

import com.upms.domain.Book;

import java.util.List;


public interface BookService {
    List<Book> findAll();

    Book insertBook(Book book);

    Book findById(Long id);

    Boolean deleteBook(Long id);

    Book updateBook(Book book);
}
