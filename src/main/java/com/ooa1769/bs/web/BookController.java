package com.ooa1769.bs.web;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.SearchOption;
import com.ooa1769.bs.book.SearchTarget;
import com.ooa1769.bs.book.support.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, @ModelAttribute("searchOption") SearchOption searchOption) {
        Page<Book> pageBook = bookService.getBooksByKeyword(searchOption);
        model.addAttribute("books", pageBook.getContent());
        model.addAttribute("targets", SearchTarget.values());
        return "book/index";
    }
}
