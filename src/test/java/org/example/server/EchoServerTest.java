package org.example.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class EchoServerTest {
    private EchoServer server;
    private ExecutorService executor;
    private Thread thread;

    @BeforeEach
    public void setUp() {
        executor = Executors.newFixedThreadPool(10);
        server = new EchoServer(9091, executor);
        thread = new Thread(() -> server.start());
        thread.start();
    }

    @AfterEach
    public void tearDown() {
        executor.shutdownNow();
        thread.interrupt();
    }

    @Test
    public void testEcho() throws IOException {
        try (Socket socket = new Socket("localhost", 9091);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String message = "Well done!";
            out.println(message);
            String response = in.readLine();

            assertEquals(message, response);
        }
    }
}