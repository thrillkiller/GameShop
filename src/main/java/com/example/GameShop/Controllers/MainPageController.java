package com.example.GameShop.Controllers;

import com.example.GameShop.Models.User;
import com.example.GameShop.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private boolean firstTime = true;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String getMainPage(Model model)
    {
        if(firstTime) {
            User user = new User();
            user.setAdress("adress");
            user.setLogin("admin");
            user.setPassword("admin");
            user.setName("Admin");
            user.setSupervisor(true);
            user.setSurname("Admin");
            userRepo.save(user);
            firstTime = false;
        }
        return "index";
    }

    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }
}
