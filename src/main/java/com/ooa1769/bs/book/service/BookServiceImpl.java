package com.ooa1769.bs.book.service;

import com.ooa1769.bs.book.domain.Book;
import com.ooa1769.bs.book.domain.SearchOption;
import com.ooa1769.bs.book.respository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> search(SearchOption searchOption) {
        return bookRepository.findByQuery(searchOption);
    }
}
