package com.brunoyam.networking.examples.datagrams;

import java.io.IOException;
import java.net.*;

public class DatagramClent extends Thread {

    private final String name;

    public DatagramClent(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while (true) {
            // get a datagram socket
            try (DatagramSocket socket = new DatagramSocket()) {
                // send request
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName(name);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);

                // get response
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // display response
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Quote of the Moment: " + received);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
