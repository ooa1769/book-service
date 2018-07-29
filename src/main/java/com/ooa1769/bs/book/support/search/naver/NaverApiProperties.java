package com.ooa1769.bs.book.support.search.naver;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Getter
@Setter
@Component
public class NaverApiProperties {

    private final static String NAVER_AUTHORIZATION_HEADER_CLIENT_ID = "X-Naver-Client-Id";
    private final static String KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET = "X-Naver-Client-Secret";

    @Value("${naver.api.url}")
    private String url;

    @Value("${naver.api.clientId}")
    private String clientId;

    @Value("${naver.api.clientSecret}")
    private String clientSecret;

    public String getHeaderClientId() {
        return NAVER_AUTHORIZATION_HEADER_CLIENT_ID;
    }

    public String getHeaderClientSecret() {
        return KAKAO_AUTHORIZATION_HEADER_CLIENT_SECRET;
    }

    public String requestUrl(Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(URI.create(url));
        for (Map.Entry<String, String> param : params.entrySet()) {
            builder.queryParam(param.getKey(), param.getValue());
        }
        return builder.build().toUriString();
    }
}
