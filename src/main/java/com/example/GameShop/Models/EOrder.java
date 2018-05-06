package com.example.GameShop.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class EOrder { //EntityOrder

    public enum State{
      pending,processed,sent ;
    }

    @GeneratedValue
    @Id
    private long id;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Product> cart;

    @Column
    private LocalDateTime orderTime;

    @Column
    private State state;

    @Column
    private String adress;


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

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
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