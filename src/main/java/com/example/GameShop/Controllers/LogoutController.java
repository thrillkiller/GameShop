package com.example.GameShop.Controllers;

import com.example.GameShop.Services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
@Controller
public class LogoutController {

    @Autowired
    SettingService settingService;

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpServletResponse response){
        settingService.setLoggedUser(null);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}
