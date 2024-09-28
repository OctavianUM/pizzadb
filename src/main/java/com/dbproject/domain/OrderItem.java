package com.dbproject.domain;

import org.hibernate.annotations.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderItem {
    @Id
    private int orderItemId;
    private int orderId;
    private int menuItemId;
    private int quantity;

    public OrderItem(){}
    public OrderItem(int orderId, int menuItemId, int quantity) {
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
