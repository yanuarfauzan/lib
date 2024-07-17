package com.domain.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.library.entity.Book;
import com.domain.library.enums.BookStatus;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByStatus(BookStatus status);
}
