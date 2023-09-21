package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DeleteACustomer {

    @FXML
    Text response;

    @FXML
    TextField custId;

    @FXML
    private void deleteACustomer() {
        int custId;
        try {
            custId = Integer.parseInt(this.custId.getText());
            response.setText(DatabaseConnection.callDeleteACustomerFromDB(custId));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number!");
        } finally {
            this.custId.setText("");
        }
    }
}
