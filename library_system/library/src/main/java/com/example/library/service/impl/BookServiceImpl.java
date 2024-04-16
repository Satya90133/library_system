package com.example.library.service.impl;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    private AtomicLong nextBookId = new AtomicLong(1);

    @Override
    public Book addBook(Book book) throws Exception {
        // Generate unique ID for the book
        var bookId = nextBookId.getAndIncrement();
        book.setId(bookId);

        // Check if a book with the same ISBN exists
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            // If a book with the same ISBN exists, ensure title and author match
            if (!existingBook.get().getTitle().equals(book.getTitle()) || !existingBook.get().getAuthor().equals(book.getAuthor())) {
                throw new Exception("A book with the same ISBN but different title or author already exists.");
            }
            // If title and author match, return the existing book
            return existingBook.get();
        }

        // If no book with the same ISBN exists, save the new book
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long bookId) {
        return bookRepository.getBookById(bookId);
    }

    @Override
    public List<Book> findAllBooks(String title, String author) {
        try {
            if (title != null || author != null) {
                return bookRepository.searchBookByTitleAndAuthor(title, author);
            }
            return bookRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error in BookService --> findAllBooks " + ex);
            throw ex;
        }
    }
}
