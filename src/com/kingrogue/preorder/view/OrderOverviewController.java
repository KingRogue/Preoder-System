package com.kingrogue.preorder.view;

/**
 * Created by Tim G on 09-Mar-17.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.kingrogue.preorder.MainApp;
import com.kingrogue.preorder.model.Order;

import java.time.LocalDate;

public class OrderOverviewController {
    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Order, String> customerTableColumn;
    @FXML
    private TableColumn<Order, String> dateTableColumn;
    @FXML
    private TableColumn<Order, String> productTableColumn;
    @FXML
    private TableColumn<Order, Integer> quantityTableColumn;

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


    private MainApp mainApp;

    public OrderOverviewController(){

    }

    @FXML
    private void initialize(){
        customerTableColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        dateTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty().asString());
        productTableColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        quantityTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityOwedProperty().asObject());

        showOrderDetails(null);

        orderTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newvalue) -> showOrderDetails(newvalue));
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        orderTable.setItems(mainApp.getOrderData());
    }

    public void showOrderDetails(Order order){
        if (order != null) {
            customerNameLabel.setText(order.getCustomerName());
            orderDateLabel.setText(order.getDate().toString());
            receiptNoLabel.setText(Integer.toString(order.getReceiptNo()));
            productNameLabel.setText(order.getProductName());
            quantityOwedLabel.setText(Integer.toString(order.getQuantityOwed()));
            quantitySuppliedLabel.setText(Integer.toString(order.getQuantitySupplied()));
        }else{
            customerNameLabel.setText("");
            orderDateLabel.setText("");
            receiptNoLabel.setText("");
            productNameLabel.setText("");
            quantityOwedLabel.setText("");
            quantitySuppliedLabel.setText("");
        }

    }
}


