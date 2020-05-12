package com.safeway.emclclient.emcl.impl;

import com.safeway.emclclient.emcl.EMCLService;
import com.safeway.emclclient.emcl.client.EMCLClient;
import com.safeway.emclclient.emcl.client.EMCLClientFactory;
import com.safeway.emclclient.emcl.dto.UpdateCustomerProfileDTO;
import com.safeway.emclclient.emcl.mapper.EMCLCustomerInformationMapper;
import com.safeway.emclclient.emcl.model.CustomerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EMCLServiceImpl implements EMCLService {

    @Autowired
    private EMCLCustomerInformationMapper mapper;

    @Autowired
    private EMCLMessageBuilder messageBuilder;

    @Autowired
    private EMCLClientFactory emclClientFactory;

    @Override
    public void customerUpdate(UpdateCustomerProfileDTO updateCustomerProfile) {
        CustomerInformation customerInformation = mapper.convertToCustomerInformation(updateCustomerProfile);
        List<String> messages = messageBuilder.buildT5Message(customerInformation);
        EMCLClient emclClient = emclClientFactory.getInstance();
        emclClient.sendMessages(customerInformation, messages);
    }
}
