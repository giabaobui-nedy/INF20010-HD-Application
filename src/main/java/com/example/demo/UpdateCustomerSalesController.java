package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateCustomerSalesController {
    @FXML
    TextField custId;
    @FXML
    TextField amount;
    @FXML
    Text response;

    @FXML
    private void updateCustomerSales() {
        int custId;
        float amount;
        try {
            custId = Integer.parseInt(this.custId.getText());
            try {
                amount = Float.parseFloat(this.amount.getText());
                this.response.setText(DatabaseConnection.callUpdCustomerSalesYtdInDb(custId, amount));
            } catch (NumberFormatException e) {
                this.response.setText("Please enter a number for amount!");
            }
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number for ID!");
        } finally {
            this.custId.setText("");
            this.amount.setText("");
        }
    }
}
