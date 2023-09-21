package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GetCustomerController {

    @FXML
    TextArea response;

    @FXML
    TextField custId;

    @FXML
    private void getCustomerFromDB() {
        int custId;
        try {
            custId = Integer.parseInt(this.custId.getText());
            response.setText(DatabaseConnection.callGetCustomerStringFromDB(custId));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.custId.setText("");
        }
    }
}
