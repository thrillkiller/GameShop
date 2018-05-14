package com.example.GameShop.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SuppressWarnings("unused")
@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String getSettings()
    {
        return "settings";
    }
}
