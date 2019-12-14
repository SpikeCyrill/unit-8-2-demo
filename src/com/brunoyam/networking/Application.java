package com.brunoyam.networking;

import com.brunoyam.networking.examples.datagrams.DatagramClent;
import com.brunoyam.networking.examples.datagrams.DatagramServer;
import com.brunoyam.networking.examples.sockets.ClientSocket;
import com.brunoyam.networking.examples.sockets.ServerSocketListener;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws Exception {
        //new UrlExample().show();
//        runServer(4004);
//        runClient("localhost", 4004);

        runDatagrsmClient();
        //runDatagramServer();

    }

    private static void runServer(int port) {
        ServerSocketListener serverSocket = new ServerSocketListener(port);
        Thread serverThread = new Thread(serverSocket);
        serverThread.start();
    }

    private static void runClient(String host, int port) {
        ClientSocket clientSocket = new ClientSocket(host, port);
        Thread clientThread = new Thread(clientSocket);
        clientThread.start();
    }

    private static void runDatagramServer() throws IOException {
        DatagramServer datagramServer = new DatagramServer("datagram");
        datagramServer.start();
    }

    private static void runDatagrsmClient() {
        DatagramClent datagramClent = new DatagramClent("localhost");
        datagramClent.start();
    }

}
