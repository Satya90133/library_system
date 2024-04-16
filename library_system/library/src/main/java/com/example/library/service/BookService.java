package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAllBooks(String title, String author);

    Book addBook(Book book) throws Exception;

    Book getBookById(long l);
}
