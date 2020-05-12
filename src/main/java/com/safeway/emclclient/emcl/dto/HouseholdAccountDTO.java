package com.safeway.emclclient.emcl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HouseholdAccountDTO {
    private String houseHoldId;
    private String hhidDigitalRegInd;

    public String getHouseHoldId() {
        return houseHoldId;
    }

    public void setHouseHoldId(String houseHoldId) {
        this.houseHoldId = houseHoldId;
    }

    public String getHhidDigitalRegInd() {
        return hhidDigitalRegInd;
    }

    public void setHhidDigitalRegInd(String hhidDigitalRegInd) {
        this.hhidDigitalRegInd = hhidDigitalRegInd;
    }
}
