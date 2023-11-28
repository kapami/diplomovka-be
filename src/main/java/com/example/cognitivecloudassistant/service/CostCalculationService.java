package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.PayloadRootDTO;
import com.example.cognitivecloudassistant.dto.ResourceDetailsDTO;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CostCalculationService {

    public PayloadRootDTO calculateCosts(PayloadRootDTO payloadRootDTO) {
        Map<String, ResourceDetailsDTO> resources = payloadRootDTO.getResources();

        // Simulating a basic price calculation for each resource (replace with actual logic)
        double basePrice = 10.0;
        for (Map.Entry<String, ResourceDetailsDTO> entry : resources.entrySet()) {
            ResourceDetailsDTO resourceDetails = entry.getValue();
            resourceDetails.setPrice(basePrice); // Set a price for each resource
            basePrice += 5.0; // Increment the price for demonstration purposes
        }


        return payloadRootDTO; // Return the updated template with prices
    }
}
