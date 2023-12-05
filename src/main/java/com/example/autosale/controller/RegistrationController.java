package com.example.autosale.controller;

import com.example.autosale.model.RoleModel;
import com.example.autosale.model.UserModel;
import com.example.autosale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    private String RegView()
    {
        return "registration";
    }

    @PostMapping("/registration")
    private String Reg(UserModel user, Model model)
    {
        UserModel user_from_db = _userRepository.findByUsername(user.getUsername());
        if (user_from_db != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "registration";
        }
        user.setRole(new RoleModel(1L, "User"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        _userRepository.save(user);
        return "redirect:/login";
    }
}
