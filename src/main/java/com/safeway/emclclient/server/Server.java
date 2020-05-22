package com.safeway.emclclient.server;

import org.apache.commons.lang3.ArrayUtils;

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
                        byte[] response = writeCustomerSyncResponse();
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

    private static byte[] writeCustomerSyncResponse() {
        byte[] byteOutput = "S0004E".getBytes();

        byteOutput = ArrayUtils
                .addAll(byteOutput,
                        convertHexToByte(convertDecimalToHex(
                                "0006", 2)));

        byteOutput = ArrayUtils
                .addAll(byteOutput,
                        convertHexToByte(convertDecimalToHex(
                                "0", 2)));

        return byteOutput;
    }

    private static byte[] convertHexToByte(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(
                    hexString.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    private static String convertDecimalToHex(String decimal, int numberOfBytes) {

        int decimalValue = Integer.parseInt(decimal);
        String hexValue = null;
        int hexSize = 0;
        StringBuilder hexBuilder = new StringBuilder("");
        if (numberOfBytes == 2) {
            hexValue = Integer.toHexString(decimalValue).toString();

            hexSize = 4 - hexValue.length();

            for (int ctr = 0; ctr < hexSize; ctr += 1) {
                hexBuilder.append("0");
            }

            hexBuilder.append(hexValue);
            String bigEndian = hexBuilder.toString();
            String firstBit = bigEndian.substring(0, 2);
            String secondBit = bigEndian.substring(2, 4);
            hexBuilder = new StringBuilder().append(secondBit).append(firstBit);

        } else if (numberOfBytes == 4) {
            hexValue = Integer.toHexString(decimalValue).toString();

            hexSize = 8 - hexValue.length();

            for (int ctr = 0; ctr < hexSize; ctr += 1) {
                hexBuilder.append("0");
            }

            hexBuilder.append(hexValue);
            String bigEndian = hexBuilder.toString();
            String firstBit = bigEndian.substring(0, 2);
            String secondBit = bigEndian.substring(2, 4);
            String thirdBit = bigEndian.substring(4, 6);
            String fourthBit = bigEndian.substring(6, 8);

            hexBuilder = new StringBuilder().append(fourthBit).append(thirdBit)
                    .append(secondBit).append(firstBit);

        }

        return hexBuilder.toString();
    }

    public static void main(String args[])
    {
        Server server = new Server(7001);
    }
}
