package com.ealrybird.paymentservice.common;

import java.util.UUID;

public class IdempootencyCreator {

    public static String create(Object object) {
        return UUID.nameUUIDFromBytes(object.toString().getBytes()).toString();
    }

}
