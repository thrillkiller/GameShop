package com.example.GameShop.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Game extends Product {

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Genre> genres;

    @Column
    private LocalDate releaseDate;

    @Column
    private String publisher;

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public LocalDate getRelase() {
        return releaseDate;
    }

    public void setRelase(LocalDate relase) {
        this.releaseDate = relase;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean hasGenres() {return !genres.isEmpty();}

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
}
