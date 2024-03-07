package com.example.cognitivecloudassistant.util;

import com.example.cognitivecloudassistant.dto.ResourceItemDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;

public class DTOConverter {

    public static ResponseItemDTO convertPayloadItemToResponseItem(ResourceItemDTO resourceItemDTO){
        ResponseItemDTO responseItemDTO = new ResponseItemDTO();

        responseItemDTO.setId(resourceItemDTO.getId());
        responseItemDTO.setType(resourceItemDTO.getType());
        responseItemDTO.setZone(resourceItemDTO.getZone());
        responseItemDTO.setProvider(resourceItemDTO.getProvider());
        responseItemDTO.setRegion(resourceItemDTO.getRegion());
        responseItemDTO.setVpc(resourceItemDTO.getVpc());
        responseItemDTO.setCurrency(resourceItemDTO.getCurrency());
        responseItemDTO.setMeasurementType(resourceItemDTO.getMeasurementType());
        responseItemDTO.setConnections(resourceItemDTO.getConnections());
        responseItemDTO.setProperties(resourceItemDTO.getProperties());
        responseItemDTO.setMetadata(resourceItemDTO.getMetadata());

        return responseItemDTO;
    }
}
