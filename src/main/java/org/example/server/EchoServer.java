package org.example.server;

import org.example.exception.EchoServerException;
import org.example.handler.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class EchoServer {
    private final int port;
    private final ExecutorService executor;

    public EchoServer(int port, ExecutorService executor) {
        this.port = port;
        this.executor = executor;
    }

    public void start() {
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = socket.accept();
                Handler handler = new Handler(clientSocket);
                executor.execute(handler);
            }
        } catch (IOException e) {
            throw new EchoServerException("Error while starting the EchoServer", e);
        }
    }
}
