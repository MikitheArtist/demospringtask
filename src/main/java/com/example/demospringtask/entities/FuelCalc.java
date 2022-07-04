package com.example.demospringtask.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "fuel_calc")
@NoArgsConstructor
@AllArgsConstructor
public class FuelCalc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rout", nullable = false, length = 200)
    private String rout;

    @Column(name = "fuel", nullable = false, length = 200)
    private String fuel;

    @Column(name = "distance", nullable = false)
    private String distance;

    @Column(name = "fuel_price", nullable = false)
    private String fuelPrice;

    @Column(name = "avg_consumption", nullable = false)
    private String avgConsumption;

    @Column(name = "total_coast")
    private Double totalCoast;

    @Column(name = "total_fuel")
    private Double totalFuel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRout() {
        return rout;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(String fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public String getAvgConsumption() {
        return avgConsumption;
    }

    public void setAvgConsumption(String avgConsumption) {
        this.avgConsumption = avgConsumption;
    }

    public Double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(Double totalCoast) {
        this.totalCoast = totalCoast;
    }

    public Double getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(Double totalFuel) {
        this.totalFuel = totalFuel;
    }

}