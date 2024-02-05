package my.utm.ip.spring_jdbc.controller;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import my.utm.ip.spring_jdbc.model.MonthlyCarbonFootprint;

import java.io.IOException;

public class MonthlyCarbonFootprintSerializer extends JsonSerializer<MonthlyCarbonFootprint> {
    @Override
    public void serialize(MonthlyCarbonFootprint value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("month", value.getMonth());
        gen.writeStringField("totalCarbonData", value.getTotalCarbonData());
        gen.writeEndObject();
    }
}