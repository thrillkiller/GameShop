package com.example.GameShop.Models;

import javax.persistence.*;

@Entity
public class Product {

    @GeneratedValue
    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private float basePrice;

    @Column
    private int available;

    @ManyToOne
    private Order order;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public boolean isAvaible()
    {
        if(this.available <= 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass())
            return false;
        if(!this.getName().equals(((Product) o).name))
            return false;
        else
            return true;
    }

    public void add(int quantity) {
        this.available += quantity;
    }

    public boolean substract (int quantity){
        if(quantity < 0 || quantity > available)
            return false;
        else
        {
            available -= quantity;
            return true;
        }
    }

    public float getPrice(){
        return (float)available * basePrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
