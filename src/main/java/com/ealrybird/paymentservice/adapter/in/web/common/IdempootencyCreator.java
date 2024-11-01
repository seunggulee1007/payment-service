package com.ealrybird.paymentservice.adapter.in.web.common;

import java.util.UUID;

public class IdempootencyCreator {

    public static String create(Object object) {
        return UUID.nameUUIDFromBytes(object.toString().getBytes()).toString();
    }

}
