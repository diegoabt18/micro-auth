package com.crediya.model.users.exeptions;

public class UserInvalidException extends RuntimeException{

    public UserInvalidException(String message) {
        super(message);
    }
}
