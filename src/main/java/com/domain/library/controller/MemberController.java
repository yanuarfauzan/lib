package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.dto.requests.ReqBorrowReturn;
import com.domain.library.dto.responses.ResBorrowReturn;
import com.domain.library.dto.responses.ResBorrowReturnData;
import com.domain.library.dto.responses.ResponseData;
import com.domain.library.entity.Members;
import com.domain.library.service.MemberService;
import com.domain.library.utility.ErrorsParsingUtiliy;

import jakarta.validation.Valid;



@RestController()
@Validated
@RequestMapping("/api/member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @GetMapping("/get-all-member")
    public ResponseEntity<ResponseData<List<Members>>> getAllMember() {
        List<Members> members = memberService.getAllMember();
        ResponseData<List<Members>> response = new ResponseData<>();
        response.setStatus(true);
        response.getMessage().add("success");
        response.setPayload(members);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/borrow-book")
    public ResponseEntity<ResponseData<ResBorrowReturnData<ResBorrowReturn>>> borrowBook(@Valid @RequestBody ReqBorrowReturn request, Errors errors) {
        ResponseData<ResBorrowReturnData<ResBorrowReturn>> response = new ResponseData<>();
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessage(ErrorsParsingUtiliy.parse(errors));
        }
        ResBorrowReturnData<ResBorrowReturn> result = memberService.borrowBook(request.getMemberId(), request.getBookId());
        response.setStatus(true);
        response.getMessage().add(result.getMessage());
        response.setPayload(result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/return-book")
    public ResponseEntity<ResponseData<ResBorrowReturnData<ResBorrowReturn>>> returnBook(@Valid @RequestBody ReqBorrowReturn request, Errors errors) {
        ResponseData<ResBorrowReturnData<ResBorrowReturn>> response = new ResponseData<>();
        if (errors.hasErrors()) {
            response.setStatus(false);
            response.setMessage(ErrorsParsingUtiliy.parse(errors));
        }
        ResBorrowReturnData<ResBorrowReturn> result = memberService.returnBook(request.getMemberId(), request.getBookId());
        response.setStatus(true);
        response.getMessage().add(result.getMessage());
        response.setPayload(result);
        return ResponseEntity.ok(response);
    }
    
    
}
