package com.saman.sso.rest.model;

import java.util.stream.Stream;

public enum ClientType {

    PUBLIC, CONFIDENTIAL, UNKNOWN;


    public static ClientType instanceOf(String name) {
        return Stream.of(ClientType.values()).findAny().orElse(UNKNOWN);
    }
}