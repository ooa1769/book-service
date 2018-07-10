package com.ooa1769.bs.book.respository;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.domain.SearchOption;
import org.springframework.data.domain.Page;

public interface BookRepository {

    Page<Book> findByQuery(SearchOption searchOption);
}
