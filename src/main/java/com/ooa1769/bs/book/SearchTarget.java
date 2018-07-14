package com.ooa1769.bs.book;

public enum SearchTarget {

    ALL("all"),
    TITLE("title"),
    ISBN("isbn"),
    KEYWORD("keyword"),
    CONTENTS("contents"),
    OVERVIEW("overview"),
    PUBLISHER("publisher");

    private String target;

    SearchTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return getTarget();
    }
}
