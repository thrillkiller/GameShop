package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Product;
import com.example.GameShop.Repositories.ProductRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    private long id;

    @Autowired
    private SettingService settingService;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") long id, Model model){
        this.id = id;
        if(!productRepo.findById(this.id).isPresent())
            model.addAttribute("error",true);
        else
        {
            addAtrributes(model);
        }
        return "product";
    }

    @GetMapping("/product/add/{id}")
    public String addProductToCart(@PathVariable("id") long id, Model model){
        this.id = id;
        if(!settingService.isLogged() || settingService.isSupervisor())
        {
            model.addAttribute("error",true);
        }
        else {
            Product product = productRepo.findById(id).get();
            settingService.getLoggedUser().addProductToCart(product);
            product.substract(1);
            productRepo.save(product);
            addAtrributes(model);
        }
        return "product";
    }

    @GetMapping("/product/restock/{id}")
    public String restockProduct(@PathVariable("id") long id,Model model){
        this.id = id;
        if(!settingService.isSupervisor())
            model.addAttribute("error",true);
        else
        {
            Product product = productRepo.findById(id).get();
            product.add(1);
            productRepo.save(product);
            addAtrributes(model);
        }
        return "product";
    }

    private void addAtrributes(Model model){
        Product product = productRepo.findById(id).get();
        model.addAttribute("id",this.id);
        model.addAttribute("name",product.getName());
        model.addAttribute("description",product.getDescription());
        model.addAttribute("price",product.getBasePrice());
        model.addAttribute("onStock",product.isAvaible());
        model.addAttribute("logged",settingService.isLogged());
        model.addAttribute("supervisor",settingService.isSupervisor());
        model.addAttribute("error",false);
    }

}
