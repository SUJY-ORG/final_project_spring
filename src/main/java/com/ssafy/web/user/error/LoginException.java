package com.ssafy.web.user.error;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }
}
