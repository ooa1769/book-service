package com.ooa1769.bs.book.web.api;

import com.ooa1769.bs.book.domain.SearchOption;
import com.ooa1769.bs.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(SearchOption searchOption) {
        return new ResponseEntity<>(bookService.search(searchOption), HttpStatus.OK);
    }
}
