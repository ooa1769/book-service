package com.ooa1769.bs.web;

import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.security.LoginMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    public BookRestController(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@LoginMember Member member, SearchOption searchOption) {
        memberService.addSearchHistory(member, searchOption.getQuery());
        return new ResponseEntity<>(bookService.getBooks(searchOption), HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<?> getKeywords(@LoginMember Member member) {
        return new ResponseEntity<>(member.getSearchHistories(), HttpStatus.OK);
    }
}
