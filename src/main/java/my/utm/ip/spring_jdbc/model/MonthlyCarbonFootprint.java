package my.utm.ip.spring_jdbc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyCarbonFootprint {
    private int month;
    private String totalCarbonData;


    public MonthlyCarbonFootprint(@JsonProperty("month") int month, @JsonProperty("totalCarbonData") String totalCarbonData) {
        this.month = month;
        this.totalCarbonData = totalCarbonData;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTotalCarbonData() {
        return totalCarbonData;
    }

    public void setTotalCarbonData(String totalCarbonData) {
        this.totalCarbonData = totalCarbonData;
    }

    
}



