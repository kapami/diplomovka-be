package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ResponseItemDTO;
import org.springframework.stereotype.Service;

@Service
public class AwsCostCalculationService implements CalculationStrategy{
    @Override
    public void calculateCosts(ResponseItemDTO resourceDetailsDTO) {

    }


//    @Override
//    public void calculateCosts(ResourceDetailsDTO resourceDetailsDTO) {
//        Map<String, ResourceDetailsDTO> resources = payloadRootDTO.getResources();
//
//        // Simulating a basic price calculation for each resource (replace with actual logic)
//        double basePrice = 10.0;
//        for (Map.Entry<String, ResourceDetailsDTO> entry : resources.entrySet()) {
//            ResourceDetailsDTO resourceDetails = entry.getValue();
//            resourceDetails.setPrice(basePrice); // Set a price for each resource
//            basePrice += 5.0; // Increment the price for demonstration purposes
//        }


//        return payloadRootDTO;
//        return;
//    }
}
