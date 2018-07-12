package com.ooa1769.bs.support.security;

public class UnAuthenticationException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnAuthenticationException() {
        super();
    }

    public UnAuthenticationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnAuthenticationException(final String message) {
        super(message);
    }

    public UnAuthenticationException(final Throwable cause) {
        super(cause);
    }
}
