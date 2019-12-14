package com.brunoyam.networking.examples.datagrams;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramServer extends Thread {

    protected DatagramSocket socket = null;
    protected boolean moreQuotes = true;
    private int counter = 0;

    public DatagramServer() throws IOException {
        this("QuoteServerThread");
    }

    public DatagramServer(String name) throws IOException {
        super(name);

    }

    public void run() {


        try(DatagramSocket socket = new DatagramSocket(4445) ){
            while (true) {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // figure out response
                String dString = "response № " + counter++;
                buf = dString.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            }
        } catch(IOException e){
            e.printStackTrace();
            moreQuotes = false;
        }
    }

}
