package com.safeway.emclclient.emcl.dto;

public class EMCLLookUpRequest {

    private String phoneNumber;
    private String clubCardNumber;
    private String storeId;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClubCardNumber() {
        return clubCardNumber;
    }

    public void setClubCardNumber(String clubCardNumber) {
        this.clubCardNumber = clubCardNumber;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
