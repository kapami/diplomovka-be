package com.example.cognitivecloudassistant.controller;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.service.ArchitectureMatchingService;
import com.example.cognitivecloudassistant.service.CostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JSONController {

    @Autowired
    private CostCalculationService costCalculationService;
    @Autowired
    private ArchitectureMatchingService architectureMatchingService;

    // will be renamed to GET /calculation
    // removed POST /validate-date because of @NotNull annotation

//    @PostMapping("/process-json")
//    public ResponseEntity<List<ResponseItemDTO>> processJson(@RequestBody PayloadDTO payload) {
//        try {
//            List<ResponseItemDTO> response = costCalculationService.calculateCosts(payload);
//            return ResponseEntity.ok(response);
//        } catch (UnrecognizedCloudProviderException e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unrecognized cloud provider: " + e.getMessage(), e);
//        }
//    }

    @PostMapping("/process-json")
    public ResponseEntity<List<ArchitectureDTO>> findArchitectures(@RequestBody List<String> resourceTypes) {
        List<ArchitectureDTO> architectures = architectureMatchingService.matchArchitecture(resourceTypes);
        return ResponseEntity.ok(architectures);
    }

    @PutMapping("/calculation")
    public ResponseEntity<List<ArchitectureDTO>> calculatePrices(@RequestBody List<ArchitectureDTO> architectures) {
        costCalculationService.calculateCosts(architectures);
        return ResponseEntity.ok(architectures);
    }

}
