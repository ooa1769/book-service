package com.ooa1769.bs.service.book;

import com.ooa1769.bs.domain.book.Book;
import com.ooa1769.bs.domain.book.SearchOption;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookService {

    Page<Book> search(SearchOption searchOption);
}
