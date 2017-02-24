package com.kingrogue.preorder.model;

import com.sun.xml.internal.bind.v2.model.core.ID;
import javafx.beans.property.*;

import javax.lang.model.element.Name;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Tim G on 16-Feb-17.
 */
public class Order {

    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty receiptNo;
    private final SimpleIntegerProperty customerID;
    private final SimpleIntegerProperty productID;
    private final SimpleIntegerProperty quantity;
    private final ObjectProperty<LocalDate> date;

    public Order(int id, int receiptNo, int customerID, int productID, int quantity, LocalDate date) {

        this.id = new SimpleIntegerProperty(id);
        this.receiptNo = new SimpleIntegerProperty(receiptNo);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.productID = new SimpleIntegerProperty(productID);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getID(){
        return id.get();
    }

    public void setId(int id){
        this.id.set(id);
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public int getReceiptNo(){
        return receiptNo.get();
    }

    public void setReceiptNo(int receiptNo){
        this.receiptNo.set(receiptNo);
    }

    public IntegerProperty receiptNoProperty(){
        return receiptNo;
    }

    public int getCustomerID(){
        return customerID.get();
    }

    public void setCustomerID(int customerID){
        this.customerID.set(customerID);
    }

    public IntegerProperty customerIdProperty(){
        return customerID;
    }

    public int getProductID(){
        return productID.get();
    }

    public void setProductID(int productID){
        this.productID.set(productID);
    }

    public IntegerProperty productIdProperty(){
        return productID;
    }

    public int getQuantity(){
        return quantity.get();
    }

    public void setQuantity(int quantity){
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty(){
        return quantity;
    }

    public LocalDate getDate(){
        return date.get();
    }

    public void setDate(LocalDate date){
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty(){
        return date;
    }

}
