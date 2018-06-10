package com.example.GameShop.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {

    @GeneratedValue
    @Id
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Game> gamesList;

    public Genre(String name)
    {
        gamesList = new ArrayList<>();
        this.name = name;
    }

    public Genre() {gamesList = new ArrayList<>();}

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

    public List<Game> getGames() {
        return gamesList;
    }

    public void setGames(List<Game> gamesList) {
        this.gamesList = gamesList;
    }

    public boolean addGame(Game g)
    {
        return gamesList.add(g);
    }


}
