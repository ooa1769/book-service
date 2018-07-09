package com.ooa1769.bs.service;

import com.ooa1769.bs.domain.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class KakaoSearchService {

    @Autowired
    private RestTemplate restTemplate;

    public BookDto.Response search(BookDto.Request searchRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK 5c1c834ba187f8aef58ad37de5b374ea");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl("https://dapi.kakao.com/v2/search/book")
                .queryParam("target", searchRequest.getTarget())
                .queryParam("query", searchRequest.getQuery())
                .queryParam("page", searchRequest.getPage())
                .queryParam("size", searchRequest.getSize())
                .build();

        return restTemplate.exchange(
                uriComponents.toUriString(),
                HttpMethod.GET,
                entity,
                BookDto.Response.class).getBody();
    }
}
