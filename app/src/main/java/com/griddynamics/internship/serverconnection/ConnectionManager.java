package com.griddynamics.internship.serverconnection;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager {
    private final ServerSocket serverSocket;
    private final static int BACKLOG = 50;

    public ConnectionManager(String address, int port) {
        try {
            serverSocket = new ServerSocket(port, BACKLOG, InetAddress.getByName(address));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public SocketConnection acceptNewClient() {
        try {
            Socket socket = serverSocket.accept();
            return new SocketConnection(socket);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

