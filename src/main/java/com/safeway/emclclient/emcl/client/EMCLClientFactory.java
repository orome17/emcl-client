package com.safeway.emclclient.emcl.client;

import com.safeway.emclclient.emcl.client.net.EMCLClientSocket;
import com.safeway.emclclient.emcl.client.netty.EMCLClientNetty;
import com.safeway.emclclient.emcl.client.springintegration.EMCLClientSpringIntegration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EMCLClientFactory {
    private static final Log logger = LogFactory.getLog(EMCLClientFactory.class);

    @Value("${emcl.client.implementation}")
    private String clientImplementation;

    @Autowired
    private EMCLClientNetty emclClientNetty;

    @Autowired
    private EMCLClientSocket emclClientSocket;

    @Autowired
    private EMCLClientSpringIntegration emclClientSpringIntegration;

    public EMCLClient getInstance() {
        switch (clientImplementation) {
            case "spring-integration":
                return emclClientSpringIntegration;
            case "netty":
                return emclClientNetty;
            default:
                return emclClientSocket;
        }
    }
}
