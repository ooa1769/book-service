package com.ooa1769.bs.book;

public enum ApiType {

    KAKAO("kakao", "카카오");

    private String code;
    private String name;

    ApiType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ApiType code(String code){
        switch (code){
            case "kakao" :
                return KAKAO;
            default:
                return KAKAO;
        }
    }
}
