package com.kingrogue.preorder.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tim G on 17-Feb-17.
 */
public class Product {
    //set on initialization
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    //populated after init
    private SimpleIntegerProperty quantityOnOrder;
    private SimpleIntegerProperty numberOfOrders;


    public Product(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);

        this.quantityOnOrder = new SimpleIntegerProperty(0);
        this.numberOfOrders = new SimpleIntegerProperty(0);
    }

    public String toString(){
        return getName();
    }

    public int getId(){
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty(){
        return id;
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty(){
        return name;
    }

    public int getQuantityOnOrder() {
        return quantityOnOrder.get();
    }

    public void setQuantityOnOrder(int quantityOnOrder){
        this.quantityOnOrder.set(quantityOnOrder);
    }

    public SimpleIntegerProperty quantityOnOrderProperty() {
        return quantityOnOrder;
    }

    public int getNumberOfOrders() {
        return numberOfOrders.get();
    }

    public void setNumberOfOrders(int numberOfOrders){
        this.numberOfOrders.set(numberOfOrders);
    }

    public SimpleIntegerProperty numberOfOrdersProperty(){
        return numberOfOrders;
    }
}
