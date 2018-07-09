package com.ooa1769.bs.book.web.api;

import com.ooa1769.bs.book.domain.BookDto;
import com.ooa1769.bs.book.infra.respository.rest.KakaoBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private KakaoBookRepository repository;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String query) {
        BookDto.Request searchRequest = new BookDto.Request(query, "title", 1, 10);
        return new ResponseEntity<>(repository.search(searchRequest), HttpStatus.OK);
    }
}
