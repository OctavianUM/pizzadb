package com.dbproject.util;

public enum CourierStatus {
    AVAILABLE("AVAILABLE"),     // he's just given an order
    WAITING("WAITING"),         // 5 min period where he can accept extra orders
    DELIVERING("DELIVERING");   // unavailable for some amount of time

    private final String status;

    // Constructor to accept the string value
    CourierStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

