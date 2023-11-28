package com.example.cognitivecloudassistant.controller;

import com.example.cognitivecloudassistant.dto.PayloadRootDTO;
import com.example.cognitivecloudassistant.dto.ResponsePayloadRootDTO;
import com.example.cognitivecloudassistant.service.CostCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.cognitivecloudassistant.util.JSONConvertor.generateResponseFromRequest;

@RestController
public class JSONController {

    @Autowired
    private CostCalculationService costCalculationService;

    @PostMapping("/process-json")
    public ResponsePayloadRootDTO processJson(@RequestBody PayloadRootDTO payload) {
        payload = costCalculationService.calculateCosts(payload);
        return generateResponseFromRequest(payload);
    }
}
