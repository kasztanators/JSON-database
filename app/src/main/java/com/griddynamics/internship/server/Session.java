package com.griddynamics.internship.server;

import com.google.gson.Gson;
import com.griddynamics.internship.server.response.Response;
import com.griddynamics.internship.serverconnection.SocketConnection;

public class Session implements Runnable{
    private final SocketConnection connection;
    private final AppSetup app;
    private final Interrupter server;

    public Session(SocketConnection connection, AppSetup app, Interrupter server) {
        this.connection = connection;
        this.app = app;
        this.server = server;
    }

    @Override
    public void run() {
        String message = connection.receive();
        Response response = app.run(message, server);
        Gson gson  = new Gson();
        sendMessage(connection, gson.toJson(response));
    }
    private void sendMessage(SocketConnection connection, String message) {
        connection.send(message);
        System.out.println("Sent: " + message);
    }
}
