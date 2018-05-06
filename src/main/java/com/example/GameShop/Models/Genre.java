package com.example.GameShop.Models;

import javax.persistence.*;

@Entity
public class Genre {

    @GeneratedValue
    @Id
    public long id;

    @Column
    public String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    public Game game;

    public Genre(String name)
    {
        this.name = name;
    }

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
