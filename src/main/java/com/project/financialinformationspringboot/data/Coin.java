package com.project.financialinformationspringboot.data;

import jakarta.persistence.*;

@Entity
@Table
public class Coin {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String toString() {
        return "ID = " + id + " NAME = " + name + " AMOUNT = " + amount + " CURRENCY = " + currency ;
    }
}
