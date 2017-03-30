package com.kingrogue.preorder.view;

/**
 * Created by Tim G on 09-Mar-17.
 */

import com.kingrogue.preorder.model.Activity;
import com.kingrogue.preorder.model.DataController;
import com.kingrogue.preorder.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.kingrogue.preorder.MainApp;
import com.kingrogue.preorder.model.Order;
import com.kingrogue.preorder.util.DateUtil;

import java.time.LocalDate;
import java.util.Date;


public class OrderOverviewController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> customerTableColumn;
    @FXML
    private TableColumn<Order, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<Order, String> productTableColumn;
    @FXML
    private TableColumn<Order, Integer> quantityTableColumn;

    @FXML
    private Button updateButton;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label orderDateLabel;
    @FXML
    private Label receiptNoLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label quantityOwedLabel;
    @FXML
    private Label quantitySuppliedLabel;
    @FXML
    private CheckBox hideCompleteCheckbox;
    @FXML
    private TableView<Activity> activityTable;
    @FXML
    private TableColumn<Activity, LocalDate> activityDateTableColumn;
    @FXML
    private TableColumn<Activity, String> activityDescriptionTableColumn;


    private MainApp mainApp;

    public OrderOverviewController(){

    }

    @FXML
    private void initialize(){
        customerTableColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        dateTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        productTableColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        quantityTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityOwedProperty().asObject());

        activityDateTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        activityDescriptionTableColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        dateTableColumn.setCellFactory(column -> {
            return new TableCell<Order, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty){
                        setText(null);
                        setStyle("");
                    }else{
                        setText(DateUtil.DATE_FORMATTER.format(item));
                    }
                }
            };
        });

        activityDateTableColumn.setCellFactory(column -> {
            return new TableCell<Activity, LocalDate>() {
                @Override
                protected void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty){
                        setText(null);
                        setStyle("");
                    }else{
                        setText(DateUtil.DATE_FORMATTER.format(item));
                    }
                }
            };
        });

        showOrderDetails(null);
        this.updateButton.disableProperty().set(true);

        orderTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                if (newValue == null){
                    updateButton.disableProperty().set(true);
                }
                else{
                    updateButton.disableProperty().set(false);
                    showOrderDetails(newValue);
                }
            }
        });

        hideCompleteCheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                toggleFilterOrders(newValue);
            }
        });
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        orderTable.setItems(mainApp.getOrderData());
        activityTable.setItems(FXCollections.observableArrayList());
    }

    public void showOrderDetails(Order order){
        if (order != null) {
            customerNameLabel.setText(order.getCustomerName());
            orderDateLabel.setText(DateUtil.DATE_FORMATTER.format(order.getDate()));
            receiptNoLabel.setText(order.getReceiptNo());
            productNameLabel.setText(order.getProductName());
            quantityOwedLabel.setText(Integer.toString(order.getQuantityOwed()));
            quantitySuppliedLabel.setText(Integer.toString(order.getQuantitySupplied()));
            activityTable.setItems(mainApp.getOrderActivities(order));
        }else{
            customerNameLabel.setText("");
            orderDateLabel.setText("");
            receiptNoLabel.setText("");
            productNameLabel.setText("");
            quantityOwedLabel.setText("");
            quantitySuppliedLabel.setText("");
            activityTable.setItems(FXCollections.observableArrayList());
        }

    }

    private void toggleFilterOrders(boolean filter){
        if (filter){
            orderTable.setItems(mainApp.getOrderDataFiltered());
        }else{
            orderTable.setItems(mainApp.getOrderData());
        }
    }

    @FXML
    private void handleNewOrder(){
        mainApp.showNewOrderDialogue();
    }

    @FXML
    private void handleUpdateOrder(){
        mainApp.showUpdateOrderDialogue(this.orderTable.getSelectionModel().getSelectedItem());
        showOrderDetails(this.orderTable.getSelectionModel().getSelectedItem());
    }

}


