package com.ooa1769.bs.web;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.domain.BookMark;
import com.ooa1769.bs.book.domain.SearchHistory;
import com.ooa1769.bs.book.support.BookService;
import com.ooa1769.bs.book.support.SearchHistoryService;
import com.ooa1769.bs.book.support.search.ApiType;
import com.ooa1769.bs.book.support.search.BookSearchParam;
import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.security.LoginMember;
import com.ooa1769.bs.support.util.Mappings;
import com.ooa1769.bs.web.paging.PagingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private final BookService bookService;
    private final SearchHistoryService searchHistoryService;

    @Autowired
    public BookController(BookService bookService, SearchHistoryService searchHistoryService) {
        this.bookService = bookService;
        this.searchHistoryService = searchHistoryService;
    }

    @RequestMapping(value = "/search/{apiType}", method = RequestMethod.GET)
    public String search(@LoginMember Member member,
                         @PathVariable("apiType") ApiType apiType,
                         @ModelAttribute("searchOption") BookSearchParam bookSearchParam, Model model) {
        if (!ObjectUtils.isEmpty(member) && !ObjectUtils.isEmpty(bookSearchParam.getQuery())) {
            searchHistoryService.addSearchHistory(member, bookSearchParam.getQuery());
        }

        Page<Book> pageBook = bookService.getBooksByKeyword(apiType, bookSearchParam);
        model.addAttribute("targets", apiType.targetTypeValues());
        model.addAttribute("categories", apiType.categoryTypeValues());
        model.addAttribute("sorts", apiType.sortTypeValues());
        model.addAttribute("books", pageBook.getContent());
        model.addAttribute("apiType", apiType);
        model.addAttribute("pagingInfo", new PagingInfo(pageBook));
        return "book/search";
    }

    @RequestMapping(value = "/book/{apiType}", method = RequestMethod.GET)
    public String view(@PathVariable("apiType") ApiType apiType,
                       @ModelAttribute("searchOption") BookSearchParam bookSearchParam,
                       @RequestParam(defaultValue = "") String type,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        model.addAttribute("book", bookService.getBookByIsbn(apiType, bookSearchParam));
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("apiType", apiType);
        return "book/view";
    }

    // ============== BookMark ============
    @RequestMapping(value = Mappings.BOOKMARK, method = RequestMethod.GET)
    public String bookmarkList(@LoginMember Member member,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,
                               Model model) {
        Pageable pageable = new PageRequest(page - 1, size, Sort.Direction.DESC, "createDate");
        Page<BookMark> pageBookMark = bookService.getBookMarksByMember(member, pageable);
        model.addAttribute("pageable", pageable);
        model.addAttribute("bookmarks", pageBookMark.getContent());
        model.addAttribute("pagingInfo", new PagingInfo(pageBookMark));
        return "book/bookmark";
    }

    // ============== SearchHistory ============
    @RequestMapping("/history")
    public String searchHistory(@LoginMember Member member,
                                @RequestParam(defaultValue = "1") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model) {
        Pageable pageable = new PageRequest(page - 1, size, Sort.Direction.DESC, "searchDate");
        Page<SearchHistory> pageSearchHistory = searchHistoryService.getHistoryByMember(member, pageable);
        model.addAttribute("pageable", pageable);
        model.addAttribute("histories", pageSearchHistory.getContent());
        model.addAttribute("pagingInfo", new PagingInfo(pageSearchHistory));
        return "book/searchHistory";
    }
}
