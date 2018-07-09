package com.ooa1769.bs.book.infra.respository.rest;

import lombok.Data;

@Data
public class KakaoApiProperties {

    private final static String DEFAULT_URL = "https://dapi.kakao.com/v2/search/book";

    private String key;
    private String url;


}
