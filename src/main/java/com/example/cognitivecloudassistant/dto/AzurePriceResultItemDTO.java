package com.example.cognitivecloudassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AzurePriceResultItemDTO {

    public String currencyCode;
    public float tierMinimumUnits;
    public float retailPrice;
    public float unitPrice;
    public String armRegionName;
    public String location;
    public String effectiveStartDate;
    public String meterId;
    public String meterName;
    public String productId;
    public String skuId;
    public String productName;
    public String skuName;
    public String serviceName;
    public String serviceId;
    public String serviceFamily;
    public String unitOfMeasure;
    public String type;
    public String isPrimaryMeterRegion;
    public String armSkuName;
}
