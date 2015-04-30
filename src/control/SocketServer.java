/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Week;

/**
 *
 * @author Jonas
 */
public class SocketServer implements Runnable {

    private ServerHandler serverHandler;
    private boolean active;
    private ServerSocket ss;
    private Week week;

    public SocketServer(Week week) throws IOException {
        this.week = week;
        active = true;
        serverHandler = null;
        ss = new ServerSocket(8189);
    }

    public ServerHandler getServerHandler() {
        return serverHandler;
    }

    public void setServerHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void run() {
        while (active) {
            try {
                System.out.println("Venter på klient...");
                Socket socket = ss.accept();
                System.out.println("Klient forbundet");
                serverHandler = new ServerHandler(socket);
                serverHandler.sendData(week);
            } catch (IOException ex) {
                System.out.println("FEJL VED SYNKRONISERING i RUN" + ex);
            }
        }
    }

    public void close() {
        try {
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
