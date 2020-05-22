package com.safeway.emclclient.emcl;

import com.safeway.emclclient.emcl.dto.EMCLLookUpRequest;
import com.safeway.emclclient.emcl.dto.EMCLLookUpResponse;
import com.safeway.emclclient.emcl.dto.UpdateCustomerProfileDTO;

public interface EMCLService {

    void customerUpdate(UpdateCustomerProfileDTO updateCustomerProfile)  throws Exception;

    EMCLLookUpResponse lookUpByPhoneOrClubcrad(EMCLLookUpRequest emclLookUpRequest) throws Exception;

}
