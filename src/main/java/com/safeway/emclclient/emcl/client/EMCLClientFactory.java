package com.safeway.emclclient.emcl.client;

import com.safeway.emclclient.emcl.client.net.EMCLClientSocket;
import com.safeway.emclclient.emcl.client.netty.EMCLClientNetty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EMCLClientFactory {
    private static final Log logger = LogFactory.getLog(EMCLClientFactory.class);

    @Value("${emcl.client.netty.enabled}")
    private String nettyEnabled;

    @Autowired
    private EMCLClientNetty emclClientNetty;

    @Autowired
    private EMCLClientSocket emclClientSocket;

    public EMCLClient getInstance() {
        if (Boolean.valueOf(nettyEnabled)) {
            return emclClientNetty;
        } else {
            return emclClientSocket;
        }
    }
}
