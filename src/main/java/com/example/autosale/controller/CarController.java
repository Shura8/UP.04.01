package com.example.autosale.controller;

import com.example.autosale.model.CarModel;
import com.example.autosale.model.ClassCarModel;
import com.example.autosale.model.StatusCarModel;
import com.example.autosale.repositories.CarRepository;
import com.example.autosale.repositories.ClassCarRepository;
import com.example.autosale.repositories.StatusCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
@PreAuthorize("hasAnyAuthority('Mechanic','Admin')")
public class CarController {
    private CarRepository _carRepository;
    private ClassCarRepository _classCarRepository;
    private StatusCarRepository _statusCarRepository;

    @Autowired
    public CarController(CarRepository carRepository,
                         ClassCarRepository classCarRepository, StatusCarRepository statusCarRepository){
        _carRepository = carRepository;
        _classCarRepository = classCarRepository;
        _statusCarRepository = statusCarRepository;
    }
    @GetMapping("/cars")
    public String index(Model model){
        model.addAttribute("cars", _carRepository.findAll());
        return "cars/cars";
    }
    @GetMapping("/carAdd")
    public String newCar(Model model){
        List<ClassCarModel> classCars = _classCarRepository.findAll();
        List<StatusCarModel> status = _statusCarRepository.findAll();
        model.addAttribute("cars", new CarModel());
        model.addAttribute("classCars", classCars);
        model.addAttribute("status", status);
        return "cars/carAdd";
    }
    @PostMapping("/success1")
    public String create(@Valid CarModel carModel,BindingResult result, Model model){
        if(result.hasErrors()){
            return "cars/carAdd";
        }
        List<ClassCarModel> classCars = _classCarRepository.findAll();
        List<StatusCarModel> status = _statusCarRepository.findAll();
        model.addAttribute("classCars", classCars);
        model.addAttribute("status", status);
        _carRepository.save(carModel);
        return "redirect:/cars";
    }
    @GetMapping("/editCar")
    public String editCar( @RequestParam(name = "id") long id, Model model, Model type) {
        List<ClassCarModel> classCars = _classCarRepository.findAll();
        List<StatusCarModel> status = _statusCarRepository.findAll();
        type.addAttribute("classCar", classCars);
        model.addAttribute("status", status);
        CarModel cars = _carRepository.findById(id).orElseThrow();
        model.addAttribute("edit", cars);
        return "cars/editCar";
    }

    @PostMapping("/editCar")
    public String editCar(@Valid CarModel carModel) {
        _carRepository.save(carModel);
        return "redirect:/cars";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("id") Long id) {
        _carRepository.delete(_carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/cars";
    }
    @GetMapping("/classCars")
    public String indexClassCar(Model model){
        model.addAttribute("classCars", _classCarRepository.findAll());
        return "classCars/classCars";
    }
    @GetMapping("/classCarAdd")
    public String newClassCar(Model model){
        model.addAttribute("classCars", new ClassCarModel());
        return "classCars/classCarAdd";
    }
    @PostMapping("/success2")
    public String create(@Valid ClassCarModel classCarModel, BindingResult result){
        if(result.hasErrors()){
            return "classCars/classCarAdd";
        }
        _classCarRepository.save(classCarModel);
        return "redirect:/classCars";
    }
    @GetMapping("/editClassCar")
    public String editClassCar(Model model, @RequestParam(name = "id") int id) {
        List<ClassCarModel> allStrings = _classCarRepository.findAll();
        for (ClassCarModel item : allStrings) {
            if (item.getId() == id) {
                ArrayList<ClassCarModel> classCar = new ArrayList<>();
                classCar.add(item);
                model.addAttribute("edit", classCar);
            }
        }
        return "classCars/editClassCar";
    }

    @PostMapping("/editClassCar")
    public String editClassCar(@Valid ClassCarModel classCarModel) {
        _classCarRepository.save(classCarModel);
        return "redirect:/classCars";
    }

    @PostMapping("/deleteClassCar")
    public String deleteClassCar(@RequestParam("id") Long id) {
        _classCarRepository.delete(_classCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/classCars";
    }
    @GetMapping("/statusCar")
    public String indexStatus(Model model){
        model.addAttribute("statusCar", _statusCarRepository.findAll());
        return "statusCar/statusCar";
    }
    @GetMapping("/statusCarAdd")
    public String newStatusCar(Model model){
        model.addAttribute("statusCar", new StatusCarModel());
        return "statusCar/statusCarAdd";
    }
    @PostMapping("/success8")
    public String create(@Valid StatusCarModel statusCarModel, BindingResult result){
        if(result.hasErrors()){
            return "statusCar/statusCarAdd";
        }
        _statusCarRepository.save(statusCarModel);
        return "redirect:/statusCar";
    }
    @GetMapping("/editStatusCar")
    public String editStatusCar(Model model, @RequestParam(name = "id") int id) {
        List<StatusCarModel> allStrings = _statusCarRepository.findAll();
        for (StatusCarModel item : allStrings) {
            if (item.getId() == id) {
                ArrayList<StatusCarModel> statusCar = new ArrayList<>();
                statusCar.add(item);
                model.addAttribute("edit", statusCar);
            }
        }
        return "statusCar/editStatusCar";
    }

    @PostMapping("/editStatusCar")
    public String editStatusCar(@Valid StatusCarModel statusCarModel) {
        _statusCarRepository.save(statusCarModel);
        return "redirect:/statusCar";
    }

    @PostMapping("/deleteStatusCar")
    public String deleteStatusCar(@RequestParam("id") Long id) {
        _statusCarRepository.delete(_statusCarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/statusCar";
    }
}
