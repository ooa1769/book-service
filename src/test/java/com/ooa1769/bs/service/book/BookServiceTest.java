package com.ooa1769.bs.service.book;

import com.ooa1769.bs.domain.book.Book;
import com.ooa1769.bs.domain.book.SearchOption;
import com.ooa1769.bs.service.book.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(value = {"classpath:kakao.properties"})
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void 책_검색() throws Exception {
        SearchOption searchOption = new SearchOption();
        searchOption.setQuery("토비의 스프링");
        searchOption.setTarget("title");

        Page<Book> books = bookService.search(searchOption);
        assertThat(books).isNotEmpty();
        assertThat(books.getNumber()).isEqualTo(0);
        assertThat(books.getSize()).isEqualTo(10);
    }
}
