package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Genre;
import com.example.GameShop.Repositories.GenreRepo;
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
public class GenresController {

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private SettingService settingService;

    @GetMapping("/genres")
    public String getGenres(Model model){
        model.addAttribute("genres",genreRepo.findAll());
        model.addAttribute("genre",new Genre());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "genres";
    }

    @PostMapping("/genres")
    public String postGenres(Model model, @ModelAttribute("genre")Genre genre){
        if(genreRepo.existsWithName(genre.getName()))
            model.addAttribute("error",true);
        else {
            genreRepo.save(genre);
        }
        model.addAttribute("genres",genreRepo.findAll());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "genres";
    }

    @GetMapping("/genres/delete/{id}")
    public String deleteGenre(@PathVariable("id") long id,Model model){
        genreRepo.deleteById(id);
        model.addAttribute("genres",genreRepo.findAll());
        model.addAttribute("genre",new Genre());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "genres";
    }
}
