package com.example.cognitivecloudassistant.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseItemDTO extends ResourceItemDTO {
    //TODO: pridat description z AWS
    public float[] price;
}
