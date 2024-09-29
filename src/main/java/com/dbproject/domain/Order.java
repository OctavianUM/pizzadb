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
    @NaturalId
    private String orderTime;
    private int discountId;
    private  String status;

    public Order(){}
    public Order(int customerId, LocalDateTime dateTime, int discountId, OrderStatus status) {
        this.customerId = customerId;
        this.orderTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
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

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
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
