package com.example.cognitivecloudassistant.util;

import com.example.cognitivecloudassistant.dto.PayloadItemDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;

public class DTOConverter {

    public static ResponseItemDTO convertPayloadItemToResponseItem(PayloadItemDTO payloadItemDTO){
        ResponseItemDTO responseItemDTO = new ResponseItemDTO();

        responseItemDTO.setId(payloadItemDTO.getId());
        responseItemDTO.setType(payloadItemDTO.getType());
        responseItemDTO.setZone(payloadItemDTO.getZone());
        responseItemDTO.setProvider(payloadItemDTO.getProvider());
        responseItemDTO.setRegion(payloadItemDTO.getRegion());
        responseItemDTO.setVpc(payloadItemDTO.getVpc());
        responseItemDTO.setCurrency(payloadItemDTO.getCurrency());
        responseItemDTO.setMeasurementType(payloadItemDTO.getMeasurementType());
        responseItemDTO.setProperties(payloadItemDTO.getProperties());
        responseItemDTO.setMetadata(payloadItemDTO.getMetadata());

        return responseItemDTO;
    }
}
