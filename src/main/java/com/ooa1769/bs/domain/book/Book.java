package com.ooa1769.bs.domain.book;

import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Book {
    private String title;
    private String contents;
    private String url;
    private String isbn;
    private ZonedDateTime datetime;
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
