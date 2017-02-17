package com.kingrogue.preorder.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tim G on 17-Feb-17.
 */
public class Product {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;

    public Product(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
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
}
