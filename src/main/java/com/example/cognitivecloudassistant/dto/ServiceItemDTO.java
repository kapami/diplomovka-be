package com.example.cognitivecloudassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ServiceItemDTO {
    public String name;
    public String type;
    public String id;
    public String zone;
    public String region;
    public String vpc;
    public String price;
    public String measurementType;
    public Map<String, Object> properties;
}
