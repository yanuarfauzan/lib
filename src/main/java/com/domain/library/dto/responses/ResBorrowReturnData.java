package com.domain.library.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBorrowReturnData<T> {
    private String message;
    private T data;
}
