package com.domain.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.library.dto.responses.ResponseData;
import com.domain.library.entity.Member;
import com.domain.library.service.MemberService;


@RestController()
@RequestMapping("/api/member")
public class MemberController {
    
    @Autowired
    private MemberService memberService;

    @GetMapping("/get-all-member")
    public ResponseEntity<ResponseData<List<Member>>> getAllMember() {
        List<Member> members = memberService.getAllMember();
        ResponseData<List<Member>> response = new ResponseData<>();
        response.setStatus(true);
        response.getMessage().add("success");
        response.setPayload(members);
        return ResponseEntity.ok(response);
    }
    
}
