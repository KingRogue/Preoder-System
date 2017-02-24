package com.kingrogue.preorder;/**
 * Created by Tim G on 16-Feb-17.
 */

import com.kingrogue.preorder.model.*;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Product> productData = FXCollections.observableArrayList();
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    private ObservableList<Activity> activityData = FXCollections.observableArrayList();
    private ObservableList<Order> orderData = FXCollections.observableArrayList();

    public Main(){
        //Sample Data
        customerData.add(new Customer(1,"Tim",95717053));
        customerData.add(new Customer(2,"Matt",95716541));

        productData.add(new Product(1,"A2 Stage 3"));
        productData.add(new Product(2,"A2 Stage 2"));

        orderData.add(new Order(1,1,1,1,1, LocalDate.now()));
        orderData.add(new Order(2,2,2,2,5, LocalDate.now()));
        orderData.add(new Order(3,2,1,1,10, LocalDate.now()));
    }

    public ObservableList<Product> getProductData(){
        return productData;
    }

    public ObservableList<Order> getOrderData(){
        return orderData;
    }

    public ObservableList<Customer> getCustomerData(){
        return customerData;
    }

    public ObservableList<Activity> getActivityData(){
        return activityData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Preorders");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("paper.png")));

        initRootLayout();
    }

    private void initRootLayout() {
        try {
            //load root layout from file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene  = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            DataController dataController = new DataController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
