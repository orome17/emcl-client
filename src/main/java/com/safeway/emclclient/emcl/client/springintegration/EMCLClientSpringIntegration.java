package com.safeway.emclclient.emcl.client.springintegration;

import com.safeway.emclclient.emcl.client.EMCLClient;
import com.safeway.emclclient.emcl.model.CustomerInformation;
import com.safeway.emclclient.emcl.utils.HexUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMCLClientSpringIntegration implements EMCLClient {
    private static final Log logger = LogFactory.getLog(EMCLClientSpringIntegration.class);

    @Autowired
    EMCLGateway emclGateway;

    @Override
    public void sendMessages(CustomerInformation customerInformation, List<String> messages) {
        for (String message : messages) {
            byte[] byteMessage = HexUtil.hexToBin(message);
            String response = emclGateway.send(byteMessage);
            logger.info("[Spring Integration] Message sent!");
            logger.info("[Spring Integration] Response: " + response);
        }
    }
}
