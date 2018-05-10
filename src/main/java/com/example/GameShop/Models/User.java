package com.example.GameShop.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @GeneratedValue
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(nullable = false)
    private String adress;

    @Column
    private boolean supervisor;

    @Transient
    private List<Product> cart;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Eorder> Eorders;

    public User() {
        cart = new ArrayList<>();
        Eorders = new ArrayList<>();
        supervisor = false;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    public void addProductToCart(Product product) {
        boolean exists = false;
            for(Product p : cart)
            {
                if(p.getName().equals(product.getName()))
                    exists = true;
                    p.add(1);
            }
            if(exists)
                cart.add(product);
    }

    public void clearCart(){
        this.cart.clear();
    }

    public boolean isCartEmpty(){
        return cart.isEmpty();
    }

    public int getCartSize(){return cart.size();}

    public boolean removeFromCart(Product product){
        return cart.remove(product);
    }

    public List<Product> getCart() {return cart;}


    public List<Eorder> getEorders() {
        return Eorders;
    }

    public void setEorders(List<Eorder> eorders) {
        this.Eorders = eorders;
    }

    public String getUserName(){
        return name + " " + surname;
    }

}
