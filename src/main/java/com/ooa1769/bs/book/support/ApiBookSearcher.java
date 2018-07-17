package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.Book;
import org.springframework.data.domain.Page;

public interface ApiBookSearcher {

    Page<Book> search(ApiSearchOption apiSearchOption);
}
