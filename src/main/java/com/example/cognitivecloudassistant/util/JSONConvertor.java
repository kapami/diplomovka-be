package com.example.cognitivecloudassistant.util;

import com.example.cognitivecloudassistant.dto.PayloadRootDTO;
import com.example.cognitivecloudassistant.dto.ResourceDetailsDTO;
import com.example.cognitivecloudassistant.dto.ResponsePayloadRootDTO;
import com.example.cognitivecloudassistant.dto.ResponseResourceDetailsDTO;

import java.util.HashMap;
import java.util.Map;

public class JSONConvertor {

    public static ResponsePayloadRootDTO generateResponseFromRequest(PayloadRootDTO incomingPayload){
        ResponsePayloadRootDTO response = new ResponsePayloadRootDTO();

        response.setAwstemplateFormatVersion(incomingPayload.getAwstemplateFormatVersion());
        response.setDescription(incomingPayload.getDescription());

        // Convert each ResourceDetailsDTO to ResponseResourceDetailsDTO
        Map<String, ResponseResourceDetailsDTO> responseResources = new HashMap<>();
        for (Map.Entry<String, ResourceDetailsDTO> entry : incomingPayload.getResources().entrySet()) {
            String key = entry.getKey();
            ResourceDetailsDTO resourceDetails = entry.getValue();

            ResponseResourceDetailsDTO responseResource = new ResponseResourceDetailsDTO();
            responseResource.setType(resourceDetails.getType());
            responseResource.setProperties(resourceDetails.getProperties());

            responseResources.put(key, responseResource);
        }

        response.setResources(responseResources);

        return response;
    }
}
