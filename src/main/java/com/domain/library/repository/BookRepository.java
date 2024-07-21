package com.domain.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.library.entity.Books;
import com.domain.library.enums.BookStatus;

public interface BookRepository extends JpaRepository<Books, String> {
    List<Books> findByStatus(BookStatus status);
}
