package com.dbproject.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DiscountCode {
    @Id
    private int discountID;
    private int discount;
    private String discountString;
    private boolean isUsed;
    public DiscountCode(){}
    public DiscountCode(String discountString, int discount) {
        this.discount = discount;
        this.discountString = discountString;
        this.isUsed = false;
    }

    public int getDiscountID() {
        return discountID;
    }

    public boolean getIsUsed(){
        return isUsed;
    }
    public void setIsUsed(boolean bool){
        isUsed = bool;
    }

    public String getDiscountString() {
        return discountString;
    }
    public void setDiscountString(String discountString) {
        this.discountString = discountString;
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
