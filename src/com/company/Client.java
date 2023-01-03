package com.company;

import java.io.IOException;
import java.net.*;

public class Client {
    private final DatagramSocket socket;

    private final String destHost;
    private final int destPort;

    public Client(String destHost, int destPort, DatagramSocket socket) {
        this.socket = socket;
        this.destHost = destHost;
        this.destPort = destPort;
    }

    public void send(String message) throws IOException {
        InetAddress address = InetAddress.getByName(destHost);

        DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), address, destPort);

        this.socket.send(datagramPacket);
        System.out.println("[client] " + message);
    }

    public String read() throws IOException {
        byte[] buffer = new byte[1024];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        this.socket.receive(packet);

        String message = new String(packet.getData(), 0, packet.getLength());

        System.out.println("[server] " + message);

        return message;
    }

    public String getDestHost() {
        return destHost;
    }

    public int getDestPort() {
        return destPort;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}

class TestClient {
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 5000, new DatagramSocket(8080));

        client.send("Hello World!");
        String message = client.read();
    }
}
