package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;

public interface CalculationStrategy {
    void calculateCosts(ResponseItemDTO responseItemDTO);
    void calculateArchitectureCost(ArchitectureDTO architectureDTO);
}
