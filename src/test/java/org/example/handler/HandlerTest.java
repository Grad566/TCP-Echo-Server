package org.example.handler;

import org.example.exception.SocketHandlerException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HandlerTest {
    @Test
    public void testHandlerThrowsExceptionOnSocketCommunicationFailure() throws IOException {
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenThrow(new IOException("Input stream error"));

        Handler handler = new Handler(mockSocket);

        assertThrows(SocketHandlerException.class, handler::run);
    }

}