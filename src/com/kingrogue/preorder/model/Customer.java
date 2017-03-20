package com.kingrogue.preorder.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Tim G on 16-Feb-17.
 */
public class Customer {

    //set on initialization
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty phone;

    //populated after init
    private SimpleIntegerProperty noOrders;

    public Customer(int id, String name, int phone){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleIntegerProperty(phone);

        this.noOrders = new SimpleIntegerProperty(0);
    }

    public String toString(){
        return this.getName();
    }

    public int getID(){
        return id.get();
    }

    public void setID(int id){
        this.id.set(id);
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return name;
    }

    public int getPhone(){
        return phone.get();
    }

    public void setPhone(int phone){
        this.phone.set(phone);
    }

    public IntegerProperty phoneProperty(){
        return phone;
    }

    public int getNoOrders() {
        return noOrders.get();
    }

    public SimpleIntegerProperty noOrdersProperty() {
        return noOrders;
    }

    public void setNoOrders(int noOrders) {
        this.noOrders.set(noOrders);
    }
}
