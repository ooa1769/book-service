package com.ooa1769.bs.book.service;

import com.ooa1769.bs.book.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> getBook(String keyword, Pageable pageable);
}
