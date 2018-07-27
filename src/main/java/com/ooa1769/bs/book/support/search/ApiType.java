package com.ooa1769.bs.book.support.search;

import com.ooa1769.bs.book.support.search.kakao.KakaoCategoryType;
import com.ooa1769.bs.book.support.search.kakao.KakaoSortType;
import com.ooa1769.bs.book.support.search.kakao.KakaoTargetType;
import com.ooa1769.bs.support.util.EnumMapperValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ApiType {

    KAKAO("kakao", "카카오") {
        @Override
        public List<EnumMapperValue> categoryTypeValues() {
            return Arrays.stream(KakaoCategoryType.values())
                    .map(EnumMapperValue::new)
                    .collect(Collectors.toList());
        }

        @Override
        public List<EnumMapperValue> targetTypeValues() {
            return Arrays.stream(KakaoTargetType.values())
                    .map(EnumMapperValue::new)
                    .collect(Collectors.toList());
        }

        @Override
        public List<EnumMapperValue> sortTypeValues() {
            return Arrays.stream(KakaoSortType.values())
                    .map(EnumMapperValue::new)
                    .collect(Collectors.toList());
        }
    },
    NAVER("naver", "네이버") {
        @Override
        public List<EnumMapperValue> categoryTypeValues() {
            return null;
        }

        @Override
        public List<EnumMapperValue> targetTypeValues() {
            return null;
        }

        @Override
        public List<EnumMapperValue> sortTypeValues() {
            return null;
        }
    };

    private String code;
    private String name;

    ApiType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public abstract List<EnumMapperValue> categoryTypeValues();
    public abstract List<EnumMapperValue> targetTypeValues();
    public abstract List<EnumMapperValue> sortTypeValues();

    public static ApiType code(String code){
        switch (code){
            case "kakao" :
                return KAKAO;
            case "naver" :
                return NAVER;
            default:
                return KAKAO;
        }
    }
}
