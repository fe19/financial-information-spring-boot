package com.project.financialinformationspringboot.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Coin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "DATE", nullable = false)
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        return "ID = " + id + " NAME = " + name + " PRICE = " + price + " CURRENCY = " + currency + " DATE = " + date;
    }
}
