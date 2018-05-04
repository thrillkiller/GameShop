package com.example.GameShop.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Order {

    public enum State{
      pending,processed,sent ;
    }

    @GeneratedValue
    @Id
    private long id;

    @OneToMany
    private Set<Product> cart;

    @Column
    private LocalDateTime dateTime;

    @Column
    private State state;

    @Column String adress;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Product> getCart() {
        return cart;
    }

    public void setCart(Set<Product> cart) {
        this.cart = cart;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public float getOrdersValue(){
        float value = 0.0f;
        for ( Product p : cart)
        {
            value += p.getPrice();
        }
        return value;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
