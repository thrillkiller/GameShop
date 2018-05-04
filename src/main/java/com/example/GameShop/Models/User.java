package com.example.GameShop.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private String login;

    @Column
    private String password;

    @Column(nullable = false)
    private String adress;

    @Column
    private boolean supervisor;

    @Transient
    private Set<Product> cart;

    @OneToMany
    private Set<Order> orders;

    public User() {
        cart = new HashSet<>();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        if(cart.contains(product))
        {
            cart.stream().forEach((Product p)-> {
                if(p.equals(product))
                    p.add(1);
                });
        }
        else
        {
            cart.add(product);
        }
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

    public Set<Product> getCart() {return cart;}


    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
