package com.dbproject.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NaturalId;

import com.dbproject.util.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="[Order]") //enclosed in brackets to prevent the sql command ORDER to override the table name
public class Order {
    @Id
    @Generated
    private int orderId;
    @NaturalId
    private int customerId;
    @Generated
    private LocalDateTime orderTime;
    private int discountId;
    private  String status;

    public Order(){}
    public Order(int customerId, int discountId, OrderStatus status) {
        this.customerId = customerId;
        this.discountId = discountId;
        this.status = status.toString();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
