package com.example.autosale.controller;

import com.example.autosale.model.CarModel;
import com.example.autosale.model.MaintenanceModel;
import com.example.autosale.model.RentalModel;
import com.example.autosale.model.UserModel;
import com.example.autosale.repositories.CarRepository;
import com.example.autosale.repositories.MaintenanceRepository;
import com.example.autosale.repositories.RentalRepository;
import com.example.autosale.repositories.UserRepository;
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
@PreAuthorize("hasAnyAuthority('User','Admin')")
public class RentalController {
    private RentalRepository _rentalRepository;
    private UserRepository _userRepository;
    private CarRepository _carRepository;

    @Autowired
    public RentalController(RentalRepository rentalRepository,
                            CarRepository carRepository, UserRepository userRepository){
        _rentalRepository = rentalRepository;
        _userRepository = userRepository;
        _carRepository = carRepository;
    }
    @GetMapping("/rentals")
    public String index(Model model){
        model.addAttribute("rentals", _rentalRepository.findAll());
        return "rentals/rentals";
    }
    @GetMapping("/rentalsAdd")
    public String newMaintenance(Model model){
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        List<UserModel> users = _userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("rentals", new RentalModel());
        return "rentals/rentalsAdd";
    }
    @PostMapping("/success7")
    public String create(@Valid RentalModel rentalModel,BindingResult result, Model model){
        if(result.hasErrors()){
            return "rentals/rentalsAdd";
        }
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        List<UserModel> users = _userRepository.findAll();
        model.addAttribute("users", users);
        _rentalRepository.save(rentalModel);
        return "redirect:/rentals";
    }
    @GetMapping("/editRentals")
    public String editRentals( @RequestParam(name = "id") long id, Model model) {
        List<CarModel> cars = _carRepository.findAll();
        model.addAttribute("cars", cars);
        List<UserModel> users = _userRepository.findAll();
        model.addAttribute("users", users);
        RentalModel rental = _rentalRepository.findById(id).orElseThrow();
        model.addAttribute("edit", rental);
        return "rentals/editRentals";
    }

    @PostMapping("/editRentals")
    public String editRentals(@Valid RentalModel rentalModel) {
        _rentalRepository.save(rentalModel);
        return "redirect:/rentals";
    }

    @PostMapping("/deleteRentals")
    public String deleteRentals(@RequestParam("id") Long id) {
        _rentalRepository.delete(_rentalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/rentals";
    }

}
