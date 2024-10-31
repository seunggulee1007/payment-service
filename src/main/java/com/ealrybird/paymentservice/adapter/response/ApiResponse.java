package com.ealrybird.paymentservice.adapter.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private int status = 200;
    private String message = "";
    private T data;

    public static <E> ApiResponse<E> with(HttpStatus status, String message, E data) {
        ApiResponse<E> response = new ApiResponse<>();
        response.status = status.value();
        response.message = message;
        response.data = data;
        return response;
    }

}
