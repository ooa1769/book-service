package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.Book;
import com.ooa1769.bs.book.SearchOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final SearchService searchService;
    private final BookMarkRepository bookMarkRepository;

    @Autowired
    public BookService(SearchService searchService, BookMarkRepository bookMarkRepository) {
        this.searchService = searchService;
        this.bookMarkRepository = bookMarkRepository;
    }

    public Page<Book> getBooks(SearchOption searchOption) {
        return searchService.findByQuery(searchOption);
    }
}
