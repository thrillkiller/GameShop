package com.example.GameShop.Services;

import com.example.GameShop.Models.User;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isAnyoneLogged()
    {
        if(loggedUser == null)
            return false;
        else
            return true;
    }
}
