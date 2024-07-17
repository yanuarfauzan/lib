package com.domain.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    
}
