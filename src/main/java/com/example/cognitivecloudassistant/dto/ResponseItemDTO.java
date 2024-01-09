package com.example.cognitivecloudassistant.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseItemDTO extends PayloadItemDTO{
    public float[] price;

    // when query for Azure price list gave more than one result -> true
    public boolean haveMultiplePrices;
}
