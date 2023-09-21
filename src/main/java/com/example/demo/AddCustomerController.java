package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddCustomerController {

    @FXML
    TextArea response;

    @FXML
    TextField custId;

    @FXML
    TextField custName;

    @FXML
    private void addCustomerToDB() {
        int custId;
        String custName;
        try {
            custId = Integer.parseInt(this.custId.getText());
            custName = this.custName.getText();
            response.setText(DatabaseConnection.callAddCustomerToDb(custId, custName));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.custId.setText("");
            this.custName.setText("");
        }
    }
}