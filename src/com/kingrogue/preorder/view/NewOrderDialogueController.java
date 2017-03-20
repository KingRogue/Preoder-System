package com.kingrogue.preorder.view;

/**
 * Created by Tim G on 10-Mar-17.
 */

import com.kingrogue.preorder.model.Customer;
import com.kingrogue.preorder.model.DataController;
import com.kingrogue.preorder.model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.kingrogue.preorder.model.Order;
import com.kingrogue.preorder.util.DateUtil;

public class NewOrderDialogueController {
    @FXML
    private ChoiceBox<Customer> customerChoiceBox;
    @FXML
    private ChoiceBox<Product> productChoiceBox;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField receiptTextField;
    @FXML
    private DatePicker datePicker;

    private Stage dialogueStage;
    private Order order;
    private boolean okClicked = false;
    private DataController dataController;

    @FXML
    private void initialize(){
        quantityTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    quantityTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        receiptTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")){
                    receiptTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    public void setDialogueStage(Stage dialogueStage) {
        this.dialogueStage = dialogueStage;
    }

    public void setDataController(DataController dataController){
        this.dataController = dataController;
        this.populate();
    }

    public void populate(){
        this.order = order;
        this.customerChoiceBox.setItems(this.dataController.getCustomers());
        this.productChoiceBox.setItems(this.dataController.getProducts());

    }

    public Boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if(isInputValid()){
            dataController.addOrder(-1, new Integer(receiptTextField.getText()), customerChoiceBox.getValue().getID(), productChoiceBox.getValue().getId(), new Integer(quantityTextField.getText()), datePicker.getValue());

            okClicked = true;
            dialogueStage.close();
        }
    }

    @FXML
    private void handleClose(){
        dialogueStage.close();
    }

    private boolean isInputValid(){
        String errorMessage = "";
        if (receiptTextField.getText() == null || receiptTextField.getText().length() == 0){
            errorMessage += "Invalid receipt number\n";
        }
        if (customerChoiceBox.getValue() == null){
            errorMessage += "Customer not selected\n";
        }
        if (productChoiceBox.getValue() == null){
            errorMessage += "Product not selected\n";
        }
        if (quantityTextField.getText() == null || quantityTextField.getText().length() == 0){
            errorMessage += "Invalid quantity\n";
        }
        if (datePicker.getValue() == null){
            errorMessage += "Date not selected\n";
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
