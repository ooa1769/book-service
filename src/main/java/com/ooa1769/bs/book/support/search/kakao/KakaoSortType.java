package com.ooa1769.bs.book.support.search.kakao;

import com.ooa1769.bs.support.util.EnumMapper;

public enum KakaoSortType implements EnumMapper {

    ACCURACY("정확도순"),
    RECENCY("최신순");

    private String sort;

    KakaoSortType(String sort) {
        this.sort = sort;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    @Override
    public String getDisplayName() {
        return sort;
    }
}
