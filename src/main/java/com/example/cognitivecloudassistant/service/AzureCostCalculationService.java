package com.example.cognitivecloudassistant.service;

import com.example.cognitivecloudassistant.dto.ArchitectureDTO;
import com.example.cognitivecloudassistant.dto.AzurePriceResultDTO;
import com.example.cognitivecloudassistant.dto.ResponseItemDTO;
import com.example.cognitivecloudassistant.dto.ServiceItemDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AzureCostCalculationService implements CalculationStrategy {

    @Value("${azure.pricing.api.url}")
    private String azurePricingApiUrl;
    private final RestTemplate restTemplate;

    public AzureCostCalculationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void calculateCosts(ResponseItemDTO responseItemDTO) {
        AzurePriceResultDTO azurePriceResult = getPrices(responseItemDTO);
        float[] prices = new float[azurePriceResult.getCount()];

        // Assign prices to each ResponseItem (service)
        if(azurePriceResult.getCount() > 1){
            for (int i = 0; i < azurePriceResult.getCount(); i++) {
                prices[i] = azurePriceResult.getItems().get(i).getRetailPrice();
            }
            responseItemDTO.setPrice(prices);
        } else if (azurePriceResult.getCount() == 1) {
            prices[0] = azurePriceResult.getItems().get(0).getRetailPrice();
            responseItemDTO.setPrice(prices);
        } else {
            prices = new float[1];
            prices[0] = 0.0f;
            responseItemDTO.setPrice(prices);
        }
    }

    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////

    @Override
    public void calculateArchitectureCost(ArchitectureDTO architecture) {
        for(ServiceItemDTO serviceItem : architecture.getResources()){
            calculateServiceCost(serviceItem);
        }
    }

    private void calculateServiceCost(ServiceItemDTO serviceItem) {
    }


    ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////


    // TODO filtrovat len typ consuption a ak neni tak reservation
    // TODO: ukladat product name spolu s cenou
    // Create filter from given json, and send request to Azure Retail price API
    private AzurePriceResultDTO getPrices(ResponseItemDTO responseItemDTO){
        String query = filterBuilder(responseItemDTO);
        AzurePriceResultDTO response = new AzurePriceResultDTO();

        String apiUrlWithQuery = azurePricingApiUrl +
                "?currencyCode='" +
                responseItemDTO.getCurrency() +
                "'" +
                "&$filter=" +
                query;

        try {
            response = restTemplate.getForObject(apiUrlWithQuery, AzurePriceResultDTO.class);
        } catch (HttpClientErrorException.BadRequest e) {
            System.out.println("Bad request: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Other exception occurred: " + e.getMessage());
        }
        System.out.println(response);
        return response;
    }

    private String filterBuilder(ResponseItemDTO responseItemDTO) {
        String result = "";

        if (!responseItemDTO.getId().isEmpty()) {
            result = result + "serviceId eq '" + responseItemDTO.getId() + "' and ";
        }
        if (responseItemDTO.getZone() != null && !responseItemDTO.getZone().isEmpty()) {
            result = result + "location eq '" + responseItemDTO.getZone() + "' and ";
        }
        if (responseItemDTO.getRegion() != null && !responseItemDTO.getRegion().isEmpty()) {
            result = result + "ArmRegionName eq '" + responseItemDTO.getRegion() + "' and ";
        }
        result = result + "serviceName eq '" + responseItemDTO.getType() + "' and ";

        return result.substring(0, result.length() - 5) + " and meterName eq 'B2s'";
    }
}
