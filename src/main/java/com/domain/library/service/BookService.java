package com.domain.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.library.entity.Books;
import com.domain.library.enums.BookStatus;
import com.domain.library.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAllBook() {
        return bookRepository.findByStatus(BookStatus.AVAILABLE);
    }
}
