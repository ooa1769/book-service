package com.ooa1769.bs.book.infra.respository.rest;

import com.ooa1769.bs.book.domain.BookDto;
import com.ooa1769.bs.book.domain.BookRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class KakaoBookRepository implements BookRepository {

    private RestTemplate restTemplate;
    private KakaoApiProperties properties;

    public KakaoBookRepository(RestTemplate restTemplate, KakaoApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public BookDto.Response search(BookDto.Request searchRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", properties.authorizationHeaderValue());

        HttpEntity<?> entity = new HttpEntity<>(headers);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(properties.getUrl())
                .queryParam("target", searchRequest.getTarget())
                .queryParam("query", searchRequest.getQuery())
                .queryParam("page", searchRequest.getPage())
                .queryParam("size", searchRequest.getSize())
                .build();

        return restTemplate.exchange(uriComponents.toUriString(),
                HttpMethod.GET,
                entity,
                BookDto.Response.class).getBody();
    }
}
