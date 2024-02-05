package my.utm.ip.spring_jdbc.controller;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import my.utm.ip.spring_jdbc.model.PercentageContributions;

public class PercentageContributionsSerializer extends JsonSerializer<PercentageContributions>{
    
    @Override
    public void serialize(PercentageContributions contributions, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException{
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("electricityPercentage", contributions.getElectricityPercentage().doubleValue());
        jsonGenerator.writeNumberField("waterPercentage", contributions.getWaterPercentage().doubleValue());
        jsonGenerator.writeEndObject();
    }

}
