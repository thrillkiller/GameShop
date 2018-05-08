package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Game;
import com.example.GameShop.Models.Product;
import com.example.GameShop.Repositories.GameRepo;
import com.example.GameShop.Repositories.ProductRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SettingService settingService;

    @GetMapping("/cart")
    public String getCart(Model model)
    {   if(settingService.isLogged() && !settingService.isSupervisor())
        {
        model.addAttribute("products",settingService.getLoggedUser().getCart().toArray());
        }
        model.addAttribute("logged",settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "cart";
    }

    @GetMapping("/cart/clear")
    public String clearCart(Model model){
        if(settingService.isLogged() && !settingService.isSupervisor())
        {   //Return  every unit to shop
            for(Product p : settingService.getLoggedUser().getCart())
            {
                int x = p.getAvailable();//Gets units in cart of that product
                if(p.getClass() == Product.class)
                {
                    Product pr = productRepo.findByName(p.getName());
                    pr.add(x);
                    productRepo.save(pr);
                }
                else//It's game
                {
                    Game g = gameRepo.findByName(p.getName());
                    g.add(x);
                    gameRepo.save(g);
                }
            }
            settingService.getLoggedUser().clearCart();
            model.addAttribute("products",settingService.getLoggedUser().getCart().toArray());
        }
        model.addAttribute("logged",settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "cart";
    }

    @GetMapping("/cart/reduce/{name}")
    public String reduce(Model model, @PathVariable("name") String name)
    {
        //To do
        model.addAttribute("logged",settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        return "cart";
    }
}
