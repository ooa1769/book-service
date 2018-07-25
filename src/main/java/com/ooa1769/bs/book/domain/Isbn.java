package com.ooa1769.bs.book.domain;

import lombok.Getter;

public class Isbn {

    public final static Isbn INVALID_ISBN = new Isbn("INVALID_ISBN");

    @Getter
    private String isbn;

    public Isbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isValid() {
        return this != INVALID_ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Isbn isbn1 = (Isbn) o;

        return isbn != null ? isbn.equals(isbn1.isbn) : isbn1.isbn == null;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Isbn[" + "isbn=" + isbn + "]";
    }
}
