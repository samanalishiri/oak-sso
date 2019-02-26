package com.saman.sso.util;

import java.util.UUID;

public final class NumberUtils {

    public NumberUtils() {
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
