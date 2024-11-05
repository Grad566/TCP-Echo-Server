package org.example;

import org.example.server.EchoServer;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EchoServer server = new EchoServer(9091, Executors.newFixedThreadPool(15));
        try {
            server.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server error: {0}", e.getMessage());
        }
    }
}