package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.service.BookService;
import com.example.library.service.BorrowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Borrower>> getById(@PathVariable Long id) {
        Optional<Borrower> returnBorrower = borrowerService.getBorrowerById(id);
        if(returnBorrower.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(returnBorrower);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping("/addBorrower")
    public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower) {
        Borrower retunBorrower = borrowerService.addBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(retunBorrower);
    }

    @PostMapping("/{borrowerId}/borrow/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        try {
            borrowerService.borrowBook(borrowerId, bookId);
            return ResponseEntity.ok("Book successfully borrowed");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
        }
    }
}
