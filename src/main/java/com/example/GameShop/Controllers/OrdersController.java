package com.example.GameShop.Controllers;

import com.example.GameShop.Models.Eorder;
import com.example.GameShop.Repositories.OrderRepo;
import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SuppressWarnings("unused")
@Controller
public class OrdersController {

    @Autowired
    private SettingService settingService;

    @Autowired
    private OrderRepo orderRepo;

    private long oId = 0;

    @GetMapping("/orders")
    public String getOrder(Model model){
        if(settingService.isSupervisor())
            model.addAttribute("eorders",orderRepo.findAll());
        else
            model.addAttribute("eorders",orderRepo.findByUser(settingService.getLoggedUser()));
        return "orders";
    }

    @GetMapping("/orders/new")
    public String newOrder(Model model){
        Eorder order = new Eorder(settingService.getLoggedUser());
        orderRepo.save(order);
        settingService.getLoggedUser().clearCart();
        model.addAttribute("eorders",orderRepo.findByUser(settingService.getLoggedUser()));
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String selectOrder(Model model, @PathVariable("id") long id)
    {
        oId = id;
        if(settingService.isSupervisor())
            model.addAttribute("eorders",orderRepo.findAll());
        else
            model.addAttribute("eorders",orderRepo.findByUser(settingService.getLoggedUser()));
     if(!orderRepo.findById(id).isPresent())
         model.addAttribute("error");
     else
     {
         model.addAttribute("selected",true);
         Eorder order = orderRepo.findById(id).get();
         model.addAttribute("products",order.getCart());
         model.addAttribute("name",order.getUser().getUserName());
         model.addAttribute("adress",order.getAdress());
         model.addAttribute("time",order.getOrderTime().toString());
         model.addAttribute("state",order.getState().toString());
         model.addAttribute("worth",order.getOrdersValue());
         model.addAttribute("id",id);
         if(order.getState() == Eorder.State.pending)
             model.addAttribute("nextState", Eorder.State.processed.toString());
         if(order.getState() == Eorder.State.processed)
             model.addAttribute("nextState", Eorder.State.sent.toString());
         model.addAttribute("supervisor",settingService.isSupervisor()) ;
     }
     return "orders";
    }

    @GetMapping("/orders/cancel/{id}")
    public String cancelOrder(Model model,@PathVariable("id") long id){
        if(settingService.isSupervisor())
            model.addAttribute("eorders",orderRepo.findAll());
        else
            model.addAttribute("eorders",orderRepo.findByUser(settingService.getLoggedUser()));
        if(!orderRepo.findById(id).isPresent())
            model.addAttribute("error",true);
        else {
            orderRepo.deleteById(id);
            model.addAttribute("cancel",true);
        }
        return "orders";
    }

    @GetMapping("/orders/changeTo/{next}")
    public String  changeState(Model model,@PathVariable("next") String next){
        if(orderRepo.findById(this.oId).isPresent())
        {
            Eorder order = orderRepo.findById(this.oId).get();
            order.setState(next);
            orderRepo.save(order);
        }
       return selectOrder(model,this.oId);
    }
}
