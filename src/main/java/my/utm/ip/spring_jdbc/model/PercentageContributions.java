package my.utm.ip.spring_jdbc.model;

import java.math.BigDecimal;

public class PercentageContributions {
    private BigDecimal electricityPercentage; 
    private BigDecimal waterPercentage;

    public PercentageContributions(BigDecimal electricityPercentage, BigDecimal waterPercentage) {
        this.electricityPercentage = electricityPercentage;
        this.waterPercentage = waterPercentage;
    }

    public BigDecimal getElectricityPercentage() {
        return electricityPercentage;
    }

    public void setElectricityPercentage(BigDecimal electricityPercentage) {
        this.electricityPercentage = electricityPercentage;
    }

    public BigDecimal getWaterPercentage() {
        return waterPercentage;
    }

    public void setWaterPercentage(BigDecimal waterPercentage) {
        this.waterPercentage = waterPercentage;
    } 

    
}
