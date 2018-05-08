package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Game;
import com.example.GameShop.Repositories.GameRepo;
import com.example.GameShop.Repositories.GenreRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SuppressWarnings("unused")
@Controller
public class GameController {

    private long id;

    @Autowired
    private SettingService settingService;

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private GenreRepo genreRepo;

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable("id") long id, Model model){
        this.id = id;
        if(!gameRepo.findById(this.id).isPresent())
            model.addAttribute("error",true);
        else
        {
        addAtrributes(model);
        }
        return "game";
    }

    @GetMapping("/game/add/{id}")
    public String addGameToCart(@PathVariable("id") long id, Model model){
        this.id = id;
        if(!settingService.isLogged() || settingService.isSupervisor())
        {
            model.addAttribute("error",true);
        }
        else {
            Game game = gameRepo.findById(id).get();
            settingService.getLoggedUser().addProductToCart(game);
            game.substract(1);
            gameRepo.save(game);
            addAtrributes(model);
        }
        return "game";
    }

    @GetMapping("/game/restock/{id}")
    public String restockGame(@PathVariable("id") long id,Model model){
        this.id = id;
        if(!settingService.isSupervisor())
            model.addAttribute("error",true);
        else
        {
            Game game = gameRepo.findById(id).get();
            game.add(1);
            gameRepo.save(game);
            addAtrributes(model);
        }
        return "game";
    }

    private void addAtrributes(Model model){
        Game game = gameRepo.findById(id).get();
        model.addAttribute("id",this.id);
        model.addAttribute("name",game.getName());
        model.addAttribute("description",game.getDescription());
        model.addAttribute("price",game.getBasePrice());
        model.addAttribute("onStock",game.isAvaible());
        model.addAttribute("publisher",game.getPublisher());
        model.addAttribute("Releasedate",game.getReleaseDate().toString());
        model.addAttribute("genres",game.getGenresString());
        model.addAttribute("logged",settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("error",false);
    }

}
