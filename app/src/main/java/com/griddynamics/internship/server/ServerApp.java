package com.griddynamics.internship.server;

public class ServerApp {
    private static final String address = "127.0.0.1";
    private static final int port = 8080;

    public static void main(String[] args) {
        ServerSideConnection server = new ServerSideConnection(port, address);
        server.run();
    }
}

