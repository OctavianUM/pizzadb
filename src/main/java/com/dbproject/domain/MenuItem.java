package com.dbproject.domain;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MenuItem {
    @Id
    private int menuItemId;
    private String name;
    private String type;
    private String description;

    public MenuItem(){}
    public MenuItem(int menuItemId, String name, String type, String description) {
        this.menuItemId = menuItemId;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
