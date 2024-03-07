package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.dto.PayloadDTO;
import com.example.cognitivecloudassistant.dto.ResourceItemDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;
import com.example.cognitivecloudassistant.exception.UnrecognizedCloudProviderException;
import com.example.cognitivecloudassistant.util.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostCalculationService {

    @Autowired
    private AwsCostCalculationService awsCostCalculationService;

    @Autowired
    private AzureCostCalculationService azureCostCalculationService;


    public List<ResponseItemDTO> calculateCosts(PayloadDTO payloadRootDTO) throws UnrecognizedCloudProviderException {
        List<ResponseItemDTO> responseList = new ArrayList<>();
        ResponseItemDTO responseItemDTO;

        for(ResourceItemDTO resourceItemDTO : payloadRootDTO.getResources()){
            responseItemDTO = DTOConverter.convertPayloadItemToResponseItem(resourceItemDTO);
            switch (responseItemDTO.getProvider().toUpperCase()) {
                case "AWS":
                    awsCostCalculationService.calculateCosts(responseItemDTO);
                    break;
                case "AZURE":
                    azureCostCalculationService.calculateCosts(responseItemDTO);
                    break;
                default:
                    throw new UnrecognizedCloudProviderException(responseItemDTO.getProvider() + "cloud provider is not supported");
            }
            responseList.add(responseItemDTO);
        }

        return responseList;
    }

    public void calculateCosts(List<ArchitectureDTO> architectures){
        for(ArchitectureDTO architecture : architectures){
            switch (architecture.getProvider().toUpperCase()){
                case "AWS":
                    awsCostCalculationService.calculateArchitectureCost(architecture);
                    break;
                case "AZURE":
                    azureCostCalculationService.calculateArchitectureCost(architecture);
                    break;
                default:
                    throw new UnrecognizedCloudProviderException(architecture.getProvider() + "cloud provider is not supported");
            }
        }

    }
}
