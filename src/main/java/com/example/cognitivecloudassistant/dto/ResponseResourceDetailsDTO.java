package com.example.cognitivecloudassistant.dto;

import com.example.cognitivecloudassistant.util.CustomMapKeySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

public class ResponseResourceDetailsDTO {
    @JsonProperty("Type")
    private String type;

    @JsonSerialize(keyUsing = CustomMapKeySerializer.class)
    private Map<String, Object> properties;

    @JsonProperty("Price")
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
