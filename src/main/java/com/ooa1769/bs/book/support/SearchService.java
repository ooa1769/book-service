package com.ooa1769.bs.book.support;

import com.ooa1769.bs.book.ApiType;
import com.ooa1769.bs.book.Book;
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
        ApiBookSearcher apiBookSearcher = searchers.get(apiType);
        return apiBookSearcher.search(apiSearchOption);
    }
}
