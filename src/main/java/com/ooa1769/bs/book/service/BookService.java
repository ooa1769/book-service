package com.ooa1769.bs.book.service;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.domain.SearchOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookService {

    Page<Book> search(SearchOption searchOption);
}
