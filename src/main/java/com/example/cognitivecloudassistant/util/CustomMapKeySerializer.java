package com.example.cognitivecloudassistant.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CustomMapKeySerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String transformedValue = value.replaceAll("([a-z])([A-Z]+)", "$1 $2");
        gen.writeFieldName(transformedValue);
    }
}
