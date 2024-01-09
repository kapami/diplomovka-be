package com.example.cognitivecloudassistant.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PayloadDTO {

    public @NonNull String description; // description of architecture
    public @NonNull List<PayloadItemDTO> resources;
}
