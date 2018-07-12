package com.ooa1769.bs.service.book;

import com.ooa1769.bs.domain.book.Book;
import com.ooa1769.bs.domain.book.SearchOption;
import com.ooa1769.bs.respository.book.BookRepository;
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
