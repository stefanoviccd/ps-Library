/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.biblioteka.server.threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.biblioteka.constants.ServerConstants;

/**
 *
 * @author Dragana Stefanovic
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;
    private List<HandleClientThread> clients;

    public ServerThread() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
        String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
        serverSocket = new ServerSocket(Integer.parseInt(port));
        clients = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket s = serverSocket.accept();
                connectClient(s);
            }
        } catch (Exception e) {

        }
    }

    private void connectClient(Socket s) {
        HandleClientThread client = new HandleClientThread(this, s);
        clients.add(client);
        client.start();

    }

    public void stopCommunication() throws IOException {
        for (HandleClientThread client : clients) {
            client.stopCommunication();
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverSocket.close();
    }

    void removeClient(HandleClientThread ct) {
        clients.remove(ct);
        System.out.println("Klijentska nit uklonjena.");
    }

    void logout(HandleClientThread ct) throws IOException {
        ct.getSocket().close();
        removeClient(ct);

    }

}
