package com.safeway.emclclient.emcl.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import org.springframework.integration.ip.tcp.serializer.SoftEndOfStreamException;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Component("connectionSerializeDeserialize")
public class CustomSerializerDeserializer implements Serializer<String>, Deserializer<String> {

    private static final Log logger = LogFactory.getLog(CustomSerializerDeserializer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String deserialize(InputStream inputStream) throws IOException {
        return readResponse(inputStream);
    }

    @Override
    public void serialize(String message, OutputStream outputStream) throws IOException {
        logger.info("Request : "+message);
        byte[] byteMessage = HexUtil.hexToBin(message);
        outputStream.write(byteMessage);
        outputStream.flush();
    }


    private String readResponse(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            // Read the first 6 bytes to extract the length of the message
            StringBuilder lengthBuilder = new StringBuilder("");
            for (int ctr = 0; ctr < 6; ctr += 1) {
                byte b = dataInputStream.readByte();
                lines.add(String.format("%02x", b));
                lengthBuilder.append(String.format("%02x", b));
            }
            String msgLenAscii = HexUtil.convertHexToASCII(lengthBuilder.toString());
            int length = 0;
            if (msgLenAscii.startsWith("S") && msgLenAscii.endsWith("E")) {
                String numericValue = msgLenAscii.substring(msgLenAscii.indexOf('S') + 1, msgLenAscii.indexOf('E'));
                length = Integer.parseInt(numericValue);
            }
            for (int ctr = 0; ctr < length ; ctr ++) {
                byte b = dataInputStream.readByte();
                lines.add(String.format("%02x", b));
            }
            return String.join(",", lines);
        } catch (IOException e) {
            throw new SoftEndOfStreamException();
        }
    }
}
