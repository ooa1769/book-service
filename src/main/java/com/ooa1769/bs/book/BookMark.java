package com.ooa1769.bs.book;

import com.ooa1769.bs.member.Member;
import com.ooa1769.bs.support.domain.UrlGeneratable;
import com.ooa1769.bs.support.jpa.AbstractEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "bookmark")
public class BookMark extends AbstractEntity implements UrlGeneratable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String isbn;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public BookMark() {
    }

    public BookMark(String isbn, String title, Member member) {
        this.isbn = isbn;
        this.title = title;
        this.member = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookMark bookMark = (BookMark) o;

        if (isbn != null ? !isbn.equals(bookMark.isbn) : bookMark.isbn != null) return false;
        return member != null ? member.equals(bookMark.member) : bookMark.member == null;

    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (member != null ? member.hashCode() : 0);
        return result;
    }

    @Override
    public String generateUrl() {
        return null;
    }

    @Override
    public String generateRestUrl() {
        return String.format("/api/books/bookmarks/%d", id);
    }
}
