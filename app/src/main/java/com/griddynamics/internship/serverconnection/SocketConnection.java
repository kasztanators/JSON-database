package com.griddynamics.internship.serverconnection;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketConnection{
    private final DataInputStream input;
    private final DataOutputStream output;
    private final Socket socket;


    public SocketConnection(Socket socket){
        try {
            this.socket = socket;
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public SocketConnection(String address, int port) {
        try {
            this.socket = new Socket(InetAddress.getByName(address), port);
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to the server", e);
        }
    }
    public String receive() {
        try {
            return input.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

