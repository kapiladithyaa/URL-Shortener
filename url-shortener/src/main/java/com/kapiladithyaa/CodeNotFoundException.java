package com.kapiladithyaa;

public class CodeNotFoundException extends IllegalArgumentException {

    public CodeNotFoundException(String code) {
        super("Code not found: " + code);
    }
}