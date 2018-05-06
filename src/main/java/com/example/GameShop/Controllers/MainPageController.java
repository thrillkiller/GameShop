package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Genre;
import com.example.GameShop.Models.User;
import com.example.GameShop.Repositories.GenreRepo;
import com.example.GameShop.Repositories.UserRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    private boolean firstTime = true;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private SettingService settingService;

    @GetMapping("/")
    public String getMainPage(Model model)
    {
        if(firstTime) {
            User user = new User();
            user.setAdress("adress");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setName("Admin");
            user.setSupervisor(true);
            user.setSurname("Admin");
            userRepo.save(user);

            Genre g1,g2,g3;
            g1 = new Genre("FPS");
            g2 = new Genre("Rpg");
            g3 = new Genre("Arcade");
            genreRepo.save(g1);
            genreRepo.save(g2);
            genreRepo.save(g3);
            firstTime = false;
        }
        model.addAttribute("logged" , settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "index";
    }

    public void setUserRepo(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public void setSettingService(SettingService settingService)
    {
        this.settingService = settingService;
    }

    public void setGenreRepo(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }
}
