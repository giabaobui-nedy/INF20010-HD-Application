package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdateProductSalesController {
    @FXML
    TextField prodId;
    @FXML
    TextField amount;
    @FXML
    Text response;

    @FXML
    private void updateProductSales() {
        int prodId;
        float amount;
        try {
            prodId = Integer.parseInt(this.prodId.getText());
            try {
                amount = Float.parseFloat(this.amount.getText());
                this.response.setText(DatabaseConnection.callUpdProdSalesYtdInDb(prodId, amount));
            } catch (NumberFormatException e) {
                this.response.setText("Please enter a number for amount!");
            }
        } catch (NumberFormatException e) {
            this.response.setText("Please enter a number for ID!");
        } finally {
            this.prodId.setText("");
            this.amount.setText("");
        }
    }
}
