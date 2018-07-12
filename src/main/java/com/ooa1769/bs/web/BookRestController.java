package com.ooa1769.bs.web;

import com.ooa1769.bs.domain.book.SearchOption;
import com.ooa1769.bs.domain.member.Member;
import com.ooa1769.bs.service.book.BookService;
import com.ooa1769.bs.service.member.MemberService;
import com.ooa1769.bs.support.security.LoginMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;
    private MemberService memberService;

    public BookRestController(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@LoginMember Member member, SearchOption searchOption) {
        memberService.addSearchHistory(member, searchOption.getQuery());
        return new ResponseEntity<>(bookService.search(searchOption), HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<?> getKeywords(@LoginMember Member member) {
        return new ResponseEntity<>(member.getSearchHistories(), HttpStatus.OK);
    }
}
