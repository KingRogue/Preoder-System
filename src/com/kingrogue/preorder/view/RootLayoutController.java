package com.kingrogue.preorder.view;

import com.kingrogue.preorder.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import sun.applet.Main;

/**
 * Created by Tim G on 21-Mar-17.
 */
public class RootLayoutController {
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNewOrder(){
        mainApp.showNewOrderDialogue();
    }

    @FXML
    private void handleNewCustomer(){
        mainApp.showNewCustomerDialogue();
    }

    @FXML
    private void handleNewProduct(){
        mainApp.showNewProductDialogue();
    }

    @FXML
    private void handleSave(){
        mainApp.save();
    }

    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText(
                "Created by Timothy Gebert\n"
        );

        alert.showAndWait();
    }

    @FXML
    private void handleViewOrders(){
        mainApp.changeScene(0);
    }

    @FXML
    private void handleViewCustomers(){
        mainApp.changeScene(1);
    }

    @FXML
    private void handleExit(){
        System.exit(0);
    }
}
