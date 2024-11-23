package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.Operator;

import java.util.HashMap;
import java.util.Map;

public enum TokenType {

    ACCESS("access"),
    REFRESH("refresh");

    private final String value;
    private static final Map<String, TokenType> SYMBOL_MAP = new HashMap<>();

    static {
        for (TokenType tokenType : TokenType.values()) {
            SYMBOL_MAP.put(tokenType.getValue(), tokenType);
        }
    }

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TokenType fromValue(String value) {
        TokenType tokenType = SYMBOL_MAP.get(value);
        if (tokenType == null)
            throw new IllegalArgumentException("No enum constant with value: " + value);

        return tokenType;
    }

}
