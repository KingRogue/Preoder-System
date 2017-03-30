package com.kingrogue.preorder.view;

import com.kingrogue.preorder.MainApp;
import com.kingrogue.preorder.model.Customer;
import com.kingrogue.preorder.model.Order;
import com.kingrogue.preorder.util.DateUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

/**
 * Created by Tim G on 30-Mar-17.
 */
public class CustomerOverviewController {
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, String> nameTableColumn;
    @FXML
    private TableColumn<Customer, Integer> numberOrdersTableColumn;
    @FXML
    private TableColumn<Customer, Integer> numberCompletedOrdersTableColumn;


    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerPhoneLabel;
    @FXML
    private Label numberOrdersLabel;
    @FXML
    private Label numberCompletedOrdersLabel;
    @FXML
    private Label numberOwedOrdersLabel;


    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableColumn<Order, String> productNameTableColumn;
    @FXML
    private TableColumn<Order, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<Order, Integer> owedTableColumn;
    @FXML
    private TableColumn<Order, Integer> suppliedTableColumn;

    @FXML
    private Button updateOrderButton;

    private MainApp mainApp;

    public CustomerOverviewController(){

    }

    @FXML
    private void initialize(){
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        numberOrdersTableColumn.setCellValueFactory(cellData -> cellData.getValue().noOrdersProperty().asObject());
        numberCompletedOrdersTableColumn.setCellValueFactory(cellData -> cellData.getValue().noCompletedOrdersProperty().asObject());

        productNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        dateTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        owedTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityOwedProperty().asObject());
        suppliedTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantitySuppliedProperty().asObject());

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

        this.ShowCustomerDetails(null);
        this.updateOrderButton.disableProperty().set(true);

        customerTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
                ShowCustomerDetails(newValue);
            }
        });

        orderTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                if (newValue == null){
                    updateOrderButton.disableProperty().set(true);
                }else{
                    updateOrderButton.disableProperty().set(false);
                }
            }
        });
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;

        this.customerTableView.setItems(this.mainApp.getCustomerData());
        this.orderTableView.setItems(FXCollections.observableArrayList());
    }

    public void ShowCustomerDetails(Customer customer) {
        if (customer != null) {
            customerNameLabel.setText(customer.getName());
            customerPhoneLabel.setText(customer.getPhone());
            numberOrdersLabel.setText(Integer.toString(customer.getNoOrders()));
            numberCompletedOrdersLabel.setText(Integer.toString(customer.getNoCompletedOrders()));
            numberOwedOrdersLabel.setText(Integer.toString(customer.getNoOrders() - customer.getNoCompletedOrders()));
            orderTableView.setItems(mainApp.getCustomerOrders(customer));
        } else {
            customerNameLabel.setText("");
            customerPhoneLabel.setText("");
            numberOrdersLabel.setText("");
            numberCompletedOrdersLabel.setText("");
            numberOwedOrdersLabel.setText("");
            orderTableView.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    private void handleUpdateOrder(){
        mainApp.showUpdateOrderDialogue(this.orderTableView.getSelectionModel().getSelectedItem());
    }
}
