package com.ooa1769.bs.book.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Book {

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
        private Integer salePrice;
        private String saleYn;
        private String category;
        private String thumbnail;
        private String barcode;
        private String ebookBarcode;
        private String status;
    }

    @Data
    private static class Meta {
        private boolean end;
        private Integer pageableCount;
        private Integer totalCount;
    }
}
