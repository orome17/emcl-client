package com.safeway.emclclient.server;

import java.net.*;
import java.io.*;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private DataOutputStream out       =  null;

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            out = new DataOutputStream(
                    new BufferedOutputStream(socket.getOutputStream()));

            byte[] buffer = new byte[1000];

            while (true) {
                try {
                    int size = in.read(buffer);
                    if (size > buffer.length) {
                        buffer = new byte[size];
                    }
                    if (size != -1 && size != 0) {
                        System.out.println(size);
                        System.out.println(new String(buffer));
                        byte[] response = "OKAY".getBytes();
                        out.write(response);
                        out.flush();
                    }
                } catch(IOException i) {
                    System.out.println(i);
                    break;
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Server server = new Server(7001);
    }
}
