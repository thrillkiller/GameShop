package com.example.GameShop.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Eorder { //EntityOrder

    public enum State{
      pending,processed,sent ;
    }

    @GeneratedValue
    @Id
    private long id;

    @OneToMany(mappedBy = "Eorder",cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Product> cart;

    @Column
    private LocalDateTime orderTime;

    @Column
    private State state;

    @Column
    private String adress;

    @ManyToOne
    @JoinColumn(name = "eorder_id")
    private User user;

    public Eorder(){
        state = State.pending;
        cart = new ArrayList<>();
        orderTime = LocalDateTime.now();
    }

    public Eorder(User user){
        state = State.pending;
        cart = new ArrayList<>();
        orderTime = LocalDateTime.now();
        this.user = user;
        this.adress = user.getAdress();
        this.cart.clear();
        this.cart.addAll(user.getCart());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
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

    public void setUser(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setState(String string){
        if(string.equals(State.pending.toString()))
            this.state = State.pending;
        if(string.equals(State.processed.toString()))
            this.state = State.processed;
        if(string.equals(State.sent.toString()))
            this.state = State.sent;
    }
}
