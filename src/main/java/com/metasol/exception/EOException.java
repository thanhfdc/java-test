package com.metasol.exception;

import com.metasol.error.ErrorStatus;
import com.metasol.utils.I18n;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class EOException extends RuntimeException {
    protected final int code;
    protected final String message;

    public EOException(HttpStatus httpStatus, String messageUrl, Object... arg) {
        this.code = httpStatus.value();
        this.message = I18n.get(messageUrl, arg);
    }

    public EOException(int code, String messageUrl, Object... arg) {
        this.code = code;
        this.message = I18n.get(messageUrl, arg);
    }
    public EOException(ErrorStatus enumError, Object... arg) {
        this.code = enumError.getCode();
        this.message = I18n.get(enumError.getMessage(), arg);
    }
}
