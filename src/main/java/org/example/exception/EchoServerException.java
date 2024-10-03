package org.example.exception;

public class EchoServerException extends RuntimeException {
    public EchoServerException(String message) {
        super(message);
    }

    public EchoServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
