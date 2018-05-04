package com.example.GameShop.Models;

import javax.persistence.*;

@Entity
public class Platform {

    @GeneratedValue
    @Id
    public long id;

    @Column
    public String name;

    @ManyToOne
    public Game game;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
