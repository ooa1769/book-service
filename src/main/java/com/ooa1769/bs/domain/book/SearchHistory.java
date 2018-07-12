package com.ooa1769.bs.domain.book;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class SearchHistory {

    @Getter
    private String searchKeyword;

    @Getter
    private LocalDateTime searchDate;

    protected SearchHistory() {}

    public SearchHistory(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        this.searchDate = LocalDateTime.now();
    }

    public SearchHistory(String searchKeyword, LocalDateTime searchDate) {
        this.searchKeyword = searchKeyword;
        this.searchDate = searchDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchHistory)) return false;
        SearchHistory that = (SearchHistory) o;
        return Objects.equals(searchKeyword, that.searchKeyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchKeyword);
    }

    @Override
    public String toString() {
        return "SearchHistory [searchKeyword=" + searchKeyword + ", searchDate=" + searchDate + "]";

    }
}
