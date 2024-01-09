package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ResponseItemDTO;

public interface CalculationStrategy {
    void calculateCosts(ResponseItemDTO responseItemDTO);
}
