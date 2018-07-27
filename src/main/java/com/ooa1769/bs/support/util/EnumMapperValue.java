package com.ooa1769.bs.support.util;

public class EnumMapperValue {

    private String code;
    private String displayName;

    public EnumMapperValue(EnumMapper enumMapper) {
        code = enumMapper.getCode();
        displayName = enumMapper.getDisplayName();
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
