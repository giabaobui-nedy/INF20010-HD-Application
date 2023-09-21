package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DeleteCustomerController {

    @FXML
    Text response;

    @FXML
    private void deleteAllCustomers() {
        response.setText(DatabaseConnection.callDeleteAllCustomerFromDB());
    }
}
