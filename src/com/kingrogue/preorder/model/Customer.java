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
    private final SimpleStringProperty phone;

    //populated after init
    private SimpleIntegerProperty noOrders;
    private SimpleIntegerProperty noCompletedOrders;

    public Customer(int id, String name, String phone){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);

        this.noOrders = new SimpleIntegerProperty(0);
        this.noCompletedOrders = new SimpleIntegerProperty(0);
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

    public String getPhone(){
        return phone.get();
    }

    public void setPhone(String phone){
        this.phone.set(phone);
    }

    public StringProperty phoneProperty(){
        return phone;
    }

    public int getNoOrders() {
        return noOrders.get();
    }

    public IntegerProperty noOrdersProperty() {
        return noOrders;
    }

    public void setNoOrders(int noOrders) {
        this.noOrders.set(noOrders);
    }

    public int getNoCompletedOrders() {
        return noCompletedOrders.get();
    }

    public SimpleIntegerProperty noCompletedOrdersProperty() {
        return noCompletedOrders;
    }

    public void setNoCompletedOrders(int noCompletedOrders) {
        this.noCompletedOrders.set(noCompletedOrders);
    }
}
