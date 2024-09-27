package com.dbproject.domain;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    private int menuId;
    private int menuItemId;

    public Menu(){}
    public Menu(int menuId, int menuItemId, int id, String name) {
        this.menuId = menuId;
        this.menuItemId = menuItemId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

}
