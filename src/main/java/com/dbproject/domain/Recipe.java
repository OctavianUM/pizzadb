package com.dbproject.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="recipe")
public class Recipe {
    @Id
    private int menuItemId;
    private int ingredientId;
    private int amount;

    public Recipe(){}
    public Recipe(int menuItemId, int ingredientId, int amount) {
        this.menuItemId = menuItemId;
        this.ingredientId = ingredientId;
        this.amount = amount;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}




