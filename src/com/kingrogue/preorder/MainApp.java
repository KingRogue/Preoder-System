package com.kingrogue.preorder;/**
 * Created by Tim G on 09-Mar-17.
 */

import com.kingrogue.preorder.model.Activity;
import com.kingrogue.preorder.model.DataController;
import com.kingrogue.preorder.model.Order;
import com.kingrogue.preorder.view.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sun.applet.Main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private DataController dataController = new DataController();

    public MainApp(){

        //addDummyData();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Preorder System");
        this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("paper.png")));

        initRootLayout();
        showOrderOverview();

    }

    public void initRootLayout(){
        try{
            //loading the root layout from file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            //displaying the scene that contains the root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void showOrderOverview(){
        try{
            //load the order overview from file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/OrderOverview.fxml"));
            AnchorPane orderOverview = (AnchorPane) loader.load();

            //set to center of root layout
            rootLayout.setCenter(orderOverview);

            OrderOverviewController controller =loader.getController();
            controller.setMainApp(this);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public ObservableList<Order> getOrderData(){
        return dataController.getOrders();
    }

    public ObservableList<Order> getOrderDataFiltered(){
        return dataController.getOrdersFiltered();
    }

    public ObservableList<Activity> getOrderActivities(Order order){
        int orderid = order.getID();
        ObservableList<Activity> orderActivities = FXCollections.observableArrayList();
        for(Activity activity: dataController.getActivities()){
            if (activity.getOrderId() == orderid) orderActivities.add(activity);
        }
        return orderActivities;
    }

    private void addDummyData(){
        dataController.addCustomer(-1, "Tim", "95717053");
        dataController.addCustomer(-1, "Matt", "95716541");
        dataController.addCustomer(-1, "Alexei", "426871118");
        dataController.addCustomer(-1, "Jason", "12345678");

        dataController.addProduct(-1, "A2 Stage 1");
        dataController.addProduct(-1, "A2 Stage 3");

        dataController.addOrder(-1, "9429432", 2, 1, 60, newLocalDate("05-03-2017"));
        dataController.addOrder(-1,"1234567", 1, 1, 42, newLocalDate("09-03-2017"));
        dataController.addOrder(-1, "579", 0, 0, 42, newLocalDate("01-01-2016"));
        dataController.addOrder(-1, "890", 2, 0, 59, newLocalDate("05-04-2016"));
        dataController.addOrder(-1, "2570", 2, 1, 2000, newLocalDate("12-01-2017"));
        dataController.addOrder(-1, "2571", 3, 1, 200, newLocalDate("12-01-2017"));
        dataController.addOrder(-1, "2572", 3, 1, 20, newLocalDate("12-01-2017"));

        dataController.addActivity(4,-1,false, false, 10, newLocalDate("13-02-2017"));
        dataController.addActivity(4,-1,false, false, 30, newLocalDate("13-02-2017"));
        dataController.addActivity(4,-1,false, false, 50, newLocalDate("13-02-2017"));
        dataController.addActivity(2,-1,false, false, 42, newLocalDate("13-02-2017"));

    }

    public LocalDate newLocalDate(String date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date,dtf);
    }

    public Boolean showNewOrderDialogue(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/kingrogue/preorder/view/NewOrderDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("New Order");
            dialogueStage.initModality(Modality.WINDOW_MODAL);
            dialogueStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            NewOrderDialogueController controller = loader.getController();
            controller.setDialogueStage(dialogueStage);
            controller.setDataController(this.dataController);

            dialogueStage.showAndWait();

            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean showUpdateOrderDialogue(Order order){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/kingrogue/preorder/view/UpdateOrderDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("Update Order");
            dialogueStage.initModality(Modality.WINDOW_MODAL);;
            dialogueStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            UpdateOrderDialogueController controller = loader.getController();
            controller.setDialogueStage(dialogueStage);
            controller.setDataController(this.dataController);
            controller.setOrder(order);

            dialogueStage.showAndWait();
            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean showNewCustomerDialogue(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/kingrogue/preorder/view/NewCustomerDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("New Customer");
            dialogueStage.initModality(Modality.WINDOW_MODAL);;
            dialogueStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            NewCustomerDialogueController controller = loader.getController();
            controller.setDialogueStage(dialogueStage);
            controller.setDataController(this.dataController);

            dialogueStage.showAndWait();
            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean showNewProductDialogue(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/kingrogue/preorder/view/NewProductDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogueStage = new Stage();
            dialogueStage.setTitle("New Product");
            dialogueStage.initModality(Modality.WINDOW_MODAL);;
            dialogueStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogueStage.setScene(scene);

            NewProductDialogueController controller = loader.getController();
            controller.setDialogueStage(dialogueStage);
            controller.setDataController(this.dataController);

            dialogueStage.showAndWait();
            return controller.isOkClicked();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void save(){
        this.dataController.saveAll(null);
    }
}
