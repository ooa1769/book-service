package com.ooa1769.bs.web;

import com.ooa1769.bs.domain.BookDto;
import com.ooa1769.bs.service.KakaoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private KakaoSearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String query) throws UnsupportedEncodingException {
        BookDto.Request searchRequest = new BookDto.Request(query, "title", 1, 10);
        return new ResponseEntity<>(searchService.search(searchRequest), HttpStatus.OK);
    }
}
