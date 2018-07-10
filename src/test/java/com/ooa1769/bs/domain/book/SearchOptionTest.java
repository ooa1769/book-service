package com.ooa1769.bs.domain.book;

import com.ooa1769.bs.book.domain.SearchOption;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchOptionTest {

    @Test
    public void testName() throws Exception {
        SearchOption searchOption = new SearchOption();
        searchOption.setTarget("title");
        searchOption.setQuery("스프링");
        searchOption.setCategory(33);
        assertEquals("page=1&size=10&query=스프링&target=title&category=33", searchOption.queryParam());
    }
}
