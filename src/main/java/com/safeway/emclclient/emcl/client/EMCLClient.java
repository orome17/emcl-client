package com.safeway.emclclient.emcl.client;

import com.safeway.emclclient.emcl.model.CustomerInformation;

import java.util.List;

public interface EMCLClient {
    void sendMessages(CustomerInformation customerInformation, List<String> messages);
}
