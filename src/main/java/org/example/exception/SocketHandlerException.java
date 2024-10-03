package org.example.exception;

public class SocketHandlerException extends RuntimeException {
    public SocketHandlerException(String message) {
        super(message);
    }

    public SocketHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
