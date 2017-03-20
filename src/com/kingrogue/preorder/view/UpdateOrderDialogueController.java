package com.kingrogue.preorder.view;/**
 * Created by Tim G on 19-Mar-17.
 */

import com.kingrogue.preorder.MainApp;
import com.kingrogue.preorder.model.Customer;
import com.kingrogue.preorder.model.DataController;
import com.kingrogue.preorder.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import com.kingrogue.preorder.model.Order;

public class UpdateOrderDialogueController {

    @FXML
    private Label customerNameLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label quantityOwedLabel;

    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField quantityTextField;

    private Stage dialogueStage;
    private Order order;
    private boolean okClicked;
    private DataController dataController;

    @FXML
    private void initialize(){
        quantityTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^\\d*")){
                    quantityTextField.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });
    }

    public void setDialogueStage(Stage dialogueStage){
        this.dialogueStage = dialogueStage;
    }

    public void setDataController(DataController dataController){
        this.dataController = dataController;
    }

    public void setOrder(Order order){
        this.order = order;
        this.populate();
    }

    public void populate(){
        this.customerNameLabel.setText(this.order.getCustomerName());
        this.productNameLabel.setText(this.order.getProductName());
        this.quantityOwedLabel.setText(Integer.toString(order.getQuantityOwed()));
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if (isInputValid()){
            dataController.addActivity(order.getID(),-1, false,false, Integer.parseInt(this.quantityTextField.getText()), this.datePicker.getValue());

            okClicked = true;
            dialogueStage.close();
        }
    }

    @FXML
    private void handleClose() {
        dialogueStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if (datePicker.getValue() == null){
            errorMessage += "Date not selected\n";
        }
        if (quantityTextField.getText() == null){
            errorMessage += "Quantity supplied not entered\n";
        }
        try{
            int quantity = Integer.parseInt(this.quantityTextField.getText());
            if (quantity <= 0){
                errorMessage += "Quantity supplied can not be less than one\n";
            }else if(quantity > this.order.getQuantityOwed()){
                errorMessage += "Quantity supplied excedes quantity owed\n";
            }
        }catch (Error e){
            errorMessage += "Quantity supplied not a number\n";
        }

        if (errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogueStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.show();
            return false;
        }

    }
}
