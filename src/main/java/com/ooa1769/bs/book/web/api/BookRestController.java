package com.ooa1769.bs.book.web.api;

import com.ooa1769.bs.book.domain.SearchOption;
import com.ooa1769.bs.book.respository.rest.KakaoBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private KakaoBookRepository repository;

    @GetMapping("/search")
    public ResponseEntity<?> search(SearchOption searchOption) {
        return new ResponseEntity<>(repository.search(searchOption), HttpStatus.OK);
    }
}
