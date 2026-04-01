package com.example;

import com.example.server.TcpServer;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        TcpServer server = new TcpServer(port);
        server.start();
    }
}