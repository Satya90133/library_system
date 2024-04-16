package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.service.BookService;
import com.example.library.service.BorrowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BorrowerControllerTest {
    private BorrowerController borrowerController;
    private BorrowerService borrowerService;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        borrowerService = mock(BorrowerService.class);
        bookService = mock(BookService.class);
        borrowerController = new BorrowerController(borrowerService);
    }

    @Test
    void saveBorrower_ReturnsCreatedStatus() {
        // Mocking the behavior of BorrowerService to return the borrower after saving
        Borrower newBorrower = new Borrower();
        newBorrower.setId(1L);
        newBorrower.setName("John Doe");
        newBorrower.setEmail("john@example.com");

        when(borrowerService.addBorrower(newBorrower)).thenReturn(newBorrower);

        // Calling the saveBorrower method of BorrowerController
        ResponseEntity<Borrower> responseEntity = borrowerController.addBorrower(newBorrower);

        // Asserting the response status
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Asserting the returned borrower
        Assertions.assertEquals(newBorrower, responseEntity.getBody());
    }

    @Test
    void borrowBook_ReturnsOkStatus() {
        // Mocking the behavior of BookService to return a book
        Book book = new Book(1L, "978-3-16-148410-0", "Book Title", "Author Name");
        when(bookService.getBookById(1L)).thenReturn(book);

        // Mocking the behavior of BorrowerService to return a borrower
        Borrower borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("ABC");
        borrower.setEmail("ABC@EXAMPLE.COM");

        when(borrowerService.getBorrowerById(1L)).thenReturn(Optional.of(borrower));

        // Calling the borrowBook method of BorrowerController
        ResponseEntity<?> responseEntity = borrowerController.borrowBook(1L, 1L);

        // Asserting the response status
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}