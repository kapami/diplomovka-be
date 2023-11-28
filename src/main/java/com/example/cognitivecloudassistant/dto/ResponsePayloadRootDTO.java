package com.example.cognitivecloudassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ResponsePayloadRootDTO {

    @JsonProperty("AWS Template Format Version")
    private String awstemplateFormatVersion;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Resources")
    private Map<String, ResponseResourceDetailsDTO> resources;

    public String getAwstemplateFormatVersion() {
        return awstemplateFormatVersion;
    }

    public void setAwstemplateFormatVersion(String awstemplateFormatVersion) {
        this.awstemplateFormatVersion = awstemplateFormatVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, ResponseResourceDetailsDTO> getResources() {
        return resources;
    }

    public void setResources(Map<String, ResponseResourceDetailsDTO> resources) {
        this.resources = resources;
    }
}
