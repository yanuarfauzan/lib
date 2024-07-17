package com.domain.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController()
@RequestMapping("/api/member")
public class MemberController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
    
}
