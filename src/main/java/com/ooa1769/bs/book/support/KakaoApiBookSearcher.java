package com.ooa1769.bs.book.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ooa1769.bs.book.*;
import com.ooa1769.bs.book.domain.*;
import com.ooa1769.bs.config.KakaoApiProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("kakao")
@Slf4j
public class KakaoApiBookSearcher implements ApiBookSearcher {

    private final RestTemplate restTemplate;
    private final KakaoApiProperties properties;

    @Autowired
    public KakaoApiBookSearcher(RestTemplate restTemplate, KakaoApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public Page<Book> search(ApiSearchOption apiSearchOption) {
        if (StringUtils.isEmpty(apiSearchOption.getQuery())) {
            return new PageImpl<>(Collections.emptyList(), new PageRequest(0, 10), 0);
        }

        Optional<SearchResult> resultOpt = execute(apiSearchOption);

        if (resultOpt.isPresent()) {
            SearchResult searchResult = resultOpt.get();
            List<Book> books = searchResult.getDocuments().stream()
                    .map(this::convertBook)
                    .collect(Collectors.toList());

            log.debug("books count : {}, pageableCount : {}", books.size(), searchResult.getMeta().getPageableCount());
            return new PageImpl<>(books,
                    new PageRequest(apiSearchOption.getPage() - 1, apiSearchOption.getSize()),
                    searchResult.getMeta().getPageableCount());
        }

        return new PageImpl<>(Collections.emptyList());
    }

    private Optional<SearchResult> execute(SearchOption searchOption) {
        ResponseEntity<SearchResult> responseEntity = null;

        String url = properties.getUrl(searchOption.queryParam());
        try {
            responseEntity = restTemplate.exchange(url,
                    HttpMethod.GET,
                    getHttpHeaders(),
                    SearchResult.class);
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

    private Book convertBook(SearchResult.Document document) {
        return Book.builder()
                .title(document.getTitle())
                .contents(document.getContents())
                .url(document.getUrl())
                .isbn(document.toIsbn())
                .datetime(document.getDatetime())
                .authors(document.getAuthors())
                .publisher(document.getPublisher())
                .translators(document.getTranslators())
                .price(document.toPrice())
                .category(document.getCategory())
                .thumbnail(document.getThumbnail())
                .barcode(document.toBarcode())
                .saleStatus(document.toSaleStatus())
                .build();
    }

    @Getter
    @NoArgsConstructor
    static class SearchResult {

        private List<Document> documents;
        private Meta meta;

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Document {
            private String title;
            private String contents;
            private String url;
            private String isbn;
            private ZonedDateTime datetime;
            private List<String> authors;
            private String publisher;
            private List<String> translators;
            private Integer price;
            @JsonProperty("sale_price")
            private Integer salePrice;
            @JsonProperty("sale_yn")
            private String saleYn;
            private String category;
            private String thumbnail;
            private String barcode;
            @JsonProperty("ebook_barcode")
            private String ebookBarcode;
            private String status;

            Isbn toIsbn() {
                if (!StringUtils.isEmpty(isbn)) {
                    String[] isbns = isbn.split(" ");

                    log.debug("{} /", isbn);
                    // isbn값이 "  " 으로 넘어오는 케이스
                    if (isbns.length == 0) {
                        return Isbn.INVALID_ISBN;
                    }
                    isbn = isbns.length == 2 ? isbns[1] : isbns[0];
                    return new Isbn(isbn);
                }

                return Isbn.INVALID_ISBN;
            }

            Price toPrice() {
                return new Price(price, salePrice);
            }

            Barcode toBarcode() {
                return new Barcode(barcode, ebookBarcode);
            }

            SaleStatus toSaleStatus() {
                return "Y".equals(saleYn) ? SaleStatus.Y : SaleStatus.N;
            }
        }

        @Getter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class Meta {
            @JsonProperty("is_end")
            private boolean end;
            @JsonProperty("pageable_count")
            private Integer pageableCount;
            @JsonProperty("total_count")
            private Integer totalCount;
        }
    }
}
