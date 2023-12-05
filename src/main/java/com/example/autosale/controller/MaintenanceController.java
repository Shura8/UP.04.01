package com.example.autosale.controller;

import com.example.autosale.model.CarModel;
import com.example.autosale.model.ClassCarModel;
import com.example.autosale.model.FineModel;
import com.example.autosale.model.MaintenanceModel;
import com.example.autosale.repositories.CarRepository;
import com.example.autosale.repositories.FineRepository;
import com.example.autosale.repositories.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller()
@PreAuthorize("hasAnyAuthority('Mechanic','Admin')")
public class MaintenanceController {
    private MaintenanceRepository _maintenanceRepository;
    private CarRepository _carRepository;

    @Autowired
    public MaintenanceController(MaintenanceRepository maintenanceRepository, CarRepository carRepository){
        _maintenanceRepository = maintenanceRepository;
        _carRepository = carRepository;
    }
    @GetMapping("/maintenance")
    public String index(Model model){
        model.addAttribute("maintenance", _maintenanceRepository.findAll());
        return "maintenance/maintenance";
    }
    @GetMapping("/maintenanceAdd")
    public String newMaintenance(Model model){
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("maintenance", new MaintenanceModel());
        return "maintenance/maintenanceAdd";
    }
    @PostMapping("/success6")
    public String create(@Valid MaintenanceModel maintenanceModel,BindingResult result, Model model){
        if(result.hasErrors()){
            return "maintenance/maintenanceAdd";
        }
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        _maintenanceRepository.save(maintenanceModel);
        return "redirect:/maintenance";
    }
    @GetMapping("/editMaintenance")
    public String editMaintenance( @RequestParam(name = "id") long id, Model model) {
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        MaintenanceModel maintenance = _maintenanceRepository.findById(id).orElseThrow();
        model.addAttribute("edit", maintenance);
        return "maintenance/editMaintenance";
    }

    @PostMapping("/editMaintenance")
    public String editMaintenance(@Valid MaintenanceModel maintenance) {
        _maintenanceRepository.save(maintenance);
        return "redirect:/maintenance";
    }

    @PostMapping("/deleteMaintenance")
    public String deleteMaintenance(@RequestParam("id") Long id) {
        _maintenanceRepository.delete(_maintenanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/maintenance";
    }

}
