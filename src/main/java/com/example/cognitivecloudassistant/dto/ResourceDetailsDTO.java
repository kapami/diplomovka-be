package com.example.cognitivecloudassistant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ResourceDetailsDTO {


    @JsonProperty("Type")
    private String type;

    @JsonProperty("Properties")
    private Map<String, Object> properties;

    @JsonProperty("Price")
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "ResourceDetailsDTO{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                '}';
    }
}

