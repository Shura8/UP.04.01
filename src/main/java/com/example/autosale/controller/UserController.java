package com.example.autosale.controller;

import com.example.autosale.model.CarModel;
import com.example.autosale.model.ClassCarModel;
import com.example.autosale.model.RoleModel;
import com.example.autosale.model.UserModel;
import com.example.autosale.repositories.CarRepository;
import com.example.autosale.repositories.ClassCarRepository;
import com.example.autosale.repositories.RoleRepository;
import com.example.autosale.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller()
@PreAuthorize("hasAnyAuthority('Admin')")
public class UserController {
    private UserRepository _userRepository;
    private RoleRepository _roleRepository;

    @Autowired
    public UserController(UserRepository userRepository, RoleRepository roleRepository){
        _userRepository = userRepository;
        _roleRepository = roleRepository;
    }
    @GetMapping("/users")
    public String index(Model model){
        model.addAttribute("users", _userRepository.findAll());
        return "users/users";
    }
    @GetMapping("/userAdd")
    public String newUser(Model model){
        List<RoleModel> roles = _roleRepository.findAll();
        model.addAttribute("users", new UserModel());
        model.addAttribute("roles", roles);
        return "users/userAdd";
    }
    @PostMapping("/success4")
    public String create(@Valid UserModel userModel,BindingResult result, Model model){
        if(result.hasErrors()){
            return "users/userAdd";
        }
        List<RoleModel> roles = _roleRepository.findAll();
        model.addAttribute("roles", roles);
        _userRepository.save(userModel);
        return "redirect:/users";
    }
    @GetMapping("/editUser")
    public String editUser( @RequestParam(name = "id") long id, Model model, Model type) {
        List<RoleModel> roles = _roleRepository.findAll();
        model.addAttribute("roles", roles);
        UserModel users = _userRepository.findById(id).orElseThrow();
        model.addAttribute("edit", users);
        return "users/editUser";
    }

    @PostMapping("/editUser")
    public String editUser(@Valid UserModel userModel) {
        _userRepository.save(userModel);
        return "redirect:/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        _userRepository.delete(_userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/users";
    }
    @GetMapping("/roles")
    public String indexRoles(Model model){
        model.addAttribute("roles", _roleRepository.findAll());
        return "roles/roles";
    }
    @GetMapping("/rolesAdd")
    public String newRoles(Model model){
        model.addAttribute("roles", new RoleModel());
        return "roles/rolesAdd";
    }
    @PostMapping("/success5")
    public String create(@Valid RoleModel roleModel, BindingResult result){
        if(result.hasErrors()){
            return "roles/rolesAdd";
        }
        _roleRepository.save(roleModel);
        return "redirect:/roles";
    }
    @GetMapping("/editRoles")
    public String editRoles(Model model, @RequestParam(name = "id") int id) {
        List<RoleModel> allStrings = _roleRepository.findAll();
        for (RoleModel item : allStrings) {
            if (item.getId() == id) {
                ArrayList<RoleModel> roles = new ArrayList<>();
                roles.add(item);
                model.addAttribute("edit", roles);
            }
        }
        return "roles/editRoles";
    }

    @PostMapping("/editRoles")
    public String editRoles(@Valid RoleModel roleModel) {
        _roleRepository.save(roleModel);
        return "redirect:/roles";
    }

    @PostMapping("/deleteRoles")
    public String deleteRoles(@RequestParam("id") Long id) {
        _roleRepository.delete(_roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id)));
        return "redirect:/roles";
    }
}
