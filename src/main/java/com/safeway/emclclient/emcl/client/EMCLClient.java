package com.safeway.emclclient.emcl.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safeway.emclclient.emcl.dto.EMCLLookUpRequest;
import com.safeway.emclclient.emcl.dto.EMCLLookUpResponse;
import com.safeway.emclclient.emcl.mapper.EmclMessageDecoder;
import com.safeway.emclclient.emcl.mapper.EmclMessageEncoder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMCLClient {

    private static final Log logger = LogFactory.getLog(EMCLClient.class);

    @Autowired
    private Gateway gateway;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmclMessageDecoder emclMessageDecoder;

    @Autowired
    private EmclMessageEncoder emclMessageEncoder;

    public void updateCustomerInformation(List<String> messages) throws Exception {
        for (String message : messages) {
            String response = gateway.send(message);
            logger.debug("[spring-int] message sent!");
            if (StringUtils.isEmpty(response)) {
                throw new Exception(
                        "Invalid Response received from EMCL");
            }
            logger.debug("[spring-int] response: " + response);
            String[] bytes = response.split(",");
            if (bytes[8].equals("01")) {
                logger.info("Error....");
            } else if(bytes[8].equals("00")) {
                logger.info("Successful....");
            } else {
                logger.info("Invalid response code");
            }
        }
    }

    public EMCLLookUpResponse lookUpEmcl(EMCLLookUpRequest emclLookUpRequest) throws Exception {
        try {
            String message = emclMessageEncoder.encodeT1Message(emclLookUpRequest);
            String response = gateway.send(message);
            String[] bytes = response.split(",");
            return emclMessageDecoder.decodeT2Message(bytes);
        } catch (Exception e) {
           throw e;
        }

    }

}
