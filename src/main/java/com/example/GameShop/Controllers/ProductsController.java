package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Game;
import com.example.GameShop.Models.Product;
import com.example.GameShop.Repositories.ProductRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@SuppressWarnings("unused")
@Controller
public class ProductsController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SettingService settingService;

    @GetMapping("/products")
    public String getProducts(Model model)
    {
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("products",productRepo.findAll());
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/products")
    public String postProducts(@ModelAttribute("product") Product product, Model model)
    {
        if(product.getBasePrice() < 0.0f || productRepo.doesExist(product.getName()))
        {
            model.addAttribute("error");
        }
        else
        {
            productRepo.save(product);
        }
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("products",productRepo.findAll());
        return "products";
    }
}

