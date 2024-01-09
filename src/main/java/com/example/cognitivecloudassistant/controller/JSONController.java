package com.example.cognitivecloudassistant.controller;

import com.example.cognitivecloudassistant.dto.PayloadDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;
import com.example.cognitivecloudassistant.exception.UnrecognizedCloudProviderException;
import com.example.cognitivecloudassistant.service.CostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class JSONController {

    @Autowired
    private CostCalculationService costCalculationService;

    // will be renamed to GET /calculation
    // removed POST /validate-date because of @NotNull annotation
    @PostMapping("/process-json")
    public ResponseEntity<List<ResponseItemDTO>> processJson(@RequestBody PayloadDTO payload) {
        try {
            List<ResponseItemDTO> response = costCalculationService.calculateCosts(payload);
            System.out.println(response);
            return ResponseEntity.ok(response);
        } catch (UnrecognizedCloudProviderException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unrecognized cloud provider: " + e.getMessage(), e);
        }
    }

}
