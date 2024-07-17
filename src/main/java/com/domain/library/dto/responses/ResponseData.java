package com.domain.library.dto.responses;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseData<T> {
    private Boolean status;
    private List<String> message = new ArrayList<>();
    private T payload;
}
