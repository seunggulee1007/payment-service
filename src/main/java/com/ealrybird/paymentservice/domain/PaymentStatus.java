package com.ealrybird.paymentservice.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatus {

    NOT_STARTED("결제 시작 전"),
    EXECUTING("결제 중"),
    SUCCESS("결제 승인 완료"),
    FAILURE("결제 승인 실패"),
    UNKNOWN("결제 승인 알 수 없는 상태"),
    ;
    private String description;

    public static PaymentStatus get(String paymentOrderStatus) {
        return valueOf(paymentOrderStatus);
    }
}
