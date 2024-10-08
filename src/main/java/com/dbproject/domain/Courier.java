package com.dbproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Courier {
    @Id
    private int courierID;

    private short postal;

    private String status;

    private LocalDateTime timeLastDelivery;

    Courier(){}

    public Courier(short postal, String status, LocalDateTime timeLastDelivery) {
        this.postal = postal;
        this.status = status;
        this.timeLastDelivery = timeLastDelivery;
    }
    public int getCourierID(){
        return courierID;
    }

    public short getPostal() {
        return postal;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimeLastDelivery() {
        return timeLastDelivery;
    }

    public void setPostal(short postal) {
        this.postal = postal;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTimeLastDelivery(LocalDateTime timeLastDelivery) {
        this.timeLastDelivery = timeLastDelivery;
    }
}
