package com.ooa1769.bs.support.security;

public class UnAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnAuthorizedException(final String message) {
        super(message);
    }

    public UnAuthorizedException(final Throwable cause) {
        super(cause);
    }
}

