package com.dbproject.domain;

import org.hibernate.annotations.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Delivery {
    @Id
    private int deliveryId;
    private int orderId;
    private int courierId;

    public Delivery(){}
    public Delivery(int orderId, int courierId) {
        this.orderId = orderId;
        this.courierId = courierId;
    }
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

}
