package org.example.handler;

import org.example.exception.SocketCloseException;
import org.example.exception.SocketHandlerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input;
            while ((input = in.readLine()) != null) {
                out.println(input);
            }
        } catch (IOException e) {
            throw new SocketHandlerException("Error while handling socket communication", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new SocketCloseException("Error while closing socket", e);
            }
        }
    }
}
