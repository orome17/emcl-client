package com.safeway.emclclient.emcl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProfileDTO {
    private List<IndicatorDTO> indicators;
    private List<StoreDTO> stores;
    private List<HouseholdAccountDTO> householdAccounts;
    private List<LoyaltyProgramDTO> loyaltyPrograms;
    private NameDTO name;
    private List<PhoneDTO> phones;
    private EmailDTO email;

    public List<IndicatorDTO> getIndicators() {
        return indicators;
    }

    public void setIndicators(List<IndicatorDTO> indicators) {
        this.indicators = indicators;
    }

    public List<StoreDTO> getStores() {
        return stores;
    }

    public void setStores(List<StoreDTO> stores) {
        this.stores = stores;
    }

    public List<HouseholdAccountDTO> getHouseholdAccounts() {
        return householdAccounts;
    }

    public void setHouseholdAccounts(List<HouseholdAccountDTO> householdAccounts) {
        this.householdAccounts = householdAccounts;
    }

    public List<LoyaltyProgramDTO> getLoyaltyPrograms() {
        return loyaltyPrograms;
    }

    public void setLoyaltyPrograms(List<LoyaltyProgramDTO> loyaltyPrograms) {
        this.loyaltyPrograms = loyaltyPrograms;
    }

    public NameDTO getName() {
        return name;
    }

    public void setName(NameDTO name) {
        this.name = name;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public EmailDTO getEmail() {
        return email;
    }

    public void setEmail(EmailDTO email) {
        this.email = email;
    }
}
