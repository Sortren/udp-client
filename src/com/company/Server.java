package com.company;

import java.io.IOException;
import java.net.DatagramSocket;


public class Server {
}

class TestServer {
    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 8080, new DatagramSocket(5000));

        String message = client.read();
        client.send("Hello World!");
    }
}
