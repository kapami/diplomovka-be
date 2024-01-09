package com.example.cognitivecloudassistant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AzurePriceResultDTO {

    public String BillingCurrency;
    public String CustomerEntity;
    public String CustomerEntityType;
    public List<AzurePriceResultItemDTO> Items;
    public String NextPageLink;
    public int Count;

}
