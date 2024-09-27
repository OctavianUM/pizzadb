package com.dbproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DiscountCode {
    @Id
    private int discountID;
    private int discount;

    public DiscountCode(){}
    public DiscountCode(int discountID, int discount) {
        this.discountID = discountID;
        this.discount = discount;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
