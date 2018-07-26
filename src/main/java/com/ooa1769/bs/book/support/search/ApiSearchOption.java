package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.ApiType;
import com.ooa1769.bs.book.SearchOption;
import lombok.Getter;
import lombok.Setter;

public class ApiSearchOption extends SearchOption {

    private static ApiType DEFAULT_API_TYPE = ApiType.KAKAO;

    public ApiSearchOption() {
        super();
        this.apiType = DEFAULT_API_TYPE;
    }

    @Getter
    @Setter
    private ApiType apiType;
}
