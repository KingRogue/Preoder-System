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

    //set on initialization
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty receiptNo;
    private final SimpleIntegerProperty customerID;
    private final SimpleIntegerProperty productID;
    private final SimpleIntegerProperty quantity;
    private final ObjectProperty<LocalDate> date;

    //populated after init
    private SimpleStringProperty customerName;
    private SimpleStringProperty productName;
    private SimpleIntegerProperty quantityOwed;
    private SimpleIntegerProperty quantitySupplied;
    private int activityCount;

    public Order(int id, String receiptNo, int customerID, int productID, int quantity, LocalDate date) {

        this.id = new SimpleIntegerProperty(id);
        this.receiptNo = new SimpleStringProperty(receiptNo);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.productID = new SimpleIntegerProperty(productID);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.date = new SimpleObjectProperty<>(date);

        this.customerName = new SimpleStringProperty();
        this.productName = new SimpleStringProperty();
        this.quantityOwed = new SimpleIntegerProperty(quantity);
        this.quantitySupplied = new SimpleIntegerProperty(0);

        this.activityCount = new Integer(0);
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

    public String getReceiptNo(){
        return receiptNo.get();
    }

    public void setReceiptNo(String receiptNo){
        this.receiptNo.set(receiptNo);
    }

    public StringProperty receiptNoProperty(){
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

    public String getCustomerName() {
        return customerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getQuantityOwed() {
        return quantityOwed.get();
    }

    public SimpleIntegerProperty quantityOwedProperty() {
        return quantityOwed;
    }

    public void setQuantityOwed(int quantityOwed) {
        this.quantityOwed.set(quantityOwed);
    }

    public int getQuantitySupplied() {
        return quantitySupplied.get();
    }

    public SimpleIntegerProperty quantitySuppliedProperty() {
        return quantitySupplied;
    }

    public void setQuantitySupplied(int quantitySupplied) {
        this.quantitySupplied.set(quantitySupplied);
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }
}
