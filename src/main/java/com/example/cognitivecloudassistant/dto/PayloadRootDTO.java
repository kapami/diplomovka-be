package com.example.cognitivecloudassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class PayloadRootDTO {
    @JsonProperty("AWSTemplateFormatVersion")
    private String awstemplateFormatVersion;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Resources")
    private Map<String, ResourceDetailsDTO> resources;

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

    public Map<String, ResourceDetailsDTO> getResources() {
        return resources;
    }

    public void setResources(Map<String, ResourceDetailsDTO> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "AWSTemplateDTO{" +
                "awstemplateFormatVersion='" + awstemplateFormatVersion + '\'' +
                ", description='" + description + '\'' +
                ", resources=" + resources +
                '}';
    }
}
