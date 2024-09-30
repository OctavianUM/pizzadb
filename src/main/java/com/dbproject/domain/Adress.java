package com.dbproject.domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@NamedQuery(
		name = "select_adress_by_street",
		query = "SELECT a "+
                "FROM Adress a "+
                "WHERE street = :street"
)

@Entity
@Table(name="[Adress]")
public class Adress {
    @Id 
    @Generated
    private long adressId;
    @NaturalId
    private short postal;
    @NaturalId
    private String street;
    @NaturalId
    private int number;

    Adress() {}

    public Adress( short postal, String street, int number) {
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
