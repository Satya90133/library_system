package com.example.library.service;

import com.example.library.model.Borrower;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface BorrowerService {
    Borrower addBorrower(Borrower borrower);

    Optional<Borrower> getBorrowerById(Long borrowerId);

    void borrowBook(Long borrowerId, Long bookId);

}
