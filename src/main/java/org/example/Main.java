package org.example;

import org.example.server.EchoServer;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        EchoServer server = new EchoServer(9091, Executors.newFixedThreadPool(15));
        server.start();
    }
}