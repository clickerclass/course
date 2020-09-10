package com.clickerclass.course.exception;

import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException {
    private String message;

    public DuplicateException(String message) {
        super(message);
    }
}
