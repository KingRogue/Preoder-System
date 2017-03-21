package com.kingrogue.preorder.view;

import com.kingrogue.preorder.model.DataController;
import com.kingrogue.preorder.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Iterator;

/**
 * Created by Tim G on 21-Mar-17.
 */
public class NewProductDialogueController {

    @FXML
    private TextField productNameField;

    private Stage dialogueStage;
    private boolean okClicked = false;
    private DataController dataController;

    @FXML
    private void initialize(){}

    public void setDialogueStage(Stage dialogueStage){
        this.dialogueStage = dialogueStage;
    }

    public void setDataController(DataController dataController){
        this.dataController = dataController;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOK(){
        if(isInputValid()){
            dataController.addProduct(-1, productNameField.getText());

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
        if (productNameField.getText() == null){
            errorMessage += "Missing product name\n";
        }
        String name = productNameField.getText();
        ObservableList<Product> products = dataController.getProducts();
        for (Iterator<Product> i = products.iterator(); i.hasNext();){
            Product product = i.next();
            if (product.getName().equals(name)){
                errorMessage += "Product name already used";
            }
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
            return false;
        }
    }

}
