package com.crediya.api.exeptions;

public class InvalidFormatDate extends RuntimeException {
    public InvalidFormatDate(String message) {
        super(message);
    }
}
