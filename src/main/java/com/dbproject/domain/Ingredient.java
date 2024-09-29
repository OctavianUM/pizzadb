package com.dbproject.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ingredient {
    @Id
    @Generated
    private int ingredientId;
    @NaturalId
    private String name;
    private String dietary;
    private double price;

    public Ingredient(){}
    public Ingredient(String name, String dietary, double price) {
        this.name = name;
        this.dietary = dietary;
        this.price = price;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDietary() {
        return dietary;
    }

    public void setDietary(String dietary) {
        this.dietary = dietary;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
