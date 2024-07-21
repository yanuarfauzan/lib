package com.domain.library.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqBorrowReturn {
    @NotEmpty(message = "Member Id cannot be empty")
    private String memberId;
    @NotEmpty(message = "Book Id cannot be empty")
    private String bookId;
}
