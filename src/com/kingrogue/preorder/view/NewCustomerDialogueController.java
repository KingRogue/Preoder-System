package com.kingrogue.preorder.view;

import com.kingrogue.preorder.model.Customer;
import com.kingrogue.preorder.model.DataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.util.Iterator;

/**
 * Created by Tim G on 21-Mar-17.
 */
public class NewCustomerDialogueController {

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerPhoneField;

    private Stage dialogueStage;
    private boolean okClicked;
    private DataController dataController;

    @FXML
    private void initialize(){
        customerPhoneField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^\\d*")){
                    customerPhoneField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void setDialogueStage(Stage dialogueStage){
        this.dialogueStage = dialogueStage;
    }

    public void setDataController(DataController dataController) {
        this.dataController = dataController;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if (isInputValid()){
            dataController.addCustomer(-1, customerNameField.getText(), customerPhoneField.getText());

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

        if(customerPhoneField.getText() == null){
            errorMessage += "Missing phone number\n";
        }
        if (customerNameField.getText() == null){
            errorMessage += "Missing customer name\n";
        }
        ObservableList<Customer> customers = dataController.getCustomers();
        boolean nameUsed = false;
        String name = customerNameField.getText();
        for(Iterator<Customer> i = customers.iterator(); i.hasNext();){
            Customer customer = i.next();
            if (customer.getName().equals(name)){
                nameUsed = true;
            }
        }
        if( nameUsed ){
            errorMessage += "Customer name has already been used\n";
        }

        if (errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogueStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.show();
            return true;
        }
    }
}
