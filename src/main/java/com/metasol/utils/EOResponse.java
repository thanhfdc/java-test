package com.metasol.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collection;

@Getter
@Setter
public class EOResponse<T> {

    private int code;
    private String message;
    private T data;
    private int total;

    public static <T> EOResponse<T> build(int code, String messageUrl, Object...args){
        EOResponse<T> response = new EOResponse<>();
        response.code = code;
        response.message = messageUrl;
        return  response;
    }

    public static <T> EOResponse<T> build (T data){
        EOResponse<T> response = new EOResponse<>();
        response.data = data;
        response.code = HttpStatus.OK.value();

        if (data instanceof Collection) {
            response.total = ((Collection) data).size();
        }

        return response;
    }
}
