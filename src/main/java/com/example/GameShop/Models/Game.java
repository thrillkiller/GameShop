package com.example.GameShop.Models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends Product {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "game_genre",joinColumns = @JoinColumn(name = "game_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column
    private String publisher;

    public Game(){
        super();
        genres = new ArrayList<>();
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean hasGenres() {return !genres.isEmpty();}

    public void addGenre(Genre g){
        genres.add(g);
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;
        else
        {
            if(((Game) o).publisher.equals(this.publisher) &&  ((Game) o).releaseDate.equals(this.releaseDate))
                return true;
            else
                return false;
        }
    }

    public String getGenresString(){
        String result ="";
        for(Genre g : genres)
        {
            result += g.getName();
            result += " ";
        }
        return result;
    }

}
