package com.domain.library.dto.responses;

import com.domain.library.entity.Books;

import lombok.Data;

@Data
public class ResBorrowReturn {
    private String memberId;
    private Books book;
}
