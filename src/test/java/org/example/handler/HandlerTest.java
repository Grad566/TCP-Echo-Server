package org.example.handler;

import org.example.exception.SocketCloseException;
import org.example.exception.SocketHandlerException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HandlerTest {
    @Test
    public void testHandlerThrowsExceptionOnSocketCommunicationFailure() throws IOException {
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenThrow(new IOException("Input stream error"));

        Handler handler = new Handler(mockSocket);

        assertThrows(SocketHandlerException.class, handler::run);
    }

    @Test
    public void testHandlerEchoesInput() throws IOException {
        Socket mockSocket = mock(Socket.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("Hello\nWorld\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Handler handler = new Handler(mockSocket);
        handler.run();

        String output = outputStream.toString();
        assertEquals("Hello\nWorld\n", output);
    }

    @Test
    public void testHandlerThrowsExceptionOnSocketCloseFailure() throws IOException {
        Socket mockSocket = mock(Socket.class);
        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("Hello\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
        doThrow(new IOException("Close error")).when(mockSocket).close();

        Handler handler = new Handler(mockSocket);

        assertThrows(SocketCloseException.class, handler::run);
    }

    @Test
    public void testHandlerHandlesEmptyString() throws IOException {
        Socket mockSocket = mock(Socket.class);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(new ByteArrayInputStream("\n".getBytes()));
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Handler handler = new Handler(mockSocket);
        handler.run();

        String output = outputStream.toString();
        assertEquals("\n", output);
    }

}