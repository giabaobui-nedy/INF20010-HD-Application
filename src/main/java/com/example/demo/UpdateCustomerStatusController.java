package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateCustomerStatusController {
    @FXML
    TextField custId;

    @FXML
    TextField status;

    @FXML
    Text response;
    @FXML
    private void updateCustomerSales() {
        int custId;
        String status = this.status.getText().trim().toUpperCase();
        try {
            custId = Integer.parseInt(this.custId.getText());
            this.response.setText(DatabaseConnection.callUpdCustomerStatusInDb(custId,status));
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number for ID!");
        } finally {
            this.custId.setText("");
            this.status.setText("");
        }
    }

}
