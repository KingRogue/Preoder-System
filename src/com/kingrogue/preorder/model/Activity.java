package com.kingrogue.preorder.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tim G on 16-Feb-17.
 */

public class Activity {

    private final SimpleIntegerProperty orderId;
    private final SimpleIntegerProperty activityNo;
    private final SimpleBooleanProperty createdOrder;
    private final SimpleBooleanProperty cancelledOrder;
    private final SimpleIntegerProperty quantitySupplied;
    private final SimpleStringProperty description;

    public Activity(int orderId, int activityNo, boolean createdOrder, boolean cancelledOrder, int quantitySupplied){
        this.orderId = new SimpleIntegerProperty(orderId);
        this.activityNo = new SimpleIntegerProperty(activityNo);
        this.createdOrder = new SimpleBooleanProperty(createdOrder);
        this.cancelledOrder = new SimpleBooleanProperty(cancelledOrder);
        this.quantitySupplied = new SimpleIntegerProperty(quantitySupplied);
        this.description = new SimpleStringProperty("");
        updateDescription();
    }

    private void updateDescription(){
        if(createdOrder.get()){
            description.set("Created order");
        }else if(cancelledOrder.get()){
            description.set("Cancelled Order");
        }else{
            description.set("Provided " + Integer.toString(quantitySupplied.get()) + " items");
        }
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

    public SimpleIntegerProperty quantitySuppliedProperty(){
        return quantitySupplied;
    }
}
