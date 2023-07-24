package com.griddynamics.internship.client;

public class ClientApp {
    private static final String address = "127.0.0.1";
    private static final int port = 8080;

    public static void main(String[] args) {
        ClientSideConnection connection = new ClientSideConnection(port ,address);
        connection.run(args);
    }
}
