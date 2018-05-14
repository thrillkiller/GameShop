package com.example.GameShop.Controllers;

import com.example.GameShop.Models.User;
import com.example.GameShop.Repositories.UserRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@SuppressWarnings("unused")
@Controller
public class UsersController {
    @Autowired
    private SettingService settingService;

    @Autowired
    public UserRepo userRepo;

    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("users",userRepo.findAll());
        model.addAttribute("issupervisor",settingService.isSupervisor());
        model.addAttribute("logged" ,settingService.isLogged());
        return "users";
    }

    @PostMapping("/users")
    public String postUsers(Model model, @ModelAttribute("user") User user){
        if(userRepo.loginExists(user.getUsername()))
            model.addAttribute("error");
        else
            userRepo.save(user);
        model.addAttribute("issupervisor",settingService.isSupervisor());
        model.addAttribute("logged" ,settingService.isLogged());
        model.addAttribute("users",userRepo.findAll());
        return "users";
    }

    @GetMapping("/users/del/{id}")
    public String deleteUsers(Model model, @PathVariable("id") long id){
        userRepo.deleteById(id);
        model.addAttribute("user",new User());
        model.addAttribute("issupervisor",settingService.isSupervisor());
        model.addAttribute("logged" ,settingService.isLogged());
        model.addAttribute("users",userRepo.findAll());
        return "users";
    }

    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }

    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

}
