package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Game;
import com.example.GameShop.Repositories.GameRepo;
import com.example.GameShop.Repositories.GenreRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@SuppressWarnings("unused")
@Controller
public class GamesController {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private SettingService settingService;

    @GetMapping("/games")
    public String getGames(Model model)
    {
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("games",gameRepo.findAll());
        model.addAttribute("game", new Game());
        model.addAttribute("genres",genreRepo.findAll());
        return "games";
    }

    @PostMapping("/games")
    public String postGames(@RequestParam(value = "genresContainer")ArrayList<Long> genresIds, @ModelAttribute("game") Game game, Model model)
    {
        if(genresIds.isEmpty()|| game.getReleaseDate().isAfter(LocalDate.now()) || game.getBasePrice() < 0.0f || gameRepo.doesExist(game.getName()))
        {
            model.addAttribute("error");
        }
        else
        {
            for(Long l : genresIds)
            {
                game.addGenre(genreRepo.findById(l).get());
            }
            gameRepo.save(game);
        }
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("games",gameRepo.findAll());
        model.addAttribute("genres",genreRepo.findAll());
        return "games";
    }

    @GetMapping("/games/byGenre/{name}")
    public String getGamesByGenre(Model model, @PathVariable("name") String name)
    {
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("games",gameRepo.selectByGenreName(name));
        model.addAttribute("game", new Game());
        model.addAttribute("genres",genreRepo.findAll());
        return "games";
    }

    public void setGenreRepo(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }
}
