package com.ooa1769.bs.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

public class SearchOption {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_MAX_PAGE = 50;
    private static final int DEFAULT_SIZE = 10;
    private static final int DEFAULT_MAX_SIZE = 50;

    @Getter
    private int page;

    @Getter
    private int size;

    @Getter @Setter
    private String query;

    @Getter @Setter
    private String target;

    @Getter @Setter
    private Integer category;

    public SearchOption() {
        System.out.println("SearchOption call");
        this.page = DEFAULT_PAGE;
        this.size = DEFAULT_SIZE;
    }

    public void setSize(int size) {
        System.out.println("setSize call");
        this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public void setPage(int page) {
        this.page = page < DEFAULT_PAGE || page > DEFAULT_MAX_PAGE ? DEFAULT_PAGE : page;
    }

    public String queryParam() {
        String queryStringFormat = "&%s=%s";
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s=%s", "page", page))
                .append(String.format(queryStringFormat, "size", size));

        if (!ObjectUtils.isEmpty(query)) {
            builder.append(String.format(queryStringFormat, "query", query));
        }
        if (!ObjectUtils.isEmpty(target)) {
            builder.append(String.format(queryStringFormat, "target", target));
        }
        if (!ObjectUtils.isEmpty(category)) {
            builder.append(String.format(queryStringFormat, "category", category));
        }

        return builder.toString();
    }
}
