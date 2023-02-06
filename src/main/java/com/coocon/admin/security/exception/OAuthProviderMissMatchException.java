package com.coocon.admin.security.exception;

public class OAuthProviderMissMatchException extends RuntimeException{
    public OAuthProviderMissMatchException(String message) {
        super(message);
    }
}
