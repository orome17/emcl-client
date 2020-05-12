package com.safeway.emclclient.emcl;

import com.safeway.emclclient.emcl.dto.UpdateCustomerProfileDTO;

public interface EMCLService {

    void customerUpdate(UpdateCustomerProfileDTO updateCustomerProfile);
}
