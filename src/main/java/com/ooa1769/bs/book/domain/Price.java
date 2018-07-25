package com.ooa1769.bs.book.domain;

import lombok.Getter;

@Getter
public class Price {

    private Integer price;
    private Integer salePrice;

    public Price(Integer price, Integer salePrice) {
        this.price = price;
        this.salePrice = salePrice;
    }
}
