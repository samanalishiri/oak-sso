package com.saman.sso.rest.model;

import java.util.stream.Stream;

public enum ClientType {

    PUBLIC(1), CONFIDENTIAL(2), UNKNOWN(-1);

    private final int value;

    ClientType(int value) {
        this.value = value;
    }

    public static ClientType instanceOf(String name) {
        return Stream.of(ClientType.values())
                .filter(t -> name.equals(t.name()))
                .findAny()
                .orElse(UNKNOWN);
    }

    public int getValue() {
        return value;
    }
}