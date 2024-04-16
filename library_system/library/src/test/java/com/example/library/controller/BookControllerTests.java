package com.example.library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "dev")
class BookControllerTests {

    private BookController bookController;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = mock(BookService.class);
        bookController = new BookController(bookService);
    }

    @Test
    void addBook_ReturnsCreatedStatus() throws Exception {
        // Mocking the behavior of BookService to return the book after saving
        Book newBook = new Book(1L, "978-3-16-148410-0", "Book Title", "Author Name");
        when(bookService.addBook(newBook)).thenReturn(newBook);

        // Calling the addBook method of BookController
        ResponseEntity<Book> responseEntity = bookController.addBook(newBook);

        // Asserting the response status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Asserting the returned book
        assertEquals(newBook, responseEntity.getBody());
    }

    @Test
    void getAllBooks_ReturnsListOfBooks() {
        // Mocking the behavior of BookService to return a list of books
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1L, "978-3-16-148410-0", "Book Title 1", "Author 1"));
        mockBooks.add(new Book(2L, "978-3-16-148410-1", "Book Title 2", "Author 2"));
        when(bookService.findAllBooks(null, null)).thenReturn(mockBooks);

        // Calling the getAllBooks method of BookController
        ResponseEntity<List<Book>> responseEntity = bookController.getAllBooks(null, null);

        // Asserting the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Asserting the returned list of books
        assertEquals(mockBooks, responseEntity.getBody());
    }
}