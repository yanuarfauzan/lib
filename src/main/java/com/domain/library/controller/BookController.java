package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.dto.responses.ResponseData;
import com.domain.library.entity.Books;
import com.domain.library.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/get-all-book")
    public ResponseEntity<ResponseData<List<Books>>> getAllBook() {
        List<Books> books = bookService.getAllBook();
        ResponseData<List<Books>> response = new ResponseData<>();
        response.setStatus(true);
        response.getMessage().add("success");
        response.setPayload(books);
        return ResponseEntity.ok(response);
    }
}
