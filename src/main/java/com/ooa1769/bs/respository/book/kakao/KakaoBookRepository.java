package com.ooa1769.bs.respository.book.kakao;

import com.ooa1769.bs.domain.book.Book;
import com.ooa1769.bs.domain.book.SearchOption;
import com.ooa1769.bs.respository.book.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class KakaoBookRepository implements BookRepository {

    private RestTemplate restTemplate;
    private KakaoApiProperties properties;

    public KakaoBookRepository(RestTemplate restTemplate, KakaoApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public Page<Book> findByQuery(SearchOption searchOption) {
        Optional<KakaoBook> kakaoBookOptional = restApiCall(searchOption);

        if (kakaoBookOptional.isPresent()) {
            KakaoBook kakaoBook = kakaoBookOptional.get();
            List<Book> books = kakaoBook.getDocuments().stream()
                    .map(this::convertBook)
                    .collect(Collectors.toList());

            return new PageImpl<>(books,
                    new PageRequest(searchOption.getPage() - 1, searchOption.getSize()),
                    kakaoBook.getMeta().getPageableCount());
        }

        return new PageImpl<>(Collections.emptyList());
    }

    private Optional<KakaoBook> restApiCall(SearchOption searchOption) {
        ResponseEntity<KakaoBook> responseEntity = null;

        String url = properties.getUrl(searchOption.queryParam());
        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET,
                    getHttpHeaders(),
                    KakaoBook.class);
        } catch (RestClientException e) {
            log.error("RestClientException {}", e.getMessage());
        }

        return Optional.ofNullable(responseEntity.getBody());
    }

    private HttpEntity<?> getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", properties.authorizationHeaderValue());
        return new HttpEntity<>(headers);
    }

    private Book convertBook(KakaoBook.Document document) {
        return Book.builder()
                .title(document.getTitle())
                .contents(document.getContents())
                .url(document.getUrl())
                .isbn(document.getIsbn())
                .datetime(document.getDatetime())
                .authors(document.getAuthors())
                .publisher(document.getPublisher())
                .translators(document.getTranslators())
                .price(document.getPrice())
                .salePrice(document.getSalePrice())
                .saleYn(document.getSaleYn())
                .category(document.getCategory())
                .thumbnail(document.getThumbnail())
                .barcode(document.getBarcode())
                .ebookBarcode(document.getEbookBarcode())
                .status(document.getStatus())
                .build();
    }
}
