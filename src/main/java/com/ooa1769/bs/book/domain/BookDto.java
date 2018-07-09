package com.ooa1769.bs.book.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class BookDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String query;
        private String target;
        private Integer page;
        private Integer size;
    }

    @Data
    public static class Response {
        private List<Document> documents;
        private Meta meta;

        @Data
        private static class Document {
            private String title;
            private String contents;
            private String url;
            private String isbn;
            private Date datetime;
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
        }

        @Data
        private static class Meta {
            @JsonProperty("is_end")
            private boolean end;
            @JsonProperty("pageable_count")
            private Integer pageableCount;
            @JsonProperty("total_count")
            private Integer totalCount;
        }
    }
}
