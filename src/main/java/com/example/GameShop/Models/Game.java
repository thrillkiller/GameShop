package com.example.GameShop.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Game extends Product {

    @OneToMany
    private Set<Platform> platforms;

    @Column
    private LocalDateTime relase;

    @Column
    private String publisher;

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public LocalDateTime getRelase() {
        return relase;
    }

    public void setRelase(LocalDateTime relase) {
        this.relase = relase;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
