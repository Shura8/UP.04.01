package com.example.autosale.controller;

import com.example.autosale.model.CarModel;
import com.example.autosale.model.ClassCarModel;
import com.example.autosale.model.FineModel;
import com.example.autosale.repositories.CarRepository;
import com.example.autosale.repositories.ClassCarRepository;
import com.example.autosale.repositories.FineRepository;
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
@PreAuthorize("hasAnyAuthority('User','Admin')")
public class FineController {
    private FineRepository _fineRepository;

    @Autowired
    public FineController(FineRepository fineRepository){ _fineRepository = fineRepository;}
    @GetMapping("/fines")
    public String index(Model model){
        model.addAttribute("fines", _fineRepository.findAll());
        return "fines/fines";
    }
    @GetMapping("/finesAdd")
    public String newFine(Model model){
        model.addAttribute("fines", new FineModel());
        return "fines/finesAdd";
    }
    @PostMapping("/success3")
    public String create(@Valid FineModel fineModel,BindingResult result){
        if(result.hasErrors()){
            return "fines/finesAdd";
        }
        _fineRepository.save(fineModel);
        return "redirect:/fines";
    }
    @GetMapping("/editFines")
    public String editFine( @RequestParam(name = "id") long id, Model model) {
        FineModel fines = _fineRepository.findById(id).orElseThrow();
        model.addAttribute("edit", fines);
        return "fines/editFines";
    }

    @PostMapping("/editFines")
    public String editFine(@Valid FineModel fineModel) {
        _fineRepository.save(fineModel);
        return "redirect:/fines";
    }

    @PostMapping("/deleteFines")
    public String deleteFine(@RequestParam("id") Long id) {
        _fineRepository.delete(_fineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/fines";
    }

}
