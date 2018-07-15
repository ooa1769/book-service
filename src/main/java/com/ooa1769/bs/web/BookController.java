package com.ooa1769.bs.web;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.book.SearchTarget;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.member.support.MemberService;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(Mappings.BOOK)
public class BookController {

    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    public BookController(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, @LoginMember Member member, @ModelAttribute("searchOption") SearchOption searchOption) {
        if (!ObjectUtils.isEmpty(member) && !ObjectUtils.isEmpty(searchOption.getQuery())) {
            memberService.addSearchHistory(member, searchOption.getQuery());
        }

        Page<Book> pageBook = bookService.getBooksByKeyword(searchOption);
        model.addAttribute("books", pageBook.getContent());
        model.addAttribute("targets", SearchTarget.values());
        model.addAttribute("pagingInfo", new PagingInfo(pageBook));
        return "book/index";
    }
}
