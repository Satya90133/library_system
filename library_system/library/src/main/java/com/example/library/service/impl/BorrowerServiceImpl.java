package com.example.library.service.impl;

import com.example.library.exception.NotFoundException;
import com.example.library.model.Book;
import com.example.library.model.Borrower;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowerServiceImpl.class);

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    private Lock lock = new ReentrantLock();

    @Override
    public Borrower addBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    @Override
    public Optional<Borrower> getBorrowerById(Long borrowerId) {
        return borrowerRepository.findById(borrowerId);
    }

    @Override
    public void borrowBook(Long borrowerId, Long bookId) {
        lock.lock();
        try {
            List<Book> bookList = new ArrayList<>();

            // Check if the borrower exists
            Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
            if (borrower.isEmpty()) {
                 throw new NotFoundException("Borrower not found");
            }

            // Check if the book exists
            Optional<Book> book= bookRepository.findById(bookId);
            if (book.isEmpty()) {
                throw new NotFoundException("Book not found");
            }

            bookList.add(book.get());
            borrower.get().setBooks(bookList);
            borrowerRepository.save(borrower.get());
            LOGGER.info("BorrowerController borrowBook()-->borrowerID:{}, bookId:{}",borrowerId, bookId);
        } catch (Exception ex) {
            LOGGER.debug("Exception..", ex);
            throw ex;
        } finally {
            lock.unlock();
        }
    }
}
