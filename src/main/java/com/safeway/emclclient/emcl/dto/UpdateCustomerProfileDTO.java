package com.safeway.emclclient.emcl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateCustomerProfileDTO {
    private EventMetadataDTO eventMetadata;
    private DataDTO data;

    public EventMetadataDTO getEventMetadata() {
        return eventMetadata;
    }

    public void setEventMetadata(EventMetadataDTO eventMetadata) {
        this.eventMetadata = eventMetadata;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
