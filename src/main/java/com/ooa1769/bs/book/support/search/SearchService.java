package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.ApiType;
import com.ooa1769.bs.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {

    private Map<ApiType, ApiBookSearcher> searchers = new HashMap<>();

    @Autowired
    public SearchService(@Qualifier("kakao") ApiBookSearcher apiBookSearcher) {
        searchers.put(ApiType.KAKAO, apiBookSearcher);
    }

    public Page<Book> search(ApiSearchOption apiSearchOption) {
        ApiType apiType = apiSearchOption.getApiType();
        return searchers.get(apiType).search(apiSearchOption);
    }
}
