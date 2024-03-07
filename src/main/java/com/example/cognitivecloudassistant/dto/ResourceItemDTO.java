package com.example.cognitivecloudassistant.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceItemDTO {
    public @NonNull String type;
    public @NonNull String id;
    public String zone;
    public @NonNull String provider;
    public String region;
    public String vpc;
    public String currency;
    public String measurementType;
    public Map<String, Object> properties;
    public Map<String, Object> metadata;
    public Map<String, String> connections;
}
