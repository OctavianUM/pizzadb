package com.dbproject.domain;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@NamedQuery(
		name = "select_adress_by_street",
		query = "SELECT a "+
                "FROM Adress b "+
                "WHERE street = :street"
)

@Entity
public class Adress {
    @Id
    private long adressId;

    
    private short postal;
    private String street;
    private int number;

    Adress() {}

    public Adress(long adressId, short postal, String street, int number) {
        this.adressId = adressId;
        this.postal = postal;
        this.street = street;
        this.number = number;
    }

    public long getId() {
        return adressId;
    }

    public int getNumber() {
        return number;
    }

    public short getPostal() {
        return postal;
    }

    public String getStreet() {
        return street;
    }

    public void setId(long adressId) {
        this.adressId = adressId;
    }

    

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPostal(short postal) {
        this.postal = postal;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        String r = ( adressId +", "+postal+", "+ street+", "+ number);
        return r;
    }
}
