package com.ooa1769.bs.respository.book;

import com.ooa1769.bs.domain.book.Book;
import com.ooa1769.bs.domain.book.SearchOption;
import org.springframework.data.domain.Page;

public interface BookRepository {

    Page<Book> findByQuery(SearchOption searchOption);
}
