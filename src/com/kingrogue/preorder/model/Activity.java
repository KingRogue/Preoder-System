package com.kingrogue.preorder.model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by Tim G on 16-Feb-17.
 */

public class Activity {

    //set on initialization
    private final SimpleIntegerProperty orderId;
    private final SimpleIntegerProperty activityNo;
    private final SimpleBooleanProperty createdOrder;
    private final SimpleBooleanProperty cancelledOrder;
    private final SimpleIntegerProperty quantitySupplied;
    private final SimpleStringProperty description;
    private final ObjectProperty<LocalDate> date;

    public Activity(int orderId, int activityNo, boolean createdOrder, boolean cancelledOrder, int quantitySupplied, LocalDate date){
        this.orderId = new SimpleIntegerProperty(orderId);
        this.activityNo = new SimpleIntegerProperty(activityNo);
        this.createdOrder = new SimpleBooleanProperty(createdOrder);
        this.cancelledOrder = new SimpleBooleanProperty(cancelledOrder);
        this.quantitySupplied = new SimpleIntegerProperty(quantitySupplied);
        this.description = new SimpleStringProperty("");
        this.date = new SimpleObjectProperty<>(date);
        updateDescription();
    }

    private void updateDescription(){
        if(createdOrder.get()){
            description.set("Created order");
        }else if(cancelledOrder.get()){
            description.set("Cancelled Order");
        }else{
            description.set("Provided " + Integer.toString(quantitySupplied.get()) + " units");
        }
    }

    public boolean isCreatedOrder() {
        return createdOrder.get();
    }

    public SimpleBooleanProperty createdOrderProperty() {
        return createdOrder;
    }

    public void setCreatedOrder(boolean createdOrder) {
        this.createdOrder.set(createdOrder);
    }

    public boolean isCancelledOrder() {
        return cancelledOrder.get();
    }

    public SimpleBooleanProperty cancelledOrderProperty() {
        return cancelledOrder;
    }

    public void setCancelledOrder(boolean cancelledOrder) {
        this.cancelledOrder.set(cancelledOrder);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public int getOrderId(){
        return orderId.get();
    }

    public void setOrderId(int orderId){
        this.orderId.set(orderId);
    }

    public SimpleIntegerProperty orderIdProperty(){
        return orderId;
    }

    public int getActivityNo(){
        return activityNo.get();
    }

    public void setActivityNo(int activityNo){
        this.activityNo.set(activityNo);
    }

    public SimpleIntegerProperty activityNoProperty(){
        return activityNo;
    }

    public int getQuantitySupplied(){
        return quantitySupplied.get();
    }

    public void setQuantitySupplied(int quantitySupplied){
        this.quantitySupplied.set(quantitySupplied);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public SimpleIntegerProperty quantitySuppliedProperty(){
        return quantitySupplied;
    }
}
