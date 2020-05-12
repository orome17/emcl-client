package com.safeway.emclclient.emcl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataDTO {
    private List<ProfileDTO> profiles;

    public List<ProfileDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileDTO> profiles) {
        this.profiles = profiles;
    }
}
