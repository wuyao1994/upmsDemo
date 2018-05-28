package com.upms.service.book.impl;

import com.upms.domain.book.Book;
import com.upms.repository.book.BookRepository;
import com.upms.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "books")
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
    @Cacheable(key = "#p0")
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }



    @Override
    @CacheEvict(key = "#p0")
    public Boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }



    @Override
    @CachePut(key = "#p0.id")
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
