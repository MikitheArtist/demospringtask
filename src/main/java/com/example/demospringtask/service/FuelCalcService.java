package com.example.demospringtask.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FuelCalcService {
    public double calcTotalFuel(Double avgConsumption, Double distance) {
        double fuel = (avgConsumption * distance)/100;
        BigDecimal decimal = new BigDecimal(fuel);
        BigDecimal result = decimal.setScale(2, RoundingMode.HALF_DOWN);
        return result.doubleValue();
    }


    public double calcTotalCoast(double totalFuel, double fuelPrice) {
        double coast = totalFuel * fuelPrice;
        BigDecimal decimal = new BigDecimal(coast);
        BigDecimal result = decimal.setScale(2, RoundingMode.HALF_DOWN);
        return result.doubleValue();
    }


    public double checkForComma(String number) {
        String verifiedStr = "";
        if(number.contains(",")) {
            verifiedStr = number.replace(',', '.');
            return Double.parseDouble(verifiedStr);
        }
        return Double.parseDouble(number);

    }
}
