package com.ooa1769.bs.book.support.search.kakao;

import com.ooa1769.bs.support.util.EnumMapper;

public enum KakaoTargetType implements EnumMapper {

    ALL("전체"),
    TITLE("책제목"),
    ISBN("isbn"),
    KEYWORD("주제어"),
    CONTENTS("목차"),
    OVERVIEW("책소개"),
    PUBLISHER("출판사");

    private String target;

    KakaoTargetType(String target) {
        this.target = target;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    @Override
    public String getDisplayName() {
        return target;
    }
}
