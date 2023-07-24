package com.griddynamics.internship.client;

import com.griddynamics.internship.messages.MessagePreparation;
import com.griddynamics.internship.serverconnection.SocketConnection;

class ClientSideConnection{
    private final String address;
    private final int port;
    ClientSideConnection(int port, String address) {
        this.port = port;
        this.address = address;
    }
    public void run(String [] args) {
        SocketConnection connection = new SocketConnection(address, port);
        System.out.println("Client started!");

        interact(connection, MessagePreparation.getMessage(args));
        connection.close();
    }
    private void interact(SocketConnection connection, String msg) {
        connection.send(msg);
        System.out.println("Sent: " + msg);

        String receivedMsg = connection.receive();
        System.out.println("Received: " + receivedMsg);
    }
}
