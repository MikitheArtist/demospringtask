package com.example.demospringtask.repository;

import com.example.demospringtask.entities.FuelCalc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelCalcRepository extends JpaRepository<FuelCalc, Integer> {
    List<FuelCalc> findByRout(String rout);
    List<FuelCalc> findByFuel(String fuel);
    List<FuelCalc> findByDistance(Integer distance);
    List<FuelCalc> findByFuelPrice(Double fuelPrice);
    List<FuelCalc> findByAvgConsumption(Integer avgConsumption);
    List<FuelCalc> findByTotalFuel(Double totalFuel);
    List<FuelCalc> findByTotalCoast(Double totalCoast);
}