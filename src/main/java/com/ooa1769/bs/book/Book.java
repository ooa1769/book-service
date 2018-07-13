package com.ooa1769.bs.book;

import lombok.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
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

    @Builder
    public Book(String title, String contents, String url, String isbn, ZonedDateTime datetime, List<String> authors, String publisher, List<String> translators, Integer price, Integer salePrice, String saleYn, String category, String thumbnail, String barcode, String ebookBarcode, String status) {
        this.title = title;
        this.contents = contents;
        this.url = url;
        this.isbn = isbn;
        this.datetime = datetime;
        this.authors = authors;
        this.publisher = publisher;
        this.translators = translators;
        this.price = price;
        this.salePrice = salePrice;
        this.saleYn = saleYn;
        this.category = category;
        this.thumbnail = thumbnail;
        this.barcode = barcode;
        this.ebookBarcode = ebookBarcode;
        this.status = status;
    }

    public String getIsbn() {
        if (Objects.nonNull(isbn)) {
            String[] isbns = isbn.split(" ");
            return isbns.length == 2 ? isbns[1] : isbns[0];
        }

        return null;
    }
}
