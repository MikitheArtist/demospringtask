package com.example.demospringtask.controllers;

import com.example.demospringtask.entities.FuelCalc;
import com.example.demospringtask.repository.FuelCalcRepository;
import com.example.demospringtask.service.FuelCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FuelCalcController {
    private final FuelCalcRepository fuelCalcRepository;

    private final FuelCalcService fuelCalcService;

    @Autowired
    public FuelCalcController(FuelCalcRepository fuelCalcRepository, FuelCalcService fuelCalcService) {
        this.fuelCalcRepository = fuelCalcRepository;
        this.fuelCalcService = fuelCalcService;
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        model.addAttribute("fuelConsumptions", fuelCalcRepository.findAll());
        return "result";
    }

    @GetMapping("/index")
    public String back() {
        return "index";
    }


    @PostMapping("/add")
    public String addFuelConsumption(@RequestParam String rout,
                      @RequestParam String fuel,
                      @RequestParam String distance,
                      @RequestParam String fuelPrice,
                      @RequestParam String avgConsumption) {
        try {
            double verifiedDistance = fuelCalcService.checkForComma(distance);
            double verifiedAvgConsumption = fuelCalcService.checkForComma(avgConsumption);
            double verifiedFuelPrice = fuelCalcService.checkForComma(fuelPrice);
            double totalFuel = fuelCalcService.calcTotalFuel(verifiedAvgConsumption, verifiedDistance);
            double totalCoast = fuelCalcService.calcTotalCoast(totalFuel, verifiedFuelPrice);
            FuelCalc fuelCalc = new FuelCalc(0, rout, fuel, String.valueOf(verifiedDistance),
                    String.valueOf(verifiedFuelPrice),
                    String.valueOf(verifiedAvgConsumption),
                    totalCoast, totalFuel);
            fuelCalcRepository.save(fuelCalc);
        } catch (Exception ignored) {}

        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteFuelConsumption(@PathVariable("id") int id) {
        FuelCalc fuelCalc = fuelCalcRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
        fuelCalcRepository.delete(fuelCalc);

        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model) {
        FuelCalc fuelCalc = fuelCalcRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
        model.addAttribute("fuelConsumption", fuelCalc);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateFuelConsumption(@PathVariable("id") int id, FuelCalc fuelCalc) {
        try {
            String tmpDistance = fuelCalc.getDistance();
            double distance = Double.parseDouble(tmpDistance);
            String tmpAvgConsumption = fuelCalc.getAvgConsumption();
            double avgConsumption = Double.parseDouble(tmpAvgConsumption);
            double totalFuel = fuelCalcService.calcTotalFuel(avgConsumption, distance);
            fuelCalc.setTotalFuel(totalFuel);
            fuelCalcRepository.save(fuelCalc);
            String tmpFuelPrice = fuelCalc.getFuelPrice();
            double fuelPrice = Double.parseDouble(tmpFuelPrice);
            double totalCoast = fuelCalcService.calcTotalCoast(totalFuel, fuelPrice);
            System.out.println(totalCoast);
            fuelCalc.setTotalCoast(totalCoast);
            fuelCalcRepository.save(fuelCalc);
        } catch (Exception ignored){}

        return "redirect:/list";
    }



}
