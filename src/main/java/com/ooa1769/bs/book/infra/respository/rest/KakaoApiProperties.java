package com.ooa1769.bs.book.infra.respository.rest;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class KakaoApiProperties {

    private final static String DEFAULT_URL = "https://dapi.kakao.com/v2/search/book";
    private final static String KAKAO_AUTHORIZATION_HEADER = "KakaoAK";

    private String key;
    private String url = DEFAULT_URL;

    public String authorizationHeaderValue() {
        Assert.notNull(key, "api key not null");
        return String.format("%s %s",KAKAO_AUTHORIZATION_HEADER, key);
    }
}
