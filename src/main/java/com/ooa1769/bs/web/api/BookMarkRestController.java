package com.ooa1769.bs.web.api;

import com.ooa1769.bs.book.BookMark;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.dto.BookMarkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(Mappings.BOOKMARKS)
public class BookMarkRestController {

    private final BookService bookService;

    @Autowired
    public BookMarkRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<?> addBookMark(@LoginMember Member member, BookMarkDto bookMarkDto) {
        BookMark bookMark = bookService.addBookMark(bookMarkDto, member);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(bookMark.generateRestUrl()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookMark(@PathVariable Long id) {
        BookMark bookMark = bookService.getBookMark(id);
        bookService.deleteBookMark(bookMark);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
