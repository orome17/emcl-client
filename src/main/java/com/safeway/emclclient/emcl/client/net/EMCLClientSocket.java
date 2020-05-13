package com.safeway.emclclient.emcl.client.net;

import com.safeway.emclclient.emcl.client.EMCLClient;
import com.safeway.emclclient.emcl.model.CustomerInformation;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Service
public class EMCLClientSocket implements EMCLClient {
    private static final Log logger = LogFactory.getLog(EMCLClient.class);

    @Autowired
    private EMCLConnectionFactory connectionFactory;

    @Override
    public void sendMessages(CustomerInformation customerInformation, List<String> messages) {
        try {
            GenericObjectPool socketPool = connectionFactory.getGenericObjectPool();
            Socket socket = (Socket) socketPool.borrowObject();
            logger.info("[Net] Current socket's local port: " + socket.getLocalPort() + ", closed: " + socket.isClosed());
            // TODO: check if BufferedOutputStream is the right way.
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
//            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            for (String message : messages) {
                byte[] byteMessage = HexUtil.hexToBin(message);
                out.write(byteMessage);
                out.flush();
                logger.info("[Net] message sent!");
            }
            // block to read the response
//            byte[] buffer = new byte[1000];
//            while (true) {
//                int size = in.read(buffer);
//                if (size > 0) {
//                    System.out.println(new String(buffer));
//                    break;
//                }
//            }

            socketPool.returnObject(socket);
        } catch (IOException ex) {
            logger.error("Request Id: " + customerInformation.getRequestId() +
                    ", Club Card Number: " + customerInformation.getClubCard() +
                    ", HHID: " + customerInformation.getHouseHold() +
                    ". Could not write to output stream. Error: " + ex);
            throw new IllegalArgumentException(ex);
        } catch (Exception ex) {
            logger.error("Request Id: " + customerInformation.getRequestId() +
                    ", Club Card Number: " + customerInformation.getClubCard() +
                    ", HHID: " + customerInformation.getHouseHold() +
                    ". Could not get a connection from the connection pool. Error: " + ex);
            connectionFactory.nullifyGenericObjectPool();
            throw new IllegalArgumentException(ex);
        }
    }
}
