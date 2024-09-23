package org.pizzadb.model;

public class Adress {

    private long id;
    private short postal;
    private String street;
    private int number;

    public Adress(long id, short postal, String street, int number) {
        this.id = id;
        this.postal = postal;
        this.street = street;
        this.number = number;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
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
}
