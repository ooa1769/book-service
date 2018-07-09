package com.ooa1769.bs.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @GetMapping("/books")
    public String book() {
        return "hello book";
    }
}
