package com.example.cognitivecloudassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ArchitectureDTO {
    public String name;
    public String description;
    public String url;
    public String provider;
    public List<ServiceItemDTO> resources;
}
