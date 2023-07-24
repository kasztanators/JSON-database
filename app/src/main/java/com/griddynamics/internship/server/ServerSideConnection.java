package com.griddynamics.internship.server;

import com.griddynamics.internship.serverconnection.ConnectionManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSideConnection implements Interrupter {
    private final AppSetup app;
    private final ExecutorService executor;
    private boolean isRunning = true;
    private final ConnectionManager listener;

    ServerSideConnection(int port, String address) {
        this.app = AppSetup.getInstance();
        this.executor = Executors.newCachedThreadPool();
        this.listener = new ConnectionManager(address, port);
    }

    public void run() {
        System.out.println("Server started!");

        while (isRunning) {
            Session session = new Session(listener.acceptNewClient(), app, this);
            executor.submit(session);
        }
    }

    @Override
    public synchronized void interrupt() {
        isRunning = false;
        listener.close();
        executor.shutdownNow();
    }
}
