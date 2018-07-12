package com.ooa1769.bs.web.error;

public class MemberAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MemberAlreadyExistException() {
        super();
    }

    public MemberAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MemberAlreadyExistException(final String message) {
        super(message);
    }

    public MemberAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}